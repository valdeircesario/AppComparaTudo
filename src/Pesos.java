public class Pesos {
    private int id;
    private double pesoQualidade;
    private double pesoAvaliacao;
    private double pesoNota;


    public Pesos(double pesoQualidade, double pesoAvaliacao, double pesoNota) {

        this.pesoQualidade = pesoQualidade;
        this.pesoAvaliacao = pesoAvaliacao;
        this.pesoNota = pesoNota;

    }

    // Getters
    public int getId() {
        return id;
    }


    public double getPesoQualidade() {
        return pesoQualidade;
    }

    public double getPesoAvaliacao() {
        return pesoAvaliacao;
    }

    public double getPesoNota() {
        return pesoNota;
    }



    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setPesoQualidade(double pesoQualidade) {
        this.pesoQualidade = pesoQualidade;
    }

    public void setPesoAvaliacao(double pesoAvaliacao) {
        this.pesoAvaliacao = pesoAvaliacao;
    }

    public void setPesoNota(double pesoNota) {
        this.pesoNota = pesoNota;
    }


}
