import java.util.Date;

public class Empleat {
    String codi;
    String cognom;
    String ofici;
    String dataAlta;
    float salari;
    float comissio;
    String codiJefe;

    public Empleat(String codi, String cognom, String ofici, String dataAlta, float salari, float comissio, String codiJefe) {
        this.codi = codi;
        this.cognom = cognom;
        this.ofici = ofici;
        this.dataAlta = dataAlta;
        this.salari = salari;
        this.comissio = comissio;
        this.codiJefe = codiJefe;
    }

    public String getCodi() {
        return codi;
    }
    public void setCodi(String codi) {
        this.codi = codi;
    }

    public String getCognom() {
        return cognom;
    }
    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public String getOfici() {
        return ofici;
    }
    public void setOfici(String ofici) {
        this.ofici = ofici;
    }

    public String getDataAlta() {
        return dataAlta;
    }
    public void setDataAlta(String dataAlta) {
        this.dataAlta = dataAlta;
    }

    public float getSalari() {
        return salari;
    }
    public void setSalari(float salari) {
        this.salari = salari;
    }

    public float getComissio() {
        return comissio;
    }
    public void setComissio(float comissio) {
        this.comissio = comissio;
    }

    public String getCodiJefe() {
        return codiJefe;
    }
    public void setCodiJefe(String codiJefe) {
        this.codiJefe = codiJefe;
    }

    @Override
    public String toString() {
        return "Empleat{" +
                "codi='" + codi + '\'' +
                ", cognom='" + cognom + '\'' +
                ", ofici='" + ofici + '\'' +
                ", dataAlta=" + dataAlta +
                ", salari=" + salari +
                ", comissio=" + comissio +
                ", codiJefe='" + codiJefe + '\'' +
                '}';
    }
}