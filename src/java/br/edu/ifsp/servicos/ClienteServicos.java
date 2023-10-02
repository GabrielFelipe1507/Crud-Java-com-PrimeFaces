package br.edu.ifsp.servicos;

import br.edu.ifsp.dao.DAO;
import br.edu.ifsp.model.Cliente;
import br.edu.ifsp.utility.NegocioException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author vfmaz
 */

public class ClienteServicos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private DAO<Cliente> dao;

    public void salvar(Cliente c) throws NegocioException {
        System.out.println("passando pelo salvar");
        dao.salvar(c);
    }

    public void remover(Cliente c) throws NegocioException {
        dao.remover(Cliente.class, c.getId());
    }

    public List<Cliente> listarTodos() {
        return dao.buscarTodos("select c from Cliente c order by c.id");
    }

    public Cliente buscarPorId(Long id) {
        return dao.buscarPorId(Cliente.class, id);
    }
    //método novo:

    public Cliente buscarPorNome(String nome) {
        System.out.println("entrei no buscar por nome");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("nome", nome);
        List<Cliente> resultados = dao.buscarPorJPQL("SELECT c FROM Cliente c WHERE c.nomecliente = :nome", parametros);

        if (!resultados.isEmpty()) {
            System.out.println("tou no if");
            return resultados.get(0); // Retorna o primeiro resultado encontrado
        } else {
            System.out.println("tou no else");
            return null; // Retorna null se nenhum cliente for encontrado com o nome especificado
        }
    }

    public DAO<Cliente> getDao() {
        return dao;
    }

    public void setDao(DAO<Cliente> dao) {
        this.dao = dao;
    }

}
