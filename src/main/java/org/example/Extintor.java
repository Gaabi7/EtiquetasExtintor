package org.example;

public class Extintor {
    private String numeroDePosicionamento;
    private String tipo;
    private String capacidade;
    private String numeroDeIdentificacao;
    private String dataDeRecarga;
    private String ultimoTeste;
    private String regiao;
    private String endereco;
    private String proximaRecarga;
    private String proximoTeste;

    public Extintor(String numeroDePosicionamento, String tipo, String capacidade, String numeroDeIdentificacao, String dataDeRecarga,
                    String ultimoTeste, String regiao, String endereco, String proximaRecarga, String proximoTeste) {

        this.numeroDePosicionamento = numeroDePosicionamento;
        this.tipo = tipo;
        this.capacidade = capacidade;
        this.numeroDeIdentificacao = numeroDeIdentificacao;
        this.dataDeRecarga = dataDeRecarga;
        this.ultimoTeste = ultimoTeste;
        this.regiao = regiao;
        this.endereco = endereco;
        this.proximaRecarga = proximaRecarga;
        this.proximoTeste = proximoTeste;
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

    public String getRegiao() {
        return regiao;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getProximaRecarga() {
        return proximaRecarga;
    }

    public String getProximoTeste() {
        return proximoTeste;
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
                ", regiao='" + regiao + '\'' +
                ", endereco='" + endereco + '\'' +
                ", proximaRecarga='" + proximaRecarga + '\'' +
                ", proximoTeste='" + proximoTeste + '\'' +
                '}';
    }
}