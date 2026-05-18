package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerInteiro.lerInteiro;

public class RemoverCliente {
    public static void removerCliente(Scanner scanner, CadastroClientes cadastroClientes) {
        int id = lerInteiro(scanner, "id do cliente: ");
        Cliente removido = cadastroClientes.removerCliente(id);
        System.out.println(removido != null ? "cliente removido: " + removido : "cliente nao encontrado");
    }

}
