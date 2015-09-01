package cc.ichoice2.web.buss.system.controller;

import cc.ichoice2.web.buss.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author twg
 *
 */
@Controller
@RequestMapping("/loginController")
public class LoginController {
	@Autowired
	private DemoService demoService;
	
	@RequestMapping(params = "index")
	public ModelAndView index(Model model){
		System.out.println("LoginController.index()");
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();

//		demoService.save();
        model.addAttribute("path",path);
		return new ModelAndView("index");
	}
	@RequestMapping(params = "save",method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request){
		System.out.println("LoginController.save()");
        String tableName = request.getParameter("tableName");
        String bussPackage = request.getParameter("bussPackage");
        String entityPackage = request.getParameter("entityPackage");
        String entityDescription = request.getParameter("entityDescription");
        String[] templates = request.getParameterValues("template");
        String[] paths = request.getParameterValues("path");

        demoService.create(tableName,bussPackage,entityPackage,entityDescription,templates,paths);
		return new ModelAndView("index");
	}
	
}
