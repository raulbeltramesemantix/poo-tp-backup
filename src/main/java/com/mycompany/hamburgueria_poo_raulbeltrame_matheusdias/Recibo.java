package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

import java.sql.Date;
import java.time.LocalDate;

public class Recibo {

    // encapsulamento: o recibo guarda dados da emissao e detalhes da venda
    private int idRecibo;
    private Date dataEmissao;
    private String detalhes;

    public Recibo() {
    }

    public Recibo(int idRecibo, Date dataEmissao, String detalhes) {
        this.idRecibo = idRecibo;
        this.dataEmissao = dataEmissao;
        this.detalhes = detalhes;
    }

    //marco a emissao do recibo
    public void gerarRecibo() {
        if (dataEmissao == null) {
            dataEmissao = Date.valueOf(LocalDate.now());
        }
    }

    public int getIdRecibo() {
        return idRecibo;
    }

    public void setIdRecibo(int idRecibo) {
        this.idRecibo = idRecibo;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    // polimorfismo sobrescrevo toString para imprimir o extrato
    @Override
    public String toString() {
        return "Recibo{"
                + "idRecibo=" + idRecibo
                + ", dataEmissao=" + dataEmissao
                + ", detalhes='" + detalhes + '\''
                + '}';
    }
}
