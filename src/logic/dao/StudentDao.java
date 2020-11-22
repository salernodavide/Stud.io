package logic.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.entity.Student;

public class StudentDao extends GenericDao {
	
	public StudentDao() {
		con=Db.getInstance().getConnection();
	}

	// SAVE STUDENT
	public int insert(String mail, String password, String username, String nome, String cognome, String telefono)
			throws SQLException {
		int status = 0;
		try {

			ps = con.prepareStatement(
					"INSERT INTO Studente(mailStudente,password,username,nome,cognome,telefono) VALUES(?,?,?,?,?,?)");
			ps.setString(1, mail);
			ps.setString(2, password);
			ps.setString(3, username);
			ps.setString(4, nome);
			ps.setString(5, cognome);
			ps.setString(6, telefono);
			status = ps.executeUpdate();

		}

		catch (Exception e) {

			myLogger.info("Salvataggio studente fallito");// definire un eccezione apposita con logger serio
			return status;
		} finally {
			
			ps.close();
		}
		return status;
	}


	public int updateStudent(Student student) {
		int status = 0;
		try {
			ps = con.prepareStatement("UPDATE Studente SET username = ?, nome = ?, cognome = ?, telefono = ?, reportCounter = ?, isBan = ?, timeStartCountdown = ? WHERE mailStudente = ?");
            ps.setString(1, student.getUsername());
			ps.setString(2, student.getName());
			ps.setString(3, student.getSurname());
			ps.setString(4,  student.getPhone());
			ps.setByte(5, student.getReportCounter());
			ps.setBoolean(6, student.isBanned());
			ps.setString(7, student.getCountdown());
			ps.setString(8, student.getMail());
			status=ps.executeUpdate();
		}
		catch (Exception e) {
			 myLogger.info("Aggiornamento fallito");
			 return status;
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		return status;
	}


	public Student select(String id1, String id2) {
		ResultSet rs = null;
		Student student=null;
		try {
			ps = null;
			if (id2==null) {
				ps = con.prepareStatement("SELECT * FROM Studente WHERE mailStudente = ?");
				fillSelectStatement(ps, id1, id2);
			}
			else {
				ps = con.prepareStatement("SELECT * FROM Studente WHERE mailStudente = ? AND password = ?");
				fillSelectStatement(ps, id1, id2);
			}
			rs = ps.executeQuery();
			while(rs.next()) {
			    student = new Student(rs.getString(NOMES), rs.getString(COGNOMES), rs.getString(USERNAME),
						    rs.getString(MAIL),   rs.getBoolean("isBan"), rs.getByte("reportCounter"), rs.getString("timeStartCountdown"));
			    student.setPassword(rs.getString(PASSWORD));
			    student.setPhone(rs.getString(TELEFONOS));
		    }			
		}
		catch (SQLException e) {
			e.printStackTrace();
			myLogger.info("Select studente fallito");// definire un eccezione apposita con logger serio
		} finally {
			
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return student;
	}

	public List<Student> getStudentFromDb(String idBiblio) throws SQLException {
		ResultSet rs = null;
		List<Student> studentList = new ArrayList<>();
		try {
			ps = null;
			ps = con.prepareStatement("SELECT * FROM Studente LEFT JOIN recent_student ON studente.mailStudente = recent_student.mailStudent WHERE recent_student.mailBiblioteca = ? ");
			fillSelectStatement(ps, idBiblio, null);
			rs = ps.executeQuery();
			while (rs.next()) {
				Student student = new Student(rs.getString(NOMES), rs.getString(COGNOMES), rs.getString(USERNAME),
						rs.getString(MAIL), rs.getBoolean("isBan"), rs.getByte("reportCounter"), rs.getString("timeStartCountdown"));
				student.setPassword("****");
				student.setPhone(rs.getString(TELEFONOS));
				studentList.add(student);
			}
			return studentList;

		}

		catch (SQLException e) {
			myLogger.info("Select studente fallito");// definire un eccezione apposita con logger serio
		} finally {
			
			ps.close();
		}
		return studentList;
	}
	
	// UPDATE GENERICO
		public int update(String attr, String newValue, String entityId) {
			int status = 0;
			try {
				ps = null;
				if (attr.equals(MAIL)) {
					ps = con.prepareStatement("UPDATE Studente SET mailStudente = ? WHERE mailStudente = ?");
					fillUpdateStatement(ps, newValue, entityId);
				}
				if (attr.equals(PASSWORD)) {
					ps = con.prepareStatement("UPDATE Studente SET password = ? WHERE mailStudente = ?");
					fillUpdateStatement(ps, newValue, entityId);
				}
				if (attr.equals(NOMES)) {
					ps = con.prepareStatement("UPDATE Studente SET nome = ? WHERE mailStudente = ?");
					fillUpdateStatement(ps, newValue, entityId);
				}
				if (attr.equals(COGNOMES)) {
					ps = con.prepareStatement("UPDATE Studente SET cognome = ? WHERE mailStudente = ?");
					fillUpdateStatement(ps, newValue, entityId);
				}
				if (attr.equals(USERNAME)) {
					ps = con.prepareStatement("UPDATE Studente SET username = ? WHERE mailStudente = ?");
					fillUpdateStatement(ps, newValue, entityId);
				}
				if (attr.equals(TELEFONOS)) {
					ps = con.prepareStatement("UPDATE Studente SET telefono = ? WHERE mailStudente = ?");
					fillUpdateStatement(ps, newValue, entityId);
				}
				status = ps.executeUpdate();
				con.close();
			}
			catch (Exception e) {
				myLogger.info("Aggiornamento Studente fallito");// definire un eccezione apposita con logger serio
				return status;
			}
			return status;
		}

	

}
