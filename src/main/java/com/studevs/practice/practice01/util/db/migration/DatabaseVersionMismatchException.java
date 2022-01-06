package com.studevs.practice.practice01.util.db.migration;

public class DatabaseVersionMismatchException extends DatabaseMigrationException {
	public DatabaseVersionMismatchException() {
	}
	
	public DatabaseVersionMismatchException(String message) {
		super(message);
	}
	
	public DatabaseVersionMismatchException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DatabaseVersionMismatchException(Throwable cause) {
		super(cause);
	}
	
	public DatabaseVersionMismatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
