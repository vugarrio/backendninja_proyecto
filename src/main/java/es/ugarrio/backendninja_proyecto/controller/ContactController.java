package es.ugarrio.backendninja_proyecto.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import es.ugarrio.backendninja_proyecto.constant.ViewConstant;
import es.ugarrio.backendninja_proyecto.model.ContactModel;
import es.ugarrio.backendninja_proyecto.service.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController {

	private static final Log LOG = LogFactory.getLog(ContactController.class);

	// Inyectamos el servicio de contactos
	@Autowired
	@Qualifier("contactServiceImpl")
	private ContactService contactService;

	@GetMapping("/cancel")
	public String cancel() {
		LOG.info("Returning to view: redirect:/contacts/showcontacts");
		return "redirect:/contacts/showcontacts";
	}

	@GetMapping("/contactform")
	public String redirectContactForm(Model model) {

		// Al formulario se le pasa el obj contactModel vacio, que es el que
		// luego nos envia con los datos introducidos por el usuario.
		model.addAttribute("contactModel", new ContactModel());

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

		mav.addObject("contacts", contactService.listAllContacts());

		LOG.info("Returning to view: " + ViewConstant.CONTACTS +  "'  -- DATA: 'contacts'");
		return mav;
	}

}
