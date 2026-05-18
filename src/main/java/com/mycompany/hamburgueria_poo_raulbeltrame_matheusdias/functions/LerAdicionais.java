package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerTexto.lerTexto;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerInteiro.lerInteiro;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerDouble.lerDouble;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerBoolean.lerBoolean;

public class LerAdicionais {
    public static List<Adicional> lerAdicionais(Scanner scanner) {
        List<Adicional> adicionais = new ArrayList<>();
        while (lerBoolean(scanner, "adicionar adicional? (s/n): ")) {
            int idAdicional = lerInteiro(scanner, "id do adicional: ");
            String nome = lerTexto(scanner, "nome do adicional: ");
            double valor = lerDouble(scanner, "valor do adicional: ");
            adicionais.add(new Adicional(idAdicional, nome, valor));
        }
        return adicionais;
    }

}
