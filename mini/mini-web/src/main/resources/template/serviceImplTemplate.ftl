package ${bussPackage}.biz.${entityPackage}.impl;

import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import ${bussPackage}.biz.base.BaseServiceImpl;
import ${bussPackage}.entity.${entityPackage}.${entityName};
import ${bussPackage}.biz.${entityPackage}.${entityName}Service;

/**   
 * @Description: ${entityDescription}Service
 * @date ${createTime}
 * @version V1.0   
 *
 */
@Service
public class ${entityName}ServiceImpl extends BaseServiceImpl implements ${entityName}Service{
        Logger logger = Logger.getLogger(${entityName}ServiceImpl.class);



}