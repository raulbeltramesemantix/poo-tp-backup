package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.util.Map;
import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerReceitaProduto.lerReceitaProduto;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerTexto.lerTexto;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerDouble.lerDouble;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerBoolean.lerBoolean;

public class IncluirProduto {
    public static void incluirProduto(Scanner scanner, CadastroProdutos cadastroProdutos, ControleEstoque controleEstoque) {
        // Produto compoe DescricaoProduto e associa uma receita por ingrediente para baixar estoque
        String nome = lerTexto(scanner, "nome do produto: ");
        boolean ativo = lerBoolean(scanner, "produto ativo? (s/n): ");
        String descricaoDetalhada = lerTexto(scanner, "descricao detalhada: ");
        double valor = lerDouble(scanner, "valor: ");
        Map<Integer, Double> receita = lerReceitaProduto(scanner, controleEstoque);
        Produto produto = cadastroProdutos.incluirProduto(nome, ativo, descricaoDetalhada, valor, receita);
        System.out.println("produto incluido: " + produto);
    }

}
