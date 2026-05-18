package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerInteiro.lerInteiro;

public class RemoverAdministrador {
    public static void removerAdministrador(Scanner scanner, CadastroUsuarios cadastroUsuarios) {
        int id = lerInteiro(scanner, "id do administrador extra: ");
        Administrador removido = cadastroUsuarios.removerAdministrador(id);
        System.out.println(removido != null ? "administrador removido: " + removido : "administrador extra nao encontrado");
    }

}
