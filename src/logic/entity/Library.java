package logic.entity;

import java.util.ArrayList;
import java.util.List;

public class Library extends User {

	private String indirizzo;
	private int capacity;
	private int postiOccupati;
	private String city;
	private List<Servizio> servizi = new ArrayList<>();
	private List<Orario> orarioGiorno = new ArrayList<>();
	private List<Post> bacheca = new ArrayList<>();

	public Library() {
	}

	public Library(String... arg) {
		this.userName = arg[0];
		this.name = arg[1];
		this.mail = arg[2];
		this.password = arg[3];
		this.indirizzo = arg[4];
		this.capacity = Integer.valueOf(arg[5]);
		this.phone = arg[6];
		this.city = arg[7];
		this.postiOccupati = Integer.valueOf(arg[8]);
		this.observers=new ArrayList<>();
	}

	// SET INDIRIZZO BIBLIOTECA
	public void setIndirizzo(String indirizzoP) {

		this.indirizzo = indirizzoP;

	}

	// GET INDIRIZZO BIBLIOTECA
	public String getIndirizzo() {

		return indirizzo;

	}

	// SET POSTI
	public void setPostiOccupati(int posti, String tipo) {
		if (tipo.equals("occupati"))
			this.postiOccupati = posti;
		if (tipo.equals("capacita"))
			this.capacity = posti;
	}

	// GET POSTI
	public int getCapacity() {
		return capacity;

	}

	public int getPostiOccupati() {
		return postiOccupati;
	}

	// ADD SERVIZIO
	public void addServizio(String nomeServ, String descrizione) {
		this.servizi.add(new Servizio(nomeServ, this.mail, descrizione));
	}

	// DELETE SERVIZIO
	public void deleteServizio(String nomeServ) {
		byte i = 0;
		for (i = 0; i < this.servizi.size(); i++) {
			if (this.servizi.get(i).getNomeServizio().contentEquals(nomeServ)) {
				this.servizi.remove(i);
				break;
			}
		}
	}

	// GET SERVIZI
	public List<Servizio> getServizi() {
		return servizi;
	}

	// GET ORARIO
	public List<Orario> getSchedule() {
		return orarioGiorno;
	}

	// ADD ORARIO
	public void addOrarioGiornaliero(String giorno, String orario) {
		this.orarioGiorno.add(new Orario(giorno, orario, this.mail));
	}

	// GET POST
	public List<Post> getBacheca() {
		return bacheca;
	}

	// ADD POST
	public void addPost(String testo, String data, String title) {
		this.bacheca.add(new Post(testo, this.mail, data, title));
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void fillLibrary(String address, String city, int capacity, int postiOccupati) {
		this.indirizzo = address;
		this.city = city;
		this.capacity = capacity;
		this.postiOccupati = postiOccupati;

	}


}
