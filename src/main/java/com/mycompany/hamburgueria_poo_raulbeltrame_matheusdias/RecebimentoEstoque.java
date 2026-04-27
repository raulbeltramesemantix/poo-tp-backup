package com.mycompany.hamburgueria_poo_raulbeltrame_matheusdias;

import java.sql.Date;
import java.time.LocalDate;

public class RecebimentoEstoque {

    // associacao por id: recebimento guarda qual fornecedor fez a entrega
    private int idRecebimento;
    private Date dataRecebimento;
    private String descricaoRecebimento;
    private int idFornecedor;
    private String nomeFornecedor;

    // construtor padrao usado para criar recebimento vazio
    public RecebimentoEstoque() {
    }

    // construtor completo para registrar entrada feita por fornecedor
    public RecebimentoEstoque(int idRecebimento, Date dataRecebimento, String descricaoRecebimento, int idFornecedor, String nomeFornecedor) {
        this.idRecebimento = idRecebimento;
        this.dataRecebimento = dataRecebimento;
        this.descricaoRecebimento = descricaoRecebimento;
        this.idFornecedor = idFornecedor;
        this.nomeFornecedor = nomeFornecedor;
    }

    //registro a entrada no estoque
    public void registrarEntrada() {
        if (dataRecebimento == null) {
            dataRecebimento = Date.valueOf(LocalDate.now());
        }
    }

    public int getIdRecebimento() {
        return idRecebimento;
    }

    public void setIdRecebimento(int idRecebimento) {
        this.idRecebimento = idRecebimento;
    }

    public Date getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(Date dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public String getDescricaoRecebimento() {
        return descricaoRecebimento;
    }

    public void setDescricaoRecebimento(String descricaoRecebimento) {
        this.descricaoRecebimento = descricaoRecebimento;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    // polimorfismo sobrescrevo toString para imprimir a entrada de estoque
    @Override
    public String toString() {
        return "RecebimentoEstoque{"
                + "idRecebimento=" + idRecebimento
                + ", dataRecebimento=" + dataRecebimento
                + ", descricaoRecebimento='" + descricaoRecebimento + '\''
                + ", idFornecedor=" + idFornecedor
                + ", nomeFornecedor='" + nomeFornecedor + '\''
                + '}';
    }
}
