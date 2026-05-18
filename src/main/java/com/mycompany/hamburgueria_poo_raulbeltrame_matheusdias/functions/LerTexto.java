package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.util.Scanner;

public class LerTexto {
    public static String lerTexto(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            if (!scanner.hasNextLine()) {
                EstadoSistema.encerrarSistema = true;
                return "";
            }
            String texto = scanner.nextLine().trim();
            if (!texto.isBlank()) {
                return texto;
            }
            System.out.println("valor obrigatorio");
        }
    }

}
