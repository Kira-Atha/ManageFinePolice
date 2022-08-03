package be.pirbaert.DAOs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import be.pirbaert.POJOs.Violation;

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
		Violation violation = null;
		ResultSet result = null;
		try{
			result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Violation where IdViolation="+id);
			if(result.next()) {
				violation=new Violation(result.getInt("IdViolation"),result.getString("Name"),result.getString("Description"),result.getFloat("Price"));
			}
		}catch(SQLException e) {
			return null;
		}
		return violation;
	}

	@Override
	public List<Violation> findAll() {
		List<Violation> allViolations = new ArrayList <Violation>();
		Violation violation = null;
		ResultSet result = null;
		try{
			result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Violation");
			while(result.next()) {
				violation=new Violation(result.getInt("IdViolation"),result.getString("Name"),result.getString("Description"),result.getFloat("Price"));
				allViolations.add(violation);
			}
		}catch(SQLException e) {
			return null;
		}
		return allViolations;
	}
}
