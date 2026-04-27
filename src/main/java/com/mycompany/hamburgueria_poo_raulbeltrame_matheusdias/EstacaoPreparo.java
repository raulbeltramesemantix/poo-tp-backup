package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

public class EstacaoPreparo {

    // encapsulamento: a estacao controla se esta ocupada ou livre
    private int idEstacao;
    private String nome;
    private boolean ocupada;

    // construtor padrao usado para criar estacao vazia
    public EstacaoPreparo() {
    }

    // construtor completo para montar uma estacao do sistema
    public EstacaoPreparo(int idEstacao, String nome, boolean ocupada) {
        this.idEstacao = idEstacao;
        this.nome = nome;
        this.ocupada = ocupada;
    }

    // marco a estacao como ocupada
    public void iniciarPreparo() {
        ocupada = true;
    }

    // libero a estacao usada
    public void liberarEstacao() {
        ocupada = false;
    }

    public int getIdEstacao() {
        return idEstacao;
    }

    public void setIdEstacao(int idEstacao) {
        this.idEstacao = idEstacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    // polimorfismo sobrescrevo toString para mostrar a estacao no resumo
    @Override
    public String toString() {
        return "EstacaoPreparo{"
                + "idEstacao=" + idEstacao
                + ", nome='" + nome + '\''
                + ", ocupada=" + ocupada
                + '}';
    }
}
