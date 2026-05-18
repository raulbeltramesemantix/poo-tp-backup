package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerTexto.lerTexto;

public class AlterarSenhaFuncionario {
    public static void alterarSenhaFuncionario(Scanner scanner, Funcionario funcionario) {
        String novaSenha = lerTexto(scanner, "nova senha: ");
        funcionario.alterarSenha(novaSenha);
        System.out.println("senha alterada");
    }

}
