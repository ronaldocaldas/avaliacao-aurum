package br.com.aurum.astrea.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.server.spi.response.UnauthorizedException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.aurum.astrea.dao.ContactDao;
import br.com.aurum.astrea.domain.Contact;
import br.com.aurum.astrea.util.ServletUtil;

@SuppressWarnings("serial")
@WebServlet("/contacts/*")
public class ContactServlet extends HttpServlet {
	
	private static final ContactDao DAO = new ContactDao();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		BufferedReader reader = req.getReader();
		Gson gson = new Gson();

		Contact contato = gson.fromJson(reader, Contact.class);

		DAO.save(contato);

		gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(contato);
		ServletUtil.writeJSON(resp, json);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		List<Contact> lista = DAO.list();

		Gson gson = new  GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(lista);
		ServletUtil.writeJSON(resp, json);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		BufferedReader reader = req.getReader();
		Gson gson = new Gson();
		Contact contato = gson.fromJson(reader, Contact.class);
		
		Long contactId = contato.getId();

		if(contactId != null){
				try {
					DAO.delete(contactId);
					 String json = gson.toJson(contato);
					 ServletUtil.writeJSON(resp, json);
				} catch (UnauthorizedException e) {
					e.printStackTrace();
				}
		}
		
	}
}
