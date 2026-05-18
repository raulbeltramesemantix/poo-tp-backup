package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.sql.Time;
import java.util.List;
import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerItensPedido.lerItensPedido;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerInteiro.lerInteiro;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerDoubleComPadrao.lerDoubleComPadrao;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerHoraComPadrao.lerHoraComPadrao;

public class EditarPedido {
    public static void editarPedido(
            Scanner scanner,
            Funcionario funcionario,
            CadastroProdutos cadastroProdutos,
            CadastroPedidos cadastroPedidos
    ) {
        int idPedido = lerInteiro(scanner, "id do pedido: ");
        Pedido pedido = cadastroPedidos.buscarPedido(idPedido);
        if (pedido == null) {
            System.out.println("pedido nao encontrado");
            return;
        }

        List<ItemPedido> itensPedido = lerItensPedido(scanner, cadastroProdutos);
        if (itensPedido.isEmpty()) {
            System.out.println("edicao cancelada porque nenhum item foi informado");
            return;
        }

        Time horarioEntregaPrevisto = lerHoraComPadrao(scanner, "horario previsto de entrega (HH:MM): ", pedido.getHorarioEntregaPrevisto());
        double valorPago = lerDoubleComPadrao(scanner, "valor pago [" + pedido.getValorPago() + "]: ", pedido.getValorPago());
        cadastroPedidos.editarPedido(idPedido, itensPedido, horarioEntregaPrevisto, valorPago, funcionario);
        System.out.println("pedido editado");
    }

}
