package logic.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.LibrBean;
import logic.control.LibraryMainPageController;

/**
 * Servlet implementation class UpdateSeatsServlet
 */
public class UpdateSeatsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String bean = "libraryBean";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateSeatsServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			LibraryMainPageController.getLibraryMainPageController().updateLibraryMainPage();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		LibrBean libraryBean = LibraryMainPageController.getLibraryMainPageController().getLibrInfoB();
		request.getSession().setAttribute(bean, libraryBean);
		request.setAttribute(bean, libraryBean);
		request.setAttribute("free", libraryBean.getCapacity()-libraryBean.getPostiOccupati()-LibraryMainPageController.getLibraryMainPageController().getBooks().size());
        request.setAttribute("booked", LibraryMainPageController.getLibraryMainPageController().getBooksBean());   //Ritorna List<BookSeatBean>
        request.getRequestDispatcher("librarianHome.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean addButtonPressed = request.getParameter("+") != null;
		String input = null;
		if(addButtonPressed) {
			input="+";
		}
		else {
			input="-";
		}
		LibraryMainPageController.getLibraryMainPageController().updateSeats(input);
		try {
			LibraryMainPageController.getLibraryMainPageController().updateLibraryMainPage();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		doGet(request,response);
		
	}

}
