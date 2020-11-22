package test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import logic.dao.StudentDao;

@TestMethodOrder(OrderAnnotation.class)
public class TestStudDao {
	// legal insert test
	StudentDao studentDao = new StudentDao();
	@Test
	@Order(1)
	void insertTest1() {		
		try {
			assertEquals(1, studentDao.insert("mailProva1@live.it", "password", "usernameProva1", "nomeProva1", "cognomeProva1",
					"telProva1"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(2)
	// illegal insert test in db mail attribute is primary key
	void insertTest2() {
		try {
			assertEquals(0, studentDao.insert("mailProva1@live.it", "passwordProva1", "usernameProva1", "nomeProva1",
					"cognomeProva1", "telProva1"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Test
	@Order(3)
	// illegal insert test in db phone attribute is VARCHAR(10)
	void insertTest3() {
		try {
			assertEquals(0, studentDao.insert("mailProva2@live.it", "passwordProva2", "usernameProva2", "nomeProva2",
					"cognomeProva2", "telefonoProva2"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// legal delete test
	@Test
	@Order(4)
	void deleteTest1() {
		assertEquals(1, studentDao.delete("Studente", "mailProva1@live.it", null));
	}

	@Test
	@Order(5)
	// illegal delete test of a not existing student
	void deleteTest2() {
		assertEquals(0, studentDao.delete("Studente", "mailProva3@live.it", null));
	}

}
