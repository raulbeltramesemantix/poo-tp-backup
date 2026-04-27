package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

public class DescricaoProduto {

    // encapsulamento: dados da descricao ficam protegidos dentro da classe
    private int idDescricao;
    private String descricaoDetalhada;
    private double valor;

    // construtor padrao usado pela persistencia e criacao vazia
    public DescricaoProduto() {
    }

    // construtor completo para ligar descricao e valor ao produto
    public DescricaoProduto(int idDescricao, String descricaoDetalhada, double valor) {
        this.idDescricao = idDescricao;
        this.descricaoDetalhada = descricaoDetalhada;
        this.valor = valor;
    }

    // altero a descricao do produto
    public void alterarDescricao(String texto, double valor) {
        this.descricaoDetalhada = texto;
        this.valor = valor;
    }

    public int getIdDescricao() {
        return idDescricao;
    }

    public void setIdDescricao(int idDescricao) {
        this.idDescricao = idDescricao;
    }

    public String getDescricaoDetalhada() {
        return descricaoDetalhada;
    }

    public void setDescricaoDetalhada(String descricaoDetalhada) {
        this.descricaoDetalhada = descricaoDetalhada;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    // polimorfismo sobrescrevo toString para mostrar a descricao no console
    @Override
    public String toString() {
        return "DescricaoProduto{"
                + "idDescricao=" + idDescricao
                + ", descricaoDetalhada='" + descricaoDetalhada + '\''
                + ", valor=" + valor
                + '}';
    }
}
