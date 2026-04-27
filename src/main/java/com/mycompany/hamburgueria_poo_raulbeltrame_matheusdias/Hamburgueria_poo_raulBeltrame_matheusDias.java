package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

import java.nio.file.Path;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Hamburgueria_poo_raulBeltrame_matheusDias {

    // static final: constantes compartilhadas pelo menu principal
    private static final String ARQUIVO_DADOS = "dados_hamburgueria.json";
    private static final String LOGIN_UNICO = "Raul";
    private static final String SENHA_UNICA = "6504";
    // static: controla o loop principal da aplicacao
    private static boolean encerrarSistema;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //inicio o sistema e monto a composicao fixa com tres estacoes de preparo
        SistemaDelivery sistemaDelivery = new SistemaDelivery();
        sistemaDelivery.iniciarSistema();

        // uso objetos de dominio para representar os dados reais da hamburgueria
        DadosLoja dadosLoja = new DadosLoja(
                "Hamburgueria Central",
                "38999990000",
                "Rua Principal 10 Centro",
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

        // deixo Raul como administrador principal, sem apagar os admins extras ja cadastrados
        configurarAdministradorPrincipal(cadastroUsuarios);

        // o autenticador recebe o cadastro por associacao e devolve Usuario, a classe abstrata do diagrama
        Autenticador autenticador = new Autenticador(cadastroUsuarios);

        while (!encerrarSistema) {
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
                case 0 -> encerrarSistema = true;
                default -> System.out.println("opcao invalida");
            }
        }

        // salvo no encerramento para manter clientes, pedidos, estoque, vendas e relatorios em json
        persistenciaJSON.salvarDados();
        scanner.close();
        System.out.println("sistema encerrado. dados salvos em " + ARQUIVO_DADOS);
    }

    private static void mostrarMenuLogin() {
        System.out.println();
        System.out.println("---- hamburgueria delivery ----");
        System.out.println("1 - entrar com login e senha");
        System.out.println("0 - encerrar sistema");
    }

    private static void realizarLogin(
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

    private static void menuAdministrador(
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

        while (!voltarLogin && !encerrarSistema) {
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
                case 99 -> encerrarSistema = true;
                default -> System.out.println("opcao invalida");
            }
        }
    }


    private static void menuFuncionario(
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

        while (!voltarLogin && !encerrarSistema) {
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
                case 99 -> encerrarSistema = true;
                default -> System.out.println("opcao invalida");
            }
        }
    }

    private static void configurarAdministradorPrincipal(CadastroUsuarios cadastroUsuarios) {
        int idUsuario = cadastroUsuarios.getAdministrador() != null
                ? cadastroUsuarios.getAdministrador().getIdUsuario()
                : proximoIdUsuario(cadastroUsuarios);
        cadastroUsuarios.setAdministrador(new Administrador(idUsuario, "Raul", LOGIN_UNICO, SENHA_UNICA));
    }

    private static int proximoIdUsuario(CadastroUsuarios cadastroUsuarios) {
        int maiorId = 0;
        if (cadastroUsuarios.getAdministrador() != null) {
            maiorId = cadastroUsuarios.getAdministrador().getIdUsuario();
        }
        for (Administrador administrador : cadastroUsuarios.getAdministradores()) {
            if (administrador.getIdUsuario() > maiorId) {
                maiorId = administrador.getIdUsuario();
            }
        }
        for (Funcionario funcionario : cadastroUsuarios.getFuncionarios()) {
            if (funcionario.getIdUsuario() > maiorId) {
                maiorId = funcionario.getIdUsuario();
            }
        }
        return maiorId + 1;
    }

    private static void listarUsuarios(CadastroUsuarios cadastroUsuarios) {
        System.out.println("administrador:");
        System.out.println(cadastroUsuarios.getAdministrador());
        System.out.println("administradores extras:");
        for (Administrador administrador : cadastroUsuarios.getAdministradores()) {
            System.out.println(administrador);
        }
        System.out.println("funcionarios:");
        for (Funcionario funcionario : cadastroUsuarios.getFuncionarios()) {
            System.out.println(funcionario);
        }
    }

    private static void incluirFuncionario(Scanner scanner, CadastroUsuarios cadastroUsuarios) {
        // cadastro cria uma nova instancia de Funcionario, que herda id, nome, login e senha de Usuario
        String nome = lerTexto(scanner, "nome do funcionario: ");
        String login = lerTexto(scanner, "login: ");
        String senha = lerTexto(scanner, "senha: ");
        Funcionario funcionario = cadastroUsuarios.incluirFuncionario(proximoIdUsuario(cadastroUsuarios), nome, login, senha);
        System.out.println("funcionario incluido: " + funcionario);
    }

    private static void removerFuncionario(Scanner scanner, CadastroUsuarios cadastroUsuarios) {
        int id = lerInteiro(scanner, "id do funcionario: ");
        Funcionario removido = cadastroUsuarios.removerFuncionario(id);
        System.out.println(removido != null ? "funcionario removido: " + removido : "funcionario nao encontrado");
    }

    private static void alterarFuncionario(Scanner scanner, CadastroUsuarios cadastroUsuarios) {
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

    private static void incluirAdministrador(Scanner scanner, CadastroUsuarios cadastroUsuarios) {
        // o admin pode criar outro admin reaproveitando a heranca de Usuario
        String nome = lerTexto(scanner, "nome do administrador: ");
        String login = lerTexto(scanner, "login: ");
        String senha = lerTexto(scanner, "senha: ");
        Administrador administrador = cadastroUsuarios.incluirNovoAdministrador(proximoIdUsuario(cadastroUsuarios), nome, login, senha);
        System.out.println("administrador incluido: " + administrador);
    }

    private static void removerAdministrador(Scanner scanner, CadastroUsuarios cadastroUsuarios) {
        int id = lerInteiro(scanner, "id do administrador extra: ");
        Administrador removido = cadastroUsuarios.removerAdministrador(id);
        System.out.println(removido != null ? "administrador removido: " + removido : "administrador extra nao encontrado");
    }

    private static void alterarAdministrador(Scanner scanner, CadastroUsuarios cadastroUsuarios) {
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

    private static void alterarSenhaFuncionario(Scanner scanner, Funcionario funcionario) {
        String novaSenha = lerTexto(scanner, "nova senha: ");
        funcionario.alterarSenha(novaSenha);
        System.out.println("senha alterada");
    }

    private static void listarClientes(CadastroClientes cadastroClientes) {
        if (cadastroClientes.getClientes().isEmpty()) {
            System.out.println("nenhum cliente cadastrado");
            return;
        }
        for (Cliente cliente : cadastroClientes.getClientes()) {
            System.out.println(cliente);
        }
    }

    private static void incluirCliente(Scanner scanner, CadastroClientes cadastroClientes) {
        // Cliente guarda os dados e o historico dinamico dos pedidos feitos
        String nome = lerTexto(scanner, "nome do cliente: ");
        String telefone = lerTexto(scanner, "telefone: ");
        String endereco = lerTexto(scanner, "endereco: ");
        Cliente cliente = cadastroClientes.incluirCliente(nome, telefone, endereco);
        System.out.println("cliente incluido: " + cliente);
    }

    private static void alterarCliente(Scanner scanner, CadastroClientes cadastroClientes) {
        int id = lerInteiro(scanner, "id do cliente: ");
        Cliente cliente = cadastroClientes.buscarCliente(id);
        if (cliente == null) {
            System.out.println("cliente nao encontrado");
            return;
        }

        String nome = lerTextoComPadrao(scanner, "nome [" + cliente.getNome() + "]: ", cliente.getNome());
        String telefone = lerTextoComPadrao(scanner, "telefone [" + cliente.getTelefone() + "]: ", cliente.getTelefone());
        String endereco = lerTextoComPadrao(scanner, "endereco [" + cliente.getEndereco() + "]: ", cliente.getEndereco());
        cadastroClientes.alterarCliente(id, nome, telefone, endereco);
        System.out.println("cliente alterado");
    }

    private static void removerCliente(Scanner scanner, CadastroClientes cadastroClientes) {
        int id = lerInteiro(scanner, "id do cliente: ");
        Cliente removido = cadastroClientes.removerCliente(id);
        System.out.println(removido != null ? "cliente removido: " + removido : "cliente nao encontrado");
    }

    private static void listarProdutos(CadastroProdutos cadastroProdutos) {
        if (cadastroProdutos.getProdutos().isEmpty()) {
            System.out.println("nenhum produto cadastrado");
            return;
        }
        for (Produto produto : cadastroProdutos.getProdutos()) {
            System.out.println(produto);
        }
    }

    private static void incluirProduto(Scanner scanner, CadastroProdutos cadastroProdutos, ControleEstoque controleEstoque) {
        // Produto compoe DescricaoProduto e associa uma receita por ingrediente para baixar estoque
        String nome = lerTexto(scanner, "nome do produto: ");
        boolean ativo = lerBoolean(scanner, "produto ativo? (s/n): ");
        String descricaoDetalhada = lerTexto(scanner, "descricao detalhada: ");
        double valor = lerDouble(scanner, "valor: ");
        Map<Integer, Double> receita = lerReceitaProduto(scanner, controleEstoque);
        Produto produto = cadastroProdutos.incluirProduto(nome, ativo, descricaoDetalhada, valor, receita);
        System.out.println("produto incluido: " + produto);
    }

    private static void alterarProduto(Scanner scanner, CadastroProdutos cadastroProdutos, ControleEstoque controleEstoque) {
        int id = lerInteiro(scanner, "id do produto: ");
        Produto produto = cadastroProdutos.buscarProduto(id);
        if (produto == null) {
            System.out.println("produto nao encontrado");
            return;
        }

        String nome = lerTextoComPadrao(scanner, "nome [" + produto.getNome() + "]: ", produto.getNome());
        boolean ativo = lerBoolean(scanner, "produto ativo? (s/n): ");
        String descricaoAtual = produto.getDescricaoProduto() != null ? produto.getDescricaoProduto().getDescricaoDetalhada() : "";
        double valorAtual = produto.getDescricaoProduto() != null ? produto.getDescricaoProduto().getValor() : 0.0;
        String descricaoDetalhada = lerTextoComPadrao(scanner, "descricao [" + descricaoAtual + "]: ", descricaoAtual);
        double valor = lerDoubleComPadrao(scanner, "valor [" + valorAtual + "]: ", valorAtual);
        Map<Integer, Double> receita = lerReceitaProduto(scanner, controleEstoque);
        cadastroProdutos.alterarProduto(id, nome, ativo, descricaoDetalhada, valor, receita);
        System.out.println("produto alterado");
    }

    private static void removerProduto(Scanner scanner, CadastroProdutos cadastroProdutos) {
        int id = lerInteiro(scanner, "id do produto: ");
        Produto removido = cadastroProdutos.removerProduto(id);
        System.out.println(removido != null ? "produto removido: " + removido : "produto nao encontrado");
    }

    private static Map<Integer, Double> lerReceitaProduto(Scanner scanner, ControleEstoque controleEstoque) {
        Map<Integer, Double> receita = new LinkedHashMap<>();

        System.out.println("informe os ingredientes da receita. digite 0 para terminar");
        listarEstoque(controleEstoque);
        while (true) {
            int idIngrediente = lerInteiro(scanner, "id do ingrediente: ");
            if (idIngrediente == 0) {
                break;
            }
            double quantidade = lerDouble(scanner, "quantidade usada por unidade do produto: ");
            receita.put(idIngrediente, quantidade);
        }

        return receita;
    }

    private static void listarEstoque(ControleEstoque controleEstoque) {
        List<Ingrediente> ingredientes = controleEstoque.verificarIngredientes();
        if (ingredientes.isEmpty()) {
            System.out.println("nenhum ingrediente cadastrado no estoque");
            return;
        }
        for (Ingrediente ingrediente : ingredientes) {
            System.out.println(ingrediente);
        }
    }

    private static void incluirIngrediente(Scanner scanner, ControleEstoque controleEstoque) {
        // Ingrediente mantem unidade de medida e quantidade minima, como pedido no PDF
        int id = proximoIdIngrediente(controleEstoque);
        String nome = lerTexto(scanner, "nome do ingrediente: ");
        double quantidadeAtual = lerDouble(scanner, "quantidade atual: ");
        String unidadeMedida = lerTexto(scanner, "unidade de medida: ");
        double quantidadeMinima = lerDouble(scanner, "quantidade minima para alerta: ");
        Ingrediente ingrediente = new Ingrediente(id, nome, quantidadeAtual, unidadeMedida, quantidadeMinima);
        controleEstoque.getEstoque().getIngredientes().add(ingrediente);
        System.out.println("ingrediente incluido: " + ingrediente);
    }

    private static int proximoIdIngrediente(ControleEstoque controleEstoque) {
        int maiorId = 0;
        for (Ingrediente ingrediente : controleEstoque.verificarIngredientes()) {
            if (ingrediente.getIdIngrediente() > maiorId) {
                maiorId = ingrediente.getIdIngrediente();
            }
        }
        return maiorId + 1;
    }

    private static void receberFornecedor(Scanner scanner, ControleEstoque controleEstoque) {
        // RecebimentoEstoque registra a entrada e repoe a quantidade do Ingrediente
        listarEstoque(controleEstoque);
        int idFornecedor = lerInteiro(scanner, "id do fornecedor: ");
        String nomeFornecedor = lerTexto(scanner, "nome do fornecedor: ");
        String telefoneFornecedor = lerTexto(scanner, "telefone do fornecedor: ");
        int idIngrediente = lerInteiro(scanner, "id do ingrediente recebido: ");
        double quantidade = lerDouble(scanner, "quantidade recebida: ");
        Fornecedor fornecedor = new Fornecedor(idFornecedor, nomeFornecedor, telefoneFornecedor);
        RecebimentoEstoque recebimento = controleEstoque.receberFornecedor(fornecedor, idIngrediente, quantidade);
        System.out.println("recebimento registrado: " + recebimento);
    }

    private static void cadastrarMotoqueiro(Scanner scanner, ControleEntregas controleEntregas) {
        int id = proximoIdMotoqueiro(controleEntregas);
        String nome = lerTexto(scanner, "nome do motoqueiro: ");
        String telefone = lerTexto(scanner, "telefone: ");
        boolean ativo = lerBoolean(scanner, "motoqueiro ativo? (s/n): ");
        Motoqueiro motoqueiro = new Motoqueiro(id, nome, telefone, ativo);
        controleEntregas.getMotoqueiros().add(motoqueiro);
        System.out.println("motoqueiro cadastrado: " + motoqueiro);
    }

    private static int proximoIdMotoqueiro(ControleEntregas controleEntregas) {
        int maiorId = 0;
        for (Motoqueiro motoqueiro : controleEntregas.getMotoqueiros()) {
            if (motoqueiro.getIdMotoqueiro() > maiorId) {
                maiorId = motoqueiro.getIdMotoqueiro();
            }
        }
        return maiorId + 1;
    }

    private static void cadastrarRegiaoEntrega(Scanner scanner, ControleEntregas controleEntregas) {
        int id = proximoIdRegiao(controleEntregas);
        String nomeRegiao = lerTexto(scanner, "nome da regiao: ");
        String bairros = lerTexto(scanner, "bairros atendidos: ");
        RegiaoEntrega regiaoEntrega = new RegiaoEntrega(id, nomeRegiao, bairros);
        controleEntregas.getRegioesEntrega().add(regiaoEntrega);
        System.out.println("regiao cadastrada: " + regiaoEntrega);
    }

    private static int proximoIdRegiao(ControleEntregas controleEntregas) {
        int maiorId = 0;
        for (RegiaoEntrega regiaoEntrega : controleEntregas.getRegioesEntrega()) {
            if (regiaoEntrega.getIdRegiao() > maiorId) {
                maiorId = regiaoEntrega.getIdRegiao();
            }
        }
        return maiorId + 1;
    }

    private static void menuRelatorios(Scanner scanner, RelatorioVendas relatorioVendas) {
        System.out.println();
        System.out.println("---- relatorios ----");
        System.out.println("1 - relatorio do dia");
        System.out.println("2 - relatorio do mes");
        System.out.println("3 - balanco mensal");
        int opcao = lerInteiro(scanner, "opcao: ");

        switch (opcao) {
            case 1 -> {
                Date data = lerData(scanner, "data (AAAA-MM-DD): ");
                relatorioVendas.emitirRelatorioDia(data);
            }
            case 2 -> {
                int mes = lerInteiro(scanner, "mes: ");
                int ano = lerInteiro(scanner, "ano: ");
                relatorioVendas.emitirRelatorioMes(mes, ano);
            }
            case 3 -> {
                int mes = lerInteiro(scanner, "mes: ");
                int ano = lerInteiro(scanner, "ano: ");
                EstatisticaVendas estatisticaVendas = relatorioVendas.gerarBalancoMensal(mes, ano);
                System.out.println(estatisticaVendas);
            }
            default -> System.out.println("opcao invalida");
        }
    }

    private static void alterarDadosLoja(Scanner scanner, DadosLoja dadosLoja) {
        String nomeLoja = lerTextoComPadrao(scanner, "nome da loja [" + dadosLoja.getNomeLoja() + "]: ", dadosLoja.getNomeLoja());
        String telefone = lerTextoComPadrao(scanner, "telefone [" + dadosLoja.getTelefone() + "]: ", dadosLoja.getTelefone());
        String endereco = lerTextoComPadrao(scanner, "endereco [" + dadosLoja.getEndereco() + "]: ", dadosLoja.getEndereco());
        String horario = lerTextoComPadrao(scanner, "horario [" + dadosLoja.getHorarioFuncionamento() + "]: ", dadosLoja.getHorarioFuncionamento());
        double taxa = lerDoubleComPadrao(scanner, "taxa de entrega [" + dadosLoja.getTaxaEntrega() + "]: ", dadosLoja.getTaxaEntrega());
        dadosLoja.alterarDadosLoja(nomeLoja, telefone, endereco, horario, taxa);
        System.out.println("dados da loja alterados");
    }

    private static void mostrarResumoSistema(
            SistemaDelivery sistemaDelivery,
            CadastroClientes cadastroClientes,
            CadastroProdutos cadastroProdutos,
            ControleVendas controleVendas,
            ControleEntregas controleEntregas
    ) {
        System.out.println(sistemaDelivery);
        System.out.println("clientes cadastrados: " + cadastroClientes.getClientes().size());
        System.out.println("produtos cadastrados: " + cadastroProdutos.getProdutos().size());
        System.out.println("vendas registradas: " + controleVendas.getVendas().size());
        System.out.println("motoqueiros cadastrados: " + controleEntregas.getMotoqueiros().size());
        System.out.println("regioes cadastradas: " + controleEntregas.getRegioesEntrega().size());
    }

    private static void incluirPedido(
            Scanner scanner,
            Funcionario funcionario,
            CadastroClientes cadastroClientes,
            CadastroProdutos cadastroProdutos,
            CadastroPedidos cadastroPedidos
    ) {
        listarClientes(cadastroClientes);
        int idCliente = lerInteiro(scanner, "id do cliente: ");
        Cliente cliente = cadastroClientes.buscarCliente(idCliente);
        if (cliente == null) {
            System.out.println("cliente nao encontrado");
            return;
        }

        // Pedido agrega ItemPedido; cada ItemPedido associa Produto e lista de Adicional
        List<ItemPedido> itensPedido = lerItensPedido(scanner, cadastroProdutos);
        if (itensPedido.isEmpty()) {
            System.out.println("pedido precisa ter pelo menos um item");
            return;
        }

        Date dataPedido = lerDataComPadrao(scanner, "data do pedido (AAAA-MM-DD) [hoje]: ", Date.valueOf(LocalDate.now()));
        Time horarioPedido = lerHoraComPadrao(scanner, "horario do pedido (HH:MM) [agora]: ", Time.valueOf(LocalTime.now().withSecond(0).withNano(0)));
        Time horarioEntregaPrevisto = lerHora(scanner, "horario previsto de entrega (HH:MM): ");
        double valorPago = lerDouble(scanner, "valor pago: ");

        Pedido pedido = cadastroPedidos.incluirPedido(
                cliente,
                funcionario,
                dataPedido,
                horarioPedido,
                horarioEntregaPrevisto,
                itensPedido,
                valorPago
        );
        System.out.println("pedido incluido: " + pedido);
    }

    private static void editarPedido(
            Scanner scanner,
            Funcionario funcionario,
            CadastroProdutos cadastroProdutos,
            CadastroPedidos cadastroPedidos
    ) {
        int idPedido = lerInteiro(scanner, "id do pedido: ");
        Pedido pedido = cadastroPedidos.buscarPedido(idPedido);
        if (pedido == null) {
            System.out.println("pedido nao encontrado");
            return;
        }

        List<ItemPedido> itensPedido = lerItensPedido(scanner, cadastroProdutos);
        if (itensPedido.isEmpty()) {
            System.out.println("edicao cancelada porque nenhum item foi informado");
            return;
        }

        Time horarioEntregaPrevisto = lerHoraComPadrao(scanner, "horario previsto de entrega (HH:MM): ", pedido.getHorarioEntregaPrevisto());
        double valorPago = lerDoubleComPadrao(scanner, "valor pago [" + pedido.getValorPago() + "]: ", pedido.getValorPago());
        cadastroPedidos.editarPedido(idPedido, itensPedido, horarioEntregaPrevisto, valorPago, funcionario);
        System.out.println("pedido editado");
    }

    private static List<ItemPedido> lerItensPedido(Scanner scanner, CadastroProdutos cadastroProdutos) {
        List<ItemPedido> itensPedido = new ArrayList<>();
        if (cadastroProdutos.getProdutos().isEmpty()) {
            System.out.println("cadastre produtos antes de criar pedidos");
            return itensPedido;
        }

        while (true) {
            listarProdutos(cadastroProdutos);
            int idProduto = lerInteiro(scanner, "id do produto (0 para terminar): ");
            if (idProduto == 0) {
                break;
            }

            Produto produto = cadastroProdutos.buscarProduto(idProduto);
            if (produto == null) {
                System.out.println("produto nao encontrado");
                continue;
            }

            int quantidade = lerInteiro(scanner, "quantidade: ");
            List<Adicional> adicionais = lerAdicionais(scanner);
            itensPedido.add(new ItemPedido(produto, quantidade, adicionais));
        }

        return itensPedido;
    }

    private static List<Adicional> lerAdicionais(Scanner scanner) {
        List<Adicional> adicionais = new ArrayList<>();
        while (lerBoolean(scanner, "adicionar adicional? (s/n): ")) {
            int idAdicional = lerInteiro(scanner, "id do adicional: ");
            String nome = lerTexto(scanner, "nome do adicional: ");
            double valor = lerDouble(scanner, "valor do adicional: ");
            adicionais.add(new Adicional(idAdicional, nome, valor));
        }
        return adicionais;
    }

    private static void removerPedido(Scanner scanner, CadastroPedidos cadastroPedidos) {
        int id = lerInteiro(scanner, "id do pedido: ");
        Pedido removido = cadastroPedidos.removerPedido(id);
        System.out.println(removido != null ? "pedido removido: " + removido : "pedido nao encontrado");
    }

    private static void cancelarPedido(Scanner scanner, CadastroPedidos cadastroPedidos) {
        int id = lerInteiro(scanner, "id do pedido: ");
        Pedido pedido = cadastroPedidos.buscarPedido(id);
        if (pedido == null) {
            System.out.println("pedido nao encontrado");
            return;
        }

        // cancelarPedido altera o estado; calcularRetencao aplica a regra dos 35 por cento do valor pago
        cadastroPedidos.cancelarPedido(id);
        System.out.println("pedido cancelado. retencao: " + pedido.calcularRetencao());
    }

    private static void pesquisarPedidos(Scanner scanner, CadastroPedidos cadastroPedidos) {
        Date dataInicio = lerDataOpcional(scanner, "data inicio (AAAA-MM-DD, vazio para ignorar): ");
        Date dataFim = lerDataOpcional(scanner, "data fim (AAAA-MM-DD, vazio para ignorar): ");
        Time horarioInicio = lerHoraOpcional(scanner, "horario inicio (HH:MM, vazio para ignorar): ");
        Time horarioFim = lerHoraOpcional(scanner, "horario fim (HH:MM, vazio para ignorar): ");
        List<Pedido> pedidos = cadastroPedidos.pesquisarPedidos(dataInicio, dataFim, horarioInicio, horarioFim);
        for (Pedido pedido : pedidos) {
            System.out.println(pedido);
        }
        System.out.println("quantidade encontrada: " + pedidos.size());
    }

    private static void registrarVenda(Scanner scanner, CadastroPedidos cadastroPedidos, ControleVendas controleVendas) {
        int idPedido = lerInteiro(scanner, "id do pedido: ");
        Pedido pedido = cadastroPedidos.buscarPedido(idPedido);
        if (pedido == null) {
            System.out.println("pedido nao encontrado");
            return;
        }

        Venda vendaExistente = buscarVendaPorPedido(controleVendas, idPedido);
        if (vendaExistente != null) {
            System.out.println("este pedido ja possui venda: " + vendaExistente);
            return;
        }

        // registrarVenda cria Venda, gera Recibo e aciona ControleEstoque para baixar ingredientes
        try {
            Venda venda = controleVendas.registrarVenda(pedido);
            System.out.println("venda registrada: " + venda);
            System.out.println("recibo gerado: " + venda.getRecibo());
        } catch (IllegalArgumentException excecao) {
            System.out.println(excecao.getMessage());
        }
    }

    private static Venda buscarVendaPorPedido(ControleVendas controleVendas, int idPedido) {
        for (Venda venda : controleVendas.getVendas()) {
            if (venda.getPedido() != null && venda.getPedido().getIdPedido() == idPedido) {
                return venda;
            }
        }
        return null;
    }

    private static void informarEntregaWhatsapp(
            Scanner scanner,
            CadastroClientes cadastroClientes,
            CadastroPedidos cadastroPedidos,
            ControleVendas controleVendas,
            ControleEntregas controleEntregas
    ) {
        int idPedido = lerInteiro(scanner, "id do pedido vendido: ");
        Pedido pedido = cadastroPedidos.buscarPedido(idPedido);
        Venda venda = buscarVendaPorPedido(controleVendas, idPedido);
        if (pedido == null || venda == null) {
            System.out.println("pedido precisa existir e ter venda registrada");
            return;
        }

        Cliente cliente = cadastroClientes.buscarCliente(pedido.getIdCliente());
        if (cliente == null) {
            System.out.println("cliente do pedido nao encontrado");
            return;
        }

        if (controleEntregas.getMotoqueiros().isEmpty() || controleEntregas.getRegioesEntrega().isEmpty()) {
            System.out.println("cadastre motoqueiros e regioes antes de informar entrega");
            return;
        }

        Entrega entrega = venda.getEntrega();
        if (entrega == null) {
            entrega = new Entrega(proximoIdEntrega(controleVendas), "em rota", false);
            venda.setEntrega(entrega);
        }

        // Entrega associa Motoqueiro e RegiaoEntrega, preservando a responsabilidade do WhatsApp no Motoqueiro
        entrega.setRegiaoEntrega(controleEntregas.definirRegiao(cliente));
        Motoqueiro motoqueiro = controleEntregas.alocarMotoqueiro(entrega);
        if (motoqueiro == null) {
            System.out.println("nenhum motoqueiro ativo disponivel");
            return;
        }

        pedido.setStatus(StatusPedido.SAIU_PARA_ENTREGA);
        controleEntregas.informarEntregaWhatsapp(entrega);
        if (lerBoolean(scanner, "confirmar entrega agora? (s/n): ")) {
            entrega.confirmarEntrega();
            pedido.setStatus(StatusPedido.ENTREGUE);
        }
        System.out.println("entrega atualizada: " + entrega);
    }

    private static int proximoIdEntrega(ControleVendas controleVendas) {
        int maiorId = 0;
        for (Venda venda : controleVendas.getVendas()) {
            if (venda.getEntrega() != null && venda.getEntrega().getIdEntrega() > maiorId) {
                maiorId = venda.getEntrega().getIdEntrega();
            }
        }
        return maiorId + 1;
    }

    private static void consultarProximoPedido(CadastroPedidos cadastroPedidos) {
        Pedido pedido = cadastroPedidos.getFilaPedidos().consultarProximo();
        System.out.println(pedido != null ? "proximo pedido fifo: " + pedido : "fila vazia");
    }

    private static void listarPedidos(CadastroPedidos cadastroPedidos) {
        if (cadastroPedidos.getPedidos().isEmpty()) {
            System.out.println("nenhum pedido cadastrado");
            return;
        }
        for (Pedido pedido : cadastroPedidos.getPedidos()) {
            System.out.println(pedido);
        }
    }

    private static void salvarDados(PersistenciaJSON persistenciaJSON) {
        persistenciaJSON.salvarDados();
        System.out.println("dados salvos");
    }

    private static String lerTexto(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            if (!scanner.hasNextLine()) {
                encerrarSistema = true;
                return "";
            }
            String texto = scanner.nextLine().trim();
            if (!texto.isBlank()) {
                return texto;
            }
            System.out.println("valor obrigatorio");
        }
    }

    private static String lerTextoLivre(Scanner scanner, String mensagem) {
        System.out.print(mensagem);
        if (!scanner.hasNextLine()) {
            encerrarSistema = true;
            return "";
        }
        return scanner.nextLine().trim();
    }

    private static String lerTextoComPadrao(Scanner scanner, String mensagem, String padrao) {
        String texto = lerTextoLivre(scanner, mensagem);
        if (encerrarSistema && texto.isBlank()) {
            return padrao;
        }
        return texto.isBlank() ? padrao : texto;
    }

    private static int lerInteiro(Scanner scanner, String mensagem) {
        while (true) {
            String texto = lerTextoLivre(scanner, mensagem);
            if (encerrarSistema && texto.isBlank()) {
                return 0;
            }
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException excecao) {
                System.out.println("informe um numero inteiro");
            }
        }
    }

    private static double lerDouble(Scanner scanner, String mensagem) {
        while (true) {
            String texto = lerTextoLivre(scanner, mensagem).replace(",", "");
            if (encerrarSistema && texto.isBlank()) {
                return 0.0;
            }
            try {
                return Double.parseDouble(texto);
            } catch (NumberFormatException excecao) {
                System.out.println("informe um numero decimal");
            }
        }
    }

    private static double lerDoubleComPadrao(Scanner scanner, String mensagem, double padrao) {
        while (true) {
            String texto = lerTextoLivre(scanner, mensagem).replace(",", "");
            if (encerrarSistema && texto.isBlank()) {
                return padrao;
            }
            if (texto.isBlank()) {
                return padrao;
            }
            try {
                return Double.parseDouble(texto);
            } catch (NumberFormatException excecao) {
                System.out.println("informe um numero decimal");
            }
        }
    }

    private static boolean lerBoolean(Scanner scanner, String mensagem) {
        while (true) {
            String texto = lerTextoLivre(scanner, mensagem).toLowerCase();
            if (encerrarSistema && texto.isBlank()) {
                return false;
            }
            if (texto.equals("s") || texto.equals("sim")) {
                return true;
            }
            if (texto.equals("n") || texto.equals("nao")) {
                return false;
            }
            System.out.println("digite s ou n");
        }
    }

    private static Date lerData(Scanner scanner, String mensagem) {
        while (true) {
            String texto = lerTexto(scanner, mensagem);
            if (encerrarSistema && texto.isBlank()) {
                return Date.valueOf(LocalDate.now());
            }
            try {
                return Date.valueOf(LocalDate.parse(texto));
            } catch (IllegalArgumentException excecao) {
                System.out.println("data invalida. use AAAA-MM-DD");
            }
        }
    }

    private static Date lerDataOpcional(Scanner scanner, String mensagem) {
        while (true) {
            String texto = lerTextoLivre(scanner, mensagem);
            if (encerrarSistema && texto.isBlank()) {
                return null;
            }
            if (texto.isBlank()) {
                return null;
            }
            try {
                return Date.valueOf(LocalDate.parse(texto));
            } catch (IllegalArgumentException excecao) {
                System.out.println("data invalida. use AAAA-MM-DD");
            }
        }
    }

    private static Date lerDataComPadrao(Scanner scanner, String mensagem, Date padrao) {
        Date data = lerDataOpcional(scanner, mensagem);
        return data == null ? padrao : data;
    }

    private static Time lerHora(Scanner scanner, String mensagem) {
        while (true) {
            String texto = lerTexto(scanner, mensagem);
            if (encerrarSistema && texto.isBlank()) {
                return Time.valueOf(LocalTime.now().withSecond(0).withNano(0));
            }
            try {
                return Time.valueOf(LocalTime.parse(texto));
            } catch (IllegalArgumentException excecao) {
                System.out.println("hora invalida. use HH:MM");
            }
        }
    }

    private static Time lerHoraOpcional(Scanner scanner, String mensagem) {
        while (true) {
            String texto = lerTextoLivre(scanner, mensagem);
            if (encerrarSistema && texto.isBlank()) {
                return null;
            }
            if (texto.isBlank()) {
                return null;
            }
            try {
                return Time.valueOf(LocalTime.parse(texto));
            } catch (IllegalArgumentException excecao) {
                System.out.println("hora invalida. use HH:MM");
            }
        }
    }

    private static Time lerHoraComPadrao(Scanner scanner, String mensagem, Time padrao) {
        Time hora = lerHoraOpcional(scanner, mensagem);
        return hora == null ? padrao : hora;
    }
}
