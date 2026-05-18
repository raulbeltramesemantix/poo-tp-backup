package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.ListarUsuarios.listarUsuarios;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.IncluirFuncionario.incluirFuncionario;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.RemoverFuncionario.removerFuncionario;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.AlterarFuncionario.alterarFuncionario;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.IncluirAdministrador.incluirAdministrador;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.RemoverAdministrador.removerAdministrador;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.AlterarAdministrador.alterarAdministrador;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.ListarClientes.listarClientes;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.IncluirCliente.incluirCliente;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.AlterarCliente.alterarCliente;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.RemoverCliente.removerCliente;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.ListarProdutos.listarProdutos;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.IncluirProduto.incluirProduto;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.AlterarProduto.alterarProduto;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.RemoverProduto.removerProduto;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.ListarEstoque.listarEstoque;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.IncluirIngrediente.incluirIngrediente;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.ReceberFornecedor.receberFornecedor;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.CadastrarMotoqueiro.cadastrarMotoqueiro;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.CadastrarRegiaoEntrega.cadastrarRegiaoEntrega;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.MenuRelatorios.menuRelatorios;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.AlterarDadosLoja.alterarDadosLoja;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.MostrarResumoSistema.mostrarResumoSistema;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.SalvarDados.salvarDados;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerInteiro.lerInteiro;

public class MenuAdministrador {
    public static void menuAdministrador(
            Scanner scanner,
            Administrador administrador,
            SistemaDelivery sistemaDelivery,
            DadosLoja dadosLoja,
            CadastroUsuarios cadastroUsuarios,
            CadastroClientes cadastroClientes,
            CadastroProdutos cadastroProdutos,
            ControleEstoque controleEstoque,
            ControleVendas controleVendas,
            ControleEntregas controleEntregas,
            RelatorioVendas relatorioVendas,
            PersistenciaJSON persistenciaJSON
    ) {
        boolean voltarLogin = false;

        while (!voltarLogin && !EstadoSistema.encerrarSistema) {
            System.out.println();
            System.out.println("---- menu admin ----");
            System.out.println("1 - listar usuarios");
            System.out.println("2 - incluir funcionario");
            System.out.println("3 - remover funcionario");
            System.out.println("4 - alterar funcionario");
            System.out.println("5 - alterar dados de administrador");
            System.out.println("6 - listar clientes");
            System.out.println("7 - incluir cliente");
            System.out.println("8 - alterar cliente");
            System.out.println("9 - remover cliente");
            System.out.println("10 - listar produtos");
            System.out.println("11 - incluir produto");
            System.out.println("12 - alterar produto");
            System.out.println("13 - remover produto");
            System.out.println("14 - listar estoque");
            System.out.println("15 - incluir ingrediente");
            System.out.println("16 - receber fornecedor");
            System.out.println("17 - cadastrar motoqueiro");
            System.out.println("18 - cadastrar regiao de entrega");
            System.out.println("19 - emitir relatorios");
            System.out.println("20 - alterar dados da loja");
            System.out.println("21 - mostrar resumo do sistema");
            System.out.println("22 - salvar dados agora");
            System.out.println("23 - incluir administrador");
            System.out.println("24 - remover administrador");
            System.out.println("0 - sair para login");
            System.out.println("99 - encerrar sistema");

            int opcao = lerInteiro(scanner, "opcao: ");

            switch (opcao) {
                case 1 -> listarUsuarios(cadastroUsuarios);
                case 2 -> incluirFuncionario(scanner, cadastroUsuarios);
                case 3 -> removerFuncionario(scanner, cadastroUsuarios);
                case 4 -> alterarFuncionario(scanner, cadastroUsuarios);
                case 5 -> alterarAdministrador(scanner, cadastroUsuarios);
                case 6 -> listarClientes(cadastroClientes);
                case 7 -> incluirCliente(scanner, cadastroClientes);
                case 8 -> alterarCliente(scanner, cadastroClientes);
                case 9 -> removerCliente(scanner, cadastroClientes);
                case 10 -> listarProdutos(cadastroProdutos);
                case 11 -> incluirProduto(scanner, cadastroProdutos, controleEstoque);
                case 12 -> alterarProduto(scanner, cadastroProdutos, controleEstoque);
                case 13 -> removerProduto(scanner, cadastroProdutos);
                case 14 -> listarEstoque(controleEstoque);
                case 15 -> incluirIngrediente(scanner, controleEstoque);
                case 16 -> receberFornecedor(scanner, controleEstoque);
                case 17 -> cadastrarMotoqueiro(scanner, controleEntregas);
                case 18 -> cadastrarRegiaoEntrega(scanner, controleEntregas);
                case 19 -> menuRelatorios(scanner, relatorioVendas);
                case 20 -> alterarDadosLoja(scanner, dadosLoja);
                case 21 -> mostrarResumoSistema(sistemaDelivery, cadastroClientes, cadastroProdutos, controleVendas, controleEntregas);
                case 22 -> salvarDados(persistenciaJSON);
                case 23 -> incluirAdministrador(scanner, cadastroUsuarios);
                case 24 -> removerAdministrador(scanner, cadastroUsuarios);
                case 0 -> voltarLogin = true;
                case 99 -> EstadoSistema.encerrarSistema = true;
                default -> System.out.println("opcao invalida");
            }
        }
    }

}
