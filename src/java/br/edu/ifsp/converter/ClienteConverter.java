package br.edu.ifsp.converter;

import br.edu.ifsp.dao.DAO;
import br.edu.ifsp.model.Cliente;

import br.edu.ifsp.servicos.ClienteServicos;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@FacesConverter("clienteConverter")
public class ClienteConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println(value);

            // Aqui, você pode adicionar uma lógica personalizada para buscar o Cliente com base no nome.
            // O exemplo a seguir assume que seu serviço possui um método 'buscarPorNome'.
            Cliente cliente = null;
            DAO<Cliente> dao = new DAO();
            try {
                System.out.println("Entrei no try:" + value);
                cliente = dao.buscarPorId(Cliente.class,Long.parseLong(value));
            } catch (Exception e) {
                System.out.println("Erro no conversor de Tipo de Usuario.");
                e.printStackTrace();
            }
            return cliente;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Cliente) {
            return String.valueOf(((Cliente) value).getId()); // Retorna apenas o nome do cliente
        }
        return ""; // Retorna vazio se o valor não for um Cliente.
    }
}
