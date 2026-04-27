package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

import java.util.ArrayList;
import java.util.List;

// centralizo o cadastro dos clientes em colecao dinamica
public class CadastroClientes {

    // agregacao cadastro mantem uma lista dinamica de clientes
    private List<Cliente> clientes;

    // construtor padrao que inicia a lista dinamica de clientes
    public CadastroClientes() {
        this.clientes = new ArrayList<>();
    }

    //incluo um cliente no cadastro
    public Cliente incluirCliente(String nome, String telefone, String endereco) {
        Cliente cliente = new Cliente(0, nome, telefone, endereco, 0, null);
        definirIdCliente(cliente);
        getClientes().add(cliente);
        return cliente;
    }

    //removo um cliente pelo id
    public Cliente removerCliente(int id) {
        for (int indice = 0; indice < getClientes().size(); indice++) {
            Cliente cliente = getClientes().get(indice);
            if (cliente.getIdCliente() == id) {
                getClientes().remove(indice);
                return cliente;
            }
        }
        return null;
    }

    // busco um cliente pelo id
    public Cliente buscarCliente(int id) {
        for (Cliente cliente : getClientes()) {
            if (cliente.getIdCliente() == id) {
                return cliente;
            }
        }
        return null;
    }

    // altero os dados do cliente
    public void alterarCliente(int id, String nome, String telefone, String endereco) {
        Cliente cliente = buscarCliente(id);
        if (cliente != null) {
            cliente.setNome(nome);
            cliente.setTelefone(telefone);
            cliente.setEndereco(endereco);
        }
    }

    // gero e aplico o id do cliente
    public int definirIdCliente(Cliente cliente) {
        int idGerado = GeradorId.gerarIdCliente();
        cliente.setIdCliente(idGerado);
        return idGerado;
    }

    public List<Cliente> getClientes() {
        if (clientes == null) {
            clientes = new ArrayList<>();
        }
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = new ArrayList<>();
        if (clientes != null) {
            this.clientes.addAll(clientes);
        }
    }

    // polimorfismo sobrescrevo toString para listar clientes cadastrados
    @Override
    public String toString() {
        return "CadastroClientes{"
                + "clientes=" + getClientes()
                + '}';
    }
}
