package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.ListarUsuarios.listarUsuarios;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerTextoComPadrao.lerTextoComPadrao;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerInteiro.lerInteiro;

public class AlterarAdministrador {
    public static void alterarAdministrador(Scanner scanner, CadastroUsuarios cadastroUsuarios) {
        listarUsuarios(cadastroUsuarios);
        int id = lerInteiro(scanner, "id do administrador: ");
        Administrador administrador = cadastroUsuarios.buscarAdministrador(id);
        if (administrador == null) {
            System.out.println("administrador nao encontrado");
            return;
        }

        // alteracao usa encapsulamento: os atributos privados de Usuario mudam por metodos publicos
        String nome = lerTextoComPadrao(scanner, "nome [" + administrador.getNome() + "]: ", administrador.getNome());
        String login = lerTextoComPadrao(scanner, "login [" + administrador.getLogin() + "]: ", administrador.getLogin());
        String senha = lerTextoComPadrao(scanner, "senha atual ou nova senha: ", administrador.getSenha());
        cadastroUsuarios.alterarAdministrador(administrador.getIdUsuario(), nome, login, senha);
        System.out.println("administrador alterado");
    }

}
