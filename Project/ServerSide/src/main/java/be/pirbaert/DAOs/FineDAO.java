package be.pirbaert.DAOs;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import be.pirbaert.POJOs.Account;
import be.pirbaert.POJOs.Charged;
import be.pirbaert.POJOs.Violation;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleDriver;
import oracle.jdbc.OraclePreparedStatement;
import oracle.sql.*;
import java.sql.*;
import be.pirbaert.POJOs.Vehicle;
import be.pirbaert.POJOs.Fine;
import be.pirbaert.POJOs.Policeman;

public class FineDAO extends DAO<Fine> {

	public FineDAO(Connection connection) {
		super(connection);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean create(Fine fine) {
		CallableStatement procedure = null;
		CallableStatement procedure2 = null;
		int new_id = 0;
		
		try {
			procedure = this.connect.prepareCall("{call manage_fine.create_fine(?,?,?,?,?,?,?,?)}");
			procedure.setDate(1,new java.sql.Date(fine.getDate().getTime()));
			procedure.setString(2, fine.getCommentary());
			procedure.setInt(3, fine.isValidated()?1:0);
			procedure.setInt(4, fine.getVehicle().getId());

			if(!Objects.isNull(fine.getCharged())) {
				procedure.setInt(5, fine.getCharged().getId());
			}else {
				procedure.setNull(5, Types.INTEGER);
			}
			procedure.setInt(6, fine.getPoliceman().getId());
			procedure.setInt(7, 0);
			procedure.registerOutParameter(8, Types.NUMERIC);
			procedure.executeQuery();
			new_id = procedure.getInt(8);
			if(new_id!=0) {
				fine.setId(new_id);
				procedure2 = this.connect.prepareCall("{call manage_fine.create_fine_violation(?,?)}");
				int idsViolations[] = new int[fine.getViolations().size()];
				
				for(int i=0;i<idsViolations.length;i++) {
					idsViolations[i] = fine.getViolations().get(i).getId();
				}
				ArrayDescriptor descriptor = ArrayDescriptor.createDescriptor("TAB_NUM", procedure2.getConnection());
		        ARRAY array = new ARRAY(descriptor ,procedure2.getConnection(), idsViolations);

	        /* LE TABLEAU CONTIENT BIEN LES VALEURS ...
		        Datum[] datumArr = array.getOracleArray();
		        System.out.println("Content of ARRAY : ");
		        for (Datum datum: datumArr){
		            System.out.println(datum.stringValue()+" ");
		        }*/
		        
				procedure2.setArray(1,array);
				procedure2.setInt(2, fine.getId());
				procedure2.execute();
				return true;
			}else {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			try {
				procedure.close();
				procedure2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
//decline
	@Override
	public boolean delete(Fine fine) {
		CallableStatement procedure = null;
		CallableStatement procedure2 = null;
		try {
			procedure2 = this.connect.prepareCall("{call manage_fine.delete_fine_violation(?)}");
			procedure2.setInt(1, fine.getId());
			procedure2.executeQuery();
			
			procedure = this.connect.prepareCall("{call manage_fine.delete_fine(?)}");
			procedure.setInt(1, fine.getId());
			procedure.executeQuery();
			
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			try {
				procedure2.close();
				procedure.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
//accept + send letter
	@Override
	public boolean update(Fine fine) {
		CallableStatement procedure = null;
		try {
			procedure = this.connect.prepareCall("{call manage_fine.update_fine(?,?,?)}");
			procedure.setInt(1, fine.getId());
			procedure.setInt(2, fine.isValidated()?1:0);
			procedure.setInt(3, fine.isLetterSent()?1:0);
			procedure.executeQuery();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			try {
				procedure.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Fine find(int id) {
		Fine fine = null;
		ResultSet result = null;
		ResultSet result2 = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;

		try{
			
			preparedStatement = this.connect.prepareStatement("SELECT * FROM Fine WHERE IdFine=?");
			preparedStatement.setInt(1, id);
			
			result = preparedStatement.executeQuery();
			
			if(result.next()) {
				List <Violation> fineViolations = new ArrayList<Violation>();
				try {
					preparedStatement2 = this.connect.prepareStatement("SELECT * FROM Vio_Fin WHERE IdFine=?");
					preparedStatement2.setInt(1, id);
					
					result2 = preparedStatement2.executeQuery();
					
					while(result2.next()) {
						fineViolations.add(Violation.getViolation(result2.getInt("IdViolation")));
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}
				fine=new Fine(result.getInt("IdFine"),fineViolations,(Policeman)Account.getAccount(result.getInt("IdAccount")),Vehicle.getVehicle(result.getInt("IdVehicle")),result.getString("commentfine"),result.getDate("datefine"),Charged.getCharged(result.getInt("IdCharged")));
				if(result.getInt("VALIDATED") == 1) {
					fine.setValidated(true);
				}
				if(result.getInt("LETTERSENT")==1) {
					fine.setLetterSent(true);
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				result.close();
				preparedStatement.close();
				result2.close();
				preparedStatement2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return fine;
	}

	@Override
	public List<Fine> findAll() {
		List <Fine> allFines = new ArrayList <Fine>();
		Fine fine = null;
		ResultSet result = null;
		ResultSet result2 = null;
		try{
			result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Fine");
			while(result.next()) {
				List <Violation> fineViolations = new ArrayList<Violation>();
				try {
					result2 = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Vio_Fin WHERE IDFINE="+result.getInt("IdFine"));
					while(result2.next()) {
						fineViolations.add(Violation.getViolation(result2.getInt("IdViolation")));
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}
				fine=new Fine(result.getInt("IdFine"),fineViolations,(Policeman)Account.getAccount(result.getInt("IdAccount")),Vehicle.getVehicle(result.getInt("IdVehicle")),result.getString("commentfine"),result.getDate("datefine"),Charged.getCharged(result.getInt("IdCharged")));
				
				if(result.getInt("VALIDATED") == 1) {
					fine.setValidated(true);
				}
				if(result.getInt("LETTERSENT")==1) {
					fine.setLetterSent(true);
				}
				allFines.add(fine);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				result.close();
				result2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return allFines;
	}
}
