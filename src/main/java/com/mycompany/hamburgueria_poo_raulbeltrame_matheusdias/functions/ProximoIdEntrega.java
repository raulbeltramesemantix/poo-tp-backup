package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

public class ProximoIdEntrega {
    public static int proximoIdEntrega(ControleVendas controleVendas) {
        int maiorId = 0;
        for (Venda venda : controleVendas.getVendas()) {
            if (venda.getEntrega() != null && venda.getEntrega().getIdEntrega() > maiorId) {
                maiorId = venda.getEntrega().getIdEntrega();
            }
        }
        return maiorId + 1;
    }

}
