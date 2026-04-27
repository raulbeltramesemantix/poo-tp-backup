package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

import java.util.ArrayList;
import java.util.List;

//concentro o cadastro de usuarios em um ponto do sistema
public class CadastroUsuarios {

    // agregacao o cadastro guarda varios funcionarios
    private List<Funcionario> funcionarios;
    // mantenho associacao com um administrador principal do sistema
    private Administrador administrador;
    // agregacao guardo administradores extras cadastrados pelo proprio admin
    private List<Administrador> administradores;

    // construtor padrao que inicia funcionarios e administradores extras
    public CadastroUsuarios() {
        this.funcionarios = new ArrayList<>();
        this.administradores = new ArrayList<>();
    }

    //incluo um funcionario no cadastro
    public Funcionario incluirFuncionario(int idUsuario, String nome, String login, String senha) {
        Funcionario funcionario = new Funcionario(idUsuario, nome, login, senha);
        funcionarios.add(funcionario);
        return funcionario;
    }

    //removo um funcionario pelo id
    public Funcionario removerFuncionario(int id) {
        for (int indice = 0; indice < getFuncionarios().size(); indice++) {
            Funcionario funcionario = getFuncionarios().get(indice);
            if (funcionario.getIdUsuario() == id) {
                getFuncionarios().remove(indice);
                return funcionario;
            }
        }
        return null;
    }

    //busco um funcionario pelo id
    public Funcionario buscarFuncionario(int id) {
        for (Funcionario funcionario : getFuncionarios()) {
            if (funcionario.getIdUsuario() == id) {
                return funcionario;
            }
        }
        return null;
    }

    // altero um funcionario do cadastro
    public void alterarFuncionario(int id, String nome, String login, String senha) {
        Funcionario funcionario = buscarFuncionario(id);
        if (funcionario != null) {
            funcionario.alterarDados(nome, login, senha);
        }
    }

    //incluo ou troco o administrador principal
    public Administrador incluirAdministrador(int idUsuario, String nome, String login, String senha) {
        administrador = new Administrador(idUsuario, nome, login, senha);
        return administrador;
    }

    //incluo um novo administrador sem remover o administrador principal
    public Administrador incluirNovoAdministrador(int idUsuario, String nome, String login, String senha) {
        Administrador novoAdministrador = new Administrador(idUsuario, nome, login, senha);
        getAdministradores().add(novoAdministrador);
        return novoAdministrador;
    }

    //busco administrador principal ou extra pelo id
    public Administrador buscarAdministrador(int id) {
        if (administrador != null && administrador.getIdUsuario() == id) {
            return administrador;
        }
        for (Administrador item : getAdministradores()) {
            if (item.getIdUsuario() == id) {
                return item;
            }
        }
        return null;
    }

    //removo apenas administradores extras, mantendo sempre o principal
    public Administrador removerAdministrador(int id) {
        for (int indice = 0; indice < getAdministradores().size(); indice++) {
            Administrador item = getAdministradores().get(indice);
            if (item.getIdUsuario() == id) {
                getAdministradores().remove(indice);
                return item;
            }
        }
        return null;
    }

    // altero os dados do administrador
    public void alterarAdministrador(int id, String nome, String login, String senha) {
        Administrador administradorEncontrado = buscarAdministrador(id);
        if (administradorEncontrado != null) {
            administradorEncontrado.alterarDados(nome, login, senha);
        }
    }

    public List<Funcionario> getFuncionarios() {
        if (funcionarios == null) {
            funcionarios = new ArrayList<>();
        }
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = new ArrayList<>();
        if (funcionarios != null) {
            this.funcionarios.addAll(funcionarios);
        }
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public List<Administrador> getAdministradores() {
        if (administradores == null) {
            administradores = new ArrayList<>();
        }
        return administradores;
    }

    public void setAdministradores(List<Administrador> administradores) {
        this.administradores = new ArrayList<>();
        if (administradores != null) {
            this.administradores.addAll(administradores);
        }
    }

    // polimorfismo sobrescrevo toString para mostrar usuarios cadastrados
    @Override
    public String toString() {
        return "CadastroUsuarios{"
                + "funcionarios=" + getFuncionarios()
                + ", administrador=" + administrador
                + ", administradores=" + getAdministradores()
                + '}';
    }
}
