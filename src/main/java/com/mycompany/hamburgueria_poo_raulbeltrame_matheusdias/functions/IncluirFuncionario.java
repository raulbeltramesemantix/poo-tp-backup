package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.ProximoIdUsuario.proximoIdUsuario;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerTexto.lerTexto;

public class IncluirFuncionario {
    public static void incluirFuncionario(Scanner scanner, CadastroUsuarios cadastroUsuarios) {
        // cadastro cria uma nova instancia de Funcionario, que herda id, nome, login e senha de Usuario
        String nome = lerTexto(scanner, "nome do funcionario: ");
        String login = lerTexto(scanner, "login: ");
        String senha = lerTexto(scanner, "senha: ");
        Funcionario funcionario = cadastroUsuarios.incluirFuncionario(proximoIdUsuario(cadastroUsuarios), nome, login, senha);
        System.out.println("funcionario incluido: " + funcionario);
    }

}
