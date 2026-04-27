package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// centralizo o cadastro dos produtos para manter a regra em um ponto
public class CadastroProdutos {

    // agregacao cadastro mantem uma lista dinamica de produtos
    private List<Produto> produtos;

    public CadastroProdutos() {
        this.produtos = new ArrayList<>();
    }

    //incluo um produto no cadastro
    public Produto incluirProduto(String nome, boolean ativo, String descricaoDetalhada, double valor, Map<Integer, Double> quantidadesIngredientesPorId) {
        int idProduto = GeradorId.gerarIdProduto();
        DescricaoProduto descricaoProduto = new DescricaoProduto(idProduto, descricaoDetalhada, valor);
        Produto produto = new Produto(idProduto, nome, ativo, descricaoProduto, quantidadesIngredientesPorId);
        getProdutos().add(produto);
        return produto;
    }

    //removo um produto pelo id
    public Produto removerProduto(int id) {
        for (int indice = 0; indice < getProdutos().size(); indice++) {
            Produto produto = getProdutos().get(indice);
            if (produto.getIdProduto() == id) {
                getProdutos().remove(indice);
                return produto;
            }
        }
        return null;
    }

    //busco um produto pelo id
    public Produto buscarProduto(int id) {
        for (Produto produto : getProdutos()) {
            if (produto.getIdProduto() == id) {
                return produto;
            }
        }
        return null;
    }

    // altero um produto existente
    public void alterarProduto(int id, String nome, boolean ativo, String descricaoDetalhada, double valor, Map<Integer, Double> quantidadesIngredientesPorId) {
        Produto produto = buscarProduto(id);
        if (produto != null) {
            produto.alterarProduto(nome, ativo);
            if (produto.getDescricaoProduto() != null) {
                produto.getDescricaoProduto().alterarDescricao(descricaoDetalhada, valor);
            }
            produto.setQuantidadesIngredientesPorId(quantidadesIngredientesPorId);
        }
    }

    public List<Produto> getProdutos() {
        if (produtos == null) {
            produtos = new ArrayList<>();
        }
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = new ArrayList<>();
        if (produtos != null) {
            this.produtos.addAll(produtos);
        }
    }

    // polimorfismo sobrescrevo toString para mostrar produtos cadastrados
    @Override
    public String toString() {
        return "CadastroProdutos{"
                + "produtos=" + getProdutos()
                + '}';
    }
}
