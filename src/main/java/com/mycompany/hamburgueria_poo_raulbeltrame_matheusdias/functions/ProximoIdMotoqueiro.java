package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

public class ProximoIdMotoqueiro {
    public static int proximoIdMotoqueiro(ControleEntregas controleEntregas) {
        int maiorId = 0;
        for (Motoqueiro motoqueiro : controleEntregas.getMotoqueiros()) {
            if (motoqueiro.getIdMotoqueiro() > maiorId) {
                maiorId = motoqueiro.getIdMotoqueiro();
            }
        }
        return maiorId + 1;
    }

}
