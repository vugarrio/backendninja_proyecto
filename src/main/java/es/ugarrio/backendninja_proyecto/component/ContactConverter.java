package es.ugarrio.backendninja_proyecto.component;

import org.springframework.stereotype.Component;


import es.ugarrio.backendninja_proyecto.entity.Contact;
import es.ugarrio.backendninja_proyecto.model.ContactModel;

@Component("contactConverter")
public class ContactConverter {
	
	//Model --> Entity
	public Contact convertContactModel2Contact (ContactModel contactModel) {
		Contact contact = new Contact();
		
		contact.setCity(contactModel.getCity());
		contact.setFirstname(contactModel.getFirstname());
		contact.setLastname(contactModel.getLastname());
		contact.setId(contactModel.getId());
		contact.setTelephone(contactModel.getTelephone());
		return contact;
	}
	
	
	//Entity --> Model
	public ContactModel convertContact2ContactModel (Contact contact) {
		ContactModel contactModel = new ContactModel();
		
		contactModel.setCity(contact.getCity());
		contactModel.setFirstname(contact.getFirstname());
		contactModel.setLastname(contact.getLastname());
		contactModel.setId(contact.getId());
		contactModel.setTelephone(contact.getTelephone());
		return contactModel;
	}
		
	
	
}
