package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// centralizo a leitura das vendas para gerar relatorios e estatisticas
public class RelatorioVendas {

    // associacao: relatorio consulta as vendas registradas
    private ControleVendas controleVendas;
    // agregacao historico dos relatorios emitidos
    private List<String> relatoriosEmitidos;

    // construtor padrao que cria controle de vendas interno
    public RelatorioVendas() {
        this(new ControleVendas());
    }

    // construtor completo para associar relatorio ao controle de vendas
    public RelatorioVendas(ControleVendas controleVendas) {
        this.controleVendas = controleVendas;
        this.relatoriosEmitidos = new ArrayList<>();
    }

    //emito relatorio diario
    public void emitirRelatorioDia(Date data) {
        double totalVendido = 0.0;
        int quantidadePedidos = 0;
        for (Venda venda : controleVendas.getVendas()) {
            if (venda.getDataVenda() != null && venda.getDataVenda().equals(data)) {
                quantidadePedidos++;
                totalVendido += venda.getValorTotal();
            }
        }
        String relatorio = "Relatorio dia " + data + " quantidade " + quantidadePedidos + " total " + totalVendido;
        getRelatoriosEmitidos().add(relatorio);
        System.out.println(relatorio);
    }

    //emito relatorio mensal
    public void emitirRelatorioMes(int mes, int ano) {
        double totalVendido = 0.0;
        int quantidadePedidos = 0;
        for (Venda venda : controleVendas.getVendas()) {
            if (venda.getDataVenda() != null) {
                LocalDate dataVenda = venda.getDataVenda().toLocalDate();
                if (dataVenda.getMonthValue() == mes && dataVenda.getYear() == ano) {
                    quantidadePedidos++;
                    totalVendido += venda.getValorTotal();
                }
            }
        }
        String relatorio = "Relatorio mes " + mes + " ano " + ano + " quantidade " + quantidadePedidos + " total " + totalVendido;
        getRelatoriosEmitidos().add(relatorio);
        System.out.println(relatorio);
    }

    //monto o balanco estatistico do mes
    public EstatisticaVendas gerarBalancoMensal(int mes, int ano) {
        int quantidadePedidos = 0;
        double totalVendido = 0.0;
        Map<String, Integer> contagemProdutos = new LinkedHashMap<>();
        for (Venda venda : controleVendas.getVendas()) {
            if (venda.getDataVenda() != null) {
                LocalDate dataVenda = venda.getDataVenda().toLocalDate();
                if (dataVenda.getMonthValue() == mes && dataVenda.getYear() == ano) {
                    quantidadePedidos++;
                    totalVendido += venda.getValorTotal();
                    for (ItemPedido itemPedido : venda.getPedido().getItensPedido()) {
                        String nomeProduto = itemPedido.getProduto().getNome();
                        contagemProdutos.put(nomeProduto, contagemProdutos.getOrDefault(nomeProduto, 0) + itemPedido.getQuantidade());
                    }
                }
            }
        }

        String produtoMaisVendido = "";
        int maiorQuantidade = 0;
        for (Map.Entry<String, Integer> entradaProduto : contagemProdutos.entrySet()) {
            if (entradaProduto.getValue() > maiorQuantidade) {
                maiorQuantidade = entradaProduto.getValue();
                produtoMaisVendido = entradaProduto.getKey();
            }
        }

        EstatisticaVendas estatisticaVendas = new EstatisticaVendas(
                Date.valueOf(LocalDate.of(ano, mes, 1)),
                quantidadePedidos,
                totalVendido,
                produtoMaisVendido
        );
        getRelatoriosEmitidos().add("Balanco mensal " + estatisticaVendas);
        return estatisticaVendas;
    }

    public ControleVendas getControleVendas() {
        return controleVendas;
    }

    public void setControleVendas(ControleVendas controleVendas) {
        this.controleVendas = controleVendas;
    }

    public List<String> getRelatoriosEmitidos() {
        if (relatoriosEmitidos == null) {
            relatoriosEmitidos = new ArrayList<>();
        }
        return relatoriosEmitidos;
    }

    public void setRelatoriosEmitidos(List<String> relatoriosEmitidos) {
        this.relatoriosEmitidos = new ArrayList<>();
        if (relatoriosEmitidos != null) {
            this.relatoriosEmitidos.addAll(relatoriosEmitidos);
        }
    }

    // polimorfismo sobrescrevo toString para mostrar relatorios emitidos
    @Override
    public String toString() {
        return "RelatorioVendas{"
                + "relatoriosEmitidos=" + getRelatoriosEmitidos()
                + '}';
    }
}
