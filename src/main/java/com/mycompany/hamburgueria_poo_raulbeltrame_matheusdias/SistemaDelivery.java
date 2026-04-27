package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

import java.util.Arrays;

//centralizo o sistema e mantenho as tres estacoes fixas do diagrama
public class SistemaDelivery {

    // composicao o sistema monta e controla as tres estacoes fixas
    private EstacaoPreparo[] estacoes;
    //uso static para compartilhar o contador de pedidos entre todas as instancias
    private static int qtdPedidosCriados;
    //uso static no nivel da classe para contar produtos criados
    protected static int qtdProdutosCriados;

    // construtor padrao que cria o vetor fixo de tres estacoes
    public SistemaDelivery() {
        estacoes = new EstacaoPreparo[3];
    }

    // monto as tres estacoes fixas
    public void iniciarSistema() {
        if (estacoes == null || estacoes.length != 3) {
            estacoes = new EstacaoPreparo[3];
        }
        estacoes[0] = new EstacaoPreparo(1, "Chapa", false);
        estacoes[1] = new EstacaoPreparo(2, "Montagem", false);
        estacoes[2] = new EstacaoPreparo(3, "Finalizacao", false);
    }

    public int getQtdPedidosCriados() {
        return qtdPedidosCriados;
    }

    public static void setQtdPedidosCriados(int qtdPedidosCriados) {
        SistemaDelivery.qtdPedidosCriados = qtdPedidosCriados;
    }

    public EstacaoPreparo[] getEstacoes() {
        return estacoes;
    }

    public void setEstacoes(EstacaoPreparo[] estacoes) {
        this.estacoes = estacoes;
    }

    static void incrementarQtdPedidosCriados() {
        qtdPedidosCriados++;
    }

    static void incrementarQtdProdutosCriados() {
        qtdProdutosCriados++;
    }

    // polimorfismo sobrescrevo toString para resumir o estado do sistema
    @Override
    public String toString() {
        return "SistemaDelivery{"
                + "estacoes=" + Arrays.toString(estacoes)
                + ", qtdPedidosCriados=" + qtdPedidosCriados
                + ", qtdProdutosCriados=" + qtdProdutosCriados
                + '}';
    }
}
