package logic.control;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import logic.bean.OrarioBean;
import logic.dao.OrarioDao;
import logic.entity.Library;
import logic.entity.Orario;

public class LibrarianScheduleController {

	private Library librInfo;
	private List<Orario> schedule;
	private List<OrarioBean> scheduleB;
	private OrarioDao orarioDao;
	static Logger myLogger = Logger.getLogger("logger");
	private static LibrarianScheduleController instance = null;

	protected LibrarianScheduleController() {
		this.librInfo = LibraryMainPageController.getLibraryMainPageController().getLibrInfo();
		this.schedule = new ArrayList<>();
		this.scheduleB = new ArrayList<>();
		this.orarioDao = new OrarioDao();
	}

	/*
	 * Metodo per richiedere l istanza signleton di controller
	 */

	public static LibrarianScheduleController getLibrarianScheduleController() {
		if (LibrarianScheduleController.instance == null)
			LibrarianScheduleController.instance = new LibrarianScheduleController();
		return instance;
	}

	public void getScheduleFromDb() {
		schedule = orarioDao.select(librInfo.getMail(), null);
		for (byte i = 0; i < 7; i++) {
			scheduleB.add(new OrarioBean());
			scheduleB.get(i).fillOrarioB(schedule.get(i).getGiorno(), "", schedule.get(i).getidBiblioteca());

		}

	}

	public void updateOrario() {
		for (int i = 0; i < 7; i++) {
			if (!scheduleB.get(i).getHour().isEmpty()) {
				orarioDao.updateOrario(scheduleB.get(i).getHour(), librInfo.getMail(), scheduleB.get(i).getDay());
			}
		}

	}

	public Library getLibrInfo() {
		return librInfo;
	}

	public void setLibrInfo(Library librInfo) {
		this.librInfo = librInfo;
	}

	public List<OrarioBean> getScheduleB() {
		return scheduleB;
	}

	public void setScheduleB(List<OrarioBean> scheduleB) {
		this.scheduleB = scheduleB;
	}

	public OrarioDao getOrarioDao() {
		return orarioDao;
	}

	public void setOrarioDao(OrarioDao orarioDao) {
		this.orarioDao = orarioDao;
	}

	public void setSchedule(List<Orario> schedule) {
		this.schedule = schedule;
	}

	public List<Orario> getSchedule() {
		return schedule;
	}

}
