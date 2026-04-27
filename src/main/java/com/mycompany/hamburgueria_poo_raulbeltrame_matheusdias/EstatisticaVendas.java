package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

import java.sql.Date;

public class EstatisticaVendas {

    // encapsulamento: a estatistica agrupa os dados calculados do mes
    private Date data;
    private int quantidadePedidos;
    private double totalVendido;
    private String produtoMaisVendido;

    public EstatisticaVendas() {
    }

    public EstatisticaVendas(Date data, int quantidadePedidos, double totalVendido, String produtoMaisVendido) {
        this.data = data;
        this.quantidadePedidos = quantidadePedidos;
        this.totalVendido = totalVendido;
        this.produtoMaisVendido = produtoMaisVendido;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getQuantidadePedidos() {
        return quantidadePedidos;
    }

    public void setQuantidadePedidos(int quantidadePedidos) {
        this.quantidadePedidos = quantidadePedidos;
    }

    public double getTotalVendido() {
        return totalVendido;
    }

    public void setTotalVendido(double totalVendido) {
        this.totalVendido = totalVendido;
    }

    public String getProdutoMaisVendido() {
        return produtoMaisVendido;
    }

    public void setProdutoMaisVendido(String produtoMaisVendido) {
        this.produtoMaisVendido = produtoMaisVendido;
    }

    // polimorfismo sobrescrevo toString para imprimir o balanco mensal
    @Override
    public String toString() {
        return "EstatisticaVendas{"
                + "data=" + data
                + ", quantidadePedidos=" + quantidadePedidos
                + ", totalVendido=" + totalVendido
                + ", produtoMaisVendido='" + produtoMaisVendido + '\''
                + '}';
    }
}
