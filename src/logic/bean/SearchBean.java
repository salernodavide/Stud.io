package logic.bean;

import java.util.ArrayList;
import java.util.List;
import logic.entity.Library;

public class SearchBean {
	private String location;
	private List<Library> resultLibraries;
	
	public SearchBean() {
		this.location = "";
		this.resultLibraries = new ArrayList<>();
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Library> getResultLibraries() {
		return resultLibraries;
	}

	public void setResultLibraries(List<Library> resultLibraries) {
		this.resultLibraries = resultLibraries;
	}
	
}
