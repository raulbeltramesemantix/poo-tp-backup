package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerTextoComPadrao.lerTextoComPadrao;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerInteiro.lerInteiro;

public class AlterarFuncionario {
    public static void alterarFuncionario(Scanner scanner, CadastroUsuarios cadastroUsuarios) {
        int id = lerInteiro(scanner, "id do funcionario: ");
        Funcionario funcionario = cadastroUsuarios.buscarFuncionario(id);
        if (funcionario == null) {
            System.out.println("funcionario nao encontrado");
            return;
        }

        String nome = lerTextoComPadrao(scanner, "nome [" + funcionario.getNome() + "]: ", funcionario.getNome());
        String login = lerTextoComPadrao(scanner, "login [" + funcionario.getLogin() + "]: ", funcionario.getLogin());
        String senha = lerTextoComPadrao(scanner, "senha atual ou nova senha: ", funcionario.getSenha());
        cadastroUsuarios.alterarFuncionario(id, nome, login, senha);
        System.out.println("funcionario alterado");
    }

}
