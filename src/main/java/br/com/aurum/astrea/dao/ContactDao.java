package br.com.aurum.astrea.dao;

import java.util.List;
import com.google.api.server.spi.response.UnauthorizedException;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

import br.com.aurum.astrea.domain.Contact;

public class ContactDao {
	
	static {
		ObjectifyService.register(Contact.class);
	}
	
	public void save(Contact contact) {
		ObjectifyService.ofy().save().entities(contact).now();		
	}
	
	public List<Contact> list() {
		 Query query = ObjectifyService.ofy().load().type(Contact.class).order("name");
		 List<Contact> lista = query.list();
		return lista;
	}
	
	public void delete(Long contactId) throws UnauthorizedException {
		Contact contact = ObjectifyService.ofy().load().key(Key.create(Contact.class, contactId)).now();
		if(contact == null){
			throw new UnauthorizedException("Authorization required");
		}
			ObjectifyService.ofy().delete().entity(contact);
		}
		
}
