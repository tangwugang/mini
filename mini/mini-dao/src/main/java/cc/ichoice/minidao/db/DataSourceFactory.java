package cc.ichoice.minidao.db;

import javax.sql.DataSource;

/**
 * 获取数据源工厂
 * @author twg
 *
 */
public interface DataSourceFactory {
	/**
	 * 获取数据源
	 * @return
	 */
	DataSource getDataSource();

}
