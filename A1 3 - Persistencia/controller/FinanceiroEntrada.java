package ultrabusinessmegatop.controller;

import java.util.Objects;

/**
 *
 * @author luigg
 */
public class FinanceiroEntrada {
    private String formaRecebimento;
    private double valor;
    private String dataBaixa;
    private String dataVencimento;
    private String dataEmissao;
    private Venda venda;
    private int pk_financeiro;
    
    //Construtor
    public FinanceiroEntrada() {
    }

    public FinanceiroEntrada(String formaRecebimento, double valor, String dataBaixa, String dataVencimento, String dataEmissao, Venda venda) {
        this.formaRecebimento = formaRecebimento;
        this.valor = valor;
        this.dataBaixa = dataBaixa;
        this.dataVencimento = dataVencimento;
        this.dataEmissao = dataEmissao;
        this.venda = venda;
    }

    public FinanceiroEntrada(String formaRecebimento, double valor, String dataBaixa, String dataVencimento, String dataEmissao, Venda venda, int pk_financeiro) {
        this.formaRecebimento = formaRecebimento;
        this.valor = valor;
        this.dataBaixa = dataBaixa;
        this.dataVencimento = dataVencimento;
        this.dataEmissao = dataEmissao;
        this.venda = venda;
        this.pk_financeiro = pk_financeiro;
    }

    //GSetters
    public String getFormaRecebimento() {
        return formaRecebimento;
    }

    public void setFormaRecebimento(String formaRecebimento) {
        this.formaRecebimento = formaRecebimento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDataBaixa() {
        return dataBaixa;
    }

    public void setDataBaixa(String dataBaixa) {
        this.dataBaixa = dataBaixa;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public int getPk_financeiro() {
        return pk_financeiro;
    }

    public void setPk_financeiro(int pk_financeiro) {
        this.pk_financeiro = pk_financeiro;
    }

    //default
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.formaRecebimento);
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
        hash = 67 * hash + Objects.hashCode(this.dataBaixa);
        hash = 67 * hash + Objects.hashCode(this.dataVencimento);
        hash = 67 * hash + Objects.hashCode(this.dataEmissao);
        hash = 67 * hash + Objects.hashCode(this.venda);
        hash = 67 * hash + this.pk_financeiro;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FinanceiroEntrada other = (FinanceiroEntrada) obj;
        if (Double.doubleToLongBits(this.valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        if (this.pk_financeiro != other.pk_financeiro) {
            return false;
        }
        if (!Objects.equals(this.formaRecebimento, other.formaRecebimento)) {
            return false;
        }
        if (!Objects.equals(this.dataBaixa, other.dataBaixa)) {
            return false;
        }
        if (!Objects.equals(this.dataVencimento, other.dataVencimento)) {
            return false;
        }
        if (!Objects.equals(this.dataEmissao, other.dataEmissao)) {
            return false;
        }
        if (!Objects.equals(this.venda, other.venda)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FinanceiroEntrada{" + "formaRecebimento=" + formaRecebimento + ", valor=" + valor + ", dataBaixa=" + dataBaixa + ", dataVencimento=" + dataVencimento + ", dataEmissao=" + dataEmissao + ", venda=" + venda + ", pk_financeiro=" + pk_financeiro + '}';
    }
    
}
