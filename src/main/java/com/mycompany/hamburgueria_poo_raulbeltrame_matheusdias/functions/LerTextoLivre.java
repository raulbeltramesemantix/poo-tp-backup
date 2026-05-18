package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.util.Scanner;

public class LerTextoLivre {
    public static String lerTextoLivre(Scanner scanner, String mensagem) {
        System.out.print(mensagem);
        if (!scanner.hasNextLine()) {
            EstadoSistema.encerrarSistema = true;
            return "";
        }
        return scanner.nextLine().trim();
    }

}
