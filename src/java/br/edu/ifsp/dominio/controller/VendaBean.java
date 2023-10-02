package br.edu.ifsp.dominio.controller;

import br.edu.ifsp.model.Cliente;
import br.edu.ifsp.model.ItemVenda;
import br.edu.ifsp.model.Produto;
import br.edu.ifsp.model.Venda;
import br.edu.ifsp.servicos.VendaServicos;
import br.edu.ifsp.utility.Menssagens;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class VendaBean implements Serializable {

    @Inject
    private Venda venda;

    @Inject
    private VendaServicos vendaServicos;

    private Cliente cliente;

    private double total;

    private String nomes = "";

    private Produto produtoSelecionado;

    private List<ItemVenda> itensVenda = new ArrayList<>();

    private List<Venda> listaVendas;

    private Venda vendaSelecionada;

    @PostConstruct
    public void carregar() {
        System.out.println("OI carregar das VENDAS");
        venda = new Venda();
        listaVendas = vendaServicos.listarTodos();
        System.out.println("Tamanho da Lista de vendas: " + listaVendas.size());
        System.out.println(" Lista de vendas: " + listaVendas);
    }

    public void adicionar() {
        System.out.println("oii adicionar vendas");
        try {
            System.out.println("oi try do vendas");
            venda.setValortotal(total);
            venda.setListaItensVenda(itensVenda);
            vendaServicos.salvar(venda);
            venda = new Venda();
            carregar();
            Menssagens.info("Registro Gravado!...");

            // Redirecionar para a página "tabelaCliente.xhtml"
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/tabelaVenda.xhtml");
        } catch (Exception e) {
            Menssagens.erro(e.getMessage());
        }
    }

    public void excluir() {
        try {
            vendaServicos.remover(venda);
            carregar();
            Menssagens.info(venda.getId() + " foi removido!...");

        } catch (Exception e) {
            Menssagens.erro(e.getMessage());
        }
    }

    public String editar() {
        try {

            // vendaServicos.salvar(venda);
            //  carregar();
            Menssagens.info(venda.getId() + "Selecionado para edição!...");
        } catch (Exception e) {
            Menssagens.erro(e.getMessage());
        }
        return "vendasEditar.xhtml";
    }

    public void selecionarProduto(Produto produtoSelecionado) {
        // Aqui você pode definir a lógica para tratar o produto selecionado
        this.produtoSelecionado = produtoSelecionado;
        System.out.println(produtoSelecionado);

        // Você pode fazer o que desejar com o produto selecionado, como salvá-lo em uma lista, banco de dados, etc.
    }

    public void colocaProduto() {
        ItemVenda item = new ItemVenda();

        item.setPreco(produtoSelecionado.getPreco());
        item.setProduto(produtoSelecionado);
        item.setQtde(1);
        item.setVenda(venda);

        boolean achou = false;

        for (ItemVenda it : itensVenda) {
            if (it.getProduto().equals(produtoSelecionado)) {
                it.setQtde(it.getQtde() + 1);
                achou = true;
            }
        }

        if (!achou) {
            itensVenda.add(item);
        }

        produtoSelecionado = null;
        somaPrecoProduto();
        produtosNome();
    }

    public void adicionarNaListaItensVenda() {

        boolean produtoExistente = false;

        for (ItemVenda item : itensVenda) {
            if (item.getProduto().getNomeproduto().equals(produtoSelecionado.getNomeproduto())) {
                item.setQtde(item.getQtde() + 1);
                produtoExistente = true;
                break;
            }
        }

        if (itensVenda.isEmpty() || (!produtoExistente)) {
            ItemVenda item = new ItemVenda();
            item.setProduto(produtoSelecionado);
            item.setPreco(produtoSelecionado.getPreco());
            item.setQtde(1);
            itensVenda.add(item);
        }

        somaPrecoProduto();
        produtosNome();

    }

    public void somaPrecoProduto() {

        total = 0;
        for (ItemVenda i : itensVenda) {
            total += i.getProduto().getPreco() * i.getQtde();
        }

    }

    public String produtosNome() {
        int cont = 0;
        int tam = itensVenda.size();

        this.nomes = "";

        for (ItemVenda i : itensVenda) {
            cont++;
            if (cont != tam) {
                nomes += i.getProduto().getNomeproduto() + " | " + i.getQtde() + " unidade., \n";
            } else {
                nomes += i.getProduto().getNomeproduto() + " | " + i.getQtde() + " unidade. \n";
            }

        }

        return nomes;
    }

    public void limparProdutos() {
        itensVenda.clear();
        produtoSelecionado = null;
        nomes = "";
        total = 0;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public List<Venda> getListaVendas() {
        return listaVendas;
    }

    public VendaServicos getVendaServicos() {
        return vendaServicos;
    }

    public void setVendaServicos(VendaServicos vendaServicos) {
        this.vendaServicos = vendaServicos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Venda getVendaSelecionada() {
        return vendaSelecionada;
    }

    public void setVendaSelecionada(Venda vendaSelecionada) {
        this.vendaSelecionada = vendaSelecionada;
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }

    public List<ItemVenda> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(List<ItemVenda> itensVenda) {
        this.itensVenda = itensVenda;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getNomes() {
        return nomes;
    }

    public void setNomes(String nomes) {
        this.nomes = nomes;
    }

}
