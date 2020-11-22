package test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import logic.dao.LibraryDao;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class testLibrDao {

	// legal insert
	@Test
	@Order(1)
	void insertTest1() throws SQLException {
		LibraryDao x = new LibraryDao();
		assertEquals(1, x.insert("mailBibProva1", "password", "nomeBibProva", "indirizzo", "telBib", "username", "30",
				"citta"));

	}

	// illegal insert the second-last parameter must be a parsable int string
	@Test
	@Order(2)
	void insertTest2() throws SQLException {
		LibraryDao x = new LibraryDao();
		assertEquals(0, x.insert("mailBibProva1", "passwordBibProva", "nomeBibProva", "indirizzoBibProva",
				"telBibProva", "usernameBibProva", "sad", "cittaProva"));

	}

	// legal update
	@Test
	@Order(3)
	void updateTest1() throws SQLException {
		LibraryDao x = new LibraryDao();
		assertEquals(1, x.update("username", "asfd", "mailBibProva1"));
	}

	// illegal update not existing library
	@Test
	@Order(4)
	void updateTest2() throws SQLException {
		LibraryDao x = new LibraryDao();
		assertEquals(0, x.update("passwordBiblioteca", "newPW", "mailBibProva"));
	}

	
	// illegal seats update not existing type of seat
	@Test
	@Order(5)
	void updateTest3() throws SQLException {
		LibraryDao x = new LibraryDao();
		assertEquals(0, x.updatePosti(50, "mailBibProva1", "tipo non esistente"));
	}
	
	// legal seats update
		@Test
		@Order(6)
		void updateTest4() throws SQLException {
			LibraryDao x = new LibraryDao();
			assertEquals(1, x.updatePosti(50,"mailBibProva1", "postiOccupati"));
		}


	@Test
	@Order(7)
	void clearDb() throws SQLException {
		LibraryDao x = new LibraryDao();
		x.delete("Biblioteca", "mailBibProva1", null);
	}

}
