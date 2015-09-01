package cc.ichoice.minidao.db.config;

import cc.ichoice.minidao.db.model.ColumnData;
import cc.ichoice.minidao.db.type.DbType;
import cc.ichoice.minidao.db.type.FiledType;
import cc.ichoice.minidao.util.SqlUtil;
import cc.ichoice.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 封装数据库表相关操作
 * @author twg
 *
 */
public class TableHelper {
	/**
	 * 表字段
	 */
	private final static Map<String, Object> columData = new ConcurrentHashMap<String, Object>();
	
	/**
	 * 获取表字段，并生产对应实例的set、get
	 */
	@SuppressWarnings("unchecked")
	public static String getFields(String tableName,JdbcTemplate jdbcTemplate,String dbType){
    	List<ColumnData> dataList = null;
    	if(null != columData && columData.size()>0){
    		dataList = (List<ColumnData>) columData.get(tableName);
    	}else{
    		dataList = getColumnDatas(tableName,jdbcTemplate,dbType);
            if(null != dataList){
                columData.put(tableName, dataList);
            }
    	}
    	StringBuffer str = new StringBuffer(100);
    	StringBuffer getset = new StringBuffer(100);
    	
        for(ColumnData d : dataList){
            String columnName = d.getColumnName();
        	String name = StringUtil.underlineToCamelhump(d.getColumnName());
			String type =  d.getDataType();
			String comment =  d.getColumnComment();
			String defaultValue = d.getDefaultValue();
            String excludeColumn = SqlUtil.excludeColumnFromProperties();
			if(StringUtils.contains(excludeColumn,columnName)){
				continue;
	        }
			String maxChar=name.substring(0, 1).toUpperCase();
			if(null!= defaultValue){
				str.append("\r\t").append("private ").append(type+" ").append(name).append(" = ").append(type+".valueOf(\"").append(defaultValue).append("\")").append(";//   ").append(comment);
				
			}else{
				str.append("\r\t").append("private ").append(type+" ").append(name).append(";//   ").append(comment);
			}
			
			String method=maxChar+name.substring(1, name.length());
			getset.append("\r\t").append("public ").append(type+" ").append("get"+method+"() {\r\t");
			getset.append("    return this.").append(name).append(";\r\t}");
			getset.append("\r\t").append("public void ").append("set"+method+"("+type+" "+name+") {\r\t");
			getset.append("    this."+name+"=").append(name).append(";\r\t}");
        }
		return str.toString()+getset.toString();
    }
	
	
	/**
     * 获取表字段，封装成List
     */
    @SuppressWarnings("unchecked")
	public static List<ColumnData> getColumnDatas(String tableName,JdbcTemplate jdbcTemplate,String dbType){
    	if(null != columData && columData.size()>0){
    		
    		if(null!=((List<ColumnData>) columData.get(tableName)) &&
    				((List<ColumnData>) columData.get(tableName)).size()>0){
    			return (List<ColumnData>) columData.get(tableName);
    		}
    		return getColumnDataList(tableName,jdbcTemplate, dbType);
    	}
		return getColumnDataList(tableName,jdbcTemplate, dbType);
    }
    
    private static List<ColumnData> getColumnDataList(String tableName,JdbcTemplate jdbcTemplate,String dbType){
    	String SQLColumns="";
    	List<ColumnData> dataList = new ArrayList<ColumnData>();
    	if (dbType.equals(DbType.MySQL.toString())||dbType.toUpperCase().equals(DbType.MySQL.toString())) {
    		SQLColumns="SELECT column_name,column_type,column_comment,column_default,column_key,IS_NULLABLE,CHARACTER_OCTET_LENGTH LENGTH, NUMERIC_PRECISION PRECISIONS,NUMERIC_SCALE SCALE,TABLE_NAME FROM  INFORMATION_SCHEMA.COLUMNS  WHERE TABLE_SCHEMA=DATABASE() AND TABLE_NAME='"+tableName+"'";
		}
    	if (dbType.equals(DbType.ORACLE.toString())||dbType.toUpperCase().equals(DbType.ORACLE.toString())) {
    		SQLColumns="select a.column_name,a.data_type,a.data_length,b.comments from cols a,user_col_comments b  where a.column_name=b.column_name and a.table_name=b.table_name and b.table_name='"+tableName+"'";
		}
    	List<Map<String, Object>> list = jdbcTemplate.queryForList(SQLColumns);
    	for (Map<String, Object> map : list) {
    		String name = (String) map.get("column_name");
    		String comment = (String) map.get("column_comment");
    		String defaultValue = (String) map.get("column_default");
    		String type = FiledType.getType((String) map.get("column_type"));
    		ColumnData cd= new ColumnData();
			cd.setColumnName(name);
			cd.setDataType(type);
			cd.setColumnComment(comment);
			cd.setDefaultValue(defaultValue);
            cd.setColumnNameByHump(StringUtil.underlineToCamelhump(name));
			dataList.add(cd);
		}
		columData.put(tableName, dataList);
		return dataList;
    }

    /**

     * 生成(mybaits)sql语句

     * @param tableName 数据库表名
     * @param jdbcTemplate spring jdbc
     * @param dbType 数据库类型mysql、oracle
     */
    public static Map<String,Object> createSql(String tableName,JdbcTemplate jdbcTemplate,String dbType){
        Map<String,Object> sqlMap=new HashMap<String, Object>();
        List<ColumnData> columnData = getColumnDatas(tableName, jdbcTemplate, dbType);
        String columns= SqlUtil.getColumn(columnData);
        String[] columnList = SqlUtil.getColumnList(columns);  //表所有字段 按“|”分割

        String columnFields =  SqlUtil.getColumnFields(columns); //表所有字段 按","隔开

        String insert="insert into "+tableName+"("+columns.replaceAll("\\|", ",")+")\n \t values(#{"+columns.replaceAll("\\|", "},#{")+"})";
        String insertBatch="insert into "+tableName+"("+columns.replaceAll("\\|", ",")+")\n \t values \n \t <foreach collection=\"list\" item=\"i\" index=\"index\" separator=\",\">";
        insertBatch+= "\n \t (#{i."+columns.replaceAll("\\|", "},#{i.")+"}) \n \t </foreach>";
        String baseUpdateSet = SqlUtil.createBaseUpdateSet(columnData);
        String updateSql = SqlUtil.createUpdateSql(tableName);
        String baseColumnList = SqlUtil.createBaseColumnList(columnData);
        String selectSql = SqlUtil.createSelectSql(tableName);
        String whereClause = SqlUtil.createWhereClause(columnData);
        String insertSql = SqlUtil.createInsertSql(tableName,columnData);
        String selectByIdsSql = SqlUtil.createSelectByIdsSql(tableName);
        String deleteByIdsSql = SqlUtil.createDeleteByIdsSql(tableName);
        String deleteByIdSql = SqlUtil.createDeleteByIdSql(tableName);
        sqlMap.put("selectByIdsSql",selectByIdsSql);
        sqlMap.put("deleteByIdsSql",deleteByIdsSql);
        sqlMap.put("deleteByIdSql",deleteByIdSql);

        sqlMap.put("updateSql",updateSql);
        sqlMap.put("selectSql",selectSql);
        sqlMap.put("insertSql",insertSql);
        sqlMap.put("whereClause",whereClause);
        sqlMap.put("baseUpdateSet",baseUpdateSet);
        sqlMap.put("baseColumnList",baseColumnList);

        return sqlMap;
    }


}
