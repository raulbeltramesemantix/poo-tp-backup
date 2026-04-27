package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//aplico encapsulamento ao guardar dados e historico do cliente
//uso comparator para mostrar comparacoes por criterios diferentes
public class Cliente implements Comparator<Cliente> {

    private int idCliente;
    private String nome;
    private String telefone;
    private String endereco;
    private int idUltimoPedido;
    private Date dataUltimoPedido;
    // associacao: cliente se relaciona com pedidos feitos de forma dinamica
    private transient List<Pedido> pedidos;
    // associacao: cliente tambem consulta recibos gerados pelas vendas
    private transient List<Recibo> recibos;

    // construtor padrao que inicializa as colecoes do cliente
    public Cliente() {
        inicializarColecoes();
    }

    // construtor completo para cadastrar cliente e ultimo pedido
    public Cliente(int idCliente, String nome, String telefone, String endereco, int idUltimoPedido, Date dataUltimoPedido) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.idUltimoPedido = idUltimoPedido;
        this.dataUltimoPedido = dataUltimoPedido;
        inicializarColecoes();
    }

    private void inicializarColecoes() {
        if (pedidos == null) {
            pedidos = new ArrayList<>();
        }
        if (recibos == null) {
            recibos = new ArrayList<>();
        }
    }

    //busco os pedidos ligados ao cliente
    public List<Pedido> consultarUltimosPedidos() {
        inicializarColecoes();
        return new ArrayList<>(pedidos);
    }

    // guardo o pedido no historico do cliente
    public void adicionarPedido(Pedido pedido) {
        inicializarColecoes();
        pedidos.add(pedido);
        idUltimoPedido = pedido.getIdPedido();
        dataUltimoPedido = pedido.getDataPedido();
    }

    // tiro um pedido do historico do cliente
    public void removerPedido(int idPedido) {
        inicializarColecoes();
        pedidos.removeIf(pedido -> pedido.getIdPedido() == idPedido);
        if (pedidos.isEmpty()) {
            idUltimoPedido = 0;
            dataUltimoPedido = null;
        } else {
            Pedido ultimoPedido = pedidos.get(pedidos.size() - 1);
            idUltimoPedido = ultimoPedido.getIdPedido();
            dataUltimoPedido = ultimoPedido.getDataPedido();
        }
    }

    // limpo o historico para refazer os vinculos
    public void limparHistorico() {
        inicializarColecoes();
        pedidos.clear();
        recibos.clear();
    }

    // guardo o recibo do cliente
    public void adicionarRecibo(Recibo recibo) {
        inicializarColecoes();
        recibos.add(recibo);
    }

    // polimorfismo implemento o compare definido pela interface Comparator
    @Override
    public int compare(Cliente primeiroCliente, Cliente segundoCliente) {
        return Integer.compare(primeiroCliente.getIdCliente(), segundoCliente.getIdCliente());
    }

    public static Comparator<Cliente> porNome() {
        return Comparator.comparing(Cliente::getNome, String.CASE_INSENSITIVE_ORDER);
    }

    public static Comparator<Cliente> porDataUltimoPedido() {
        return Comparator.comparing(
                Cliente::getDataUltimoPedido,
                Comparator.nullsLast(Comparator.naturalOrder())
        );
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getIdUltimoPedido() {
        return idUltimoPedido;
    }

    public void setIdUltimoPedido(int idUltimoPedido) {
        this.idUltimoPedido = idUltimoPedido;
    }

    public Date getDataUltimoPedido() {
        return dataUltimoPedido;
    }

    public void setDataUltimoPedido(Date dataUltimoPedido) {
        this.dataUltimoPedido = dataUltimoPedido;
    }

    public List<Pedido> getPedidos() {
        inicializarColecoes();
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
        inicializarColecoes();
    }

    public List<Recibo> getRecibos() {
        inicializarColecoes();
        return recibos;
    }

    public void setRecibos(List<Recibo> recibos) {
        this.recibos = recibos;
        inicializarColecoes();
    }

    // polimorfismo sobrescrevo toString para apresentar o estado do cliente
    @Override
    public String toString() {
        inicializarColecoes();
        return "Cliente{"
                + "idCliente=" + idCliente
                + ", nome='" + nome + '\''
                + ", telefone='" + telefone + '\''
                + ", endereco='" + endereco + '\''
                + ", idUltimoPedido=" + idUltimoPedido
                + ", dataUltimoPedido=" + dataUltimoPedido
                + ", quantidadePedidos=" + pedidos.size()
                + ", quantidadeRecibos=" + recibos.size()
                + '}';
    }
}
