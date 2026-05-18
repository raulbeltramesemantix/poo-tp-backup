package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerInteiro.lerInteiro;

public class RemoverFuncionario {
    public static void removerFuncionario(Scanner scanner, CadastroUsuarios cadastroUsuarios) {
        int id = lerInteiro(scanner, "id do funcionario: ");
        Funcionario removido = cadastroUsuarios.removerFuncionario(id);
        System.out.println(removido != null ? "funcionario removido: " + removido : "funcionario nao encontrado");
    }

}
