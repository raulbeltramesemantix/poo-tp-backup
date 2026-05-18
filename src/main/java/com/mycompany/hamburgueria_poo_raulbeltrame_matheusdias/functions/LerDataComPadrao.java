package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.sql.Date;
import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerDataOpcional.lerDataOpcional;

public class LerDataComPadrao {
    public static Date lerDataComPadrao(Scanner scanner, String mensagem, Date padrao) {
        Date data = lerDataOpcional(scanner, mensagem);
        return data == null ? padrao : data;
    }

}
