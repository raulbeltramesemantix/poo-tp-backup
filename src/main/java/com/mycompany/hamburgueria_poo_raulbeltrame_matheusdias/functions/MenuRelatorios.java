package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.sql.Date;
import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerInteiro.lerInteiro;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerData.lerData;

public class MenuRelatorios {
    public static void menuRelatorios(Scanner scanner, RelatorioVendas relatorioVendas) {
        System.out.println();
        System.out.println("---- relatorios ----");
        System.out.println("1 - relatorio do dia");
        System.out.println("2 - relatorio do mes");
        System.out.println("3 - balanco mensal");
        int opcao = lerInteiro(scanner, "opcao: ");

        switch (opcao) {
            case 1 -> {
                Date data = lerData(scanner, "data (AAAA-MM-DD): ");
                relatorioVendas.emitirRelatorioDia(data);
            }
            case 2 -> {
                int mes = lerInteiro(scanner, "mes: ");
                int ano = lerInteiro(scanner, "ano: ");
                relatorioVendas.emitirRelatorioMes(mes, ano);
            }
            case 3 -> {
                int mes = lerInteiro(scanner, "mes: ");
                int ano = lerInteiro(scanner, "ano: ");
                EstatisticaVendas estatisticaVendas = relatorioVendas.gerarBalancoMensal(mes, ano);
                System.out.println(estatisticaVendas);
            }
            default -> System.out.println("opcao invalida");
        }
    }

}
