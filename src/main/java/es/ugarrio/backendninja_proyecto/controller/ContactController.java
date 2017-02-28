package es.ugarrio.backendninja_proyecto.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import es.ugarrio.backendninja_proyecto.constant.ViewConstant;

import es.ugarrio.backendninja_proyecto.model.ContactModel;
import es.ugarrio.backendninja_proyecto.service.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController {

	private static final Logger LOG = Logger.getLogger(ContactController.class);

	// Inyectamos el servicio de contactos
	@Autowired
	@Qualifier("contactServiceImpl")
	private ContactService contactService;

	@GetMapping("/cancel")
	public String cancel() {
		LOG.info("Returning to view: redirect:/contacts/showcontacts");
		return "redirect:/contacts/showcontacts";
	}
	
	@PreAuthorize("hasRole('ROLE_USER')") // Indicamos que solo pueden acceder los usuarios con el role ROLE_USER
	@GetMapping("/contactform")
	public String redirectContactForm( @RequestParam(name="id", required=false) int id,
			                           Model model) {
		LOG.info("METHOD: redirectContactForm() -- PARAMS: id=" + id);	
		ContactModel contactModel = new ContactModel();  
		
		if (id != 0) {
			contactModel = contactService.findContactByIdModel(id);
		}
		
		// Al formulario se le pasa el obj contactModel vacio, que es el que
		// luego nos envia con los datos introducidos por el usuario.
		model.addAttribute("contactModel", contactModel);
		
		LOG.info("Returning to view: " + ViewConstant.CONTACT_FORM);
		return ViewConstant.CONTACT_FORM;
	}

	@PostMapping("/addcontact")
	public String addContact(@ModelAttribute(name = "contactModel") ContactModel contactModel, Model model) {
		LOG.info("METHOD: addContact() -- PARAMS: contactModel=" + contactModel.toString());

		if (null != contactService.addContact(contactModel)) {
			model.addAttribute("result", 1);
		} else {
			model.addAttribute("result", 0);
		}
		
		LOG.info("Returning to view: redirect:contacts/showcontacts" +  "'  -- DATA: 'result'");
		return "redirect:/contacts/showcontacts";
	}

	@GetMapping("/showcontacts")
	public ModelAndView showcontacts() {
		ModelAndView mav = new ModelAndView(ViewConstant.CONTACTS);
		
		LOG.info("METHOD: showcontacts() -- PARAMS: ");
		
		//Obtenemos el usuario que esta logado.
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		mav.addObject("username", user.getUsername());
		mav.addObject("contacts", contactService.listAllContacts());

		LOG.info("Returning to view: " + ViewConstant.CONTACTS +  "'  -- DATA: 'contacts'");
		return mav;
	}
	
	
	@GetMapping("/removecontact")
	public ModelAndView removeContact(@RequestParam(name="id", required=true) int id) {
		LOG.info("METHOD: removeContact() -- PARAMS: id=" + id);
		
		contactService.removeContact(id);
		return showcontacts();
	}

}
