package br.edu.ifsp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "vendas")
@NamedQueries({
    @NamedQuery(name = "Venda.findAll", query = "SELECT v FROM Venda v order by v.id"),
    @NamedQuery(name = "Venda.findById", query = "SELECT v FROM Venda v WHERE v.id = :id")
})
public class Venda implements Serializable, Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idvenda")
    private long id;
    @Column(name = "datavenda")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datavenda;
    @Column(name = "valortotal")
    private double valortotal;
    @ManyToOne
    @JoinColumn(name = "cliente_idcliente", nullable = false)
    private Cliente cliente;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "itemVenda", nullable = false)
    List<ItemVenda> listaItensVenda = new ArrayList<>();

    //constructores:  
    public Venda(long id, Date datavenda, double valortotal, Cliente cliente) {
        this.id = id;
        this.datavenda = datavenda;
        this.valortotal = valortotal;
        this.cliente = cliente;
    }

    public Venda() {
    }

    public Date getDatavenda() {
        return datavenda;
    }

    public void setDatavenda(Date datavenda) {
        this.datavenda = datavenda;
    }

    public double getValortotal() {
        return valortotal;
    }

    public void setValortotal(double valortotal) {
        this.valortotal = valortotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemVenda> getListaItensVenda() {
        return listaItensVenda;
    }

    public void setListaItensVenda(List<ItemVenda> listaItensVenda) {
        this.listaItensVenda = listaItensVenda;
    }

    public String produtosNome() {
        int cont = 0;
        int tam = listaItensVenda.size();

        String nomes = "";

        for (ItemVenda i : listaItensVenda) {
            cont++;
            if (cont != tam) {
                nomes += i.getProduto().getNomeproduto() + " | " + i.getQtde() + " unidade., \n";
            } else {
                nomes += i.getProduto().getNomeproduto() + " | " + i.getQtde() + " unidade. \n";
            }

        }

        return nomes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Venda other = (Venda) obj;
        return this.id == other.id;
    }

    //toString:    
    @Override
    public String toString() {
        return "Venda{" + "id=" + id + ", datavenda=" + datavenda + ", valortotal=" + valortotal + ", cliente=" + cliente + '}';
    }

    @Override
    public Long getId() {
        return this.id;
    }
}
