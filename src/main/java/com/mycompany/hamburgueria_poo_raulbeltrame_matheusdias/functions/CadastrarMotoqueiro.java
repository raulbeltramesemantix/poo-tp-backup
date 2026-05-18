package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.ProximoIdMotoqueiro.proximoIdMotoqueiro;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerTexto.lerTexto;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerBoolean.lerBoolean;

public class CadastrarMotoqueiro {
    public static void cadastrarMotoqueiro(Scanner scanner, ControleEntregas controleEntregas) {
        int id = proximoIdMotoqueiro(controleEntregas);
        String nome = lerTexto(scanner, "nome do motoqueiro: ");
        String telefone = lerTexto(scanner, "telefone: ");
        boolean ativo = lerBoolean(scanner, "motoqueiro ativo? (s/n): ");
        Motoqueiro motoqueiro = new Motoqueiro(id, nome, telefone, ativo);
        controleEntregas.getMotoqueiros().add(motoqueiro);
        System.out.println("motoqueiro cadastrado: " + motoqueiro);
    }

}
