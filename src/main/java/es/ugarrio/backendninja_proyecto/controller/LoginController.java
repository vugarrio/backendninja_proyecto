package es.ugarrio.backendninja_proyecto.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.ugarrio.backendninja_proyecto.constant.ViewConstant;

@Controller
public class LoginController {
	
	private static final Logger LOG = Logger.getLogger(LoginController.class);

		
	@GetMapping("/login")
	public String showLoginForm(Model model, 
			                    @RequestParam(name="error", required=false) String error,
			                    @RequestParam(name="logout", required=false) String logout){
		
		LOG.info("METHOD: showLoginForm() -- PARAMS: error=" + error + ", logout:" + logout);
		
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);		
		LOG.info("Returning to view: login");
		
		return ViewConstant.LOGIN;
	}
	
	@GetMapping({"/loginsuccess", "/"})
	public String loginCheck(){
		LOG.info("METHOD: loginCheck()");		
		
		LOG.info("Returning to view: redirect:contacts/showcontacts");
		return "redirect:/contacts/showcontacts";
		
		
	}
	
}
