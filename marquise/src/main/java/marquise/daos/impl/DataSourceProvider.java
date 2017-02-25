package marquise.daos.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.sql.DataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;




public class DataSourceProvider {

	private static class DataSourceProviderHolder {
		private final static DataSourceProvider instance = new DataSourceProvider();
	}
	
	public static DataSourceProvider getInstance() {
		return DataSourceProviderHolder.instance;
	}

	private MysqlDataSource dataSource;

	private DataSourceProvider() {
		initDataSource();
	}

	private void initDataSource() {
		Properties jdbcProperties = new Properties();
		InputStream configFileStream = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		try {
			jdbcProperties.load(configFileStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		dataSource = new MysqlDataSource();
		dataSource.setServerName(jdbcProperties.getProperty("localhost"));
		dataSource.setPort(Integer.parseInt(jdbcProperties.getProperty("3306")));
		dataSource.setDatabaseName(jdbcProperties.getProperty("marquiseBase"));
		dataSource.setUser(jdbcProperties.getProperty("root"));
		dataSource.setPassword(jdbcProperties.getProperty("mylove"));
	}

	public DataSource getDataSource() {
		return dataSource;
	}
}