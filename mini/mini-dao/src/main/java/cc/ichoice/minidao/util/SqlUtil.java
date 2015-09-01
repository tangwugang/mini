package cc.ichoice.minidao.util;

import cc.ichoice.minidao.db.model.ColumnData;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.ResourceBundle;

public class SqlUtil {
    private static final Logger logger = Logger.getLogger(SqlUtil.class);

    /**
     * 获取所有列名字(按|分隔)
     */
    public static String getColumn(List<ColumnData> columnList) {
        StringBuffer column = new StringBuffer();
        for (ColumnData data : columnList) {
            column.append(data.getColumnName() + "|");
        }
        return column.delete(column.length() - 1, column.length()).toString();
    }

    /**
     * 获取所有的字段，并按","分割
     */
    public static String getColumnFields(String columns) {
        String fields = columns;
        if (fields != null && !"".equals(fields)) {
            fields = fields.replaceAll("[|]", ",");
        }
        return fields;
    }

    /**
     * 获取字段集合，按“|”分割
     */
    public static String[] getColumnList(String columns) {
        String[] columnList = columns.split("[|]");
        return columnList;
    }

    /**
     * 转换数据库字段名，去掉下划线为骆驼命名
     */
    public static String changeColumnName(String columnName) {
        String[] columnNames = columnName.split("_");
        StringBuilder sb = new StringBuilder(100);
        for (int i = 0; i < columnNames.length; i++) {
            if (i == 0) {
                sb.append(columnNames[i]);
            } else {
                sb.append(columnNames[i].substring(0, 1).toUpperCase());
                sb.append(columnNames[i].substring(1, columnNames[i].length()));
            }
        }
        return sb.toString();
    }

    /**
     * 生成更新字段
     */
    public static String createBaseUpdateSet(List<ColumnData> columnList) {
        String excludeColumn = excludeColumnFromProperties();
        StringBuilder sb = new StringBuilder("<set>\n\t\t<trim suffix=\"\" suffixOverrides=\",\">\n\t");
        sb.append("<include refid=\"COMMON.BASE_UPDATE_SET\"/>\n\t");
        for (ColumnData column : columnList) {
            if (!StringUtils.contains(excludeColumn, column.getColumnName())) {
                sb.append("<if test=\"" + column.getColumnNameByHump() + "!= null\">\n\t\t");
                sb.append(column.getColumnName());
                sb.append(" = #{");
                sb.append(column.getColumnNameByHump());
                sb.append("},\n\t");
                sb.append("</if>\n\t\t");
            }
        }
        sb.append("</trim>\n\t");
        sb.append("</set>\r\t");
        return sb.toString();
    }

    /**
     * 获取需要排除的字段，从配置文件获取
     */
    public static String excludeColumnFromProperties() {
        return ResourceBundle.getBundle("mini_code").getString("exclude_column");
    }

    /**
     * 生成更新语句
     */
    public static String createUpdateSql(String tableName) {
        StringBuffer sb = new StringBuffer("update ");
        sb.append(tableName).append("\n\t").append("<include refid=\"BaseUpdateSet\"/>\n\t").append("where id = #{id}\n\t");
        return sb.toString();
    }

    /**
     * 生成查询字段
     */
    public static String createBaseColumnList(List<ColumnData> columnList) {
        String excludeColumn = excludeColumnFromProperties();
        StringBuilder sb = new StringBuilder("<trim suffix=\"\" suffixOverrides=\",\">\n\t");
        sb.append("<include refid=\"COMMON.BASE_COLUMN_LIST\"/>\n\t");
        for (ColumnData column : columnList) {
            if (!StringUtils.contains(excludeColumn, column.getColumnName())) {
                sb.append(column.getColumnName());
                sb.append(" as ");
                sb.append(column.getColumnNameByHump());
                sb.append(",\n\t");
            }
        }
        sb.append("</trim>\n\t");
        return sb.toString();
    }

