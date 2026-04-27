package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

import java.util.LinkedHashMap;
import java.util.Map;

// aplico encapsulamento nos dados do lanche
public class Produto {

    private int idProduto;
    private String nome;
    private boolean ativo;
    // composicao DescricaoProduto pertence ao Produto
    private DescricaoProduto descricaoProduto;
    // associacao: produto referencia ingredientes da receita pelo id
    private Map<Integer, Double> quantidadesIngredientesPorId;

    public Produto() {
        this.quantidadesIngredientesPorId = new LinkedHashMap<>();
    }

    public Produto(int idProduto, String nome, boolean ativo, DescricaoProduto descricaoProduto, Map<Integer, Double> quantidadesIngredientesPorId) {
        this();
        this.idProduto = idProduto;
        this.nome = nome;
        this.ativo = ativo;
        this.descricaoProduto = descricaoProduto;
        if (quantidadesIngredientesPorId != null) {
            this.quantidadesIngredientesPorId.putAll(quantidadesIngredientesPorId);
        }
        SistemaDelivery.incrementarQtdProdutosCriados();
    }

    //altero os dados principais do produto
    public void alterarProduto(String nome, boolean ativo) {
        this.nome = nome;
        this.ativo = ativo;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public DescricaoProduto getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(DescricaoProduto descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public Map<Integer, Double> getQuantidadesIngredientesPorId() {
        if (quantidadesIngredientesPorId == null) {
            quantidadesIngredientesPorId = new LinkedHashMap<>();
        }
        return quantidadesIngredientesPorId;
    }

    public void setQuantidadesIngredientesPorId(Map<Integer, Double> quantidadesIngredientesPorId) {
        this.quantidadesIngredientesPorId = new LinkedHashMap<>();
        if (quantidadesIngredientesPorId != null) {
            this.quantidadesIngredientesPorId.putAll(quantidadesIngredientesPorId);
        }
    }

    // polimorfismo sobrescrevo toString para mostrar produto, descricao e receita
    @Override
    public String toString() {
        return "Produto{"
                + "idProduto=" + idProduto
                + ", nome='" + nome + '\''
                + ", ativo=" + ativo
                + ", descricaoProduto=" + descricaoProduto
                + ", quantidadesIngredientesPorId=" + getQuantidadesIngredientesPorId()
                + '}';
    }
}
