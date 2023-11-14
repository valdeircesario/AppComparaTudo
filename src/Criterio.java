public class Criterio {
    private int id;

    private double qualidade;
    private double avaliacao;
    private double nota;






    public Criterio( double qualidade,double avaliacao,double nota) {
        this.qualidade = qualidade;
        this.avaliacao = avaliacao;
        this.nota = nota;

    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public double getQualidade() {

        return qualidade;
    }

    public void setQualidade(double qualidade) {

        this.qualidade = qualidade;
    }

    public double getAvaliacao() {

        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public double getNota() {
        return nota;
    }


    public void setNota(double nota) {
        this.nota = nota;
    }
}
