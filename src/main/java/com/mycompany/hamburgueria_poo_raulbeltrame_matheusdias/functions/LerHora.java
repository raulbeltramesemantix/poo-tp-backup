package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerTexto.lerTexto;

public class LerHora {
    public static Time lerHora(Scanner scanner, String mensagem) {
        while (true) {
            String texto = lerTexto(scanner, mensagem);
            if (EstadoSistema.encerrarSistema && texto.isBlank()) {
                return Time.valueOf(LocalTime.now().withSecond(0).withNano(0));
            }
            try {
                return Time.valueOf(LocalTime.parse(texto));
            } catch (IllegalArgumentException excecao) {
                System.out.println("hora invalida. use HH:MM");
            }
        }
    }

}
