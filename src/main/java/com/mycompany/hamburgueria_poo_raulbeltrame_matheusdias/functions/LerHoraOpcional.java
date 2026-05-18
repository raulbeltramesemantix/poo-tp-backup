package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerTextoLivre.lerTextoLivre;

public class LerHoraOpcional {
    public static Time lerHoraOpcional(Scanner scanner, String mensagem) {
        while (true) {
            String texto = lerTextoLivre(scanner, mensagem);
            if (EstadoSistema.encerrarSistema && texto.isBlank()) {
                return null;
            }
            if (texto.isBlank()) {
                return null;
            }
            try {
                return Time.valueOf(LocalTime.parse(texto));
            } catch (IllegalArgumentException excecao) {
                System.out.println("hora invalida. use HH:MM");
            }
        }
    }

}
