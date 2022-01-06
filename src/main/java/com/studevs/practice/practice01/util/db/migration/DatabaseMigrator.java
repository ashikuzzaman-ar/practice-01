package com.studevs.practice.practice01.util.db.migration;

import java.io.Serializable;

@FunctionalInterface
public interface DatabaseMigrator extends Serializable {
	public boolean migrate() throws DatabaseMigrationException;
}
