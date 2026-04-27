package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

public class Motoqueiro {

    // encapsulamento: o motoqueiro guarda seus dados e status de ativo
    private int idMotoqueiro;
    private String nome;
    private String telefone;
    private boolean ativo;

    public Motoqueiro() {
    }

    public Motoqueiro(int idMotoqueiro, String nome, String telefone, boolean ativo) {
        this.idMotoqueiro = idMotoqueiro;
        this.nome = nome;
        this.telefone = telefone;
        this.ativo = ativo;
    }

    // aviso a entrega via whatsapp
    public void informarEntregaWhatsapp() {
        System.out.println("Motoqueiro informou entrega " + nome);
    }

    public int getIdMotoqueiro() {
        return idMotoqueiro;
    }

    public void setIdMotoqueiro(int idMotoqueiro) {
        this.idMotoqueiro = idMotoqueiro;
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    // polimorfismo sobrescrevo toString para mostrar o motoqueiro na entrega
    @Override
    public String toString() {
        return "Motoqueiro{"
                + "idMotoqueiro=" + idMotoqueiro
                + ", nome='" + nome + '\''
                + ", telefone='" + telefone + '\''
                + ", ativo=" + ativo
                + '}';
    }
}
