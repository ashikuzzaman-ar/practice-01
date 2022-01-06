package com.studevs.practice.practice01.util.db.migration;

import com.studevs.practice.practice01.model.SystemProperty;
import com.studevs.practice.practice01.service.SystemPropertyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class DataLoader implements ApplicationRunner {
	
	private final List<VersionSupportedDatabaseMigrator> migrators;
	private final SystemPropertyService systemPropertyService;
	
	@Override
	public void run(ApplicationArguments args) {
		log.debug("Calling: com.studevs.practice.practice01.util.db.migration.DataLoader.run");
		if (!CollectionUtils.isEmpty(this.migrators)) {
			Long dbVersion = this.systemPropertyService.getLong(SystemProperty.DB_VERSION);
			this.migrators.stream()
			  .parallel()
			  .filter(migrator -> migrator.supportedVersion() >= dbVersion)
			  .sorted(Comparator.comparingLong(VersionSupportedDatabaseMigrator::supportedVersion))
			  .forEachOrdered(DatabaseMigrator::migrate);
		}
	}
}