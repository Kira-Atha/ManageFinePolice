package be.pirbaert.DAOc;

import java.sql.Connection;
import java.util.List;

import be.pirbaert.POJO.Fine;

public class FineDAO extends DAO<Fine> {

	public FineDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Fine obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Fine obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Fine obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Fine find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
