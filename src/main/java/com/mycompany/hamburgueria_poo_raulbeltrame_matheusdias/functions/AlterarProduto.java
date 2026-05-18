package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.util.Map;
import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerReceitaProduto.lerReceitaProduto;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerTextoComPadrao.lerTextoComPadrao;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerInteiro.lerInteiro;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerDoubleComPadrao.lerDoubleComPadrao;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerBoolean.lerBoolean;

public class AlterarProduto {
    public static void alterarProduto(Scanner scanner, CadastroProdutos cadastroProdutos, ControleEstoque controleEstoque) {
        int id = lerInteiro(scanner, "id do produto: ");
        Produto produto = cadastroProdutos.buscarProduto(id);
        if (produto == null) {
            System.out.println("produto nao encontrado");
            return;
        }

        String nome = lerTextoComPadrao(scanner, "nome [" + produto.getNome() + "]: ", produto.getNome());
        boolean ativo = lerBoolean(scanner, "produto ativo? (s/n): ");
        String descricaoAtual = produto.getDescricaoProduto() != null ? produto.getDescricaoProduto().getDescricaoDetalhada() : "";
        double valorAtual = produto.getDescricaoProduto() != null ? produto.getDescricaoProduto().getValor() : 0.0;
        String descricaoDetalhada = lerTextoComPadrao(scanner, "descricao [" + descricaoAtual + "]: ", descricaoAtual);
        double valor = lerDoubleComPadrao(scanner, "valor [" + valorAtual + "]: ", valorAtual);
        Map<Integer, Double> receita = lerReceitaProduto(scanner, controleEstoque);
        cadastroProdutos.alterarProduto(id, nome, ativo, descricaoDetalhada, valor, receita);
        System.out.println("produto alterado");
    }

}
