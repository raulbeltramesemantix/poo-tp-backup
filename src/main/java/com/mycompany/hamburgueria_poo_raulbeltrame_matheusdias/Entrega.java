package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

// faco associacao da entrega com motoqueiro e regiao
public class Entrega {

    private int idEntrega;
    private String statusEntrega;
    private boolean confirmacaoWhatsapp;
    // associacao: entrega conhece o motoqueiro responsavel
    private Motoqueiro motoqueiro;
    // associacao: entrega pertence a uma regiao de entrega
    private RegiaoEntrega regiaoEntrega;

    public Entrega() {
    }

    public Entrega(int idEntrega, String statusEntrega, boolean confirmacaoWhatsapp) {
        this.idEntrega = idEntrega;
        this.statusEntrega = statusEntrega;
        this.confirmacaoWhatsapp = confirmacaoWhatsapp;
    }

    // confirmo a entrega finalizada
    public void confirmarEntrega() {
        confirmacaoWhatsapp = true;
        statusEntrega = "Concluida";
    }

    public int getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(int idEntrega) {
        this.idEntrega = idEntrega;
    }

    public String getStatusEntrega() {
        return statusEntrega;
    }

    public void setStatusEntrega(String statusEntrega) {
        this.statusEntrega = statusEntrega;
    }

    public boolean isConfirmacaoWhatsapp() {
        return confirmacaoWhatsapp;
    }

    public void setConfirmacaoWhatsapp(boolean confirmacaoWhatsapp) {
        this.confirmacaoWhatsapp = confirmacaoWhatsapp;
    }

    public Motoqueiro getMotoqueiro() {
        return motoqueiro;
    }

    public void setMotoqueiro(Motoqueiro motoqueiro) {
        this.motoqueiro = motoqueiro;
    }

    public RegiaoEntrega getRegiaoEntrega() {
        return regiaoEntrega;
    }

    public void setRegiaoEntrega(RegiaoEntrega regiaoEntrega) {
        this.regiaoEntrega = regiaoEntrega;
    }

    // polimorfismo sobrescrevo toString para mostrar a entrega completa
    @Override
    public String toString() {
        return "Entrega{"
                + "idEntrega=" + idEntrega
                + ", statusEntrega='" + statusEntrega + '\''
                + ", confirmacaoWhatsapp=" + confirmacaoWhatsapp
                + ", motoqueiro=" + motoqueiro
                + ", regiaoEntrega=" + regiaoEntrega
                + '}';
    }
}
