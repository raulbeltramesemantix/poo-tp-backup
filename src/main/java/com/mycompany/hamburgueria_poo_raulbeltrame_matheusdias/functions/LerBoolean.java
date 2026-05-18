package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerTextoLivre.lerTextoLivre;

public class LerBoolean {
    public static boolean lerBoolean(Scanner scanner, String mensagem) {
        while (true) {
            String texto = lerTextoLivre(scanner, mensagem).toLowerCase();
            if (EstadoSistema.encerrarSistema && texto.isBlank()) {
                return false;
            }
            if (texto.equals("s") || texto.equals("sim")) {
                return true;
            }
            if (texto.equals("n") || texto.equals("nao")) {
                return false;
            }
            System.out.println("digite s ou n");
        }
    }

}
