package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerTextoLivre.lerTextoLivre;

public class LerInteiro {
    public static int lerInteiro(Scanner scanner, String mensagem) {
        while (true) {
            String texto = lerTextoLivre(scanner, mensagem);
            if (EstadoSistema.encerrarSistema && texto.isBlank()) {
                return 0;
            }
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException excecao) {
                System.out.println("informe um numero inteiro");
            }
        }
    }

}
