package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.ListarEstoque.listarEstoque;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerInteiro.lerInteiro;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerDouble.lerDouble;

public class LerReceitaProduto {
    public static Map<Integer, Double> lerReceitaProduto(Scanner scanner, ControleEstoque controleEstoque) {
        Map<Integer, Double> receita = new LinkedHashMap<>();

        System.out.println("informe os ingredientes da receita. digite 0 para terminar");
        listarEstoque(controleEstoque);
        while (true) {
            int idIngrediente = lerInteiro(scanner, "id do ingrediente: ");
            if (idIngrediente == 0) {
                break;
            }
            double quantidade = lerDouble(scanner, "quantidade usada por unidade do produto: ");
            receita.put(idIngrediente, quantidade);
        }

        return receita;
    }

}
