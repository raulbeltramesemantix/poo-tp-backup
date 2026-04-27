package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

import java.util.ArrayList;
import java.util.List;

// faco agregacao de ingredientes dentro do estoque
public class Estoque {

    // agregacao estoque agrupa varios ingredientes cadastrados
    private List<Ingrediente> ingredientes;

    public Estoque() {
        this.ingredientes = new ArrayList<>();
    }

    public Estoque(List<Ingrediente> ingredientes) {
        this();
        if (ingredientes != null) {
            this.ingredientes.addAll(ingredientes);
        }
    }

    // procuro um ingrediente pelo id
    public Ingrediente verificarIngrediente(int id) {
        for (Ingrediente ingrediente : getIngredientes()) {
            if (ingrediente.getIdIngrediente() == id) {
                return ingrediente;
            }
        }
        return null;
    }

    // ajusto a quantidade atual do ingrediente
    public void atualizarQuantidade(int id, double quantidade) {
        Ingrediente ingrediente = verificarIngrediente(id);
        if (ingrediente != null) {
            ingrediente.setQuantidadeAtual(quantidade);
        }
    }

    // aviso quando o minimo e atingido
    public void emitirAlertaMinimo() {
        for (Ingrediente ingrediente : getIngredientes()) {
            if (ingrediente.getQuantidadeAtual() <= ingrediente.getQuantidadeMinima()) {
                System.out.println("Alerta estoque minimo " + ingrediente.getNome());
            }
        }
    }

    public List<Ingrediente> getIngredientes() {
        if (ingredientes == null) {
            ingredientes = new ArrayList<>();
        }
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = new ArrayList<>();
        if (ingredientes != null) {
            this.ingredientes.addAll(ingredientes);
        }
    }

    // polimorfismo sobrescrevo toString para imprimir todos os ingredientes
    @Override
    public String toString() {
        return "Estoque{"
                + "ingredientes=" + getIngredientes()
                + '}';
    }
}
