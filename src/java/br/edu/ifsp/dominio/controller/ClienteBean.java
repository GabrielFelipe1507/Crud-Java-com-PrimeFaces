package br.edu.ifsp.dominio.controller;

import br.edu.ifsp.model.Cliente;
import br.edu.ifsp.servicos.ClienteServicos;
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
public class ClienteBean implements Serializable {

    @Inject
    private Cliente cliente;

    @Inject
    private ClienteServicos clienteServicos;

    private List<Cliente> listaClientes; 
    
    @PostConstruct
    public void carregar() {
        System.out.println("oiii - do carregar dos clientes");
        listaClientes = clienteServicos.listarTodos();
        System.out.println("Tamanho da Lista de clientes: "+ listaClientes.size());
        System.out.println(" Lista de clientes: "+ listaClientes);
    }

    public List<String> getListaNomesClientes() {
        List<String> nomesClientes = new ArrayList<>();
        for (Cliente cliente : listaClientes) {
            nomesClientes.add(cliente.getNomecliente());
        }
        return nomesClientes;
    }

    public void adicionar() {
        try {
            clienteServicos.salvar(cliente);
            cliente = new Cliente();
            carregar();
            Menssagens.info("Registro Gravado!...");

            // Redirecionar para a página "tabelaCliente.xhtml"
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/tabelaCliente.xhtml");
        } catch (Exception e) {
            Menssagens.erro(e.getMessage());
        }
    }

    public void excluir() {
        try {
            clienteServicos.remover(cliente);
            carregar();
            Menssagens.info(cliente.getNomecliente() + " foi removido!...");

        } catch (Exception e) {
            Menssagens.erro(e.getMessage());
        }
    }

    public String editar() {
        try {

            // clienteServicos.salvar(cliente);
            //  carregar();
            Menssagens.info(cliente.getNomecliente() + "Selecionado para edição!...");
        } catch (Exception e) {
            Menssagens.erro(e.getMessage());
        }
        return "clientesEditar.xhtml";
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

}
