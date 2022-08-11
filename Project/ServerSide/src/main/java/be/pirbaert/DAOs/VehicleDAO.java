package be.pirbaert.DAOs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import be.pirbaert.POJOs.Account;
import be.pirbaert.POJOs.Vehicle;
import be.pirbaert.POJOs.Policeman;
import be.pirbaert.POJOs.Registration;
import be.pirbaert.POJOs.Violation;
import javassist.bytecode.analysis.Type;
import be.pirbaert.POJOs.TypeVehicle;

public class VehicleDAO extends DAO<Vehicle> {

	public VehicleDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Vehicle vehicle) {
		CallableStatement procedure = null;
		int new_id = 0;
		try {
			procedure = this.connect.prepareCall("{call manage_vehicle.create_vehicle(?,?,?)}");
			procedure.setInt(1, vehicle.getType().getId());
			if(!Objects.isNull(vehicle.getRegistration())) {
				procedure.setInt(2, vehicle.getRegistration().getId());
			}else {
				procedure.setNull(2, Types.INTEGER);
			}
			procedure.registerOutParameter(3, Types.NUMERIC);
			procedure.executeQuery();
			new_id = procedure.getInt(3); 
			if(new_id!=0) {
				vehicle.setId(procedure.getInt(3));
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public boolean delete(Vehicle obj) {
		return false;
	}

	@Override
	public boolean update(Vehicle obj) {
		return false;
	}

	@Override
	public Vehicle find(int id) {
		Vehicle vehicle = null;
		ResultSet result = null;
		PreparedStatement preparedStatement = null;

		try{
			preparedStatement = this.connect.prepareStatement("SELECT * FROM Vehicle where IdVehicle=?");
			preparedStatement.setInt(1, id);
			
			result = preparedStatement.executeQuery();
			if(result.next()) {
				vehicle=new Vehicle(result.getInt("IdVehicle"),Registration.getRegistration(result.getInt("IdRegistration")),TypeVehicle.getType(result.getInt("IdType")));
			}
			result.close();
		}catch(SQLException e) {
			return null;
		}
		return vehicle;
	}

	@Override
	public List<Vehicle> findAll() {
		List<Vehicle> allVehicles = new ArrayList <Vehicle>();
		Vehicle vehicle = null;
		ResultSet result = null;
		try{
			result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Vehicle");
			while(result.next()) {
				vehicle=new Vehicle(result.getInt("IdVehicle"),Registration.getRegistration(result.getInt("IdRegistration")),TypeVehicle.getType(result.getInt("IdType")));
				allVehicles.add(vehicle);
			}
			result.close();
		}catch(SQLException e) {
			return null;
		}
		return allVehicles;
	}
}
