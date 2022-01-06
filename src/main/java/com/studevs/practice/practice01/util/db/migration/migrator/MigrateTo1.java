package com.studevs.practice.practice01.util.db.migration.migrator;

import com.studevs.practice.practice01.model.Address;
import com.studevs.practice.practice01.model.Contact;
import com.studevs.practice.practice01.model.Customer;
import com.studevs.practice.practice01.model.SystemProperty;
import com.studevs.practice.practice01.service.CustomerService;
import com.studevs.practice.practice01.service.SystemPropertyService;
import com.studevs.practice.practice01.util.db.migration.DatabaseMigrationException;
import com.studevs.practice.practice01.util.db.migration.DatabaseVersionMismatchException;
import com.studevs.practice.practice01.util.db.migration.VersionSupportedDatabaseMigrator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

@Slf4j
@RequiredArgsConstructor
@Component
@Scope("prototype")
public class MigrateTo1 implements VersionSupportedDatabaseMigrator {
	
	private final SystemPropertyService systemPropertyService;
	private final CustomerService customerService;
	@Value("${custom.db.initialize.dummy-data}")
	private boolean initDummyData;
	
	@Override
	public boolean migrate() throws DatabaseMigrationException {
		log.debug("Calling: com.studevs.practice.practice01.util.db.migration.migrator.MigrateTo1.migrate");
		long dbVersion = this.systemPropertyService.getLong(SystemProperty.DB_VERSION);
		if (dbVersion == this.supportedVersion()) {
			if (this.initDummyData) {
				this.customerService.saveCustomers(this.prepareDummyCustomers().orElseThrow());
			}
			this.systemPropertyService.setLong(SystemProperty.DB_VERSION, ++dbVersion);
			return true;
		} else {
			throw new DatabaseVersionMismatchException(String.format("Excepted: %d, Found: %d", this.supportedVersion(), dbVersion));
		}
	}
	
	@Override
	public long supportedVersion() throws DatabaseMigrationException {
		return 0;
	}
	
	private Optional<List<Customer>> prepareDummyCustomers() {
		final Random random = new Random();
		return Optional.of(
		  IntStream.range(1, 11)
			.parallel()
			.mapToObj(i -> Customer.builder()
			  .name("Customer " + i)
			  .age(random.nextDouble(100))
			  .presentAddress(Address.builder()
				.roadNumber(String.valueOf(random.nextDouble()))
				.city("City " + i)
				.build())
			  .permanentAddress(Address.builder()
				.roadNumber(String.valueOf(random.nextDouble()))
				.city("City " + i)
				.build())
			  .contacts(IntStream.range(0, random.nextInt(5) + 1)
				.mapToObj(n -> Contact.builder()
				  .phoneNumber(String.valueOf(random.nextDouble()))
				  .build())
				.toList())
			  .build()).toList()
		);
	}
}
