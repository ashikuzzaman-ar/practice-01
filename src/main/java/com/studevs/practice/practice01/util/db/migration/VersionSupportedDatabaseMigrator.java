package com.studevs.practice.practice01.util.db.migration;

public interface VersionSupportedDatabaseMigrator extends DatabaseMigrator {
	public long supportedVersion() throws DatabaseMigrationException;
}