    /**
     * 生成查询语句
     */
    public static String createSelectSql(String tableName) {
        StringBuffer sb = new StringBuffer("select \n\t");
        sb.append("<include refid=\"BaseColumnList\"/>\n\t").append(" from ").append(tableName).append("\n\t").append("<include refid=\"BaseWhereClause\"/>\n\t").append("<include refid=\"COMMON.ORDER_BY\"/>\n\t").append("<include refid=\"COMMON.LIMIT\"/>\n\t");
        return sb.toString();
    }

    /**
     * 生成按id批量查询
     */
    public static String createSelectByIdsSql(String tableName) {
        StringBuffer sb = new StringBuffer("select \n\t");
        sb.append("<include refid=\"BaseColumnList\"/>\n\t").append(" from ").append(tableName).append("\n\t").append("where id in (\n\t").append("<foreach collection = \"array\" index = \"index\" item = \"tag\" separator = \",\">\n\t").append("#{tag}\n\t").append("</foreach>\n\t").append(") and is_deleted = 'N'\n\t");
        return sb.toString();
    }

    /**
     * 生成按id批量删除
     */
    public static String createDeleteByIdsSql(String tableName){
        StringBuffer sb = new StringBuffer("update "+tableName+"\n\t");
        sb.append("set is_deleted='Y'\n\t").append("where id in (\n\t").append("<foreach collection = \"array\" index = \"index\" item = \"tag\" separator = \",\">\n\t").append("#{tag}\n\t").append("</foreach>\n\t").append(")\n\t");
        return sb.toString();
    }

    /**
     * 生成删除语句
     */
    public static String createDeleteByIdSql(String tableName){
        StringBuffer sb = new StringBuffer("update "+tableName+"\n\t");
        sb.append("set is_deleted='Y'\n\t").append("where id=#{id}\n\t");
        return sb.toString();
    }

    /**
     * 生成条件查询语句
     */
    public static String createWhereClause(List<ColumnData> columnList) {
        String excludeColumn = excludeColumnFromProperties();
        StringBuilder sb = new StringBuilder("<where>\n\t\t<trim prefixOverrides=\"and\">\n\t");
        sb.append("<include refid=\"COMMON.BASE_WHERE_CLAUSE\"/>\n\t");
        for (ColumnData column : columnList) {
            if (!StringUtils.contains(excludeColumn, column.getColumnName())) {
                sb.append("<if test=\"" + column.getColumnNameByHump() + "!= null\">\n\t\t").append("and ");
                sb.append(column.getColumnName());
                sb.append(" = #{");
                sb.append(column.getColumnNameByHump());
                sb.append("}\n\t");
                sb.append("</if>\n\t\t");
            }
        }
        sb.append("</trim>\n\t");
        sb.append("</where>\r\t");
        return sb.toString();
    }

    /**
     * 生成插入语句
     */
    public static String createInsertSql(String tableName, List<ColumnData> columnList) {
        String excludeColumn = excludeColumnFromProperties();
        StringBuilder sb = new StringBuilder("insert into ");
        sb.append(tableName).append(" (\n\t\t").append("<trim suffix=\"\" suffixOverrides=\",\">\n\t\t\t").append("<include refid=\"COMMON.BASE_INSERT_COLUMN\"/>\n\t");
        for (ColumnData column : columnList) {
            if (!StringUtils.contains(excludeColumn, column.getColumnName())) {
                sb.append("<if test=\"" + column.getColumnNameByHump() + "!= null\">\n\t\t");
                sb.append(column.getColumnName());
                sb.append(",\n\t");
                sb.append("</if>\n\t");
            }
        }
        sb.append("</trim>\n\t\t\t)\n\t values (\n\t\t");
        sb.append("<trim suffix=\"\" suffixOverrides=\",\">\n\t\t\t").append("<include refid=\"COMMON.BASE_INSERT_VALUE\"/>\n\t");
        for (ColumnData column : columnList) {
            if (!StringUtils.contains(excludeColumn, column.getColumnName())) {
                sb.append("<if test=\"" + column.getColumnNameByHump() + "!= null\">\n\t\t");
                sb.append("#{");
                sb.append(column.getColumnNameByHump());
                sb.append("},\n\t");
                sb.append("</if>\n\t");
            }
        }
        sb.append("</trim>\n\t)");
        return sb.toString();
    }

}
