package be.pirbaert.DAOs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import be.pirbaert.POJOs.Charged;

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
		Charged charged = null;
		ResultSet result = null;
		try{
			result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Charged where IdCharged="+id);
			if(result.next()) {
				charged=new Charged(result.getInt("IdCharged"),result.getString("firstname"),result.getString("lastname"),result.getString("address"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return charged;
	}

	@Override
	public List<Charged> findAll() {
		List<Charged> allChargeds = new ArrayList <Charged>();
		Charged charged = null;
		ResultSet result = null;
		try{
			result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Charged");
			while(result.next()) {
				charged=new Charged(result.getInt("IdCharged"),result.getString("firstname"),result.getString("lastname"),result.getString("address"));
				allChargeds.add(charged);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return allChargeds;
	}

}
