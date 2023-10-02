package br.edu.ifsp.dominio.controller;

import br.edu.ifsp.model.Produto;
import br.edu.ifsp.servicos.ProdutoServicos;
import br.edu.ifsp.utility.Menssagens;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class ProdutoBean implements Serializable {

    @Inject
    private Produto produto;

    @Inject
    private ProdutoServicos produtoServicos;

    private List<Produto> listaProdutos;  

    @PostConstruct
    public void carregar() {
        
        listaProdutos = produtoServicos.listarTodos();
    }

    public void adicionar() {
        try {
            produtoServicos.salvar(produto);
            produto = new Produto();
            carregar();
            Menssagens.info("Registro Gravado!...");

            // Redirecionar para a página "produtos.xhtml"
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto adicionado com sucesso!", null));
            FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "tabelaProduto.xhtml?faces-redirect=true");

        } catch (Exception e) {
            Menssagens.erro(e.getMessage());
        }
    }

    public void excluir() {
        try {
            produtoServicos.remover(produto);
            carregar();
            Menssagens.info(produto.getNomeproduto() + " foi removido!...");

        } catch (Exception e) {
            Menssagens.erro(e.getMessage());
        }
    }

    public String editar() {
        try {

            // produtoServicos.salvar(produto);
            //  carregar();
            Menssagens.info(produto.getNomeproduto() + "Selecionado para edição!...");
        } catch (Exception e) {
            Menssagens.erro(e.getMessage());
        }
        return "produtosEditar.xhtml";
    }

    public List<Produto> completeProduto(String query) {
        List<Produto> produtosFiltrados = new ArrayList<>();

        for (Produto produto : listaProdutos) {
            if (produto.getNomeproduto().toLowerCase().contains(query.toLowerCase())) {
                produtosFiltrados.add(produto);
            }
        }

        return produtosFiltrados;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }
 
}
