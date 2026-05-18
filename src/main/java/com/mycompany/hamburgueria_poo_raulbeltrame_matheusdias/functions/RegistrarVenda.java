package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.BuscarVendaPorPedido.buscarVendaPorPedido;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerInteiro.lerInteiro;

public class RegistrarVenda {
    public static void registrarVenda(Scanner scanner, CadastroPedidos cadastroPedidos, ControleVendas controleVendas) {
        int idPedido = lerInteiro(scanner, "id do pedido: ");
        Pedido pedido = cadastroPedidos.buscarPedido(idPedido);
        if (pedido == null) {
            System.out.println("pedido nao encontrado");
            return;
        }

        Venda vendaExistente = buscarVendaPorPedido(controleVendas, idPedido);
        if (vendaExistente != null) {
            System.out.println("este pedido ja possui venda: " + vendaExistente);
            return;
        }

        // registrarVenda cria Venda, gera Recibo e aciona ControleEstoque para baixar ingredientes
        try {
            Venda venda = controleVendas.registrarVenda(pedido);
            System.out.println("venda registrada: " + venda);
            System.out.println("recibo gerado: " + venda.getRecibo());
        } catch (IllegalArgumentException excecao) {
            System.out.println(excecao.getMessage());
        }
    }

}
