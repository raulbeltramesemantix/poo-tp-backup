package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.ProximoIdRegiao.proximoIdRegiao;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerTexto.lerTexto;

public class CadastrarRegiaoEntrega {
    public static void cadastrarRegiaoEntrega(Scanner scanner, ControleEntregas controleEntregas) {
        int id = proximoIdRegiao(controleEntregas);
        String nomeRegiao = lerTexto(scanner, "nome da regiao: ");
        String bairros = lerTexto(scanner, "bairros atendidos: ");
        RegiaoEntrega regiaoEntrega = new RegiaoEntrega(id, nomeRegiao, bairros);
        controleEntregas.getRegioesEntrega().add(regiaoEntrega);
        System.out.println("regiao cadastrada: " + regiaoEntrega);
    }

}
