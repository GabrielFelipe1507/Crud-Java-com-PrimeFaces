package br.edu.ifsp.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "ItemVenda.findAll", query = "SELECT iv FROM ItemVenda iv order by iv.id"),
    @NamedQuery(name = "ItemVenda.findById", query = "SELECT iv FROM ItemVenda iv WHERE iv.id = :id")
})
public class ItemVenda implements Serializable, Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @ManyToOne
    @JoinColumn(name = "venda")
    private Venda venda;
    @ManyToOne
    @JoinColumn(name = "produto")
    private Produto produto;
    @Column(name = "qtde")
    private int qtde;
    @Column(name = "preco")
    private double preco;

    //construtores:
    public ItemVenda(long id, Venda venda, Produto produto, int qtde, double preco) {
        this.id = id;
        this.venda = venda;
        this.produto = produto;
        this.qtde = qtde;
        this.preco = preco;
    }

    public ItemVenda() {
    }

    //getters e setters:W
    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    //equals e hascode
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 67 * hash + Objects.hashCode(this.venda);
        hash = 67 * hash + Objects.hashCode(this.produto);
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
        final ItemVenda other = (ItemVenda) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.venda, other.venda)) {
            return false;
        }
        return Objects.equals(this.produto, other.produto);
    }

    //toString
    @Override
    public String toString() {
        return "ItemVenda{" + "id=" + id + ", venda=" + venda + ", produto=" + produto + ", qtde=" + qtde + ", preco=" + preco + '}';
    }

    @Override
    public Long getId() {
        return this.id;
    }
}
