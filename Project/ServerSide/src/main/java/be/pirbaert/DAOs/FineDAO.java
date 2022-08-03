package be.pirbaert.DAOs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import be.pirbaert.POJOs.Account;
import be.pirbaert.POJOs.Charged;
import be.pirbaert.POJOs.Violation;
import be.pirbaert.POJOs.Vehicle;
import be.pirbaert.POJOs.Fine;
import be.pirbaert.POJOs.Policeman;

public class FineDAO extends DAO<Fine> {

	public FineDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Fine obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Fine obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Fine obj) {
		// TODO Auto-generated method stub
		return false;
	}

	
	@Override
	public Fine find(int id) {
		Fine fine = null;
		ResultSet result = null;
		try{
			result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Fine "
					+ "INNER JOIN Vio_Fin "
					+ "ON Fine.IdFine = Vio_Fin.IdFine "
					+ "where IdFine="+id);
			if(result.next()) {
				fine=new Fine(result.getInt("IdFine"),Violation.getViolation(result.getInt("IdViolation")),(Policeman)Account.getAccount(result.getInt("IdAccount")),Vehicle.getVehicle(result.getInt("IdVehicle")),result.getString("commentfine"),result.getDate("datefine"));
				if(result.getInt("IdCharged")!=0) {
					fine.setCharged(Charged.getCharged(result.getInt("IdCharged")));
				}
			}
		}catch(SQLException e) {
			return null;
		}
		return fine;
	}

	
	// NEED TO FIX => Policeman ??? 
	@Override
	public List<Fine> findAll() {
		List <Fine> allFines = new ArrayList <Fine>();
		Fine fine = null;
		ResultSet result = null;
		try{
			result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Fine "
					+"INNER JOIN Vio_Fin "
					+"ON Fine.IdFine = Vio_Fin.IdFine");
			while(result.next()) {
				fine=new Fine(result.getInt("IdFine"),Violation.getViolation(result.getInt("IdViolation")),(Policeman)Account.getAccount(result.getInt("IdAccount")),Vehicle.getVehicle(result.getInt("IdVehicle")),result.getString("commentfine"),result.getDate("datefine"));
				if(result.getInt("IdCharged")!=0) {
					fine.setCharged(Charged.getCharged(result.getInt("IdCharged")));
				}
				allFines.add(fine);
			}
		}catch(SQLException e) {
			return null;
		}
		return allFines;
	}
}
