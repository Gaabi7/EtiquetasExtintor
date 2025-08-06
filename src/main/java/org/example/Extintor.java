package org.example;

public class Extintor {
    private String numeroDePosicionamento;
    private String tipo;
    private String capacidade;
    private String numeroDeIdentificacao;
    private String regiao;
    private String endereco;
    private String mesRecarga;
    private String anoRecarga;
    private String anoUltimoTeste;
    private String mesProximaRecarga;
    private String anoProximaRecarga;
    private String anoProximoTeste;


    public Extintor(String numeroDePosicionamento, String tipo, String capacidade, String numeroDeIdentificacao,
                    String regiao, String endereco, String mesRecarga, String anoRecarga, String anoUltimoTeste,
                    String mesProximaRecarga, String anoProximaRecarga, String anoProximoTeste) {
        this.numeroDePosicionamento = numeroDePosicionamento;
        this.tipo = tipo;
        this.capacidade = capacidade;
        this.numeroDeIdentificacao = numeroDeIdentificacao;
        this.regiao = regiao;
        this.endereco = endereco;
        this.mesRecarga = mesRecarga;
        this.anoRecarga = anoRecarga;
        this.anoUltimoTeste = anoUltimoTeste;
        this.mesProximaRecarga = mesProximaRecarga;
        this.anoProximaRecarga = anoProximaRecarga;
        this.anoProximoTeste = anoProximoTeste;
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

    public String getRegiao() {
        return regiao;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getMesRecarga() {
        return mesRecarga;
    }

    public String getAnoRecarga() {
        return anoRecarga;
    }

    public String getAnoUltimoTeste() {
        return anoUltimoTeste;
    }

    public String getMesProximaRecarga() {
        return mesProximaRecarga;
    }

    public String getAnoProximaRecarga() {
        return anoProximaRecarga;
    }

    public String getAnoProximoTeste() {
        return anoProximoTeste;
    }

    @Override
    public String toString() {
        return "Extintor{" +
                "numeroDePosicionamento='" + numeroDePosicionamento + '\'' +
                ", tipo='" + tipo + '\'' +
                ", capacidade='" + capacidade + '\'' +
                ", numeroDeIdentificacao='" + numeroDeIdentificacao + '\'' +
                ", regiao='" + regiao + '\'' +
                ", endereco='" + endereco + '\'' +
                ", mesRecarga='" + mesRecarga + '\'' +
                ", anoRecarga='" + anoRecarga + '\'' +
                ", anoUltimoTeste='" + anoUltimoTeste + '\'' +
                ", mesProximaRecarga='" + mesProximaRecarga + '\'' +
                ", anoProximaRecarga='" + anoProximaRecarga + '\'' +
                ", anoProximoTeste='" + anoProximoTeste + '\'' +
                '}';
    }
}