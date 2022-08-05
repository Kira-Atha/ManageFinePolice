package be.pirbaert.DAOs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
		CallableStatement proc = null;
		
		int id = 0;

		try {
			proc = this.connect.prepareCall("{call manage_violation.create_violation(?,?,?,?)}");
			proc.setString(1, obj.getName());
			proc.setFloat(2, obj.getPrice());
			proc.setString(3, obj.getDescription());
			proc.registerOutParameter(4, Types.NUMERIC);
			

			proc.executeQuery();

			
			id = proc.getInt(4);
			
			if(id != 0) {
				obj.setId(id);
				return true;
			}
			
			return false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		}
	}

	@Override
	public boolean delete(Violation obj) {
		CallableStatement proc = null;
		
		
		try {
			proc = this.connect.prepareCall("{call  manage_violation.delete_violation(?)}");
			proc.setInt(1, obj.getId());
			

			proc.executeQuery();

					
			return true;
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		}
	}

	@Override
	public boolean update(Violation obj) {
		CallableStatement proc = null;
		
		try {
			proc = this.connect.prepareCall("{call manage_violation.update_violation(?,?,?,?)}");
			proc.setInt(1, obj.getId());
			proc.setString(2, obj.getName());
			proc.setFloat(3, obj.getPrice());
			proc.setString(4, obj.getDescription());
			
	
			proc.executeQuery();
			
			return true;
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
	
		}
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
