package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.ListarClientes.listarClientes;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerItensPedido.lerItensPedido;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerInteiro.lerInteiro;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerDouble.lerDouble;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerDataComPadrao.lerDataComPadrao;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerHora.lerHora;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerHoraComPadrao.lerHoraComPadrao;

public class IncluirPedido {
    public static void incluirPedido(
            Scanner scanner,
            Funcionario funcionario,
            CadastroClientes cadastroClientes,
            CadastroProdutos cadastroProdutos,
            CadastroPedidos cadastroPedidos
    ) {
        listarClientes(cadastroClientes);
        int idCliente = lerInteiro(scanner, "id do cliente: ");
        Cliente cliente = cadastroClientes.buscarCliente(idCliente);
        if (cliente == null) {
            System.out.println("cliente nao encontrado");
            return;
        }

        // Pedido agrega ItemPedido; cada ItemPedido associa Produto e lista de Adicional
        List<ItemPedido> itensPedido = lerItensPedido(scanner, cadastroProdutos);
        if (itensPedido.isEmpty()) {
            System.out.println("pedido precisa ter pelo menos um item");
            return;
        }

        Date dataPedido = lerDataComPadrao(scanner, "data do pedido (AAAA-MM-DD) [hoje]: ", Date.valueOf(LocalDate.now()));
        Time horarioPedido = lerHoraComPadrao(scanner, "horario do pedido (HH:MM) [agora]: ", Time.valueOf(LocalTime.now().withSecond(0).withNano(0)));
        Time horarioEntregaPrevisto = lerHora(scanner, "horario previsto de entrega (HH:MM): ");
        double valorPago = lerDouble(scanner, "valor pago: ");

        Pedido pedido = cadastroPedidos.incluirPedido(
                cliente,
                funcionario,
                dataPedido,
                horarioPedido,
                horarioEntregaPrevisto,
                itensPedido,
                valorPago
        );
        System.out.println("pedido incluido: " + pedido);
    }

}
