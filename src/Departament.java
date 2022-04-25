import java.util.List;

public class Departament {
    String codi;
    String nom;
    String localitat;
    List<Empleat> empleatsList;

    public Departament(String codi, String nom, String localitat) {
        this.codi = codi;
        this.nom = nom;
        this.localitat = localitat;
    }

    public String getCodi() {
        return codi;
    }
    public String getNom() {
        return nom;
    }
    public String getLocalitat() {
        return localitat;
    }
    public List<Empleat> getEmpleatsList() {
        return empleatsList;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setLocalitat(String localitat) {
        this.localitat = localitat;
    }
    public void setEmpleatsList(List<Empleat> empleatsList) {
        this.empleatsList = empleatsList;
    }

    @Override
    public String toString() {
        return "Departament{" +
                "codi='" + codi + '\'' +
                ", nom='" + nom + '\'' +
                ", localitat='" + localitat + '\'' +
                ", empleats=" + empleatsList +
                '}';
    }
}
