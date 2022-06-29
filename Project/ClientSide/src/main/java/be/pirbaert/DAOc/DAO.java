package be.pirbaert.DAOc;


import java.net.URI;
import java.sql.Connection;
import javax.ws.rs.core.UriBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public abstract class DAO<T> {
	protected Connection connect = null;
	private Client client;
	//Projet à nommer
	private static final String baseURI = "http://localhost:8080:NONAMEPROJECT";
	private WebResource resource;
	private ObjectMapper mapper;
	
	public DAO(Connection connection) {
		this.connect = connection;
		ClientConfig config = new DefaultClientConfig();
		client = Client.create(config);
		resource = client.resource(getBaseURI());
		mapper = new ObjectMapper();
	}
	private static URI getBaseURI() {
		return UriBuilder.fromUri(baseURI).build();
	}
	public abstract boolean create(T obj);
	
	public abstract boolean delete(T obj);
	
	public abstract boolean update(T obj);
	
	public abstract T find(int id);
	
	public abstract String findAll();
	
	public WebResource getResource() {
		return resource;
	}
	public ObjectMapper getMapper() {
		return mapper;
	}
}
