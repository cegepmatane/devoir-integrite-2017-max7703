package ca.qc.cgmatane.informatique.devoirintegrite.modele;

public class Todo {
	protected int id;
    protected String titre;
    protected String daterealisation;
    protected String heure;
    protected String description;
    protected String url;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDaterealisation() {
        return daterealisation;
    }

    public void setDaterealisation(String daterealisation) {
        this.daterealisation = daterealisation;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
