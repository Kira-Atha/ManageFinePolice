package be.pirbaert.DAOc;


import java.net.URI;
import java.sql.Connection;
import java.util.List;

import javax.ws.rs.core.UriBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public abstract class DAO<T> {
	protected Connection connect = null;
	private Client client;
	private static final String baseURI = "http://localhost:8080/ServerSide/api";
	
	private WebResource resource;
	private ObjectMapper mapper;
	
	public DAO() {
		ClientConfig config = new DefaultClientConfig();
		client = Client.create(config);
		resource = client.resource(getBaseURI());
		mapper = new ObjectMapper();
	}
	private static URI getBaseURI() {
		return UriBuilder.fromUri(baseURI).build();
	}
	public ObjectMapper getMapper() {
		return mapper;
	}
	public abstract boolean create(T obj);
	
	public abstract boolean delete(T obj);
	
	public abstract boolean update(T obj);
	
	public abstract T find(int id);
	
	public abstract List<T> findAll();
	
	public WebResource getResource() {
		return resource;
	}

}
