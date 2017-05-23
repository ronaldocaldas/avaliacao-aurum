package br.com.aurum.astrea.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthSeparatorUI;

import com.google.api.server.spi.response.UnauthorizedException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import br.com.aurum.astrea.dao.ContactDao;
import br.com.aurum.astrea.domain.Contact;
import br.com.aurum.astrea.util.ServletUtil;

@SuppressWarnings("serial")
@WebServlet("/contacts/*")
public class ContactServlet extends HttpServlet {
	
	private static final ContactDao DAO = new ContactDao();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		// TODO: Implementar um método que irá ler o corpo da requisição e, com essas informações,
		// salvar no banco de dados uma entidade do tipo 'Contato' com essas informações.
		
		BufferedReader reader = req.getReader();
		Gson gson = new Gson();

		Contact contato = gson.fromJson(reader, Contact.class);

		DAO.save(contato);

		gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(contato);
		ServletUtil.writeJSON(resp, json);

	}
	
	private Contact getContactFromRequest(HttpServletRequest req) {
		Contact contact = new Contact();
		contact.setName(req.getParameter("name"));
		return contact;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		// TODO: Implementar um método que irá listar todas as entidades do tipo 'Contato' e devolver para o client essa listagem.
		List<Contact> lista = DAO.list();

		Gson gson = new  GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(lista);
		ServletUtil.writeJSON(resp, json);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// TODO: Implementar um método que irá deletar uma entidade do tipo 'Contato', dado parâmetro de identificação.
		
		BufferedReader reader = req.getReader();
		Gson gson = new Gson();
		Contact contato = gson.fromJson(reader, Contact.class);
		
		Long contactId = contato.getId();
		if(contactId != null){
				try {
					DAO.delete(contactId);
					 resp.sendError(200, "Contato excluído com sucesso");
					 String json = gson.toJson(contactId);
					 ServletUtil.writeJSON(resp, json);
				} catch (UnauthorizedException e) {
					e.printStackTrace();
				}
				
		}
		
	}
}
