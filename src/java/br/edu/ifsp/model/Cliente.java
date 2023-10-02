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
@Table(name = "clientes")
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c order by c.id")
})
public class Cliente implements Serializable, Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcliente")
    private long id;
    @Column(name = "nomecliente", length = 60)
    private String nomecliente;
    @Column(name = "endereco", length = 60)
    private String endereco;
    @Column(name = "cidade", length = 60)
    private String cidade;
    @Column(name = "uf", length = 60)
    private String uf;
    @Column(name = "cep", length = 60)
    private String cep;
    @Column(name = "email", length = 60)
    private String email;

    @OneToMany(mappedBy = "cliente")
    private List<Venda> vendas = new ArrayList<>();

    public Cliente(long id, String nomecliente, String endereco, String cidade, String uf, String cep, String email) {
        this.id = id;
        this.nomecliente = nomecliente;
        this.endereco = endereco;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.email = email;
    }

    public Cliente() {
    }

    public String getNomecliente() {
        return nomecliente;
    }

    public void setNomecliente(String nomecliente) {
        this.nomecliente = nomecliente;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }  

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Cliente other = (Cliente) obj;
        return this.id == other.id;
    }
    
    

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nomecliente=" + nomecliente + ", endereco=" + endereco + ", cidade=" + cidade + ", uf=" + uf + ", cep=" + cep + ", email=" + email + '}';
    }

    @Override
    public Long getId() {
        return this.id;
    }
}
