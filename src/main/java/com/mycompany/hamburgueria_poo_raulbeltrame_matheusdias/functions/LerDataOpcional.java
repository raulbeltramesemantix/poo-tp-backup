package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerTextoLivre.lerTextoLivre;

public class LerDataOpcional {
    public static Date lerDataOpcional(Scanner scanner, String mensagem) {
        while (true) {
            String texto = lerTextoLivre(scanner, mensagem);
            if (EstadoSistema.encerrarSistema && texto.isBlank()) {
                return null;
            }
            if (texto.isBlank()) {
                return null;
            }
            try {
                return Date.valueOf(LocalDate.parse(texto));
            } catch (IllegalArgumentException excecao) {
                System.out.println("data invalida. use AAAA-MM-DD");
            }
        }
    }

}
