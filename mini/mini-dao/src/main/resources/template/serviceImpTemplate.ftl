package ${bussiPackage}.biz.${entityPackage}.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.takinframework.core.common.service.impl.MybatisBaseServiceImpl;
import org.takinframework.core.util.UUIDGenerator;
import ${bussiPackage}.${entityPackage}.entity.${entityName};
import ${bussiPackage}.${entityPackage}.service.${entityName}Service;

/**   
 * @Title:${entityName}Service
 * @Description: ${ftl_description}Service
 * @date ${ftl_create_time}
 * @version V1.0   
 *
 */
@Service("${entityName?uncap_first}Service")
@Transactional
public class ${entityName}ServiceImpl extends MybatisBaseServiceImpl<${entityName}, String>
	implements ${entityName}Service{
	
	public List<${entityName}> select() {
		this.mybatisBaseDAO.setNamespace("${bussiPackage}.${entityPackage}.mapper.${entityName}Mapper");
		return this.mybatisBaseDAO.select();
	}

	public List<${entityName}> selectParam(Map<?, ?> param) {
		this.mybatisBaseDAO.setNamespace("${bussiPackage}.${entityPackage}.mapper.${entityName}Mapper");
		return this.mybatisBaseDAO.selectParam(param);
	}

	public void insert(${entityName} ${entityName?uncap_first}) {
		this.mybatisBaseDAO.setNamespace("${bussiPackage}.${entityPackage}.mapper.${entityName}Mapper");
		${entityName?uncap_first}.setId(UUIDGenerator.generate());
		this.mybatisBaseDAO.insert(${entityName?uncap_first});
	}

	public void update(${entityName} ${entityName?uncap_first}) {
		this.mybatisBaseDAO.setNamespace("${bussiPackage}.${entityPackage}.mapper.${entityName}Mapper");
		this.mybatisBaseDAO.update(${entityName?uncap_first});
	}

	public void updateParam(Map<?, ?> param) {
		this.mybatisBaseDAO.setNamespace("${bussiPackage}.${entityPackage}.mapper.${entityName}Mapper");
		this.mybatisBaseDAO.updateParam(param);
		
	}

	public void delete(String primaryKey) {
		this.mybatisBaseDAO.setNamespace("${bussiPackage}.${entityPackage}.mapper.${entityName}Mapper");
		this.mybatisBaseDAO.delete(primaryKey);
	}

	public void deleteParam(Map<?, ?> param) {
		this.mybatisBaseDAO.setNamespace("${bussiPackage}.${entityPackage}.mapper.${entityName}Mapper");
		this.mybatisBaseDAO.deleteParam(param);
	}

	public ${entityName} get(String primaryKey) {
		this.mybatisBaseDAO.setNamespace("${bussiPackage}.${entityPackage}.mapper.${entityName}Mapper");
		return this.mybatisBaseDAO.get(primaryKey);
	}

	public int count() {
		this.mybatisBaseDAO.setNamespace("${bussiPackage}.${entityPackage}.mapper.${entityName}Mapper");
		return this.mybatisBaseDAO.count();
	}

	public int count(Object param) {
		this.mybatisBaseDAO.setNamespace("${bussiPackage}.${entityPackage}.mapper.${entityName}Mapper");
		return this.mybatisBaseDAO.count(param);
	}

	public void insertBatch(List<${entityName}> list) {
		this.mybatisBaseDAO.setNamespace("${bussiPackage}.${entityPackage}.mapper.${entityName}Mapper");
		for (${entityName} ${entityName?uncap_first} : list) {
			${entityName?uncap_first}.setId(UUIDGenerator.generate());
		}
		this.mybatisBaseDAO.insertBatch(list);
	}

	public void updateBatch(List<${entityName}> list) {
		this.mybatisBaseDAO.setNamespace("${bussiPackage}.${entityPackage}.mapper.${entityName}Mapper");
		this.mybatisBaseDAO.updateBatch(list);
	}

	public void deleteBatch(List<String> list) {
		this.mybatisBaseDAO.setNamespace("${bussiPackage}.${entityPackage}.mapper.${entityName}Mapper");
		this.mybatisBaseDAO.deleteBatch(list);
	}

}