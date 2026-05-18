package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.ProximoIdIngrediente.proximoIdIngrediente;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerTexto.lerTexto;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerDouble.lerDouble;

public class IncluirIngrediente {
    public static void incluirIngrediente(Scanner scanner, ControleEstoque controleEstoque) {
        // Ingrediente mantem unidade de medida e quantidade minima, como pedido no PDF
        int id = proximoIdIngrediente(controleEstoque);
        String nome = lerTexto(scanner, "nome do ingrediente: ");
        double quantidadeAtual = lerDouble(scanner, "quantidade atual: ");
        String unidadeMedida = lerTexto(scanner, "unidade de medida: ");
        double quantidadeMinima = lerDouble(scanner, "quantidade minima para alerta: ");
        Ingrediente ingrediente = new Ingrediente(id, nome, quantidadeAtual, unidadeMedida, quantidadeMinima);
        controleEstoque.getEstoque().getIngredientes().add(ingrediente);
        System.out.println("ingrediente incluido: " + ingrediente);
    }

}
