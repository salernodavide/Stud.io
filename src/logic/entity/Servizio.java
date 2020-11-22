package logic.entity;

public class Servizio {
	private String nomeServizio;
	private String idBiblioteca;
	private String descrizioneServizio;

	public Servizio() {
		this.nomeServizio = null;
		this.idBiblioteca = null;
		this.descrizioneServizio = null;
	}

	public Servizio(String servizioP, String idBibliotecaP, String descrizioneServizio) {
		this.nomeServizio = servizioP;
		this.idBiblioteca = idBibliotecaP;
		this.descrizioneServizio = descrizioneServizio;
	}

	public String getNomeServizio() {
		return nomeServizio;
	}

	public void setNomeServizio(String nomeServizio) {
		this.nomeServizio = nomeServizio;
	}

	public String getIdBiblioteca() {
		return idBiblioteca;
	}

	public void setIdBiblioteca(String idBiblioteca) {
		this.idBiblioteca = idBiblioteca;
	}

	public String getDescrizioneServizio() {
		return descrizioneServizio;
	}

	public void setDescrizioneServizio(String descrizioneServizio) {
		this.descrizioneServizio = descrizioneServizio;
	}

}
