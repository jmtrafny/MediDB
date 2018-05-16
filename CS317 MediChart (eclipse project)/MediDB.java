import java.sql.*;
import java.sql.Date; //explicit import to disambiguate util.Date and sql.Date
import java.util.*;

public class MediDB {

	Connection sql;

	public MediDB(String url, String user, String pswd) {

		/** Creates an instance of the database connection **/
		try {
			this.sql = DriverManager.getConnection(url, user, pswd);
		} catch (SQLException e) {
			System.out.println(e.getMessage()); // Handle exceptions
		}
	}


	/*
	 * Verify log in credentials
	 */
	public boolean logIn(int userID, String userPW) {
		boolean success = false;
		try {
			// Define the query as a string
			String logInQuery = "SELECT userPW FROM patients WHERE userID = ?";

			// Request a Statement object from SQL class
			PreparedStatement pstmt = sql.prepareStatement(logInQuery);
			pstmt.setInt(1, userID);

			// Execute the query
			ResultSet results = pstmt.executeQuery();

			// Compare given pass to actual pass
			while (results.next()) {
				String correctPW = results.getString("userPW");
				if (correctPW.equals(userPW)) {
					// System.out.println("Success");
					success = true;
				} else {
					// System.out.println("Fail");
					success = false;
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage()); // Handle exceptions
		}
		// System.out.println(success);
		return success;
	}

	/*
	 * Add a new user to the database
	 */
	public boolean addNewPatient(int userID, String userPW, String firstName, String lastName, String dateOfBirth,
			double weight, String gender) {
		boolean success = false;
		
		try {

			// Create insert query string
			String insertString = "INSERT INTO patients VALUES (?, ?, ?, ?, ?, ?, ?)";

			// Create prepared statement for insert and assign it the values.
			PreparedStatement insertStatement = sql.prepareStatement(insertString);
			insertStatement.setInt(1, userID);
			insertStatement.setString(2, userPW);
			insertStatement.setString(3, firstName);
			insertStatement.setString(4, lastName);
			insertStatement.setDate(5, Date.valueOf(dateOfBirth));
			insertStatement.setDouble(6, weight);
			insertStatement.setString(7, gender);

			// Perform the insert and confirm success.
			int rows = insertStatement.executeUpdate();
			success = true;
			if (rows != 1) {
				System.out.println("ALERT: Insertion failed.");
				success = false;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage()); // Handles exceptons.
		}
		return success;
	}

	/*
	 * Remove a user to the database, also removes them from regimen
	 */
	public boolean deletePatient(int userID) {
		boolean success = false;
		
		// Prepare the delete statement strings with ? for the key attributes of
		// title and year
		String deletePatientString = "DELETE FROM patients WHERE userID = ?";
		String deleteRegimenString = "DELETE FROM regimen WHERE userID = ?";

		// Declare prepared statement variables.
		PreparedStatement deletePatientStmt;
		PreparedStatement deleteRegimenStmt;

		try {

			// Start Transaction by Setting Auto Commit to False
			// Note: we must re-enable auto-commit when we are done to restore
			// the system to the default
			// state.
			sql.setAutoCommit(false);

			// Perform the first delete by preparing and executing the statement
			deletePatientStmt = sql.prepareStatement(deletePatientString);
			deletePatientStmt.setInt(1, userID);
			deletePatientStmt.executeUpdate();

			// Perform the second delete by preparing and executing the
			// statement.
			deleteRegimenStmt = sql.prepareStatement(deleteRegimenString);
			deleteRegimenStmt.setInt(1, userID);
			deleteRegimenStmt.executeUpdate();

			// Commit the change to make the change live to the database.
			sql.commit();
			success = true;
			
		} catch (SQLException e) {

			// If an exception occurs, we will roll back the transaction to
			// avoid having an error state.

			try {
				System.out.println("Rolling Back Transaction.");

				// Performs the roll back.
				sql.rollback();
				success = false;
			} catch (SQLException e2) {
				System.out.println(e2.getMessage()); // Handle exception
			}

		} finally {

			// Finally is called after the try ends by reaching the end of its
			// code or from a return statement.
			// We include it here so that we can reenable autocommit and take it
			// out of transaction mode.

			try {
				// Re-enable autocommit
				sql.setAutoCommit(true);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return success;
	}


	/*
	 * Add to user regimen
	 */
	public boolean addToRegimen(int userID, String brandName, double dosage, String frequency, int count) {
		boolean success = false;
		try {

			// Create insert query string
			String insertString = "INSERT INTO regimen VALUES (?, ?, ?, ?, ?)";

			// Create prepared statement for insert and assign it the values.
			PreparedStatement insertStatement = sql.prepareStatement(insertString);
			insertStatement.setInt(1, userID);
			insertStatement.setString(2, brandName);
			insertStatement.setDouble(3, dosage);
			insertStatement.setString(4, frequency);
			insertStatement.setInt(5, count);

			// Perform the insert and confirm success.
			int rows = insertStatement.executeUpdate();
			success = true;
			if (rows != 1) {
				System.out.println("ALERT: Insertion failed.");
				success = false;
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage()); // Handles exceptons.
		}
		return success;
	}

	/*
	 * Remove a medication from user regimen
	 */
	public boolean removeFromRegimen(int userID, String brandName) {
		boolean success = false;
		
		// Prepare the delete statement strings with ? for the key attributes of
		// title and year
		String deleteMedString = "DELETE FROM regimen WHERE userID = ? AND brandName = ?";

		// Declare prepared statement variables.
		PreparedStatement deleteMedStmt;

		try {

			// Start Transaction by Setting Auto Commit to False
			// Note: we must re-enable auto-commit when we are done to restore
			// the system to the default
			// state.
			sql.setAutoCommit(false);

			// Perform the first delete by preparing and executing the statement
			deleteMedStmt = sql.prepareStatement(deleteMedString);
			deleteMedStmt.setInt(1, userID);
			deleteMedStmt.setString(2, brandName);
			deleteMedStmt.executeUpdate();

			// Commit the change to make the change live to the database.
			sql.commit();
			success = true;
			
		} catch (SQLException e) {
			// If an exception occurs, we will roll back the transaction to
			// avoid having an error state.
			try {
				System.out.println("Rolling Back Transaction.");
				// Performs the roll back.
				sql.rollback();
				success = false;
			} catch (SQLException e2) {
				System.out.println(e2.getMessage()); // Handle exception
			}

		} finally {
			// Finally is called after the try ends by reaching the end of its
			// code or from a return statement.
			// We include it here so that we can reenable autocommit and take it
			// out of transaction mode.
			try {
				// Re-enable autocommit
				sql.setAutoCommit(true);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return success;
	}

	/*
	 * Get user's regimen table
	 */
	public ArrayList<String> getFullRegimen(int userID) {
		ArrayList<String> fullResult = new ArrayList<String>();
		
		try {
			// Define the query as a string
			String query = "SELECT * FROM regimen WHERE userID = ?";

			// Prepare a Statement object from SQL class
			PreparedStatement stmt = sql.prepareStatement(query);
			stmt.setInt(1, userID);

			// Execute the query
			ResultSet results = stmt.executeQuery();

			// Print each record
			while (results.next()) {

				// Regimen(int userID, String brandName, FLOAT(6.2) dosage,
				// String frequency, int count)
				//
				// ResultSet also has get methods for each data type based upon
				// the index of the returned item
				// in the tuple. Please note that the indexing starts at 1 and
				// not zero.
				String partResult = new String(
										 results.getString("brandName") + "    " +
										 results.getFloat("dosage") +     "    " +
										 results.getString("frequency") + "    " +
										 results.getInt("count"));
				fullResult.add(partResult);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage()); // Handle exceptions
		}
		return fullResult;
	}
	
	/*
	 * Get user's medication names from regimen
	 */
	public ArrayList<String> getPartRegimen(int userID) {
	    ArrayList<String> patientMeds = new ArrayList<String>();

		try {
			// Define the query as a string
			String query = "SELECT brandName FROM regimen WHERE userID = ?";

			// Prepare a Statement object from SQL class
			PreparedStatement stmt = sql.prepareStatement(query);
			stmt.setInt(1, userID);

			// Execute the query
			ResultSet results = stmt.executeQuery();

			// Print each record
			while (results.next()) {
				//Put results into array list for javafx
				patientMeds.add(results.getString("brandName"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage()); // Handle exceptions
		}
		return patientMeds;
	}

	/*
	 * Increment or decrement pill count in a user regimen
	 */
	// pub

	/*
	 * Update pill count
	 */
	public boolean updatePillCount(int newCount, int userID, String brandName) {
		boolean success = false;
		try {
			// Query String
			String query = "UPDATE regimen SET count = ? WHERE userID = ? AND brandName = ?";

			// Prepared Statement
			PreparedStatement pstmt = sql.prepareStatement(query);
			pstmt.setInt(1, newCount);
			pstmt.setInt(2, userID);
			pstmt.setString(3, brandName);

			// Execute the update
			pstmt.executeUpdate();
			success = true;

		} catch (SQLException e) {
			System.out.println(e.getMessage()); // Handles exception
		}
		return success;
	}

	/*
	 * Print out all medications in the system
	 */
	public ArrayList<String> getMedicationList() {
		ArrayList<String> meds = new ArrayList<String>();
		try {
			// Define the query as a string
			String query = "SELECT * FROM medications";

			// Request a Statement object from SQL class
			Statement stmt = sql.createStatement();

			// Execute the query
			ResultSet results = stmt.executeQuery(query);

			// Print each record
			while (results.next()) {

				// Medications(VARCHAR(50) brandName, VARCHAR(50) genericName,
				// FLOAT(6.2) amountMG)
				//
				// ResultSet also has get methods for each data type based upon
				// the index of the returned item
				// in the tuple. Please note that the indexing starts at 1 and
				// not zero.
				meds.add(results.getString("brandName"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage()); // Handle exceptions
		}
		return meds;
	}
}
