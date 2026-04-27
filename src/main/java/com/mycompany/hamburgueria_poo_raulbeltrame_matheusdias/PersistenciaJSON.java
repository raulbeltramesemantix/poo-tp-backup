package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.List;

// abstraio a persistencia para nao espalhar json nas entidades
public class PersistenciaJSON {

    // encapsulamento: caminho do arquivo fica protegido dentro da persistencia
    private final Path caminhoArquivo;
    // associacao: persistencia usa os objetos principais para salvar e carregar
    private final SistemaDelivery sistemaDelivery;
    private final DadosLoja dadosLoja;
    private final CadastroUsuarios cadastroUsuarios;
    private final CadastroClientes cadastroClientes;
    private final CadastroProdutos cadastroProdutos;
    private final CadastroPedidos cadastroPedidos;
    private final ControleEstoque controleEstoque;
    private final ControleVendas controleVendas;
    private final ControleEntregas controleEntregas;
    private final RelatorioVendas relatorioVendas;
    // composicao Gson configurado pertence a esta persistencia
    private final Gson gson;

    // construtor completo que recebe todos os objetos que serao persistidos
    public PersistenciaJSON(
            Path caminhoArquivo,
            SistemaDelivery sistemaDelivery,
            DadosLoja dadosLoja,
            CadastroUsuarios cadastroUsuarios,
            CadastroClientes cadastroClientes,
            CadastroProdutos cadastroProdutos,
            CadastroPedidos cadastroPedidos,
            ControleEstoque controleEstoque,
            ControleVendas controleVendas,
            ControleEntregas controleEntregas,
            RelatorioVendas relatorioVendas
    ) {
        this.caminhoArquivo = caminhoArquivo;
        this.sistemaDelivery = sistemaDelivery;
        this.dadosLoja = dadosLoja;
        this.cadastroUsuarios = cadastroUsuarios;
        this.cadastroClientes = cadastroClientes;
        this.cadastroProdutos = cadastroProdutos;
        this.cadastroPedidos = cadastroPedidos;
        this.controleEstoque = controleEstoque;
        this.controleVendas = controleVendas;
        this.controleEntregas = controleEntregas;
        this.relatorioVendas = relatorioVendas;
        this.gson = criarGson();
    }

    private Gson criarGson() {
        return new GsonBuilder()
                .registerTypeAdapter(Date.class, new AdaptadorDataSQL())
                .registerTypeAdapter(Time.class, new AdaptadorHoraSQL())
                .setPrettyPrinting()
                .create();
    }

    //salvo os dados do sistema em json
    public void salvarDados() {
        EstadoPersistido estadoPersistido = new EstadoPersistido();
        estadoPersistido.estacoes = sistemaDelivery.getEstacoes();
        estadoPersistido.qtdPedidosCriados = sistemaDelivery.getQtdPedidosCriados();
        estadoPersistido.qtdProdutosCriados = SistemaDelivery.qtdProdutosCriados;
        estadoPersistido.dadosLoja = dadosLoja;
        estadoPersistido.administrador = cadastroUsuarios.getAdministrador();
        estadoPersistido.administradores = cadastroUsuarios.getAdministradores();
        estadoPersistido.funcionarios = cadastroUsuarios.getFuncionarios();
        estadoPersistido.clientes = cadastroClientes.getClientes();
        estadoPersistido.produtos = cadastroProdutos.getProdutos();
        estadoPersistido.pedidos = cadastroPedidos.getPedidos();
        estadoPersistido.estoque = controleEstoque.getEstoque();
        estadoPersistido.fornecedores = controleEstoque.getFornecedores();
        estadoPersistido.recebimentosEstoque = controleEstoque.getRecebimentosEstoque();
        estadoPersistido.acoesEstoque = controleEstoque.getAcoesEstoque();
        estadoPersistido.vendas = controleVendas.getVendas();
        estadoPersistido.motoqueiros = controleEntregas.getMotoqueiros();
        estadoPersistido.regioesEntrega = controleEntregas.getRegioesEntrega();
        estadoPersistido.relatoriosEmitidos = relatorioVendas.getRelatoriosEmitidos();
        try (Writer escritor = Files.newBufferedWriter(caminhoArquivo)) {
            gson.toJson(estadoPersistido, escritor);
        } catch (IOException excecao) {
            throw new RuntimeException("Falha ao salvar json", excecao);
        }
    }

