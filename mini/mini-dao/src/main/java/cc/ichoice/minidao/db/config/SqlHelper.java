package cc.ichoice.minidao.db.config;

import cc.ichoice.constant.FrameworkConstant;
import cc.ichoice.core.ConfigHelper;
import cc.ichoice.minidao.db.model.ColumnData;
import cc.ichoice.minidao.util.SqlUtil;
import cc.ichoice.util.MapUtil;
import cc.ichoice.util.PropertiesUtil;
import cc.ichoice.util.StringUtil;
import org.apache.commons.collections.MapUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

/**
 * 封装 SQL 语句相关操作
 *
 * @author twg
 */
public class SqlHelper {

    /**
     * SQL 属性文件对象
     */
    private static final Properties configProps = PropertiesUtil.loadDefaultProperties(ConfigHelper.getString("SQL_PROPS", FrameworkConstant.SQL_PROPS));

    /**
     * 从 SQL 属性文件中获取相应的 SQL 语句
     */
    public static String getSql(String key) {
        String sql;
        if (configProps.containsKey(key)) {
            sql = configProps.getProperty(key);
        } else {
            throw new RuntimeException("无法在 " + FrameworkConstant.SQL_PROPS + " 文件中获取属性：" + key);
        }
        return sql;
    }

    /**
     * 生成 select 语句
     */
    public static String generateSelectSql(Class<?> entityClass,Map<String, String> fieldMap, String condition, String sort) {
        StringBuilder sql = new StringBuilder("select ");
        for (String key : fieldMap.keySet()) {
            sql.append(fieldMap.get(key));
            sql.append(",");
        }
        sql.delete(sql.length()-1,sql.length());
        sql.append(" from ").append(getTable(entityClass));
        sql.append(generateWhere(condition));
        sql.append(generateOrder(sort));
        return sql.toString();
    }

    /**
     * 生成 select 语句
     */
    public static Map<String, Object> generateSelectSql(String tableName, List<ColumnData> columnList, Object... param) {
        StringBuffer sb = new StringBuffer();
        List<ColumnData> colum = new ArrayList<ColumnData>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        for (int i = 0; i < columnList.size(); i++) {
            ColumnData data = columnList.get(i);
            String columnName = data.getColumnName();
            for (int j = 0; j < param.length; j++) {
                if (param[j].equals(columnName)) {
                    sb.append(" and ");
                    sb.append(columnName).append("= ?");
                    colum.add(data);
                    break;
                } else if (null != param[j] && ((String) param[j]).startsWith(columnName)) {
                    sb.append(" and ");
                    sb.append(param[j]).append("?");
                    colum.add(data);
                } else if (null != param[j] && ((String) param[j]).startsWith("order by ")) {
                    sb.append(param[j]);
                } else if (null != param[j] && ((String) param[j]).startsWith("group by ")) {
                    sb.append(param[j]);
                }
            }
        }
        String column = SqlUtil.getColumn(columnList);
        String sql = "select " + column.replaceAll("\\|", ",") + " from " + tableName + " where 1=1" + sb.toString();
        paramMap.put("sql", sql);
        paramMap.put("colum", colum);
        return paramMap;
    }

    /**
     * 生成 insert 语句
     */
    public static String generateInsertSql(Class<?> entityClass) {
        StringBuilder sql = new StringBuilder("insert into ").append(getTable(entityClass));
        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder(") values (");
        Map<String, String> fieldMap = EntityHelper.getFieldMap(entityClass);
        fieldMap.remove(ConfigHelper.getString("PK_NAME", FrameworkConstant.PK_NAME));
        fieldMap = MapUtil.invertToLinkedHashMap(fieldMap);
        fieldMap.put("id", ConfigHelper.getString("PK_NAME", FrameworkConstant.PK_NAME));
        for (Entry<String, String> fieldEntry : fieldMap.entrySet()) {
            columns.append(fieldEntry.getValue()).append(",");
            values.append("?").append(",");
        }
        sql.append(columns.delete(columns.length() - 1, columns.length()).append(values.delete(values.length() - 1, values.length())).append(")"));
        return sql.toString();
    }

    /**
     * 生成 delete 语句
     */
    public static String generateDeleteSql(Class<?> entityClass, Map<String, Object> params) {
        StringBuilder condtion = new StringBuilder();
        StringBuilder sql = new StringBuilder("delete from ").append(getTable(entityClass));
        for (Entry<String, Object> fieldEntry : params.entrySet()) {
            String columnName = fieldEntry.getKey();
            condtion.append(columnName).append("= ?").append(",");
        }
        sql.append(generateWhere(condtion.delete(condtion.length() - 1, condtion.length()).toString()));
        return sql.toString();
    }

    /**
     * 生成 update 语句
     */
    public static String generateUpdateSql(Class<?> entityClass) {
        StringBuilder condtion = new StringBuilder();
        Map<String, String> fieldMap = EntityHelper.getFieldMap(entityClass);
        //默认取配置文件中的主键
        if (fieldMap.containsKey(ConfigHelper.getString("PK_NAME", FrameworkConstant.PK_NAME))) {
            condtion.append(fieldMap.get(ConfigHelper.getString("PK_NAME", FrameworkConstant.PK_NAME))).append("= ? ");
        }
        StringBuilder sql = new StringBuilder("update ").append(getTable(entityClass));
        if (MapUtils.isNotEmpty(fieldMap)) {
            sql.append(" set ");
            for (Entry<String, String> fieldEntry : fieldMap.entrySet()) {
                String columnName = fieldEntry.getValue();
                if (!fieldEntry.getKey().equals(ConfigHelper.getString("PK_NAME", FrameworkConstant.PK_NAME))) {
                    sql.append(columnName).append(" = ?");
                    sql.append(",");
                }
            }
        }
        sql.delete(sql.length() - 1, sql.length()).append(generateWhere(condtion.toString()));
        return sql.toString();
    }

    /**
     * 生成 select count(*) 语句
     */
    public static String generateSelectSqlForCount(Class<?> entityClass, Map<String, Object> params) {
        StringBuilder condtion = new StringBuilder();
        StringBuilder sql = new StringBuilder("select count(*) from ").append(getTable(entityClass));
        for (Entry<String, Object> fieldEntry : params.entrySet()) {
            String columnName = fieldEntry.getKey();
            condtion.append(columnName).append("= ?").append(",");
        }
        sql.append(generateWhere(condtion.delete(condtion.length() - 1, condtion.length()).toString()));
        return sql.toString();
    }

    private static String getTable(Class<?> entityClass) {
        return EntityHelper.getTableName(entityClass);
    }

    private static String generateWhere(String condition) {
        String where = "";
        if (StringUtil.isNotEmpty(condition)) {
            where += " where " + condition;
        }
        return where;
    }

    private static String generateOrder(String sort) {
        String order = "";
        if (StringUtil.isNotEmpty(sort)) {
            order += " order by " + sort;
        }
        return order;
    }

}
