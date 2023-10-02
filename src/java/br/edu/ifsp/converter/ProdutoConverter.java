package br.edu.ifsp.converter;

import br.edu.ifsp.dao.DAO;
import br.edu.ifsp.model.Produto;

import br.edu.ifsp.servicos.ProdutoServicos;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@FacesConverter("produtoConverter")
public class ProdutoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println(value);

            // Aqui, você pode adicionar uma lógica personalizada para buscar o Produto com base no nome.
            // O exemplo a seguir assume que seu serviço possui um método 'buscarPorNome'.
            Produto produto = null;
            DAO<Produto> dao = new DAO();
            try {
                System.out.println("Entrei no try:" + value);
                produto = dao.buscarPorId(Produto.class,Long.parseLong(value));
            } catch (Exception e) {
                System.out.println("Erro no conversor de Tipo de Produto.");
                e.printStackTrace();
            }
            return produto;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Produto) {
            return ((Produto) value).getNomeproduto();
        }
        return null;
    }
}
