package es.ugarrio.backendninja_proyecto.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.ugarrio.backendninja_proyecto.model.UserCredencial;

@Controller
public class LoginController {
	
	private static final Log LOG = LogFactory.getLog(LoginController.class);

	@GetMapping("/")
	public String redirectToLogin(){
		LOG.info("METHOD: redirectToLogin()");
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String showLoginForm(Model model, 
			                    @RequestParam(name="error", required=false) String error,
			                    @RequestParam(name="logout", required=false) String logout){
		
		LOG.info("METHOD: showLoginForm() -- PARAMS: error=" + error + ", logout:" + logout);
		
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		model.addAttribute("userCredential", new UserCredencial());
		
		LOG.info("Returning to view: login");
		
		return "login";
	}
	
	@PostMapping("/logincheck")
	public String loginCheck(@ModelAttribute(name="userCredentials") UserCredencial userCredencial){
		LOG.info("METHOD: loginCheck() -- PARAMS: userCredencial=" + userCredencial.toString());		
		
		if (userCredencial.getUsername().equals("user") && userCredencial.getPassword().equals("user")) {
			LOG.info("Returning to view: contacts");
			return "contacts";
		}
		
		LOG.info("Returning to view: redirect:/login?error");
		return "redirect:/login?error";
		
	}
	
}
