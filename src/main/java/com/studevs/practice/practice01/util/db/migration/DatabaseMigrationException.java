package com.studevs.practice.practice01.util.db.migration;

public class DatabaseMigrationException extends RuntimeException {
	public DatabaseMigrationException() {
	}
	
	public DatabaseMigrationException(String message) {
		super(message);
	}
	
	public DatabaseMigrationException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DatabaseMigrationException(Throwable cause) {
		super(cause);
	}
	
	public DatabaseMigrationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
