package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// aplico encapsulamento no estado do pedido
// uso comparator para comparar pedidos por mais de um criterio
public class Pedido implements Comparator<Pedido> {

    private int idPedido;
    private Date dataPedido;
    private Time horarioPedido;
    private Time horarioEntregaPrevisto;
    private StatusPedido status;
    private double valorTotal;
    private double valorPago;
    private int idCliente;
    // associacao: pedido guarda qual funcionario registrou a venda
    private Funcionario funcionarioResponsavel;
    // composicao ItemPedido faz parte do Pedido e nao tem sentido isolado no fluxo
    private List<ItemPedido> itensPedido;

    // construtor padrao que inicia a lista de itens do pedido
    public Pedido() {
        this.itensPedido = new ArrayList<>();
    }

    // construtor completo para criar pedido com cliente funcionario e itens
    public Pedido(
            int idPedido,
            int idCliente,
            Date dataPedido,
            Time horarioPedido,
            Time horarioEntregaPrevisto,
            StatusPedido status,
            double valorTotal,
            double valorPago,
            Funcionario funcionarioResponsavel,
            List<ItemPedido> itensPedido
    ) {
        this();
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.dataPedido = dataPedido;
        this.horarioPedido = horarioPedido;
        this.horarioEntregaPrevisto = horarioEntregaPrevisto;
        this.status = status;
        this.valorTotal = valorTotal;
        this.valorPago = valorPago;
        this.funcionarioResponsavel = funcionarioResponsavel;
        if (itensPedido != null) {
            this.itensPedido.addAll(itensPedido);
        }
        calcularTotal();
        SistemaDelivery.incrementarQtdPedidosCriados();
    }

    //atualizo os dados principais do pedido
    public void editarPedido(List<ItemPedido> itensPedido, Time horarioEntregaPrevisto, double valorPago, Funcionario funcionarioResponsavel) {
        this.itensPedido = new ArrayList<>();
        if (itensPedido != null) {
            this.itensPedido.addAll(itensPedido);
        }
        this.horarioEntregaPrevisto = horarioEntregaPrevisto;
        this.valorPago = valorPago;
        this.funcionarioResponsavel = funcionarioResponsavel;
        calcularTotal();
    }

    // somo o total de todos os itens
    public double calcularTotal() {
        valorTotal = 0.0;
        for (ItemPedido itemPedido : getItensPedido()) {
            valorTotal += itemPedido.calcularSubtotal();
        }
        return valorTotal;
    }

    //marco o pedido como cancelado
    public void cancelarPedido() {
        if (status != StatusPedido.ENTREGUE) {
            status = StatusPedido.CANCELADO;
        }
    }

    // calculo os trinta e cinco por cento retidos
    public double calcularRetencao() {
        return valorPago * 0.35;
    }

    // polimorfismo implemento o compare exigido pela interface Comparator
    @Override
    public int compare(Pedido primeiroPedido, Pedido segundoPedido) {
        return Integer.compare(primeiroPedido.getIdPedido(), segundoPedido.getIdPedido());
    }

    public static Comparator<Pedido> porDataPedido() {
        return Comparator
                .comparing(Pedido::getDataPedido, Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing(Pedido::getHorarioPedido, Comparator.nullsLast(Comparator.naturalOrder()));
    }

    public static Comparator<Pedido> porValorTotal() {
        return Comparator.comparingDouble(Pedido::getValorTotal);
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Time getHorarioPedido() {
        return horarioPedido;
    }

    public void setHorarioPedido(Time horarioPedido) {
        this.horarioPedido = horarioPedido;
    }

    public Time getHorarioEntregaPrevisto() {
        return horarioEntregaPrevisto;
    }

    public void setHorarioEntregaPrevisto(Time horarioEntregaPrevisto) {
        this.horarioEntregaPrevisto = horarioEntregaPrevisto;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Funcionario getFuncionarioResponsavel() {
        return funcionarioResponsavel;
    }

    public void setFuncionarioResponsavel(Funcionario funcionarioResponsavel) {
        this.funcionarioResponsavel = funcionarioResponsavel;
    }

    public List<ItemPedido> getItensPedido() {
        if (itensPedido == null) {
            itensPedido = new ArrayList<>();
        }
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = new ArrayList<>();
        if (itensPedido != null) {
            this.itensPedido.addAll(itensPedido);
        }
    }

    // polimorfismo sobrescrevo toString para imprimir todos os dados do pedido
    @Override
    public String toString() {
        return "Pedido{"
                + "idPedido=" + idPedido
                + ", dataPedido=" + dataPedido
                + ", horarioPedido=" + horarioPedido
                + ", horarioEntregaPrevisto=" + horarioEntregaPrevisto
                + ", status=" + status
                + ", valorTotal=" + valorTotal
                + ", valorPago=" + valorPago
                + ", idCliente=" + idCliente
                + ", funcionarioResponsavel=" + funcionarioResponsavel
                + ", itensPedido=" + getItensPedido()
                + '}';
    }
}
