package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerInteiro.lerInteiro;

public class CancelarPedido {
    public static void cancelarPedido(Scanner scanner, CadastroPedidos cadastroPedidos) {
        int id = lerInteiro(scanner, "id do pedido: ");
        Pedido pedido = cadastroPedidos.buscarPedido(id);
        if (pedido == null) {
            System.out.println("pedido nao encontrado");
            return;
        }

        // cancelarPedido altera o estado; calcularRetencao aplica a regra dos 35 por cento do valor pago
        cadastroPedidos.cancelarPedido(id);
        System.out.println("pedido cancelado. retencao: " + pedido.calcularRetencao());
    }

}
