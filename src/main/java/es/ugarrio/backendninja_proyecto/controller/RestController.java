package es.ugarrio.backendninja_proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.ugarrio.backendninja_proyecto.model.ContactModel;
import es.ugarrio.backendninja_proyecto.service.ContactService;


	@org.springframework.web.bind.annotation.RestController
	@RequestMapping("/rest")
	public class RestController {
		
	
	// Inyectamos el servicio de contactos
	@Autowired
	@Qualifier("contactServiceImpl")
	private ContactService contactService;

	@GetMapping("/checkrest")
	public ResponseEntity<ContactModel> checkRest() {
		
		ContactModel cm = new ContactModel(2, "Mikel", "Perez", "11111111111111", "Madrid");
		
		return new ResponseEntity<ContactModel>(cm, HttpStatus.OK); 
	}
	
}
