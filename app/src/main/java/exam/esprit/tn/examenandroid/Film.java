package exam.esprit.tn.examenandroid;

public class Film {
    private long id;
    private String nom;
    private String description;
    private String typeProjection;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeProjection() {
        return typeProjection;
    }

    public void setTypeProjection(String typeProjection) {
        this.typeProjection = typeProjection;
    }
}
