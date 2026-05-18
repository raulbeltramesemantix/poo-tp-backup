package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

public class BuscarVendaPorPedido {
    public static Venda buscarVendaPorPedido(ControleVendas controleVendas, int idPedido) {
        for (Venda venda : controleVendas.getVendas()) {
            if (venda.getPedido() != null && venda.getPedido().getIdPedido() == idPedido) {
                return venda;
            }
        }
        return null;
    }

}
