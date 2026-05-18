package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

import java.util.ArrayList;
import java.util.List;

// faco associacao entre item pedido e produto
//faco agregacao de adicionais no mesmo item
// uso decorator para calcular produto base com adicionais sem criar subclasses de Produto
public class ItemPedido {

    private int quantidade;
    private double subtotal;
    // associacao: cada item aponta para um Produto cadastrado
    private Produto produto;
    // agregacao o item pode ter varios adicionais escolhidos no pedido
    private List<Adicional> adicionais;

    // construtor padrao que inicia os adicionais do item
    public ItemPedido() {
        this.adicionais = new ArrayList<>();
    }

    // construtor completo para criar item com produto quantidade e adicionais
    public ItemPedido(Produto produto, int quantidade, List<Adicional> adicionais) {
        this();
        this.produto = produto;
        this.quantidade = quantidade;
        if (adicionais != null) {
            this.adicionais.addAll(adicionais);
        }
        calcularSubtotal();
    }

    // somo produto e adicionais usando a cadeia do padrao decorator
    public double calcularSubtotal() {
        ItemCardapio itemCardapio = montarItemCardapio();
        double valorUnitario = itemCardapio != null ? itemCardapio.calcularValor() : 0.0;
        subtotal = valorUnitario * quantidade;
        return subtotal;
    }

    // monto a cadeia decorator somente no calculo para preservar Produto e List<Adicional> no json
    private ItemCardapio montarItemCardapio() {
        if (produto == null) {
            return null;
        }
        ItemCardapio itemCardapio = produto;
        for (Adicional adicional : getAdicionais()) {
            itemCardapio = new AdicionalDecorator(itemCardapio, adicional);
        }
        return itemCardapio;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Adicional> getAdicionais() {
        if (adicionais == null) {
            adicionais = new ArrayList<>();
        }
        return adicionais;
    }

    public void setAdicionais(List<Adicional> adicionais) {
        this.adicionais = new ArrayList<>();
        if (adicionais != null) {
            this.adicionais.addAll(adicionais);
        }
    }

    // polimorfismo sobrescrevo toString para detalhar produto e adicionais
    @Override
    public String toString() {
        return "ItemPedido{"
                + "quantidade=" + quantidade
                + ", subtotal=" + subtotal
                + ", produto=" + produto
                + ", adicionais=" + getAdicionais()
                + '}';
    }
}
