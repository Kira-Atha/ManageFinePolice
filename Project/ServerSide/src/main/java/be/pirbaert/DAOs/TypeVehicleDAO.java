package be.pirbaert.DAOs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import be.pirbaert.POJOs.TypeVehicle;

public class TypeVehicleDAO extends DAO<TypeVehicle> {

	public TypeVehicleDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(TypeVehicle obj) {
		CallableStatement proc = null;
	
		int id = 0;
	
		try {
			proc = this.connect.prepareCall("{call manage_vehicle_type.create_vehicle_type(?,?)}");
			proc.setString(1, obj.getName());
			proc.registerOutParameter(2, Types.NUMERIC);
			
	
			proc.executeQuery();
	
			
			id = proc.getInt(2);
			
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
	public boolean delete(TypeVehicle obj) {
		CallableStatement proc = null;
		
		
		try {
			proc = this.connect.prepareCall("{call  manage_vehicle_type.delete_vehicle_type(?)}");
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
	public boolean update(TypeVehicle obj) {
		CallableStatement proc = null;
		
		try {
			proc = this.connect.prepareCall("{call manage_vehicle_type.update_vehicle_type(?,?)}");
			proc.setInt(1, obj.getId());
			proc.setString(2, obj.getName());
			
	
			proc.executeQuery();
			
			return true;
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
	
		}
	}

	@Override
	public TypeVehicle find(int id) {
		TypeVehicle type = null;
		ResultSet result = null;
		try{
			result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM TypeVehicle WHERE IDType="+id);
			while(result.next()) {
				type=new TypeVehicle(result.getInt("IdType"),result.getString("Name"));
			}
		}catch(SQLException e) {
			return null;
		}
		return type;
	}

	@Override
	public List<TypeVehicle> findAll() {
		List<TypeVehicle> allTypes = new ArrayList <TypeVehicle>();
		TypeVehicle type = null;
		ResultSet result = null;
		try{
			result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM TypeVehicle");
			while(result.next()) {
				type=new TypeVehicle(result.getInt("IdType"),result.getString("Name"));
				allTypes.add(type);
			}
		}catch(SQLException e) {
			return null;
		}
		return allTypes;
	}

}
