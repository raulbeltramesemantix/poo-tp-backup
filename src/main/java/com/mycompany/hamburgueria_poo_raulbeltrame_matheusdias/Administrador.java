package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

// aplico heranca ao especializar usuario para o papel de administrador
public class Administrador extends Usuario {

    // construtor padrao usado para criar administrador vazio
    public Administrador() {
    }

    // construtor completo que usa super para chamar usuario
    //uso super para reaproveitar o construtor da classe mae
    public Administrador(int idUsuario, String nome, String login, String senha) {
        super(idUsuario, nome, login, senha);
    }

    //mostro os dados do administrador
    public void consultarDados() {
        System.out.println(this);
    }

    // altero os dados do administrador
    public void alterarDados(String nome, String login, String senha) {
        setNome(nome);
        setLogin(login);
        if (senha != null && !senha.isBlank()) {
            alterarSenha(senha);
        }
    }

    // polimorfismo adm imprime seus dados usando o toString da classe mae
    @Override
    public String toString() {
        return "Administrador{" + super.toString() + '}';
    }
}
