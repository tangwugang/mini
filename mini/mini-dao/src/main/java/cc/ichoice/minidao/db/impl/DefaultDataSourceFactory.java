package cc.ichoice.minidao.db.impl;

import com.alibaba.druid.pool.DruidDataSource;


@SuppressWarnings("serial")
public class DefaultDataSourceFactory extends AbstractDataSourceFactory<DruidDataSource> {

	public DruidDataSource createDataSource() {
		return new DruidDataSource();
	}

	public void setDriver(DruidDataSource ds, String driver) {
		ds.setDriverClassName(driver);
	}

	public void setUrl(DruidDataSource ds, String url) {
		ds.setUrl(url);
	}

	public void setUsername(DruidDataSource ds, String username) {
		ds.setUsername(username);
	}

	public void setPassword(DruidDataSource ds, String password) {
		ds.setPassword(password);
	}

	public void setAdvancedConfig(DruidDataSource ds) {
	}
	

}
