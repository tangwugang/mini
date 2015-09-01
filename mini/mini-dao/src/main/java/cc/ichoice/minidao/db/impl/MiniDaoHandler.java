package cc.ichoice.minidao.db.impl;

import cc.ichoice.constant.FrameworkConstant;
import cc.ichoice.core.ConfigHelper;
import cc.ichoice.minidao.db.MiniDao;
import cc.ichoice.minidao.db.config.EntityHelper;
import cc.ichoice.minidao.db.config.SqlHelper;
import cc.ichoice.minidao.db.model.ColumnData;
import cc.ichoice.util.ClassReflectUtil;
import cc.ichoice.util.MapUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 数据库访问层实现类
 *
 * @author twg
 */
@Component
public class MiniDaoHandler implements MiniDao {
    private static Logger log = LoggerFactory.getLogger(MiniDaoHandler.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public <T> boolean batchSave(List<T> entity) {
        int[] result;
        try {
            if (CollectionUtils.isNotEmpty(entity)) {
                String sql = SqlHelper.generateInsertSql(entity.get(0).getClass());
                result = jdbcTemplate.batchUpdate(sql, new MyBatchPreparedStatementSetter<T>(entity));
                printSQL(sql);
                if (result.length == entity.size()) {
                    return true;
                }
            }
        } catch (DataAccessException e) {
            log.error("批量添加错误：{}", e);
            throw new RuntimeException(e);
        }
        return false;
    }

    public <T> boolean batchUpdate(List<T> entity) {
        int[] result;
        try {
            if (CollectionUtils.isNotEmpty(entity)) {
                String sql = SqlHelper.generateUpdateSql(entity.get(0).getClass());
                result = jdbcTemplate.batchUpdate(sql, new MyBatchPreparedStatementSetter<T>(entity));
                printSQL(sql);
                if (result.length == entity.size()) {
                    return true;
                }
            }
        } catch (DataAccessException e) {
            log.error("批量更新错误：{}", e);
            throw new RuntimeException(e);
        }
        return false;
    }

    //调用存储过程，未实现
    public <T> Map<String, Object> callProcedure(String procName, Object... params) {
        return null;
    }

    public <T> int delete(T entity, Map<String, Object> params) {
        String sql = SqlHelper.generateDeleteSql(entity.getClass(), params);
        printSQL(sql);
        return jdbcTemplate.update(sql, params.values().toArray());
    }

    public <T> T queryDataSource() {
        return (T) jdbcTemplate.getDataSource();
    }

    public <T> String queryDBType() {
        try {
            return jdbcTemplate.getDataSource().getConnection().getMetaData().getDatabaseProductName();
        } catch (SQLException e) {
            log.error("查询数据库类型错误：{}", e);
            throw new RuntimeException(e);
        }
    }

    public <T> T queryEntity(T entity) {
        return queryEntity(entity, new Object[] { ConfigHelper.getString("PK_NAME", FrameworkConstant.PK_NAME) });
    }

    @SuppressWarnings("unchecked")
    public <T> T queryEntity(T entity, Object... params) {
        T result = null;
        try {
            StringBuilder condition = new StringBuilder();
            List<Object> list = new ArrayList<Object>();
            Map<String, String> fieldMap = EntityHelper.getFieldMap(entity.getClass());
            for (Object filed : params) {
                if (fieldMap.containsKey(filed)) {
                    condition.append(fieldMap.get(filed)).append("= ?").append(",");
                    try {
                        list.add(ClassReflectUtil.getFieldValue(entity, (String) filed));
                    } catch (Exception e) {
                        log.error("获取对象属性值错误：{}", e);
                        throw new RuntimeException(e);
                    }
                }
            }
            String sql = SqlHelper.generateSelectSql(entity.getClass(), fieldMap, condition.delete(condition.length() - 1, condition.length()).toString(), null);
            result = (T) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<T>((Class<T>) entity.getClass()), list.toArray());
            printSQL(sql);
        } catch (DataAccessException e) {
            log.error("查询对应的实体错误：{}", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> queryEntityList(T entity, String sort, Object... params) {
        List<T> result = null;
        try {
            StringBuilder condition = new StringBuilder();
            List<Object> list = new ArrayList<Object>();
            Map<String, String> fieldMap = EntityHelper.getFieldMap(entity.getClass());
            for (Object filed : params) {
                if (fieldMap.containsKey(filed)) {
                    condition.append(fieldMap.get(filed)).append("= ?").append(",");
                    try {
                        list.add(ClassReflectUtil.getFieldValue(entity, (String) filed));
                    } catch (Exception e) {
                        log.error("获取对象属性值错误：{}", e);
                        throw new RuntimeException(e);
                    }
                }
            }
            String sql = SqlHelper.generateSelectSql(entity.getClass(), fieldMap, condition.delete(condition.length() - 1, condition.length()).toString(), sort);
            result = (List<T>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<T>((Class<T>) entity.getClass()), list.toArray());
            printSQL(sql);
        } catch (DataAccessException e) {
            log.error("查询对应的实体列表错误：{}", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> queryEntityList(T entity, Object... params) {
        List<T> result = null;
        try {
            StringBuilder condition = new StringBuilder();
            List<Object> list = new ArrayList<Object>();
            Map<String, String> fieldMap = EntityHelper.getFieldMap(entity.getClass());
            for (Object filed : params) {
                if (fieldMap.containsKey(filed)) {
                    condition.append(fieldMap.get(filed)).append("= ?").append(",");
                    try {
                        list.add(ClassReflectUtil.getFieldValue(entity, (String) filed));
                    } catch (Exception e) {
                        log.error("获取对象属性值错误：{}", e);
                        throw new RuntimeException(e);
                    }
                }
            }
            String sql = SqlHelper.generateSelectSql(entity.getClass(), fieldMap, condition.delete(condition.length() - 1, condition.length()).toString(), null);
            result = (List<T>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<T>((Class<T>) entity.getClass()), list.toArray());
            printSQL(sql);
        } catch (DataAccessException e) {
            log.error("查询对应的实体列表错误：{}", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    public <T> T queryEntity(Class<T> entityClass, String sql, Object... params) {
        T result = null;
        try {
            result = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<T>(entityClass), params);
            printSQL(sql);
        } catch (DataAccessException e) {
            log.error("查询对应的实体错误：{}", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    public <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params) {
        List<T> result = null;
        try {
            result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<T>(entityClass), params);
            printSQL(sql);
        } catch (DataAccessException e) {
            log.error("查询对应的实体列表错误：{}", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    public Map<String, Object> queryMap(String sql, Object... params) {
        Map<String, Object> result = null;
        try {
            result = jdbcTemplate.queryForMap(sql, params);
            printSQL(sql);
        } catch (DataAccessException e) {
            log.error("查询对应的数据错误：{}", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<Map<String, Object>> queryMapList(String sql, Object... params) {
        List<Map<String, Object>> result = null;
        try {
            result = jdbcTemplate.queryForList(sql, params);
            printSQL(sql);
        } catch (DataAccessException e) {
            log.error("查询对应的数据错误：{}", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    public long queryCount(String sql, Object... params) {
        long result = 0;
        try {
            result = jdbcTemplate.queryForLong(sql, params);
            printSQL(sql);
        } catch (DataAccessException e) {
            log.error("查询记录条数错误：{}", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    public <T> long queryCount(Class<T> entityClass, Map<String, Object> params) {
        long result = 0;
        try {
            String sql = SqlHelper.generateSelectSqlForCount(entityClass, params);
            result = jdbcTemplate.queryForLong(sql, params.values().toArray());
            printSQL(sql);
        } catch (DataAccessException e) {
            log.error("查询记录条数错误：{}", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    public <T> T queryJdbcTemplate() {
        return (T) this.jdbcTemplate;
    }

    public int update(String sql, Object... params) {
        int result = 0;
        try {
            result = jdbcTemplate.update(sql, params);
            printSQL(sql);
        } catch (DataAccessException e) {
            log.error("执行更新操作（包括：update、insert、delete）错误：{}", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    public <T> Serializable insertReturnPK(T entity) {
        List<T> entities = new ArrayList<T>();
        entities.add(entity);
        if (batchSave(entities)) {
            try {
                return (Serializable) ClassReflectUtil.getFieldValue(entity, ConfigHelper.getString("PK_NAME", FrameworkConstant.PK_NAME));
            } catch (Exception e) {
                log.error("获取对象属性值错误：{}", e);
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    /**
     * *********************************************************************************
     */
    private static void printSQL(String sql) {
        log.info("[mini] SQL - {}", sql);
    }

    @SuppressWarnings("unchecked")
    private <T> T BeanPropertyRowMapper(List<Map<String, Object>> res, T entity) {
        Object ob = null;
        if (null != res && res.size() > 0) {
            Map<String, Object> map = res.get(0);
            Set<String> keySet = map.keySet();
            for (Iterator<String> iterator = keySet.iterator(); iterator.hasNext(); ) {
                String fileName = (String) iterator.next();
                try {
                    ob = ClassReflectUtil.setFieldValue(entity, fileName, map.get(fileName) != null ? map.get(fileName) : null);
                } catch (Exception e) {
                    log.error("添加对象属性值错误: {}", e);
                }
            }
        }
        return (T) ob;
    }


    private class DefaultStoredProcedure extends StoredProcedure {
        DefaultStoredProcedure(String procName, Map<String, Object> param) {
            super(jdbcTemplate, procName);
        }

        //		public Map<K, V> execute(){
        //
        //		}

    }

    private class MyBeanPropertyRowMapper<T> implements RowMapper<T> {

        private List<ColumnData> columnDatas;
        private Class<T> cls;

        public MyBeanPropertyRowMapper() {
        }

        @SuppressWarnings("unchecked")
        public MyBeanPropertyRowMapper(T entity, List<ColumnData> columnDatas) {
            this.columnDatas = columnDatas;
            this.cls = (Class<T>) entity.getClass();
        }

        public MyBeanPropertyRowMapper(Class<T> entity, List<ColumnData> columnDatas) {
            this.columnDatas = columnDatas;
            this.cls = entity;
        }

        public T mapRow(ResultSet rs, int rowNum) throws SQLException {
            T entity = BeanUtils.instantiate(cls);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int index = 1; index <= columnCount; index++) {
                if (null != columnDatas && columnDatas.size() > 0) {
                    setBeanProperValue(rs, index, columnDatas, entity);
                }
            }
            return (T) entity;
        }

        @SuppressWarnings("unchecked")
        private T setBeanProperValue(ResultSet rs, int index, List<ColumnData> columnDatas, T entity) {
            Object value = null;
            ColumnData c = columnDatas.get(index - 1);
            try {
                if (c.getDataType().toLowerCase().equals("string")) {
                    value = rs.getString(index);
                } else if (c.getDataType().toLowerCase().equals("boolean")) {
                    value = rs.getBoolean(index);
                } else if (c.getDataType().toLowerCase().equals("byte")) {
                    value = rs.getByte(index);
                } else if (c.getDataType().toLowerCase().contains("short")) {
                    value = rs.getShort(index);
                } else if (c.getDataType().toLowerCase().contains("int")) {
                    value = rs.getInt(index);
                } else if (c.getDataType().toLowerCase().equals("long")) {
                    value = rs.getLong(index);
                } else if (c.getDataType().toLowerCase().equals("float")) {
                    value = rs.getFloat(index);
                } else if (c.getDataType().toLowerCase().equals("double")) {
                    value = rs.getDouble(index);
                } else if (c.getDataType().toLowerCase().equals("byte[]")) {
                    value = rs.getBytes(index);
                } else if (c.getDataType().toLowerCase().equals("date")) {
                    value = getResultSetValue(rs, index);
                } else {
                    value = getResultSetValue(rs, index);
                }
                entity = (T) ClassReflectUtil.setFieldValue(entity, c.getColumnName(), value);
            } catch (Exception e) {
                log.error("添加属性值错误：{}", e);
                throw new RuntimeException(e);
            }
            return entity;

        }

        private Object getResultSetValue(ResultSet rs, int index) throws SQLException {
            Object obj = rs.getObject(index);
            String className = null;
            if (obj != null) {
                className = obj.getClass().getName();
            }
            if (obj instanceof Blob) {
                obj = rs.getBytes(index);
            } else if (obj instanceof Clob) {
                obj = rs.getString(index);
            } else if (className != null && "java.lang.Boolean".equals(className)) {
                obj = rs.getBoolean(index);
            } else if (className != null && "java.lang.Byte".equals(className)) {
                obj = rs.getByte(index);
            } else if (className != null && "java.lang.Short".equals(className)) {
                obj = rs.getShort(index);
            } else if (className != null && "java.lang.Integer".equals(className)) {
                obj = rs.getInt(index);
            } else if (className != null && "java.lang.Long".equals(className)) {
                obj = rs.getLong(index);
            } else if (className != null && "java.lang.Float".equals(className)) {
                obj = rs.getFloat(index);
            } else if (className != null && "java.lang.Double".equals(className)) {
                obj = rs.getDouble(index);
            } else if (className != null && "java.lang.Double".equals(className)) {
                obj = rs.getDouble(index);
            } else if (className != null && ("oracle.sql.TIMESTAMP".equals(className) || "oracle.sql.TIMESTAMPTZ".equals(className))) {
                obj = rs.getTimestamp(index);
            } else if (className != null && className.startsWith("oracle.sql.DATE")) {
                String metaDataClassName = rs.getMetaData().getColumnClassName(index);
                if ("java.sql.Timestamp".equals(metaDataClassName) || "oracle.sql.TIMESTAMP".equals(metaDataClassName)) {
                    obj = rs.getTimestamp(index);
                } else {
                    obj = rs.getDate(index);
                }
            } else if (obj != null && obj instanceof java.sql.Date) {
                if ("java.sql.Timestamp".equals(rs.getMetaData().getColumnClassName(index))) {
                    obj = rs.getTimestamp(index);
                }
            }
            return obj;
        }
    }

    private class MyBatchPreparedStatementSetter<T> implements BatchPreparedStatementSetter {
        /**
         * 实体集
         */
        private List<T> entities;

        public MyBatchPreparedStatementSetter(List<T> entities) {
            this.entities = entities;
        }

        public void setValues(PreparedStatement ps, int i) throws SQLException {
            Map<String, String> fieldMap = EntityHelper.getFieldMap(entities.get(0).getClass());
            fieldMap.remove(ConfigHelper.getString("PK_NAME", FrameworkConstant.PK_NAME));
            fieldMap = MapUtil.invertToLinkedHashMap(fieldMap);
            fieldMap.put("id", ConfigHelper.getString("PK_NAME", FrameworkConstant.PK_NAME));
            int k = 0;
            for (Entry<String, String> fieldEntry : fieldMap.entrySet()) {
                try {
                    ps.setObject(k + 1, ClassReflectUtil.getFieldValue(entities.get(i), fieldEntry.getKey()));
                } catch (Exception e) {
                    log.error("获取属性值错误：{}", e);
                }
                k++;
            }
        }

        public int getBatchSize() {
            return entities.size();
        }

    }
}
