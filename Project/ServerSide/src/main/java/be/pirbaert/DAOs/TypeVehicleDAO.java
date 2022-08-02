package be.pirbaert.DAOs;

import java.sql.Connection;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TypeVehicle> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
