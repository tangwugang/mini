package ${bussPackage}.web.${entityPackage};
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import ${bussPackage}.web.common.BaseController;
import ${bussPackage}.web.common.Result;
import ${bussPackage}.entity.${entityPackage}.${entityName};
import ${bussPackage}.biz.${entityPackage}.${entityName}Service;
import com.google.common.collect.Lists;

/**   
 * @Description: ${entityDescription}Controller
 * @date ${createTime}
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/${entityName?uncap_first}/func")
public class ${entityName}Controller extends BaseController {

	@Autowired
	private ${entityName}Service ${entityName?uncap_first}Service;


	/**
	 * ${entityDescription}列表 页面跳转
	 */
	@RequestMapping("ng")
	public String index(Model model) {
        model.addAttribute("moduleUrl", "${entityName?uncap_first}");
		return "${entityPackage}/${entityName?uncap_first}/${entityName?uncap_first}";
	}
	
	/**
	 * 添加${entityDescription}
	 * @return
	 */
	@RequestMapping("save")
	@ResponseBody
	public Result save(@RequestBody ${entityName} ${entityName?uncap_first}) {

		return Result.wrapErrorResult("9999", "保存失败");
	}

}
