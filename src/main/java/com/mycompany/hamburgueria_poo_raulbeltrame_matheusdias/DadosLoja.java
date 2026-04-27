package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

public class DadosLoja {

    // encapsulamento: DadosLoja concentra as informacoes da empresa
    private String nomeLoja;
    private String telefone;
    private String endereco;
    private String horarioFuncionamento;
    private double taxaEntrega;

    public DadosLoja() {
    }

    public DadosLoja(String nomeLoja, String telefone, String endereco, String horarioFuncionamento, double taxaEntrega) {
        this.nomeLoja = nomeLoja;
        this.telefone = telefone;
        this.endereco = endereco;
        this.horarioFuncionamento = horarioFuncionamento;
        this.taxaEntrega = taxaEntrega;
    }

    //altero os dados da loja
    public void alterarDadosLoja(String nomeLoja, String telefone, String endereco, String horarioFuncionamento, double taxaEntrega) {
        this.nomeLoja = nomeLoja;
        this.telefone = telefone;
        this.endereco = endereco;
        this.horarioFuncionamento = horarioFuncionamento;
        this.taxaEntrega = taxaEntrega;
    }

    public String getNomeLoja() {
        return nomeLoja;
    }

    public void setNomeLoja(String nomeLoja) {
        this.nomeLoja = nomeLoja;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getHorarioFuncionamento() {
        return horarioFuncionamento;
    }

    public void setHorarioFuncionamento(String horarioFuncionamento) {
        this.horarioFuncionamento = horarioFuncionamento;
    }

    public double getTaxaEntrega() {
        return taxaEntrega;
    }

    public void setTaxaEntrega(double taxaEntrega) {
        this.taxaEntrega = taxaEntrega;
    }

    // polimorfismo sobrescrevo toString para exibir os dados da loja
    @Override
    public String toString() {
        return "DadosLoja{"
                + "nomeLoja='" + nomeLoja + '\''
                + ", telefone='" + telefone + '\''
                + ", endereco='" + endereco + '\''
                + ", horarioFuncionamento='" + horarioFuncionamento + '\''
                + ", taxaEntrega=" + taxaEntrega
                + '}';
    }
}
