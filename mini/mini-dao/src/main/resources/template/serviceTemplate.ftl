package ${bussiPackage}.biz.${entityPackage};

import java.util.List;
import java.util.Map;
import org.takinframework.core.common.service.MybatisBaseService;
import ${bussiPackage}.entity.${entityPackage}.${entityName};

public interface ${entityName}Service extends MybatisBaseService<${entityName}, String>{
	/**
	 * 获取对象全部结果集
	 * @return
	 */
	public List<${entityName}> select();
	
	/**  
     * 按条件查询记录  
     * @param param 查询条件参数，包括WHERE条件、分页条件、排序条件  
     * @return 符合条件记录的实体对象的List  
     */    
    public List<${entityName}> selectParam(Map<?, ?> param);
	
	/**
	 * 添加
	 * @param ${entityName?uncap_first}
	 */
	public void insert(${entityName} ${entityName?uncap_first});
	/**
	 * 更新
	 * @param ${entityName?uncap_first}
	 */
	public void update(${entityName} ${entityName?uncap_first});
	/**
	 * 修改符合条件的记录
	 * @param param
	 */
	public void updateParam(Map<?, ?> param);
	/**  
     * 查询整表总记录数  
     * @return 整表总记录数  
     */    
    public int count();    
    /**  
     * 查询符合条件的记录数  
     * @param param 查询条件参数，包括WHERE条件（其他参数内容不起作用）。此参数设置为null，则相当于count()  
     * @return  
     */    
    public int count(Object param);  
	/**  
     * 按主键删除记录  
     * @param primaryKey 主键对象  
     * @return 删除的对象个数，正常情况=1  
     */    
    public void delete(String primaryKey); 
    
    /**  
     * 删除符合条件的记录  
     * <p><strong>此方法一定要慎用，如果条件设置不当，可能会删除有用的记录！</strong></p>  
     * @param param 用于产生SQL的参数值，包括WHERE条件（其他参数内容不起作用）  
     * @return  
     */    
    public void deleteParam(Map<?, ?> param);
    
    
    /**  
     * 按主键取记录  
     * @param primaryKey 主键值  
     * @return 记录实体对象，如果没有符合主键条件的记录，则返回null  
     */    
    public ${entityName} get(String primaryKey);  
    /**  
     * 批量插入  
     * @param list  
     */    
    public void insertBatch(final List<${entityName}> list);    
        
    /**  
     * 批量修改  
     * @param list  
     */    
    public void updateBatch(final List<${entityName}> list);    
        
    /**  
     * 批量删除  
     * @param list  
     */    
    public void deleteBatch(final List<String> list);  

}
