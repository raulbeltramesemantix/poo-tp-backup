package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

public class Adicional {

    // encapsulamento atributos  privados acessados por getters e setters
    private int idAdicional;
    private String nome;
    private double valor;

    // construtor padrao usado quando o objeto e criado vazio
    public Adicional() {
    }

    // construtor completo usado para criar um adicional com seus dados
    public Adicional(int idAdicional, String nome, double valor) {
        this.idAdicional = idAdicional;
        this.nome = nome;
        this.valor = valor;
    }

    //altero o adicional do item
    public void alterarAdicional(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public int getIdAdicional() {
        return idAdicional;
    }

    public void setIdAdicional(int idAdicional) {
        this.idAdicional = idAdicional;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    // polimorfismo sobrescrevo o toString para facilitar impressao do adicional
    @Override
    public String toString() {
        return "Adicional{"
                + "idAdicional=" + idAdicional
                + ", nome='" + nome + '\''
                + ", valor=" + valor
                + '}';
    }
}
