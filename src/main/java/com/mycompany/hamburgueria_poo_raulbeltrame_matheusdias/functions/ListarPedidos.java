package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

public class ListarPedidos {
    public static void listarPedidos(CadastroPedidos cadastroPedidos) {
        if (cadastroPedidos.getPedidos().isEmpty()) {
            System.out.println("nenhum pedido cadastrado");
            return;
        }
        for (Pedido pedido : cadastroPedidos.getPedidos()) {
            System.out.println(pedido);
        }
    }

}
