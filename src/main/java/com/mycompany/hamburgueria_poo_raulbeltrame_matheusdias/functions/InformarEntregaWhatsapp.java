package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.BuscarVendaPorPedido.buscarVendaPorPedido;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.ProximoIdEntrega.proximoIdEntrega;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerInteiro.lerInteiro;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerBoolean.lerBoolean;

public class InformarEntregaWhatsapp {
    public static void informarEntregaWhatsapp(
            Scanner scanner,
            CadastroClientes cadastroClientes,
            CadastroPedidos cadastroPedidos,
            ControleVendas controleVendas,
            ControleEntregas controleEntregas
    ) {
        int idPedido = lerInteiro(scanner, "id do pedido vendido: ");
        Pedido pedido = cadastroPedidos.buscarPedido(idPedido);
        Venda venda = buscarVendaPorPedido(controleVendas, idPedido);
        if (pedido == null || venda == null) {
            System.out.println("pedido precisa existir e ter venda registrada");
            return;
        }

        Cliente cliente = cadastroClientes.buscarCliente(pedido.getIdCliente());
        if (cliente == null) {
            System.out.println("cliente do pedido nao encontrado");
            return;
        }

        if (controleEntregas.getMotoqueiros().isEmpty() || controleEntregas.getRegioesEntrega().isEmpty()) {
            System.out.println("cadastre motoqueiros e regioes antes de informar entrega");
            return;
        }

        Entrega entrega = venda.getEntrega();
        if (entrega == null) {
            entrega = new Entrega(proximoIdEntrega(controleVendas), "em rota", false);
            venda.setEntrega(entrega);
        }

        // Entrega associa Motoqueiro e RegiaoEntrega, preservando a responsabilidade do WhatsApp no Motoqueiro
        entrega.setRegiaoEntrega(controleEntregas.definirRegiao(cliente));
        Motoqueiro motoqueiro = controleEntregas.alocarMotoqueiro(entrega);
        if (motoqueiro == null) {
            System.out.println("nenhum motoqueiro ativo disponivel");
            return;
        }

        pedido.setStatus(StatusPedido.SAIU_PARA_ENTREGA);
        controleEntregas.informarEntregaWhatsapp(entrega);
        if (lerBoolean(scanner, "confirmar entrega agora? (s/n): ")) {
            entrega.confirmarEntrega();
            pedido.setStatus(StatusPedido.ENTREGUE);
        }
        System.out.println("entrega atualizada: " + entrega);
    }

}
