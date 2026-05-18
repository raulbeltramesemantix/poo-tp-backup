package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerTexto.lerTexto;

public class LerData {
    public static Date lerData(Scanner scanner, String mensagem) {
        while (true) {
            String texto = lerTexto(scanner, mensagem);
            if (EstadoSistema.encerrarSistema && texto.isBlank()) {
                return Date.valueOf(LocalDate.now());
            }
            try {
                return Date.valueOf(LocalDate.parse(texto));
            } catch (IllegalArgumentException excecao) {
                System.out.println("data invalida. use AAAA-MM-DD");
            }
        }
    }

}
