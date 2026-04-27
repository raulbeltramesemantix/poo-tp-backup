package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

import java.util.ArrayList;
import java.util.List;

// centralizo a logica de entrega e a ligacao com regioes e motoqueiros
public class ControleEntregas {

    // agregacao controle mantem varios motoqueiros cadastrados
    private List<Motoqueiro> motoqueiros;
    // agregacao controle mantem varias regioes de entrega
    private List<RegiaoEntrega> regioesEntrega;

    public ControleEntregas() {
        this.motoqueiros = new ArrayList<>();
        this.regioesEntrega = new ArrayList<>();
    }

    public ControleEntregas(List<Motoqueiro> motoqueiros, List<RegiaoEntrega> regioesEntrega) {
        this();
        if (motoqueiros != null) {
            this.motoqueiros.addAll(motoqueiros);
        }
        if (regioesEntrega != null) {
            this.regioesEntrega.addAll(regioesEntrega);
        }
        validarMinimoMotoqueiros();
    }

    private void validarMinimoMotoqueiros() {
        long quantidadeAtiva = getMotoqueiros().stream().filter(Motoqueiro::isAtivo).count();
        if (!getMotoqueiros().isEmpty() && quantidadeAtiva < 5) {
            throw new IllegalArgumentException("Preciso de pelo menos cinco motoqueiros ativos");
        }
    }

    // aloco o primeiro motoqueiro ativo
    public Motoqueiro alocarMotoqueiro(Entrega entrega) {
        for (Motoqueiro motoqueiro : getMotoqueiros()) {
            if (motoqueiro.isAtivo()) {
                entrega.setMotoqueiro(motoqueiro);
                return motoqueiro;
            }
        }
        return null;
    }

    //defino a regiao pelo endereco do cliente
    public RegiaoEntrega definirRegiao(Cliente cliente) {
        String enderecoNormalizado = cliente.getEndereco().toLowerCase();
        for (RegiaoEntrega regiaoEntrega : getRegioesEntrega()) {
            if (regiaoEntrega.getBairros() != null
                    && enderecoNormalizado.contains(regiaoEntrega.getNomeRegiao().toLowerCase())) {
                return regiaoEntrega;
            }
        }
        return getRegioesEntrega().isEmpty() ? null : getRegioesEntrega().get(0);
    }

    //aciono o aviso de entrega no whatsapp
    public void informarEntregaWhatsapp(Entrega entrega) {
        if (entrega.getMotoqueiro() != null) {
            entrega.getMotoqueiro().informarEntregaWhatsapp();
            entrega.setConfirmacaoWhatsapp(true);
        }
    }

    public List<Motoqueiro> getMotoqueiros() {
        if (motoqueiros == null) {
            motoqueiros = new ArrayList<>();
        }
        return motoqueiros;
    }

    public void setMotoqueiros(List<Motoqueiro> motoqueiros) {
        this.motoqueiros = new ArrayList<>();
        if (motoqueiros != null) {
            this.motoqueiros.addAll(motoqueiros);
        }
    }

    public List<RegiaoEntrega> getRegioesEntrega() {
        if (regioesEntrega == null) {
            regioesEntrega = new ArrayList<>();
        }
        return regioesEntrega;
    }

    public void setRegioesEntrega(List<RegiaoEntrega> regioesEntrega) {
        this.regioesEntrega = new ArrayList<>();
        if (regioesEntrega != null) {
            this.regioesEntrega.addAll(regioesEntrega);
        }
    }

    // polimorfismo sobrescrevo toString para mostrar entregadores e regioes
    @Override
    public String toString() {
        return "ControleEntregas{"
                + "motoqueiros=" + getMotoqueiros()
                + ", regioesEntrega=" + getRegioesEntrega()
                + '}';
    }
}
