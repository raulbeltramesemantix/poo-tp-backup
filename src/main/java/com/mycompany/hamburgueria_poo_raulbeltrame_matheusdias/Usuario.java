package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

import java.util.Objects;

//aplico abstracao ao definir a base comum dos usuarios
//aplico encapsulamento ao proteger o estado com private e get e set
public abstract class Usuario {

    private int idUsuario;
    private String nome;
    private String login;
    private String senha;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nome, String login, String senha) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    // confiro login e senha informados
    public boolean autenticar(String login, String senha) {
        return Objects.equals(this.login, login) && Objects.equals(this.senha, senha);
    }

    // troco a senha do usuario
    public void alterarSenha(String novaSenha) {
        this.senha = novaSenha;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // polimorfismo sobrescrevo o toString herdado de Object
    @Override
    public String toString() {
        return "Usuario{"
                + "idUsuario=" + idUsuario
                + ", nome='" + nome + '\''
                + ", login='" + login + '\''
                + '}';
    }
}
