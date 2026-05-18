package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

public class ProximoIdIngrediente {
    public static int proximoIdIngrediente(ControleEstoque controleEstoque) {
        int maiorId = 0;
        for (Ingrediente ingrediente : controleEstoque.verificarIngredientes()) {
            if (ingrediente.getIdIngrediente() > maiorId) {
                maiorId = ingrediente.getIdIngrediente();
            }
        }
        return maiorId + 1;
    }

}
