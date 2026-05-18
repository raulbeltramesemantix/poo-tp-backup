package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

public class ListarUsuarios {
    public static void listarUsuarios(CadastroUsuarios cadastroUsuarios) {
        System.out.println("administrador:");
        System.out.println(cadastroUsuarios.getAdministrador());
        System.out.println("administradores extras:");
        for (Administrador administrador : cadastroUsuarios.getAdministradores()) {
            System.out.println(administrador);
        }
        System.out.println("funcionarios:");
        for (Funcionario funcionario : cadastroUsuarios.getFuncionarios()) {
            System.out.println(funcionario);
        }
    }

}