    // carrego os dados do sistema do json
    public void carregarDados() {
        if (!Files.exists(caminhoArquivo)) {
            return;
        }
        try (Reader leitor = Files.newBufferedReader(caminhoArquivo)) {
            EstadoPersistido estadoPersistido = gson.fromJson(leitor, EstadoPersistido.class);
            if (estadoPersistido == null) {
                return;
            }

            sistemaDelivery.setEstacoes(estadoPersistido.estacoes != null ? estadoPersistido.estacoes : new EstacaoPreparo[3]);
            if (sistemaDelivery.getEstacoes()[0] == null) {
                sistemaDelivery.iniciarSistema();
            }
            SistemaDelivery.setQtdPedidosCriados(estadoPersistido.qtdPedidosCriados);
            SistemaDelivery.qtdProdutosCriados = estadoPersistido.qtdProdutosCriados;

            if (estadoPersistido.dadosLoja != null) {
                dadosLoja.alterarDadosLoja(
                        estadoPersistido.dadosLoja.getNomeLoja(),
                        estadoPersistido.dadosLoja.getTelefone(),
                        estadoPersistido.dadosLoja.getEndereco(),
                        estadoPersistido.dadosLoja.getHorarioFuncionamento(),
                        estadoPersistido.dadosLoja.getTaxaEntrega()
                );
            }

            cadastroUsuarios.setAdministrador(estadoPersistido.administrador);
            cadastroUsuarios.setAdministradores(estadoPersistido.administradores);
            cadastroUsuarios.setFuncionarios(estadoPersistido.funcionarios);
            cadastroClientes.setClientes(estadoPersistido.clientes);
            cadastroProdutos.setProdutos(estadoPersistido.produtos);
            cadastroPedidos.setPedidos(estadoPersistido.pedidos);
            cadastroPedidos.setCadastroClientes(cadastroClientes);
            cadastroPedidos.getFilaPedidos().reconstruirFila(cadastroPedidos.getPedidos());

            controleEstoque.setEstoque(estadoPersistido.estoque != null ? estadoPersistido.estoque : new Estoque());
            controleEstoque.setFornecedores(estadoPersistido.fornecedores);
            controleEstoque.setRecebimentosEstoque(estadoPersistido.recebimentosEstoque);
            controleEstoque.setAcoesEstoque(estadoPersistido.acoesEstoque);

            controleVendas.setDadosLoja(dadosLoja);
            controleVendas.setControleEstoque(controleEstoque);
            controleVendas.setCadastroClientes(cadastroClientes);
            controleVendas.setVendas(estadoPersistido.vendas);

            controleEntregas.setMotoqueiros(estadoPersistido.motoqueiros);
            controleEntregas.setRegioesEntrega(estadoPersistido.regioesEntrega);

            relatorioVendas.setControleVendas(controleVendas);
            relatorioVendas.setRelatoriosEmitidos(estadoPersistido.relatoriosEmitidos);

            reconstruirAssociacoes();
            sincronizarGeradorId();
        } catch (IOException excecao) {
            throw new RuntimeException("Falha ao carregar json", excecao);
        }
    }

    //refaco ligacoes dinamicas apos a leitura
    private void reconstruirAssociacoes() {
        //reorganizo as associacoes depois da desserializacao
        for (Cliente cliente : cadastroClientes.getClientes()) {
            cliente.limparHistorico();
        }

        for (Pedido pedido : cadastroPedidos.getPedidos()) {
            Cliente cliente = cadastroClientes.buscarCliente(pedido.getIdCliente());
            if (cliente != null) {
                cliente.adicionarPedido(pedido);
            }
        }

        for (Venda venda : controleVendas.getVendas()) {
            if (venda.getPedido() != null && venda.getRecibo() != null) {
                Cliente cliente = cadastroClientes.buscarCliente(venda.getPedido().getIdCliente());
                if (cliente != null) {
                    cliente.adicionarRecibo(venda.getRecibo());
                }
            }
        }
    }

    // sincronizo ids para novas inclusoes
    private void sincronizarGeradorId() {
        int maiorIdCliente = 0;
        int maiorIdPedido = 0;
        int maiorIdProduto = 0;

        for (Cliente cliente : cadastroClientes.getClientes()) {
            if (cliente.getIdCliente() > maiorIdCliente) {
                maiorIdCliente = cliente.getIdCliente();
            }
        }

        for (Pedido pedido : cadastroPedidos.getPedidos()) {
            if (pedido.getIdPedido() > maiorIdPedido) {
                maiorIdPedido = pedido.getIdPedido();
            }
        }

        for (Produto produto : cadastroProdutos.getProdutos()) {
            if (produto.getIdProduto() > maiorIdProduto) {
                maiorIdProduto = produto.getIdProduto();
            }
        }

        GeradorId.definirUltimosIds(maiorIdCliente, maiorIdPedido, maiorIdProduto);
    }

