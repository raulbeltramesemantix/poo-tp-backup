package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

//centralizo o cadastro dos pedidos e a ligacao com a fila fifo
public class CadastroPedidos {

    // agregacao cadastro guarda varios pedidos
    private List<Pedido> pedidos;
    // associo o cadastro a fila para seguir a ordem de atendimento
    private FilaPedidos filaPedidos;
    // associo o cadastro de pedidos aos clientes para manter o historico
    private CadastroClientes cadastroClientes;

    // construtor padrao que cria a fila e deixa clientes sem associacao inicial
    public CadastroPedidos() {
        this(new FilaPedidos(), null);
    }

    // construtor completo para associar fila fifo e cadastro de clientes
    public CadastroPedidos(FilaPedidos filaPedidos, CadastroClientes cadastroClientes) {
        this.pedidos = new ArrayList<>();
        this.filaPedidos = filaPedidos;
        this.cadastroClientes = cadastroClientes;
    }

    //incluo um novo pedido no sistema
    public Pedido incluirPedido(
            Cliente cliente,
            Funcionario funcionario,
            Date dataPedido,
            Time horarioPedido,
            Time horarioEntregaPrevisto,
            List<ItemPedido> itensPedido,
            double valorPago
    ) {
        int idPedido = GeradorId.gerarIdPedido();
        Pedido pedido = new Pedido(
                idPedido,
                cliente.getIdCliente(),
                dataPedido,
                horarioPedido,
                horarioEntregaPrevisto,
                StatusPedido.RECEBIDO,
                0.0,
                valorPago,
                funcionario,
                itensPedido
        );
        getPedidos().add(pedido);
        if (filaPedidos != null) {
            filaPedidos.enfileirar(pedido);
        }
        cliente.adicionarPedido(pedido);
        return pedido;
    }

    //removo um pedido pelo id
    public Pedido removerPedido(int id) {
        for (int indice = 0; indice < getPedidos().size(); indice++) {
            Pedido pedido = getPedidos().get(indice);
            if (pedido.getIdPedido() == id) {
                getPedidos().remove(indice);
                if (filaPedidos != null) {
                    filaPedidos.retirarPedido(id);
                }
                if (cadastroClientes != null) {
                    Cliente cliente = cadastroClientes.buscarCliente(pedido.getIdCliente());
                    if (cliente != null) {
                        cliente.removerPedido(id);
                    }
                }
                return pedido;
            }
        }
        return null;
    }

    // busco um pedido pelo id
    public Pedido buscarPedido(int id) {
        for (Pedido pedido : getPedidos()) {
            if (pedido.getIdPedido() == id) {
                return pedido;
            }
        }
        return null;
    }

    // altero os dados de um pedido
    public void editarPedido(int id, List<ItemPedido> itensPedido, Time horarioEntregaPrevisto, double valorPago, Funcionario funcionario) {
        Pedido pedido = buscarPedido(id);
        if (pedido != null) {
            pedido.editarPedido(itensPedido, horarioEntregaPrevisto, valorPago, funcionario);
        }
    }

    //cancelo um pedido pelo id
    public void cancelarPedido(int id) {
        Pedido pedido = buscarPedido(id);
        if (pedido != null) {
            pedido.cancelarPedido();
            if (filaPedidos != null) {
                filaPedidos.retirarPedido(id);
            }
        }
    }

    // pesquiso pedidos por data e hora
    public List<Pedido> pesquisarPedidos(Date dataInicio, Date dataFim, Time horarioInicio, Time horarioFim) {
        List<Pedido> pedidosEncontrados = new ArrayList<>();
        for (Pedido pedido : getPedidos()) {
            boolean dataValida = (dataInicio == null || !pedido.getDataPedido().before(dataInicio))
                    && (dataFim == null || !pedido.getDataPedido().after(dataFim));
            boolean horarioValido = (horarioInicio == null || !pedido.getHorarioPedido().before(horarioInicio))
                    && (horarioFim == null || !pedido.getHorarioPedido().after(horarioFim));
            if (dataValida && horarioValido) {
                pedidosEncontrados.add(pedido);
            }
        }
        return pedidosEncontrados;
    }

    public List<Pedido> getPedidos() {
        if (pedidos == null) {
            pedidos = new ArrayList<>();
        }
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = new ArrayList<>();
        if (pedidos != null) {
            this.pedidos.addAll(pedidos);
        }
    }

    public FilaPedidos getFilaPedidos() {
        return filaPedidos;
    }

    public void setFilaPedidos(FilaPedidos filaPedidos) {
        this.filaPedidos = filaPedidos;
    }

    public CadastroClientes getCadastroClientes() {
        return cadastroClientes;
    }

    public void setCadastroClientes(CadastroClientes cadastroClientes) {
        this.cadastroClientes = cadastroClientes;
    }

    // polimorfismo sobrescrevo toString para exibir pedidos e fila
    @Override
    public String toString() {
        return "CadastroPedidos{"
                + "pedidos=" + getPedidos()
                + ", filaPedidos=" + filaPedidos
                + '}';
    }
}
