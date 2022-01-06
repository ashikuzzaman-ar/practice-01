package com.studevs.practice.practice01.service;

import com.studevs.practice.practice01.model.SystemProperty;
import com.studevs.practice.practice01.repository.SystemPropertyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Slf4j
@RequiredArgsConstructor
@Service
public class SystemPropertyService implements Serializable {
	
	private final SystemPropertyRepository systemPropertyRepository;
	
	public Long getLong(final String key) {
		return Long.parseLong(this.getProperty(key));
	}
	
	public String getProperty(final String key) {
		log.debug("Calling: com.studevs.practice.practice01.service.SystemPropertyService.getProperty");
		log.debug("key: {}", key);
		return this.systemPropertyRepository.findByKey(key).orElse(SystemProperty.builder().build()).getValue();
	}
	
	public boolean setLong(final String key, final long value) {
		return this.setProperty(key, String.valueOf(value));
	}
	
	public boolean setProperty(final String key, final String value) {
		log.debug("Calling: com.studevs.practice.practice01.service.SystemPropertyService.setProperty");
		log.debug("key: {}, value: {}", key, value);
		if (StringUtils.isBlank(key)) {
			return false;
		}
		try {
			this.systemPropertyRepository.findByKey(key).ifPresentOrElse(property -> {
				property.setValue(value);
				this.systemPropertyRepository.saveAndFlush(property);
			}, () -> this.systemPropertyRepository.saveAndFlush(SystemProperty.builder().key(key).value(value).build()));
			return true;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return false;
	}
}
