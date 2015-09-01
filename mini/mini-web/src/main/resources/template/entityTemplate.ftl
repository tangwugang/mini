package ${bussPackage}.entity.${entityPackage};

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.*;
import java.math.BigDecimal;
import ${bussPackage}.entity.base.BaseEntity;

/**   
 * @功能描述: ${entityDescription}
 * @日期 ${createTime}
 * @版本号 V1.0
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ${entityName} extends BaseEntity{
	${fields}

}
