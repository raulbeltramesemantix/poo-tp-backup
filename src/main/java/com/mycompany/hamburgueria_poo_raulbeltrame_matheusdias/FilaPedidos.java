package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

// aplico a estrutura fifo para atender a regra da fila de pedidos
public class FilaPedidos {

    // fifo: Queue garante que o primeiro pedido que entra seja o primeiro a sair
    private transient Queue<Pedido> pedidos;

    public FilaPedidos() {
        this.pedidos = new ArrayDeque<>();
    }

    //coloco o pedido no fim da fila
    public void enfileirar(Pedido pedido) {
        getPedidos().offer(pedido);
    }

    //pego o proximo pedido da fila
    public Pedido desenfileirar() {
        return getPedidos().poll();
    }

    //olho o proximo sem tirar da fila
    public Pedido consultarProximo() {
        return getPedidos().peek();
    }

    //tiro um pedido especifico da fila
    public void retirarPedido(int idPedido) {
        Queue<Pedido> novaFila = new ArrayDeque<>();
        for (Pedido pedido : getPedidos()) {
            if (pedido.getIdPedido() != idPedido) {
                novaFila.offer(pedido);
            }
        }
        pedidos = novaFila;
    }

    // refaco a fila com os pedidos ativos
    public void reconstruirFila(List<Pedido> pedidosAtivos) {
        pedidos = new ArrayDeque<>();
        if (pedidosAtivos != null) {
            for (Pedido pedido : pedidosAtivos) {
                if (pedido.getStatus() != StatusPedido.CANCELADO && pedido.getStatus() != StatusPedido.ENTREGUE) {
                    pedidos.offer(pedido);
                }
            }
        }
    }

    public Queue<Pedido> getPedidos() {
        if (pedidos == null) {
            pedidos = new ArrayDeque<>();
        }
        return pedidos;
    }

    public void setPedidos(Queue<Pedido> pedidos) {
        this.pedidos = pedidos;
        if (this.pedidos == null) {
            this.pedidos = new ArrayDeque<>();
        }
    }

    // polimorfismo sobrescrevo toString para mostrar o estado da fila
    @Override
    public String toString() {
        return "FilaPedidos{"
                + "quantidadePedidosNaFila=" + getPedidos().size()
                + '}';
    }
}
