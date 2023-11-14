public class Comparacao {
    private int id;
    private String nome_objeto;
    private int idObjeto;
    private int idCriterio;
    private int idPesos;
    private double resultado;





    public Comparacao(int id, String nome_objeto, int idObjeto, int idCriterio, int idPesos, double resultado) {
        this.id = id;
        this.nome_objeto = nome_objeto;
        this.idObjeto = idObjeto;
        this.idCriterio = idCriterio;
        this.idPesos = idPesos;
        this.resultado = resultado;

    }

    public int getIdPesos() {
        return idPesos;
    }

    public void setIdPesos(int idPesos) {
        this.idPesos = idPesos;
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
    public String getNome_objeto(){
        return nome_objeto;
    }

    public void setNome_objeto(String nome_objeto) {
        this.nome_objeto = nome_objeto;
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






    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }
}