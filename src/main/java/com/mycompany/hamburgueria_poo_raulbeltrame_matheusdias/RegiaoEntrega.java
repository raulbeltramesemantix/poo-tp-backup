package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

public class RegiaoEntrega {

    // encapsulamento: a regiao guarda o nome e os bairros atendidos
    private int idRegiao;
    private String nomeRegiao;
    private String bairros;

    // construtor padrao usado para criar regiao vazia
    public RegiaoEntrega() {
    }

    // construtor completo para cadastrar a regiao de entrega
    public RegiaoEntrega(int idRegiao, String nomeRegiao, String bairros) {
        this.idRegiao = idRegiao;
        this.nomeRegiao = nomeRegiao;
        this.bairros = bairros;
    }

    // defino os bairros da regiao
    public void definirBairros(String bairros) {
        this.bairros = bairros;
    }

    public int getIdRegiao() {
        return idRegiao;
    }

    public void setIdRegiao(int idRegiao) {
        this.idRegiao = idRegiao;
    }

    public String getNomeRegiao() {
        return nomeRegiao;
    }

    public void setNomeRegiao(String nomeRegiao) {
        this.nomeRegiao = nomeRegiao;
    }

    public String getBairros() {
        return bairros;
    }

    public void setBairros(String bairros) {
        this.bairros = bairros;
    }

    // polimorfismo sobrescrevo toString para imprimir a regiao
    @Override
    public String toString() {
        return "RegiaoEntrega{"
                + "idRegiao=" + idRegiao
                + ", nomeRegiao='" + nomeRegiao + '\''
                + ", bairros='" + bairros + '\''
                + '}';
    }
}
