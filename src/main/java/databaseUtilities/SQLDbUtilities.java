package databaseUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.List;

/**
 * SQL Database Utility Generic Methods.
 *
 */
public class SQLDbUtilities {

	private Statement statement;
	private Connection connection;
	
	/**
	 * Establish Connection
	 * @param url
	 * @param username
	 * @param password
	 * @return
	 * @throws Throwable
	 */
	public Connection connectToDb(String url, String username, String password) throws Throwable {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(url, username, password);
		return connection;
	}

	/**
	 * Execute Query
	 * @param connection
	 * @param query
	 * @return
	 * @throws Throwable
	 */
	public ResultSet executeQuery(Connection connection, String query) throws Throwable {
		statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(query);

		return resultSet;
	}

	/**
	 * Get the column names from database table
	 * @param resultSet
	 * @return
	 * @throws Throwable
	 */
	@SuppressWarnings("null")
	public List<String> getColumnNamesFromDb(ResultSet resultSet) throws Throwable {
		List<String>columnNames = null;

		ResultSetMetaData metadata = resultSet.getMetaData();
		int columnCount = metadata.getColumnCount();
		for (int i = 1; i<= columnCount;i++) {
		String columnName = metadata.getColumnName(i);
		columnNames.add(columnName);
		}
		return columnNames;
	}

	/**
	 * Get the colum name String values from database table
	 * @param resultSet
	 * @param columnName
	 * @return
	 * @throws Throwable
	 */
	@SuppressWarnings("null")
	public List<String> getColumnStringValuesFromDb(ResultSet resultSet, String columnName) throws Throwable {
		List<String> outputList = null;
		while (resultSet.next()) {
			String outputStrings = resultSet.getString(columnName);
			outputList.add(outputStrings);
		}
		return outputList;
	}

	/**
	 * Get the colum name Integer values from database table
	 * @param resultSet
	 * @param columnName
	 * @return
	 * @throws Throwable
	 */
	@SuppressWarnings("null")
	public List<Integer> getColumnIntegerValueFromDb(ResultSet resultSet, String columnName) throws Throwable {
		List<Integer> outputList = null;
		while (resultSet.next()) {
			int outputStrings = resultSet.getInt(columnName);
			outputList.add(outputStrings);
		}
		return outputList;
	}

	/**
	 * Get the number of rows from database table
	 * @param resultSet
	 * @return
	 * @throws Throwable
	 */
	public int getNumberOfRowsFromDb(ResultSet resultSet) throws Throwable {
		int rowCount = 0;
		boolean isLastRowPresent = resultSet.last();
		if (isLastRowPresent)
			rowCount = resultSet.getRow();

		return rowCount;
	}

	/**
	 * Close connection
	 * @throws Throwable
	 */
	public void closeConnection() throws Throwable {
		this.statement.close();
		this.connection.close();
	}
}
