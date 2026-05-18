package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

// decorator concreto: adiciona um Adicional ao item de cardapio sem alterar Produto
public class AdicionalDecorator extends ItemCardapioDecorator {

    // associacao: o decorator usa os dados do adicional escolhido no pedido
    private Adicional adicional;

    // construtor liga o item base ao adicional que sera somado
    public AdicionalDecorator(ItemCardapio itemDecorado, Adicional adicional) {
        super(itemDecorado);
        this.adicional = adicional;
    }

    // polimorfismo: complemento o nome do item com o nome do adicional
    @Override
    public String getNome() {
        if (adicional == null || adicional.getNome() == null || adicional.getNome().isBlank()) {
            return super.getNome();
        }
        return super.getNome() + " + " + adicional.getNome();
    }

    // polimorfismo: complemento a descricao para mostrar a personalizacao do lanche
    @Override
    public String getDescricao() {
        if (adicional == null || adicional.getNome() == null || adicional.getNome().isBlank()) {
            return super.getDescricao();
        }
        String descricaoBase = super.getDescricao();
        String descricaoAdicional = "Adicional: " + adicional.getNome();
        if (descricaoBase == null || descricaoBase.isBlank()) {
            return descricaoAdicional;
        }
        return descricaoBase + " | " + descricaoAdicional;
    }

    // polimorfismo: somo o valor do adicional ao valor acumulado pelo item decorado
    @Override
    public double calcularValor() {
        double valorAdicional = adicional != null ? adicional.getValor() : 0.0;
        return super.calcularValor() + valorAdicional;
    }
}
