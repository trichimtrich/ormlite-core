package com.j256.ormlite.dao;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.field.SqlType;

/**
 * Results returned by a call to {@link Dao#queryForAllRaw(String)} or {@link Dao#iteratorRaw(String)} which handles
 * each result as a String[].
 * 
 * <p>
 * <b>NOTE:</b> You must call {@link CloseableIterator#close()} method when you are done otherwise the underlying SQL
 * statement and connection may be kept open.
 * </p>
 * 
 * @author graywatson
 */
public interface RawResults extends CloseableIterable<String[]> {

	/**
	 * Return the number of columns in each result row.
	 */
	public int getNumberColumns();

	/**
	 * Return the array of column names for each result row.
	 */
	public String[] getColumnNames();

	/**
	 * Return a list of results mapped by the mapper argument.
	 */
	public List<String[]> getResults() throws SQLException;

	/**
	 * Return a list of results mapped by the mapper argument.
	 */
	public <T> List<T> getMappedResults(RawRowMapper<T> mapper) throws SQLException;

	/**
	 * Get an iterator which will return a T which is mapped from the String[] array raw results my the mapper argument.
	 */
	public <T> CloseableIterator<T> iterator(RawRowMapper<T> mapper);

	/**
	 * Close any open database connections associated with the RawResults. This is only applicable if the
	 * {@link Dao#iteratorRaw(String)} or another iterator method was called.
	 */
	public void close() throws SQLException;

	/**
	 * In advanced situations, you may not be able to convert all of your fields into strings which is the default --
	 * serialized fields is one example. This method allows you to set the types of the various resulting columns
	 */
	public void setColumnTypes(SqlType[] columnTypes);
}
