package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

// interface do padrao decorator onde trato produto e adicionais decorados pelo mesmo contrato
public interface ItemCardapio {

    // polimorfismo cada componente sabe informar o proprio nome
    String getNome();

    // polimorfismo cada componente monta sua descricao para o pedido
    String getDescricao();

    // polimorfismo produto e decorators participam do calculo do valor final
    double calcularValor();
}
