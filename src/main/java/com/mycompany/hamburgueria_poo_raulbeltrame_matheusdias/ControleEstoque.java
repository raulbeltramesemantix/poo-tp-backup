package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// centralizo as regras de estoque em um ponto do sistema
public class ControleEstoque {

    // associacao: controle usa o estoque para consultar e alterar ingredientes
    private Estoque estoque;
    // agregacao guardo todos os recebimentos feitos no estoque
    private List<RecebimentoEstoque> recebimentosEstoque;
    //guardo um historico dinamico das acoes feitas no estoque
    private List<String> acoesEstoque;
    // agregacao fornecedores ficam cadastrados no controle de estoque
    private List<Fornecedor> fornecedores;

    // construtor padrao que cria um estoque novo
    public ControleEstoque() {
        this(new Estoque());
    }

    // construtor completo para associar um estoque existente ao controle
    public ControleEstoque(Estoque estoque) {
        this.estoque = estoque;
        this.recebimentosEstoque = new ArrayList<>();
        this.acoesEstoque = new ArrayList<>();
        this.fornecedores = new ArrayList<>();
    }

    //pego todos os ingredientes do estoque
    public List<Ingrediente> verificarIngredientes() {
        return new ArrayList<>(estoque.getIngredientes());
    }

    //baixo ingredientes conforme itens vendidos
    public void atualizarAposPedido(Pedido pedido) {
        for (ItemPedido itemPedido : pedido.getItensPedido()) {
            Produto produto = itemPedido.getProduto();
            if (produto == null) {
                continue;
            }
            for (Map.Entry<Integer, Double> entradaIngrediente : produto.getQuantidadesIngredientesPorId().entrySet()) {
                Ingrediente ingrediente = estoque.verificarIngrediente(entradaIngrediente.getKey());
                if (ingrediente != null) {
                    double quantidadeConsumida = entradaIngrediente.getValue() * itemPedido.getQuantidade();
                    ingrediente.baixarQuantidade(quantidadeConsumida);
                    acoesEstoque.add("Baixei " + quantidadeConsumida + " de " + ingrediente.getNome() + " no pedido " + pedido.getIdPedido());
                }
            }
        }
        estoque.emitirAlertaMinimo();
    }

    // recebo item do fornecedor e atualizo estoque
    public RecebimentoEstoque receberFornecedor(Fornecedor fornecedor, int idIngrediente, double quantidade) {
        if (fornecedor != null && !getFornecedores().stream().anyMatch(item -> item.getIdFornecedor() == fornecedor.getIdFornecedor())) {
            getFornecedores().add(fornecedor);
        }
        if (fornecedor != null) {
            fornecedor.fornecerIngrediente();
        }
        Ingrediente ingrediente = estoque.verificarIngrediente(idIngrediente);
        if (ingrediente != null) {
            ingrediente.reporQuantidade(quantidade);
        }
        RecebimentoEstoque recebimentoEstoque = new RecebimentoEstoque(
                proximoIdRecebimento(),
                Date.valueOf(LocalDate.now()),
                "Recebimento do ingrediente " + idIngrediente + " na quantidade " + quantidade,
                fornecedor != null ? fornecedor.getIdFornecedor() : 0,
                fornecedor != null ? fornecedor.getNome() : ""
        );
        recebimentoEstoque.registrarEntrada();
        getRecebimentosEstoque().add(recebimentoEstoque);
        getAcoesEstoque().add("Recebi estoque do fornecedor " + recebimentoEstoque.getNomeFornecedor());
        estoque.emitirAlertaMinimo();
        return recebimentoEstoque;
    }

    private int proximoIdRecebimento() {
        int maiorId = 0;
        for (RecebimentoEstoque recebimentoEstoque : getRecebimentosEstoque()) {
            if (recebimentoEstoque.getIdRecebimento() > maiorId) {
                maiorId = recebimentoEstoque.getIdRecebimento();
            }
        }
        return maiorId + 1;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public List<RecebimentoEstoque> getRecebimentosEstoque() {
        if (recebimentosEstoque == null) {
            recebimentosEstoque = new ArrayList<>();
        }
        return recebimentosEstoque;
    }

    public void setRecebimentosEstoque(List<RecebimentoEstoque> recebimentosEstoque) {
        this.recebimentosEstoque = new ArrayList<>();
        if (recebimentosEstoque != null) {
            this.recebimentosEstoque.addAll(recebimentosEstoque);
        }
    }

    public List<String> getAcoesEstoque() {
        if (acoesEstoque == null) {
            acoesEstoque = new ArrayList<>();
        }
        return acoesEstoque;
    }

    public void setAcoesEstoque(List<String> acoesEstoque) {
        this.acoesEstoque = new ArrayList<>();
        if (acoesEstoque != null) {
            this.acoesEstoque.addAll(acoesEstoque);
        }
    }

    public List<Fornecedor> getFornecedores() {
        if (fornecedores == null) {
            fornecedores = new ArrayList<>();
        }
        return fornecedores;
    }

    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = new ArrayList<>();
        if (fornecedores != null) {
            this.fornecedores.addAll(fornecedores);
        }
    }

    // polimorfismo sobrescrevo toString para mostrar estoque e historicos
    @Override
    public String toString() {
        return "ControleEstoque{"
                + "estoque=" + estoque
                + ", recebimentosEstoque=" + getRecebimentosEstoque()
                + ", acoesEstoque=" + getAcoesEstoque()
                + ", fornecedores=" + getFornecedores()
                + '}';
    }
}
