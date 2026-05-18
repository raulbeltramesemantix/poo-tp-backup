package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import java.util.Scanner;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.ListarEstoque.listarEstoque;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerTexto.lerTexto;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerInteiro.lerInteiro;
import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.LerDouble.lerDouble;

public class ReceberFornecedor {
    public static void receberFornecedor(Scanner scanner, ControleEstoque controleEstoque) {
        // RecebimentoEstoque registra a entrada e repoe a quantidade do Ingrediente
        listarEstoque(controleEstoque);
        int idFornecedor = lerInteiro(scanner, "id do fornecedor: ");
        String nomeFornecedor = lerTexto(scanner, "nome do fornecedor: ");
        String telefoneFornecedor = lerTexto(scanner, "telefone do fornecedor: ");
        int idIngrediente = lerInteiro(scanner, "id do ingrediente recebido: ");
        double quantidade = lerDouble(scanner, "quantidade recebida: ");
        Fornecedor fornecedor = new Fornecedor(idFornecedor, nomeFornecedor, telefoneFornecedor);
        RecebimentoEstoque recebimento = controleEstoque.receberFornecedor(fornecedor, idIngrediente, quantidade);
        System.out.println("recebimento registrado: " + recebimento);
    }

}
