package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.MenuAdministrador.menuAdministrador;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.MenuFuncionario.menuFuncionario;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerTexto.lerTexto;

public class RealizarLogin {
    public static void realizarLogin(
            Scanner scanner,
            Autenticador autenticador,
            SistemaDelivery sistemaDelivery,
            DadosLoja dadosLoja,
            CadastroUsuarios cadastroUsuarios,
            CadastroClientes cadastroClientes,
            CadastroProdutos cadastroProdutos,
            CadastroPedidos cadastroPedidos,
            ControleEstoque controleEstoque,
            ControleVendas controleVendas,
            ControleEntregas controleEntregas,
            RelatorioVendas relatorioVendas,
            PersistenciaJSON persistenciaJSON
    ) {
        String login = lerTexto(scanner, "login: ");
        String senha = lerTexto(scanner, "senha: ");

        // uso polimorfismo o autenticador retorna Usuario, mas o objeto real pode ser Administrador ou Funcionario
        Usuario usuario = autenticador.autenticar(login, senha);
        if (usuario == null) {
            System.out.println("login invalido");
            return;
        }

        // instanceof direciona o fluxo conforme a subclasse concreta herdada de Usuario
        if (usuario instanceof Administrador administrador) {
            menuAdministrador(
                    scanner,
                    administrador,
                    sistemaDelivery,
                    dadosLoja,
                    cadastroUsuarios,
                    cadastroClientes,
                    cadastroProdutos,
                    controleEstoque,
                    controleVendas,
                    controleEntregas,
                    relatorioVendas,
                    persistenciaJSON
            );
        } else if (usuario instanceof Funcionario funcionario) {
            menuFuncionario(
                    scanner,
                    funcionario,
                    cadastroClientes,
                    cadastroProdutos,
                    cadastroPedidos,
                    controleVendas,
                    controleEntregas,
                    persistenciaJSON
            );
        }
    }

}
