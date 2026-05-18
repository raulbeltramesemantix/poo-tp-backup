package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerInteiro.lerInteiro;

public class RemoverPedido {
    public static void removerPedido(Scanner scanner, CadastroPedidos cadastroPedidos) {
        int id = lerInteiro(scanner, "id do pedido: ");
        Pedido removido = cadastroPedidos.removerPedido(id);
        System.out.println(removido != null ? "pedido removido: " + removido : "pedido nao encontrado");
    }

}
