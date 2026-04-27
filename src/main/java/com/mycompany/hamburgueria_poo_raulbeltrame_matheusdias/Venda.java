package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

import java.sql.Date;
import java.time.LocalDate;

// uso a venda para ligar pedido recibo e entrega no fluxo final
public class Venda {

    private int idVenda;
    private Date dataVenda;
    private double valorTotal;
    // associacao: a venda nasce a partir de um pedido
    private Pedido pedido;
    //composicao trato o recibo como parte da venda no fluxo do sistema
    private Recibo recibo;
    // associacao: a venda pode ter uma entrega ligada a ela
    private Entrega entrega;

    // construtor padrao usado para criar venda vazia
    public Venda() {
    }

    // construtor completo para ligar venda ao pedido
    public Venda(int idVenda, Date dataVenda, double valorTotal, Pedido pedido) {
        this.idVenda = idVenda;
        this.dataVenda = dataVenda;
        this.valorTotal = valorTotal;
        this.pedido = pedido;
    }

    // registro a venda no dia atual
    public void registrarVenda() {
        if (dataVenda == null) {
            dataVenda = Date.valueOf(LocalDate.now());
        }
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Recibo getRecibo() {
        return recibo;
    }

    public void setRecibo(Recibo recibo) {
        this.recibo = recibo;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }

    // polimorfismo sobrescrevo toString para imprimir venda, pedido, recibo e entrega
    @Override
    public String toString() {
        return "Venda{"
                + "idVenda=" + idVenda
                + ", dataVenda=" + dataVenda
                + ", valorTotal=" + valorTotal
                + ", pedido=" + pedido
                + ", recibo=" + recibo
                + ", entrega=" + entrega
                + '}';
    }
}
