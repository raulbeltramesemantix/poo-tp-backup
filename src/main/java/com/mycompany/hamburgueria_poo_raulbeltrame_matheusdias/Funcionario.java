package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

// aplico heranca ao especializar usuario para o papel de funcionario
public class Funcionario extends Usuario {

    // construtor padrao usado para criar funcionario vazio
    public Funcionario() {
    }

    // construtor completo que usa super para chamar usuario
    // uso super para reaproveitar o construtor da classe mae
    public Funcionario(int idUsuario, String nome, String login, String senha) {
        super(idUsuario, nome, login, senha);
    }

    //mostro os dados do funcionario
    public void consultarDados() {
        System.out.println(this);
    }

    // altero os dados do funcionario
    public void alterarDados(String nome, String login, String senha) {
        setNome(nome);
        setLogin(login);
        if (senha != null && !senha.isBlank()) {
            alterarSenha(senha);
        }
    }

    // polimorfismo Funcionario reaproveita e especializa o toString de Usuario
    @Override
    public String toString() {
        return "Funcionario{" + super.toString() + '}';
    }
}
