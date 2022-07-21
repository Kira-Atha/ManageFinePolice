package be.pirbaert.DAOs;

import java.sql.Connection;
import java.util.List;

import be.pirbaert.POJO.Vehicle;

public class VehicleDAO extends DAO<Vehicle> {

	public VehicleDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Vehicle obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Vehicle obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Vehicle obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vehicle find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vehicle> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
