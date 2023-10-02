package br.edu.ifsp.converter;

import br.edu.ifsp.dao.DAO;
import br.edu.ifsp.model.Venda;

import br.edu.ifsp.servicos.VendaServicos;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@FacesConverter("vendaConverter")
public class VendaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println(value);

            // Aqui, você pode adicionar uma lógica personalizada para buscar o Venda com base no nome.
            // O exemplo a seguir assume que seu serviço possui um método 'buscarPorNome'.
            Venda venda = null;
            DAO<Venda> dao = new DAO();
            try {
                System.out.println("Entrei no try:" + value);
                venda = dao.buscarPorId(Venda.class,Long.parseLong(value));
            } catch (Exception e) {
                System.out.println("Erro no conversor de Tipo de Usuario.");
                e.printStackTrace();
            }
            return venda;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Venda) {
            return String.valueOf(((Venda) value).getId()); // Retorna apenas o nome do venda
        }
        return ""; // Retorna vazio se o valor não for um Venda.
    }
}
