package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

public class ProximoIdUsuario {
    public static int proximoIdUsuario(CadastroUsuarios cadastroUsuarios) {
        int maiorId = 0;
        if (cadastroUsuarios.getAdministrador() != null) {
            maiorId = cadastroUsuarios.getAdministrador().getIdUsuario();
        }
        for (Administrador administrador : cadastroUsuarios.getAdministradores()) {
            if (administrador.getIdUsuario() > maiorId) {
                maiorId = administrador.getIdUsuario();
            }
        }
        for (Funcionario funcionario : cadastroUsuarios.getFuncionarios()) {
            if (funcionario.getIdUsuario() > maiorId) {
                maiorId = funcionario.getIdUsuario();
            }
        }
        return maiorId + 1;
    }

}
