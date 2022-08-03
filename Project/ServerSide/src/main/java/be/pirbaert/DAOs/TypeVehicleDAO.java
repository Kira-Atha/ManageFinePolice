package be.pirbaert.DAOs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(TypeVehicle obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(TypeVehicle obj) {
		// TODO Auto-generated method stub
		return false;
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
