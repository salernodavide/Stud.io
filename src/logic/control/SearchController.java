package logic.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logic.bean.SearchBean;
import logic.dao.LibraryDao;
import logic.entity.Library;

public class SearchController {
	List<Library> libraries;
	LibraryDao libraryDao;
	
	SearchBean searchBean;	//BEAN
	
	public SearchController() {
		this.libraries = new ArrayList<>();
		this.libraryDao = new LibraryDao();
		this.searchBean = new SearchBean();
	}
	
	public SearchController(SearchBean searchBean) {
		//da fare
		this.libraries = new ArrayList<>();
		this.libraryDao = new LibraryDao();
		this.searchBean = searchBean;
	}
	
	/*
	 * cerca librerie da db 
	 */
	public void searchLibraryFromCity(String location) {
		try {
			libraries = libraryDao.select(location.toLowerCase(), null);
		} catch (SQLException e) {
			///da fare
			e.printStackTrace();
		}
		setSearchBean(libraries);
	}
	
	/*
	 * setta la bean di risposta alla ricerca librerie da db
	 */
	public void setSearchBean(List<Library> results) {
		searchBean.setResultLibraries(results);
	}
	
	public List<Library> getResultsSearchBean() {
		return searchBean.getResultLibraries();
	}

	public void getInfoLibrary(Library library) {
		//da fare
		
	}

	public SearchBean getSearchBean() {
		//da fare
		return searchBean;
	}
	
}
