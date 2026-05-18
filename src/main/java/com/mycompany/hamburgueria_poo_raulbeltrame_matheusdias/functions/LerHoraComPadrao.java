package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.sql.Time;
import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerHoraOpcional.lerHoraOpcional;

public class LerHoraComPadrao {
    public static Time lerHoraComPadrao(Scanner scanner, String mensagem, Time padrao) {
        Time hora = lerHoraOpcional(scanner, mensagem);
        return hora == null ? padrao : hora;
    }

}
