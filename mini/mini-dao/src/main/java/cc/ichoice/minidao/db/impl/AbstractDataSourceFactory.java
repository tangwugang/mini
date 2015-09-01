package cc.ichoice.minidao.db.impl;

import cc.ichoice.core.ConfigHelper;
import cc.ichoice.minidao.db.DataSourceFactory;
import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;

/**
 * 抽象数据源工厂
 * @author twg
 *
 */
@SuppressWarnings("serial")
public abstract class AbstractDataSourceFactory<T extends DataSource> extends DruidDataSource implements DataSourceFactory {
	
    protected final static String url = ConfigHelper.getString("mini.jdbc.url");
    protected final static String driver = ConfigHelper.getString("mini.jdbc.driver");
    protected final static String username = ConfigHelper.getString("mini.jdbc.username");
    protected final static String password = ConfigHelper.getString("mini.jdbc.password");

	public T getDataSource() {
		T ds = createDataSource();
		setUrl(ds, url);
		setDriver(ds, driver);
		setAdvancedConfig(ds);
		setUsername(ds, username);
		setPassword(ds, password);
		return ds;
	}
	
	public abstract T createDataSource();

    public abstract void setDriver(T ds, String driver);

    public abstract void setUrl(T ds, String url);

    public abstract void setUsername(T ds, String username);

    public abstract void setPassword(T ds, String password);

    public abstract void setAdvancedConfig(T ds);

}
