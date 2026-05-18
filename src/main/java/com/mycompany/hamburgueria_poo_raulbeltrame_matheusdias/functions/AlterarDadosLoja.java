package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerTextoComPadrao.lerTextoComPadrao;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerDoubleComPadrao.lerDoubleComPadrao;

public class AlterarDadosLoja {
    public static void alterarDadosLoja(Scanner scanner, DadosLoja dadosLoja) {
        String nomeLoja = lerTextoComPadrao(scanner, "nome da loja [" + dadosLoja.getNomeLoja() + "]: ", dadosLoja.getNomeLoja());
        String telefone = lerTextoComPadrao(scanner, "telefone [" + dadosLoja.getTelefone() + "]: ", dadosLoja.getTelefone());
        String endereco = lerTextoComPadrao(scanner, "endereco [" + dadosLoja.getEndereco() + "]: ", dadosLoja.getEndereco());
        String horario = lerTextoComPadrao(scanner, "horario [" + dadosLoja.getHorarioFuncionamento() + "]: ", dadosLoja.getHorarioFuncionamento());
        double taxa = lerDoubleComPadrao(scanner, "taxa de entrega [" + dadosLoja.getTaxaEntrega() + "]: ", dadosLoja.getTaxaEntrega());
        dadosLoja.alterarDadosLoja(nomeLoja, telefone, endereco, horario, taxa);
        System.out.println("dados da loja alterados");
    }

}
