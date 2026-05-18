package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.functions;

import com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias.*;

public class MostrarResumoSistema {
    public static void mostrarResumoSistema(
            SistemaDelivery sistemaDelivery,
            CadastroClientes cadastroClientes,
            CadastroProdutos cadastroProdutos,
            ControleVendas controleVendas,
            ControleEntregas controleEntregas
    ) {
        System.out.println(sistemaDelivery);
        System.out.println("clientes cadastrados: " + cadastroClientes.getClientes().size());
        System.out.println("produtos cadastrados: " + cadastroProdutos.getProdutos().size());
        System.out.println("vendas registradas: " + controleVendas.getVendas().size());
        System.out.println("motoqueiros cadastrados: " + controleEntregas.getMotoqueiros().size());
        System.out.println("regioes cadastradas: " + controleEntregas.getRegioesEntrega().size());
    }

}
