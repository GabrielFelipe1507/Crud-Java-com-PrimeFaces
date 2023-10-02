package br.edu.ifsp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "produtos")
@NamedQueries({
    @NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p order by p.id")
})
public class Produto implements Serializable, Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproduto")
    private long id;
    @Column(name = "nomeproduto", length = 60)
    private String nomeproduto;
    @Column(name = "qtde", length = 60)
    private int qtde;
    @Column(name = "preco", length = 60)
    private double preco;

    //contrutores:
    public Produto(long id, String nomeproduto, int qtde, double preco) {
        this.id = id;
        this.nomeproduto = nomeproduto;
        this.qtde = qtde;
        this.preco = preco;
    }

    public Produto() {
    }

    //getters e setters:
    public String getNomeproduto() {
        return nomeproduto;
    }

    public void setNomeproduto(String nomeproduto) {
        this.nomeproduto = nomeproduto;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Produto other = (Produto) obj;
        return this.id == other.id;
    }

    //toString:
    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", nomeproduto=" + nomeproduto + ", qtde=" + qtde + ", preco=" + preco + '}';
    }    
    
    @Override
    public Long getId() {
        return this.id;
    }
}
