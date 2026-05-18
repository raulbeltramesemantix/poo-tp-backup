package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerInteiro.lerInteiro;

public class RemoverProduto {
    public static void removerProduto(Scanner scanner, CadastroProdutos cadastroProdutos) {
        int id = lerInteiro(scanner, "id do produto: ");
        Produto removido = cadastroProdutos.removerProduto(id);
        System.out.println(removido != null ? "produto removido: " + removido : "produto nao encontrado");
    }

}
