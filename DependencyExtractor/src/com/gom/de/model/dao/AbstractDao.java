package com.gom.de.model.dao;

import java.sql.SQLException;

public abstract class AbstractDao<T> {
	abstract void insert(T value) throws SQLException;
}
