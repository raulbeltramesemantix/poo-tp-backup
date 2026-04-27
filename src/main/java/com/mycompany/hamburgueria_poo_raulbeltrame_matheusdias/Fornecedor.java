package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

public class Fornecedor {

    // encapsulamento: dados do fornecedor ficam privados
    private int idFornecedor;
    private String nome;
    private String telefone;

    public Fornecedor() {
    }

    public Fornecedor(int idFornecedor, String nome, String telefone) {
        this.idFornecedor = idFornecedor;
        this.nome = nome;
        this.telefone = telefone;
    }

    //sinalizo que o fornecedor entregou material
    public void fornecerIngrediente() {
        System.out.println("Fornecedor entregou ingrediente " + nome);
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    // polimorfismo sobrescrevo toString para imprimir o fornecedor
    @Override
    public String toString() {
        return "Fornecedor{"
                + "idFornecedor=" + idFornecedor
                + ", nome='" + nome + '\''
                + ", telefone='" + telefone + '\''
                + '}';
    }
}
