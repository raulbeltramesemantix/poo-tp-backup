package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerTextoLivre.lerTextoLivre;

public class LerTextoComPadrao {
    public static String lerTextoComPadrao(Scanner scanner, String mensagem, String padrao) {
        String texto = lerTextoLivre(scanner, mensagem);
        if (EstadoSistema.encerrarSistema && texto.isBlank()) {
            return padrao;
        }
        return texto.isBlank() ? padrao : texto;
    }

}