    // polimorfismo sobrescrevo toString para identificar o arquivo json usado
    @Override
    public String toString() {
        return "PersistenciaJSON{"
                + "caminhoArquivo=" + caminhoArquivo
                + '}';
    }

    private static class EstadoPersistido {

        // classe interna: representa o estado inteiro salvo em um unico json
        private EstacaoPreparo[] estacoes;
        private int qtdPedidosCriados;
        private int qtdProdutosCriados;
        private DadosLoja dadosLoja;
        private Administrador administrador;
        // agregacao listas representam colecoes dinamicas persistidas
        private List<Administrador> administradores;
        private List<Funcionario> funcionarios;
        private List<Cliente> clientes;
        private List<Produto> produtos;
        private List<Pedido> pedidos;
        private Estoque estoque;
        private List<Fornecedor> fornecedores;
        private List<RecebimentoEstoque> recebimentosEstoque;
        private List<String> acoesEstoque;
        private List<Venda> vendas;
        private List<Motoqueiro> motoqueiros;
        private List<RegiaoEntrega> regioesEntrega;
        private List<String> relatoriosEmitidos;
    }

    private static class AdaptadorDataSQL implements JsonSerializer<Date>, JsonDeserializer<Date> {

        // polimorfismo Gson chama este metodo para serializar Date
        @Override
        public JsonElement serialize(Date data, Type tipo, JsonSerializationContext contexto) {
            return data == null ? null : new JsonPrimitive(data.toString());
        }

        // polimorfismo Gson chama este metodo para desserializar Date
        @Override
        public Date deserialize(JsonElement json, Type tipo, JsonDeserializationContext contexto) throws JsonParseException {
            if (json == null || json.isJsonNull()) {
                return null;
            }

            String texto = json.getAsString();
            try {
                return Date.valueOf(texto);
            } catch (IllegalArgumentException excecao) {
                return converterDataAntiga(texto);
            }
        }

        private Date converterDataAntiga(String texto) {
            Locale portuguesBrasil = new Locale("pt", "BR");
            Locale[] locales = {portuguesBrasil, Locale.US, Locale.getDefault()};
            String[] padroes = {"MMM d, yyyy", "MMM. d, yyyy"};

            for (Locale locale : locales) {
                for (String padrao : padroes) {
                    try {
                        SimpleDateFormat formatador = new SimpleDateFormat(padrao, locale);
                        formatador.setLenient(false);
                        java.util.Date data = formatador.parse(texto);
                        return new Date(data.getTime());
                    } catch (ParseException excecao) {
                        // tento o proximo formato aceito pelo json antigo
                    }
                }
            }
            throw new JsonParseException("Data invalida no json: " + texto);
        }
    }

    private static class AdaptadorHoraSQL implements JsonSerializer<Time>, JsonDeserializer<Time> {

        // polimorfismo Gson chama este metodo para serializar Time
        @Override
        public JsonElement serialize(Time hora, Type tipo, JsonSerializationContext contexto) {
            return hora == null ? null : new JsonPrimitive(hora.toString());
        }

        // polimorfismo Gson chama este metodo para desserializar Time
        @Override
        public Time deserialize(JsonElement json, Type tipo, JsonDeserializationContext contexto) throws JsonParseException {
            if (json == null || json.isJsonNull()) {
                return null;
            }

            String texto = json.getAsString();
            try {
                return Time.valueOf(texto.length() == 5 ? texto + ":00" : texto);
            } catch (IllegalArgumentException excecao) {
                return converterHoraAntiga(texto);
            }
        }

        private Time converterHoraAntiga(String texto) {
            String[] padroes = {"hh:mm:ss a", "hh:mm a"};
            for (String padrao : padroes) {
                try {
                    SimpleDateFormat formatador = new SimpleDateFormat(padrao, Locale.US);
                    formatador.setLenient(false);
                    java.util.Date hora = formatador.parse(texto);
                    return new Time(hora.getTime());
                } catch (ParseException excecao) {
                    // tento o proximo formato aceito pelo json antigo
                }
            }
            throw new JsonParseException("Hora invalida no json: " + texto);
        }
    }
}
