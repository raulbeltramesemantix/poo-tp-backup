package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

public class ListarProdutos {
    public static void listarProdutos(CadastroProdutos cadastroProdutos) {
        if (cadastroProdutos.getProdutos().isEmpty()) {
            System.out.println("nenhum produto cadastrado");
            return;
        }
        for (Produto produto : cadastroProdutos.getProdutos()) {
            System.out.println(produto);
        }
    }

}
