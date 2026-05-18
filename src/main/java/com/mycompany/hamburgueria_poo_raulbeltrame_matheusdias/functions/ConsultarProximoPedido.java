package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

public class ConsultarProximoPedido {
    public static void consultarProximoPedido(CadastroPedidos cadastroPedidos) {
        Pedido pedido = cadastroPedidos.getFilaPedidos().consultarProximo();
        System.out.println(pedido != null ? "proximo pedido fifo: " + pedido : "fila vazia");
    }

}
