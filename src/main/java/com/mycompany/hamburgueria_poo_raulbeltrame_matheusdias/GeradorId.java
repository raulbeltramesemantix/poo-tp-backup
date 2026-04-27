package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

public class GeradorId {

    // static: ids ficam compartilhados pela classe inteira
    private static int ultimoIdCliente;
    private static int ultimoIdPedido;
    private static int ultimoIdProduto;

    // construtor padrao mantido para o javadoc mostrar a criacao da classe
    public GeradorId() {
    }

    // gero um novo id de cliente
    public static synchronized int gerarIdCliente() {
        ultimoIdCliente++;
        return ultimoIdCliente;
    }

    //gero um novo id de pedido
    public static synchronized int gerarIdPedido() {
        ultimoIdPedido++;
        return ultimoIdPedido;
    }

    //gero um novo id de produto
    public static synchronized int gerarIdProduto() {
        ultimoIdProduto++;
        return ultimoIdProduto;
    }

    // ajusto os ultimos ids apos carga
    public static synchronized void definirUltimosIds(int ultimoIdCliente, int ultimoIdPedido, int ultimoIdProduto) {
        GeradorId.ultimoIdCliente = ultimoIdCliente;
        GeradorId.ultimoIdPedido = ultimoIdPedido;
        GeradorId.ultimoIdProduto = ultimoIdProduto;
    }

    // polimorfismo sobrescrevo toString para mostrar os ultimos ids gerados
    @Override
    public String toString() {
        return "GeradorId{"
                + "ultimoIdCliente=" + ultimoIdCliente
                + ", ultimoIdPedido=" + ultimoIdPedido
                + ", ultimoIdProduto=" + ultimoIdProduto
                + '}';
    }
}
