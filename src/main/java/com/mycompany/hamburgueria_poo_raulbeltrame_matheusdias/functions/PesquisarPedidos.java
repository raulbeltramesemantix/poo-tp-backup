package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerDataOpcional.lerDataOpcional;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerHoraOpcional.lerHoraOpcional;

public class PesquisarPedidos {
    public static void pesquisarPedidos(Scanner scanner, CadastroPedidos cadastroPedidos) {
        Date dataInicio = lerDataOpcional(scanner, "data inicio (AAAA-MM-DD, vazio para ignorar): ");
        Date dataFim = lerDataOpcional(scanner, "data fim (AAAA-MM-DD, vazio para ignorar): ");
        Time horarioInicio = lerHoraOpcional(scanner, "horario inicio (HH:MM, vazio para ignorar): ");
        Time horarioFim = lerHoraOpcional(scanner, "horario fim (HH:MM, vazio para ignorar): ");
        List<Pedido> pedidos = cadastroPedidos.pesquisarPedidos(dataInicio, dataFim, horarioInicio, horarioFim);
        for (Pedido pedido : pedidos) {
            System.out.println(pedido);
        }
        System.out.println("quantidade encontrada: " + pedidos.size());
    }

}
