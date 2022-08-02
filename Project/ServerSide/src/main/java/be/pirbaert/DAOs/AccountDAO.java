package be.pirbaert.DAOs;

import java.sql.Connection;
import java.util.List;

import be.pirbaert.POJOs.Account;

public class AccountDAO extends DAO<Account> {

	public AccountDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Account obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Account obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Account obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Account find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}