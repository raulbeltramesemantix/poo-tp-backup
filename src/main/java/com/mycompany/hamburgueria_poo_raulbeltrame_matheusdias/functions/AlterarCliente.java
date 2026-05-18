package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerTextoComPadrao.lerTextoComPadrao;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerInteiro.lerInteiro;

public class AlterarCliente {
    public static void alterarCliente(Scanner scanner, CadastroClientes cadastroClientes) {
        int id = lerInteiro(scanner, "id do cliente: ");
        Cliente cliente = cadastroClientes.buscarCliente(id);
        if (cliente == null) {
            System.out.println("cliente nao encontrado");
            return;
        }

        String nome = lerTextoComPadrao(scanner, "nome [" + cliente.getNome() + "]: ", cliente.getNome());
        String telefone = lerTextoComPadrao(scanner, "telefone [" + cliente.getTelefone() + "]: ", cliente.getTelefone());
        String endereco = lerTextoComPadrao(scanner, "endereco [" + cliente.getEndereco() + "]: ", cliente.getEndereco());
        cadastroClientes.alterarCliente(id, nome, telefone, endereco);
        System.out.println("cliente alterado");
    }

}
