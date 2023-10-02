package br.edu.ifsp.dominio.controller;

import br.edu.ifsp.model.ItemVenda;
import br.edu.ifsp.model.Produto;
import br.edu.ifsp.servicos.ItemVendaServicos;
import br.edu.ifsp.utility.Menssagens;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class ItemVendaBean implements Serializable {

    @Inject
    private ItemVenda itemVenda;    
    
    private Produto produto;

    @Inject
    private ItemVendaServicos itemVendaServicos;


    public void adicionar() {
        try {
            itemVendaServicos.salvar(itemVenda);
            itemVenda = new ItemVenda();
            Menssagens.info("Registro Gravado!...");

            // Redirecionar para a página "tabelaItemVenda.xhtml"
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/tabelaItemVenda.xhtml");
        } catch (Exception e) {
            Menssagens.erro(e.getMessage());
            e.printStackTrace();
        }
    }

    public void excluir() {
        try {
            itemVendaServicos.remover(itemVenda);
            Menssagens.info(itemVenda.getId()+ " foi removido!...");

        } catch (Exception e) {
            Menssagens.erro(e.getMessage());
        }
    }

    public String editar() {
        try {

            // itemVendaServicos.salvar(itemVenda);
            //  carregar();
            Menssagens.info(itemVenda.getId()+ "Selecionado para edição!...");
        } catch (Exception e) {
            Menssagens.erro(e.getMessage());
        }
        return "itemVendasEditar.xhtml";
    }

    public ItemVenda getItemVenda() {
        return itemVenda;
    }

    public void setItemVenda(ItemVenda itemVenda) {
        this.itemVenda = itemVenda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public ItemVendaServicos getItemVendaServicos() {
        return itemVendaServicos;
    }

    public void setItemVendaServicos(ItemVendaServicos itemVendaServicos) {
        this.itemVendaServicos = itemVendaServicos;
    }

    
}
