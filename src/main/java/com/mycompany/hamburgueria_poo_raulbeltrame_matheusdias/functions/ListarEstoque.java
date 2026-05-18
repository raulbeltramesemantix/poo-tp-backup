package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.util.List;

public class ListarEstoque {
    public static void listarEstoque(ControleEstoque controleEstoque) {
        List<Ingrediente> ingredientes = controleEstoque.verificarIngredientes();
        if (ingredientes.isEmpty()) {
            System.out.println("nenhum ingrediente cadastrado no estoque");
            return;
        }
        for (Ingrediente ingrediente : ingredientes) {
            System.out.println(ingrediente);
        }
    }

}
