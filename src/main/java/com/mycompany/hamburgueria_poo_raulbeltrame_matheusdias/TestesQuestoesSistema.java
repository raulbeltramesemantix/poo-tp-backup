package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class TestesQuestoesSistema {

    public static void main(String[] args) {
        // questao 18
        {
            CadastroUsuarios cadastroUsuarios = new CadastroUsuarios();
            cadastroUsuarios.incluirFuncionario(1, "Raul", "raul", "123");
            cadastroUsuarios.incluirFuncionario(2, "Matheus", "matheus", "123");
            cadastroUsuarios.incluirFuncionario(3, "Atendente", "atendente", "123");

            ArrayList<Funcionario> funcionarios = new ArrayList<>(cadastroUsuarios.getFuncionarios());

            System.out.println("q18 percorrendo com Iterator");
            Iterator<Funcionario> iterator = funcionarios.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }

            System.out.println();
            System.out.println("q18 percorrendo com foreach");
            for (Funcionario funcionario : funcionarios) {
                System.out.println(funcionario);
            }
        }

        System.out.println();

        // questao 19
        {
            ArrayList<Cliente> clientes = new ArrayList<>();
            clientes.add(new Cliente(3, "Bruno", "3333-3333", "Rua C", 103, Date.valueOf("2026-06-03")));
            clientes.add(new Cliente(1, "Carlos", "1111-1111", "Rua A", 101, Date.valueOf("2026-06-01")));
            clientes.add(new Cliente(2, "Ana", "2222-2222", "Rua B", 102, Date.valueOf("2026-06-02")));

            System.out.println("q19 lista original");
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }

            System.out.println();
            System.out.println("q19 Collections.sort por idCliente usando Comparator implementado em Cliente");
            Collections.sort(clientes, new Cliente());
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }

            System.out.println();
            System.out.println("q19 Collections.sort por nome usando outro Comparator da classe Cliente");
            Collections.sort(clientes, Cliente.porNome());
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }

        System.out.println();

        // questao 20
        {
            ArrayList<Cliente> clientes = new ArrayList<>();
            clientes.add(new Cliente(3, "Bruno", "3333-3333", "Rua C", 103, Date.valueOf("2026-06-03")));
            clientes.add(new Cliente(1, "Carlos", "1111-1111", "Rua A", 101, Date.valueOf("2026-06-01")));
            clientes.add(new Cliente(2, "Ana", "2222-2222", "Rua B", 102, Date.valueOf("2026-06-02")));

            CadastroClientes cadastroClientes = new CadastroClientes();
            cadastroClientes.setClientes(clientes);

            Comparator<Cliente> comparadorPorId = new Cliente();
            Cliente clienteProcuradoPorId = new Cliente(2, "", "", "", 0, null);
            Cliente clienteEncontradoFindId = cadastroClientes.find(clienteProcuradoPorId, comparadorPorId);

            Collections.sort(clientes, comparadorPorId);
            int indiceBinarySearchId = Collections.binarySearch(clientes, clienteProcuradoPorId, comparadorPorId);
            Cliente clienteEncontradoBinarySearchId = indiceBinarySearchId >= 0 ? clientes.get(indiceBinarySearchId) : null;

            System.out.println("q20 find por idCliente usando Iterator e Comparator");
            System.out.println(clienteEncontradoFindId);
            System.out.println("q20 binarySearch por idCliente apos ordenar com o mesmo Comparator");
            System.out.println("indice: " + indiceBinarySearchId);
            System.out.println(clienteEncontradoBinarySearchId);

            System.out.println();

            Comparator<Cliente> comparadorPorNome = Cliente.porNome();
            Cliente clienteProcuradoPorNome = new Cliente(0, "Bruno", "", "", 0, null);
            Cliente clienteEncontradoFindNome = cadastroClientes.find(clienteProcuradoPorNome, comparadorPorNome);

            Collections.sort(clientes, comparadorPorNome);
            int indiceBinarySearchNome = Collections.binarySearch(clientes, clienteProcuradoPorNome, comparadorPorNome);
            Cliente clienteEncontradoBinarySearchNome = indiceBinarySearchNome >= 0 ? clientes.get(indiceBinarySearchNome) : null;

            System.out.println("q20 find por nome usando Iterator e Comparator");
            System.out.println(clienteEncontradoFindNome);
            System.out.println("q20 binarySearch por nome apos ordenar com o mesmo Comparator");
            System.out.println("indice: " + indiceBinarySearchNome);
            System.out.println(clienteEncontradoBinarySearchNome);
        }
    }
}
