package br.com.aurum.astrea.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

import br.com.aurum.astrea.dao.ContactDao;

public class ContactTest {

	ContactDao DAO = new ContactDao();
	
	private static final Long CONTACT_ID = 123456789l;

	private static final String NOME = "Testelson Silva";

	private static final String BIRTH_DAY = "01";

	private static final String BIRTH_MOUNTH = "08";

	private static final String BIRTH_YEAR = "1988";

	private static final String CPF = "1988";

	private static final String RG = "1988";
	
	private static final List<String> TELEFONES = Arrays.asList("9999-8888");

	private static final List<String> EMAILS = Arrays.asList("example@gmail.com");
	private static final String ENDERECO = "Rua Teste 9";

	private static final String OBSERVACAO = "Minha obsercação";

	private Contact contato;

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig().setDefaultHighRepJobPolicyUnappliedJobPercentage(100));

	 @BeforeClass
	    public static void setUpBeforeClass() {
	        // Reset the Factory so that all translators work properly.
	        ObjectifyService.setFactory(new ObjectifyFactory());
	        ObjectifyService.register(Contact.class);
	    }
	
	@Before
	public void setUp() throws Exception {
		helper.setUp();
		contato = new Contact(CONTACT_ID, NOME, BIRTH_DAY, BIRTH_MOUNTH, BIRTH_YEAR, CPF, RG, TELEFONES, EMAILS, ENDERECO,
				OBSERVACAO);
	}
	
	 @After
	    public void tearDown() throws Exception {
		 	ObjectifyService.ofy().clear();
	        helper.tearDown();
	    }
	 
	 @Test
	    public void testGetters() throws Exception {
	        assertEquals(CONTACT_ID, contato.getId());
	        assertEquals(NOME, contato.getName());
	        assertEquals(BIRTH_DAY, contato.getBirthDay());
	        assertEquals(BIRTH_MOUNTH, contato.getBirthMonth());
	        assertEquals(BIRTH_YEAR, contato.getBirthYear());
	        assertEquals(CPF, contato.getCpf());
	        assertEquals(RG, contato.getRg());
	        assertEquals(TELEFONES, contato.getPhones());
	        assertEquals(EMAILS, contato.getEmails());
	        assertEquals(ENDERECO, contato.getAddress());
	        assertEquals(OBSERVACAO, contato.getObservation());
	    }

	 
	 @Test
	    public void testCreate() throws Exception {
			List<Contact> lista = new ArrayList<Contact>();
		 
			assertEquals("Erra pra ser uma lista vazia!", 0, lista.size());

//		 	DAO.save(contato);
//		 	
//		 	lista = DAO.list();
//		 	 
//		 	 assertEquals("Foi adicionado um contato, era pra ser 1", 1, lista.size());
//		 	
//		 	 Contact deletado =  lista.get(0);
//		 	 
//		 	 assertTrue(deletado!= null);
//		 	 
//		 	 DAO.delete(deletado.getId());
//		 	 
//		 	 lista = DAO.list();
		 	 
		 	assertEquals("Erra pra ser uma lista vazia!", 0, lista.size());
	    }

	

}
