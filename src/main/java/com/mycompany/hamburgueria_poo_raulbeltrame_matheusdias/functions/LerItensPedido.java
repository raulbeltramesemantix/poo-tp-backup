package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.ListarProdutos.listarProdutos;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerAdicionais.lerAdicionais;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerInteiro.lerInteiro;

public class LerItensPedido {
    public static List<ItemPedido> lerItensPedido(Scanner scanner, CadastroProdutos cadastroProdutos) {
        List<ItemPedido> itensPedido = new ArrayList<>();
        if (cadastroProdutos.getProdutos().isEmpty()) {
            System.out.println("cadastre produtos antes de criar pedidos");
            return itensPedido;
        }

        while (true) {
            listarProdutos(cadastroProdutos);
            int idProduto = lerInteiro(scanner, "id do produto (0 para terminar): ");
            if (idProduto == 0) {
                break;
            }

            Produto produto = cadastroProdutos.buscarProduto(idProduto);
            if (produto == null) {
                System.out.println("produto nao encontrado");
                continue;
            }

            int quantidade = lerInteiro(scanner, "quantidade: ");
            List<Adicional> adicionais = lerAdicionais(scanner);
            itensPedido.add(new ItemPedido(produto, quantidade, adicionais));
        }

        return itensPedido;
    }

}
