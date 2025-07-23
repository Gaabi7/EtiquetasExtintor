package org.example;

public class Extintor {
    private String numeroDePosicionamento;
    private String tipo;
    private String capacidade;
    private String numeroDeIdentificacao;
    private String dataDeRecarga;
    private String ultimoTeste;

    public Extintor(String numeroDePosicionamento, String tipo, String capacidade, String numeroDeIdentificacao, String dataDeRecarga, String ultimoTeste) {
        this.numeroDePosicionamento = numeroDePosicionamento;
        this.tipo = tipo;
        this.capacidade = capacidade;
        this.numeroDeIdentificacao = numeroDeIdentificacao;
        this.dataDeRecarga = dataDeRecarga;
        this.ultimoTeste = ultimoTeste;
    }

    public String getNumeroDePosicionamento() {
        return numeroDePosicionamento;
    }

    public String getTipo()  {
        return tipo;
    }

    public String getCapacidade()  {
        return capacidade;
    }

    public String getNumeroDeIdentificacao() {
        return numeroDeIdentificacao;
    }

    public String getDataDeRecarga() {
        return dataDeRecarga;
    }

    public String getUltimoTeste() {
        return ultimoTeste;
    }

    @Override
    public String toString() {
        return "Extintor{" +
                "numeroDePosicionamento='" + numeroDePosicionamento + '\'' +
                ", tipo='" + tipo + '\'' +
                ", capacidade='" + capacidade + '\'' +
                ", numeroDeIdentificacao='" + numeroDeIdentificacao + '\'' +
                ", dataDeRecarga='" + dataDeRecarga + '\'' +
                ", ultimoTeste='" + ultimoTeste + '\'' +
                '}';
    }
}