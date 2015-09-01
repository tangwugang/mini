package cc.ichoice.minidao.core;

import org.springframework.jdbc.core.SqlInOutParameter;

import java.util.LinkedList;
import java.util.List;

/**
 * 存储过程输入、输出参数封装
 * @author twg
 *
 */
public class SqlParam extends SqlInOutParameter {
	/**
	 * 参数值
	 */
	private Object value;
	/**
	 * 是否为输出参数，默认是输入参数
	 */
	private boolean isOutputValueProvided = false;
	

	/**
	 * Create a new SqlParameter, supplying name and SQL type.
	 * @param name name of the parameter, as used in input and output maps
	 * @param sqlType SQL type of the parameter according to <code>java.sql.Types</code>
	 */
	public SqlParam(String name, int sqlType,Object value) {
		super(name, sqlType);
		this.value = value;
	}

	/**
	 * Create a new SqlParameter, supplying name and SQL type.
	 * @param name name of the parameter, as used in input and output maps
	 * @param sqlType SQL type of the parameter according to <code>java.sql.Types</code>
	 * @param typeName the type name of the parameter (optional)
	 */
	public SqlParam(String name, int sqlType, String typeName,Object value) {
		super(name, sqlType, typeName);
		this.value = value;
	}

	/**
	 * Create a new SqlParameter, supplying name and SQL type.
	 * @param name name of the parameter, as used in input and output maps
	 * @param sqlType SQL type of the parameter according to <code>java.sql.Types</code>
	 * @param scale the number of digits after the decimal point
	 * (for DECIMAL and NUMERIC types)
	 */
	public SqlParam(String name, int sqlType, int scale,Object value) {
		super(name, sqlType, scale);
		this.value = value;
	}
	
	public Object getValue() {
		return value;
	}

	public boolean isOutputValueProvided() {
		return isOutputValueProvided;
	}

	public void setOutputValueProvided(boolean isOutputValueProvided) {
		this.isOutputValueProvided = isOutputValueProvided;
	}

	public static List<SqlParam> sqlTypesToAnonymousParameterList(LinkedList<SqlParam> sqlParams) {
		return sqlParams;
	}

}
