package test;
import static org.junit.jupiter.api.Assertions.*;
import logic.control.LoginController;
import org.junit.jupiter.api.Test;

class TestLogin {
	LoginController loginController = new LoginController();
	

	@Test
	void testStu() throws Exception {
		assertEquals('s', loginController.validateUser("stu@live.it", "1234"));
	}

	@Test
	void testLib() throws Exception {
		assertEquals('l', loginController.validateUser("bib@live.it", "1234"));
	}

	// mail sbagliata password sbagliata
	@Test
	void testErr1() throws Exception {
		assertEquals('0', loginController.validateUser("adsf", "dads"));
	}

	// mail giusta password sbagliata
	@Test
	void testErr2() throws Exception {
		assertEquals('0', loginController.validateUser("bib@live.it", "dads"));
	}

	// password giusta mail sbagliata
	@Test
	void testErr4() throws Exception {
		assertEquals('0', loginController.validateUser("adf", "1234"));
	}

}
