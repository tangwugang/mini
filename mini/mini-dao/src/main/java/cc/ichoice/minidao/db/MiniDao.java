package cc.ichoice.minidao.db;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 数据访问层
 * @author twg
 * @see cc.ichoice.minidao.db.impl.MiniDaoHandler
 */
public interface MiniDao {
	
	/**
	 * 批量添加，返回添加记录数与实际是否一致
	 */
	<T> boolean batchSave(List<T> entity);
	/**
	 * 批量更新，返回更新记录数与实际是否一致
	 */
	<T> boolean batchUpdate(List<T> entity);
	/**
	 * 删除对应的实体
	 */
	<T> int delete(T entity, Map<String, Object> params);

    /**
     * 查询数据源
     */
    <T> T queryDataSource();
	/**
	 * 查询数据库类型
	 */
	<T> String queryDBType();
	/**
     * 查询对应的实体，返回单条记录
     */
	<T> T queryEntity(T entity);
	/**
     * 查询对应的实体，返回单条记录
     */
	<T> T queryEntity(T entity, Object... param);
	 /**
     * 查询对应的实体，返回单条记录
     */
    <T> T queryEntity(Class<T> entityClass, String sql, Object... params);
    /**
     * 查询对应的实体列表，返回多条记录
     */
    <T> List<T> queryEntityList(T entity, Object... params);
    /**
     * 查询对应的实体列表，返回多条记录
     */
    <T> List<T> queryEntityList(T entity, String sort, Object... params);
    /**
     * 查询对应的实体列表，返回多条记录
     */
    <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params);
    /**
     * 查询对应的数据，返回单条记录（列名 => 数据）
     */
    Map<String, Object> queryMap(String sql, Object... params);
    /**
     * 查询对应的数据，返回多条记录（列名 => 数据）
     */
    List<Map<String, Object>> queryMapList(String sql, Object... params);
    /**
     * 查询记录条数，返回总记录数
     */
    long queryCount(String sql, Object... params);
    /**
     * 查询记录条数，返回总记录数
     */
    <T> long queryCount(Class<T> entityClass, Map<String, Object> params);

    /**
     * 查询数据源模板
     */
    <T> T queryJdbcTemplate();
    /**
     * 执行更新操作（包括：update、insert、delete），返回所更新的记录数
     */
    int update(String sql, Object... params);
    /**
     * 插入一条记录，返回插入后的主键
     * @param <T>
     */
    <T> Serializable insertReturnPK(T entity);

}
