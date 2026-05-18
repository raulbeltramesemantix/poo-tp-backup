package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.ProximoIdUsuario.proximoIdUsuario;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerTexto.lerTexto;

public class IncluirAdministrador {
    public static void incluirAdministrador(Scanner scanner, CadastroUsuarios cadastroUsuarios) {
        // o admin pode criar outro admin reaproveitando a heranca de Usuario
        String nome = lerTexto(scanner, "nome do administrador: ");
        String login = lerTexto(scanner, "login: ");
        String senha = lerTexto(scanner, "senha: ");
        Administrador administrador = cadastroUsuarios.incluirNovoAdministrador(proximoIdUsuario(cadastroUsuarios), nome, login, senha);
        System.out.println("administrador incluido: " + administrador);
    }

}
