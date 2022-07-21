package be.pirbaert.DAOc;

import java.sql.Connection;
import java.util.List;

import be.pirbaert.POJO.Charged;

public class ChargedDAO extends DAO<Charged>{

	public ChargedDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Charged obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Charged obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Charged obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Charged find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
