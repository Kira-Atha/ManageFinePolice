package be.pirbaert.DAOs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import be.pirbaert.POJOs.Charged;

public class ChargedDAO extends DAO<Charged>{

	public ChargedDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Charged charged) {
		CallableStatement procedure = null;
		int new_id = 0;
		try {
			procedure = this.connect.prepareCall("{call manage_charged.create_charged(?,?,?,?)}");
			procedure.setString(1,charged.getFirstname());
			procedure.setString(2,charged.getLastname());
			procedure.setString(3,charged.getAddress());
			procedure.registerOutParameter(4, Types.NUMERIC);
			procedure.executeQuery();
			new_id=procedure.getInt(4);
			if(new_id!=0) {
				charged.setId(new_id);
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
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
		PreparedStatement preparedStatement = null;
		try{
			
			preparedStatement = this.connect.prepareStatement("SELECT * FROM Charged where IdCharged=?");
			preparedStatement.setInt(1, id);
			
			result = preparedStatement.executeQuery();
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
