package ${bussPackage}.dao.${entityPackage};

import ${bussPackage}.dao.base.BaseDao;
import ${bussPackage}.dao.common.MyBatisRepository;
import ${bussPackage}.entity.${entityPackage}.${entityName};

@MyBatisRepository
public interface ${entityName}Dao extends BaseDao<${entityName}> {
    }
