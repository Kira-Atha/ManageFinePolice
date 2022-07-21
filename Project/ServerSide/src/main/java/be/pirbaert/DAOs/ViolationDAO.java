package be.pirbaert.DAOs;

import java.sql.Connection;
import java.util.List;

import be.pirbaert.POJO.Violation;

public class ViolationDAO extends DAO<Violation> {

	public ViolationDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Violation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Violation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Violation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Violation find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Violation> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
