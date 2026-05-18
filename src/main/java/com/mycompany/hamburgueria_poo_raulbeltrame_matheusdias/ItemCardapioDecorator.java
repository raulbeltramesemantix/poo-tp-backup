package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

// classe abstrata do decorator guarda o item base e delega o comportamento comum
public abstract class ItemCardapioDecorator implements ItemCardapio {

    // associacao o decorator aponta para outro ItemCardapio que sera incrementado
    protected ItemCardapio itemDecorado;

    // construtor recebe o componente que sera decorado
    public ItemCardapioDecorator(ItemCardapio itemDecorado) {
        if (itemDecorado == null) {
            throw new IllegalArgumentException("item decorado nao pode ser nulo");
        }
        this.itemDecorado = itemDecorado;
    }

    // polimorfismo pq sobreescrevo e delego o nome para o item decorado
    @Override
    public String getNome() {
        return itemDecorado.getNome();
    }

    // delego a descricao para o item decorado
    @Override
    public String getDescricao() {
        return itemDecorado.getDescricao();
    }

    // delego o valor para o item decorado
    @Override
    public double calcularValor() {
        return itemDecorado.calcularValor();
    }
}
