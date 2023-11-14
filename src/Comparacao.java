public class Comparacao {
    private int id;
    private int idObjeto;
    private int idCriterio;
    private int idPeso;
    private double resultado;

    public Comparacao(int id, int idObjeto, int idCriterio, int idPeso, double resultado) {
        this.id = id;
        this.idObjeto = idObjeto;
        this.idCriterio = idCriterio;
        this.idPeso = idPeso;
        this.resultado = resultado;
    }

    public static boolean isEmpty() {
        return true;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(int idObjeto) {
        this.idObjeto = idObjeto;
    }

    public int getIdCriterio() {
        return idCriterio;
    }

    public void setIdCriterio(int idCriterio) {
        this.idCriterio = idCriterio;
    }

    public int getIdPeso() {
        return idPeso;
    }

    public void setIdPeso(int idPeso) {
        this.idPeso = idPeso;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }
}