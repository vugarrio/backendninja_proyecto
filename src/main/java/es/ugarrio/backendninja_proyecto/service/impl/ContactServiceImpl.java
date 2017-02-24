package es.ugarrio.backendninja_proyecto.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import es.ugarrio.backendninja_proyecto.component.ContactConverter;
import es.ugarrio.backendninja_proyecto.entity.Contact;
import es.ugarrio.backendninja_proyecto.model.ContactModel;
import es.ugarrio.backendninja_proyecto.repository.ContactRepository;
import es.ugarrio.backendninja_proyecto.service.ContactService;

@Service("contactServiceImpl")
public class ContactServiceImpl implements ContactService {
	
	//Inyectamos el repositorio
	@Autowired
	@Qualifier("contactRepository")
	private ContactRepository contactRepository;
	
	
	//Inyectamos el converter de contacto entre entity y modelo
	@Autowired
	@Qualifier("contactConverter")
	private ContactConverter contactConverter;
	
	
	@Override
	public ContactModel addContact(ContactModel contactModel) {
		Contact contact = contactRepository.save(contactConverter.convertContactModel2Contact(contactModel));
		return contactConverter.convertContact2ContactModel(contact);
	}


	@Override
	public List<ContactModel> listAllContacts() {		
		List<Contact> contacts = contactRepository.findAll();
		List<ContactModel> contactModel = new ArrayList<ContactModel>();
		for (Contact contact: contacts) {
			contactModel.add(contactConverter.convertContact2ContactModel(contact));
		}
		return contactModel;
	}


	@Override
	public Contact findContactById(int id) {
		return contactRepository.findById(id);
	}
	
	
	@Override
	public ContactModel findContactByIdModel(int id) {
		return contactConverter.convertContact2ContactModel(findContactById(id));
	}


	@Override
	public void removeContact(int id) {
		Contact contact = findContactById(id);
		if (null != contact) {
			contactRepository.delete(findContactById(id));
		}
	}
	
	
	
}
