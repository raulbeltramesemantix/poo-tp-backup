package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

public class ProximoIdRegiao {
    public static int proximoIdRegiao(ControleEntregas controleEntregas) {
        int maiorId = 0;
        for (RegiaoEntrega regiaoEntrega : controleEntregas.getRegioesEntrega()) {
            if (regiaoEntrega.getIdRegiao() > maiorId) {
                maiorId = regiaoEntrega.getIdRegiao();
            }
        }
        return maiorId + 1;
    }

}
