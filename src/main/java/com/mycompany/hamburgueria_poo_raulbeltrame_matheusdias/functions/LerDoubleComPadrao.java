package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerTextoLivre.lerTextoLivre;

public class LerDoubleComPadrao {
    public static double lerDoubleComPadrao(Scanner scanner, String mensagem, double padrao) {
        while (true) {
            String texto = lerTextoLivre(scanner, mensagem).replace(",", "");
            if (EstadoSistema.encerrarSistema && texto.isBlank()) {
                return padrao;
            }
            if (texto.isBlank()) {
                return padrao;
            }
            try {
                return Double.parseDouble(texto);
            } catch (NumberFormatException excecao) {
                System.out.println("informe um numero decimal");
            }
        }
    }

}
