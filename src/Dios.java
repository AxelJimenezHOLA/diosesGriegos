import java.io.Serializable;

/**
 * No confundir con una GOD Class. Esta clase representa a un dios griego, junto con atributos como su nombre
 */
public class Dios implements Comparable<Dios> {
    private String nombreIngles;
    private String nombreGriego;
    private String tipoPrincipal;
    private String tipoSecundario;
    private String descripcion;

    public Dios() {
        nombreIngles = "";
        nombreGriego = "";
        tipoPrincipal = "";
        tipoSecundario = "";
        descripcion = "";
    }

    public Dios(String nombreIngles, String nombreGriego, String tipoPrincipal, String tipoSecundario, String descripcion) {
        this.nombreIngles = nombreIngles;
        this.nombreGriego = nombreGriego;
        this.tipoPrincipal = tipoPrincipal;
        this.tipoSecundario = tipoSecundario;
        this.descripcion = descripcion;
    }

    public String getNombreIngles() {
        return nombreIngles;
    }

    public String getNombreGriego() {
        return nombreGriego;
    }

    public String getTipoPrincipal() {
        return tipoPrincipal;
    }

    public String getTipoSecundario() {
        return tipoSecundario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return nombreIngles;
    }

    @Override
    public int compareTo(Dios otroDios) {
        return this.nombreIngles.compareTo(otroDios.nombreIngles);
    }
}