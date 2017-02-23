package es.ugarrio.backendninja_proyecto.service;

import java.util.List;

import org.springframework.stereotype.Service;

import es.ugarrio.backendninja_proyecto.entity.Contact;
import es.ugarrio.backendninja_proyecto.model.ContactModel;



public interface ContactService {
	
	public abstract ContactModel addContact(ContactModel contactModel);
	
	public abstract List<ContactModel> listAllContacts();
	
}
