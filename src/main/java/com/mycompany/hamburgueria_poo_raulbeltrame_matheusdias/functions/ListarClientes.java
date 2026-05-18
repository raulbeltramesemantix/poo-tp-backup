package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

public class ListarClientes {
    public static void listarClientes(CadastroClientes cadastroClientes) {
        if (cadastroClientes.getClientes().isEmpty()) {
            System.out.println("nenhum cliente cadastrado");
            return;
        }
        for (Cliente cliente : cadastroClientes.getClientes()) {
            System.out.println(cliente);
        }
    }

}
