package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

// separo a autenticacao para nao espalhar essa regra nas outras classes
public class Autenticador {

    // associacao o autenticador consulta o cadastro de usuarios
    private CadastroUsuarios cadastroUsuarios;

    public Autenticador() {
    }

    public Autenticador(CadastroUsuarios cadastroUsuarios) {
        this.cadastroUsuarios = cadastroUsuarios;
    }

    // procuro usuario pelo login e senha
    public Usuario autenticar(String login, String senha) {
        if (cadastroUsuarios != null && cadastroUsuarios.getAdministrador() != null
                && cadastroUsuarios.getAdministrador().autenticar(login, senha)) {
            return cadastroUsuarios.getAdministrador();
        }
        if (cadastroUsuarios != null) {
            for (Administrador administrador : cadastroUsuarios.getAdministradores()) {
                if (administrador.autenticar(login, senha)) {
                    return administrador;
                }
            }
            for (Funcionario funcionario : cadastroUsuarios.getFuncionarios()) {
                if (funcionario.autenticar(login, senha)) {
                    return funcionario;
                }
            }
        }
        return null;
    }

    public CadastroUsuarios getCadastroUsuarios() {
        return cadastroUsuarios;
    }

    public void setCadastroUsuarios(CadastroUsuarios cadastroUsuarios) {
        this.cadastroUsuarios = cadastroUsuarios;
    }

    // polimorfismo sobrescrevo toString para depurar a autenticacao
    @Override
    public String toString() {
        return "Autenticador{"
                + "cadastroUsuarios=" + cadastroUsuarios
                + '}';
    }
}
