package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.AlterarSenhaFuncionario.alterarSenhaFuncionario;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.ListarClientes.listarClientes;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.ListarProdutos.listarProdutos;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.IncluirPedido.incluirPedido;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.EditarPedido.editarPedido;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.RemoverPedido.removerPedido;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.CancelarPedido.cancelarPedido;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.PesquisarPedidos.pesquisarPedidos;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.RegistrarVenda.registrarVenda;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.InformarEntregaWhatsapp.informarEntregaWhatsapp;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.ConsultarProximoPedido.consultarProximoPedido;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.ListarPedidos.listarPedidos;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.SalvarDados.salvarDados;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerInteiro.lerInteiro;

public class MenuFuncionario {
    public static void menuFuncionario(
            Scanner scanner,
            Funcionario funcionario,
            CadastroClientes cadastroClientes,
            CadastroProdutos cadastroProdutos,
            CadastroPedidos cadastroPedidos,
            ControleVendas controleVendas,
            ControleEntregas controleEntregas,
            PersistenciaJSON persistenciaJSON
    ) {
        boolean voltarLogin = false;

        while (!voltarLogin && !EstadoSistema.encerrarSistema) {
            System.out.println();
            System.out.println("---- menu funcionario ----");
            System.out.println("1 - alterar minha senha");
            System.out.println("2 - listar clientes");
            System.out.println("3 - listar produtos");
            System.out.println("4 - incluir pedido");
            System.out.println("5 - editar pedido");
            System.out.println("6 - remover pedido");
            System.out.println("7 - cancelar pedido");
            System.out.println("8 - pesquisar pedidos por data e hora");
            System.out.println("9 - registrar venda e gerar recibo");
            System.out.println("10 - informar entrega via whatsapp");
            System.out.println("11 - consultar proximo pedido da fila");
            System.out.println("12 - listar pedidos");
            System.out.println("13 - salvar dados agora");
            System.out.println("0 - sair para login");
            System.out.println("99 - encerrar sistema");

            int opcao = lerInteiro(scanner, "opcao: ");

            switch (opcao) {
                case 1 -> alterarSenhaFuncionario(scanner, funcionario);
                case 2 -> listarClientes(cadastroClientes);
                case 3 -> listarProdutos(cadastroProdutos);
                case 4 -> incluirPedido(scanner, funcionario, cadastroClientes, cadastroProdutos, cadastroPedidos);
                case 5 -> editarPedido(scanner, funcionario, cadastroProdutos, cadastroPedidos);
                case 6 -> removerPedido(scanner, cadastroPedidos);
                case 7 -> cancelarPedido(scanner, cadastroPedidos);
                case 8 -> pesquisarPedidos(scanner, cadastroPedidos);
                case 9 -> registrarVenda(scanner, cadastroPedidos, controleVendas);
                case 10 -> informarEntregaWhatsapp(scanner, cadastroClientes, cadastroPedidos, controleVendas, controleEntregas);
                case 11 -> consultarProximoPedido(cadastroPedidos);
                case 12 -> listarPedidos(cadastroPedidos);
                case 13 -> salvarDados(persistenciaJSON);
                case 0 -> voltarLogin = true;
                case 99 -> EstadoSistema.encerrarSistema = true;
                default -> System.out.println("opcao invalida");
            }
        }
    }

}
