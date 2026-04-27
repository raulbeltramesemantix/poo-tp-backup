package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

public class Ingrediente {

    // encapsulamento: o ingrediente controla suas proprias quantidades
    private int idIngrediente;
    private String nome;
    private double quantidadeAtual;
    private String unidadeMedida;
    private double quantidadeMinima;

    // construtor padrao usado quando o ingrediente ainda nao tem dados
    public Ingrediente() {
    }

    // construtor completo para criar ingrediente com quantidade e minimo
    public Ingrediente(int idIngrediente, String nome, double quantidadeAtual, String unidadeMedida, double quantidadeMinima) {
        this.idIngrediente = idIngrediente;
        this.nome = nome;
        this.quantidadeAtual = quantidadeAtual;
        this.unidadeMedida = unidadeMedida;
        this.quantidadeMinima = quantidadeMinima;
    }

    //baixo a quantidade usada
    public void baixarQuantidade(double quantidade) {
        quantidadeAtual = Math.max(0.0, quantidadeAtual - quantidade);
    }

    // repoe a quantidade recebida
    public void reporQuantidade(double quantidade) {
        quantidadeAtual += quantidade;
    }

    public int getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getQuantidadeAtual() {
        return quantidadeAtual;
    }

    public void setQuantidadeAtual(double quantidadeAtual) {
        this.quantidadeAtual = quantidadeAtual;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public double getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(double quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    // polimorfismo sobrescrevo toString para listar o ingrediente no estoque
    @Override
    public String toString() {
        return "Ingrediente{"
                + "idIngrediente=" + idIngrediente
                + ", nome='" + nome + '\''
                + ", quantidadeAtual=" + quantidadeAtual
                + ", unidadeMedida='" + unidadeMedida + '\''
                + ", quantidadeMinima=" + quantidadeMinima
                + '}';
    }
}
