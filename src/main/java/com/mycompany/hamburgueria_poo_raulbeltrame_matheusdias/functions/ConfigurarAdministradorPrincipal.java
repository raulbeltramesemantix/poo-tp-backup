package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

import static com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions.ProximoIdUsuario.proximoIdUsuario;

public class ConfigurarAdministradorPrincipal {

    private static final String LOGIN_UNICO = "Raul";
    private static final String SENHA_UNICA = "6504";

    public static void configurarAdministradorPrincipal(CadastroUsuarios cadastroUsuarios) {
        int idUsuario = cadastroUsuarios.getAdministrador() != null
                ? cadastroUsuarios.getAdministrador().getIdUsuario()
                : proximoIdUsuario(cadastroUsuarios);
        cadastroUsuarios.setAdministrador(new Administrador(idUsuario, "Raul", LOGIN_UNICO, SENHA_UNICA));
    }

}
