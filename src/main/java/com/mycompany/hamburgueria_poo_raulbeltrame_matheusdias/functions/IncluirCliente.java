package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerTexto.lerTexto;

public class IncluirCliente {
    public static void incluirCliente(Scanner scanner, CadastroClientes cadastroClientes) {
        // Cliente guarda os dados e o historico dinamico dos pedidos feitos
        String nome = lerTexto(scanner, "nome do cliente: ");
        String telefone = lerTexto(scanner, "telefone: ");
        String endereco = lerTexto(scanner, "endereco: ");
        Cliente cliente = cadastroClientes.incluirCliente(nome, telefone, endereco);
        System.out.println("cliente incluido: " + cliente);
    }

}
