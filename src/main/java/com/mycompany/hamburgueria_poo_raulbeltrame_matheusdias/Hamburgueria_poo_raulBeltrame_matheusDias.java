package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.EstadoSistema;

import java.nio.file.Path;
import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.ConfigurarAdministradorPrincipal.configurarAdministradorPrincipal;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerInteiro.lerInteiro;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.MostrarMenuLogin.mostrarMenuLogin;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.RealizarLogin.realizarLogin;

public class Hamburgueria_poo_raulBeltrame_matheusDias {

    // static final constantes que compartilho pelo menu principal
    private static final String ARQUIVO_DADOS = "dados_hamburgueria.json";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //inicio o sistema e monto a composicao fixa com tres estacoes de preparo
        SistemaDelivery sistemaDelivery = new SistemaDelivery();
        sistemaDelivery.iniciarSistema();

        // uso objetos de dominio para representar os dados reais da hamburgueria
        DadosLoja dadosLoja = new DadosLoja(
                "Hmmmburguer",
                "38988889999",
                "Rua X, 10, Jardim",
                "18 00 ate 23 59",
                6.0
        );
        CadastroUsuarios cadastroUsuarios = new CadastroUsuarios();
        CadastroClientes cadastroClientes = new CadastroClientes();
        CadastroProdutos cadastroProdutos = new CadastroProdutos();
        ControleEstoque controleEstoque = new ControleEstoque(new Estoque());
        FilaPedidos filaPedidos = new FilaPedidos();
        CadastroPedidos cadastroPedidos = new CadastroPedidos(filaPedidos, cadastroClientes);
        ControleVendas controleVendas = new ControleVendas(dadosLoja, controleEstoque, cadastroClientes);
        ControleEntregas controleEntregas = new ControleEntregas();
        RelatorioVendas relatorioVendas = new RelatorioVendas(controleVendas);

        // a persistencia fica separada das entidades, mantendo a responsabilidade em uma classe propria
        PersistenciaJSON persistenciaJSON = new PersistenciaJSON(
                Path.of(ARQUIVO_DADOS),
                sistemaDelivery,
                dadosLoja,
                cadastroUsuarios,
                cadastroClientes,
                cadastroProdutos,
                cadastroPedidos,
                controleEstoque,
                controleVendas,
                controleEntregas,
                relatorioVendas
        );
        persistenciaJSON.carregarDados();

        // fico como administrador principal, sem apagar os admins extras ja cadastrados
        configurarAdministradorPrincipal(cadastroUsuarios);

        // o autenticador recebe o cadastro por associacao e devolve Usuario, a classe abstrata do diagrama
        Autenticador autenticador = new Autenticador(cadastroUsuarios);

        while (!EstadoSistema.encerrarSistema) {
            mostrarMenuLogin();
            int opcao = lerInteiro(scanner, "opcao: ");

            switch (opcao) {
                case 1 -> realizarLogin(
                        scanner,
                        autenticador,
                        sistemaDelivery,
                        dadosLoja,
                        cadastroUsuarios,
                        cadastroClientes,
                        cadastroProdutos,
                        cadastroPedidos,
                        controleEstoque,
                        controleVendas,
                        controleEntregas,
                        relatorioVendas,
                        persistenciaJSON
                );
                case 0 -> EstadoSistema.encerrarSistema = true;
                default -> System.out.println("opcao invalida");
            }
        }

        // salvo no encerramento para manter clientes, pedidos, estoque, vendas e relatorios em json
        persistenciaJSON.salvarDados();
        scanner.close();
        System.out.println("sistema encerrado. dados salvos em " + ARQUIVO_DADOS);
    }
}
