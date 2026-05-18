package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerTextoLivre.lerTextoLivre;

public class LerDouble {
    public static double lerDouble(Scanner scanner, String mensagem) {
        while (true) {
            String texto = lerTextoLivre(scanner, mensagem).replace(",", "");
            if (EstadoSistema.encerrarSistema && texto.isBlank()) {
                return 0.0;
            }
            try {
                return Double.parseDouble(texto);
            } catch (NumberFormatException excecao) {
                System.out.println("informe um numero decimal");
            }
        }
    }

}
