package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// coordeno a venda usando associacao com loja estoque e clientes
public class ControleVendas {

    // associacao: uso os dados da loja para montar o recibo
    private DadosLoja dadosLoja;
    // associacao: venda baixa ingredientes pelo controle de estoque
    private ControleEstoque controleEstoque;
    // associacao: venda atualiza historico do cliente
    private CadastroClientes cadastroClientes;
    //guardo dinamicamente as vendas concluidas
    private List<Venda> vendas;

    public ControleVendas() {
        this(new DadosLoja(), new ControleEstoque(), new CadastroClientes());
    }

    public ControleVendas(DadosLoja dadosLoja, ControleEstoque controleEstoque, CadastroClientes cadastroClientes) {
        this.dadosLoja = dadosLoja;
        this.controleEstoque = controleEstoque;
        this.cadastroClientes = cadastroClientes;
        this.vendas = new ArrayList<>();
    }

    // registro a venda e disparo o recibo
    public Venda registrarVenda(Pedido pedido) {
        //aplico polimorfismo ao tratar o pedido pelo comportamento da propria classe
        if (pedido == null || pedido.getStatus() == StatusPedido.CANCELADO) {
            throw new IllegalArgumentException("Nao posso registrar venda para pedido invalido");
        }
        pedido.calcularTotal();
        controleEstoque.atualizarAposPedido(pedido);
        Venda venda = new Venda(proximoIdVenda(), Date.valueOf(LocalDate.now()), pedido.getValorTotal(), pedido);
        venda.registrarVenda();
        Recibo recibo = gerarRecibo(venda);
        venda.setRecibo(recibo);
        getVendas().add(venda);
        pedido.setStatus(StatusPedido.EM_PREPARO);
        Cliente cliente = cadastroClientes.buscarCliente(pedido.getIdCliente());
        if (cliente != null) {
            cliente.adicionarRecibo(recibo);
        }
        return venda;
    }

    //monto o recibo automatico da venda
    public Recibo gerarRecibo(Venda venda) {
        Recibo recibo = venda.getRecibo();
        if (recibo == null) {
            recibo = new Recibo();
            recibo.setIdRecibo(proximoIdRecibo());
            venda.setRecibo(recibo);
        }
        recibo.gerarRecibo();
        String detalhes = "Loja " + dadosLoja.getNomeLoja()
                + System.lineSeparator()
                + "Telefone " + dadosLoja.getTelefone()
                + System.lineSeparator()
                + "Endereco " + dadosLoja.getEndereco()
                + System.lineSeparator()
                + "Pedido " + venda.getPedido().getIdPedido()
                + System.lineSeparator()
                + "Cliente " + venda.getPedido().getIdCliente()
                + System.lineSeparator()
                + "Valor total " + venda.getValorTotal();
        recibo.setDetalhes(detalhes);
        return recibo;
    }

    private int proximoIdVenda() {
        int maiorId = 0;
        for (Venda venda : getVendas()) {
            if (venda.getIdVenda() > maiorId) {
                maiorId = venda.getIdVenda();
            }
        }
        return maiorId + 1;
    }

    private int proximoIdRecibo() {
        int maiorId = 0;
        for (Venda venda : getVendas()) {
            if (venda.getRecibo() != null && venda.getRecibo().getIdRecibo() > maiorId) {
                maiorId = venda.getRecibo().getIdRecibo();
            }
        }
        return maiorId + 1;
    }

    public DadosLoja getDadosLoja() {
        return dadosLoja;
    }

    public void setDadosLoja(DadosLoja dadosLoja) {
        this.dadosLoja = dadosLoja;
    }

    public ControleEstoque getControleEstoque() {
        return controleEstoque;
    }

    public void setControleEstoque(ControleEstoque controleEstoque) {
        this.controleEstoque = controleEstoque;
    }

    public CadastroClientes getCadastroClientes() {
        return cadastroClientes;
    }

    public void setCadastroClientes(CadastroClientes cadastroClientes) {
        this.cadastroClientes = cadastroClientes;
    }

    public List<Venda> getVendas() {
        if (vendas == null) {
            vendas = new ArrayList<>();
        }
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = new ArrayList<>();
        if (vendas != null) {
            this.vendas.addAll(vendas);
        }
    }

    // polimorfismo sobrescrevo toString para resumir vendas registradas
    @Override
    public String toString() {
        return "ControleVendas{"
                + "dadosLoja=" + dadosLoja
                + ", vendas=" + getVendas()
                + '}';
    }
}
