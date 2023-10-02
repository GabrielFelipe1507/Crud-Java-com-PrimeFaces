package br.edu.ifsp.servicos;

import br.edu.ifsp.dao.DAO;
import br.edu.ifsp.model.ItemVenda;
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

public class ItemVendaServicos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private DAO<ItemVenda> dao;

    public void salvar(ItemVenda iv) throws NegocioException {
        System.out.println("passando pelo salvar");
        dao.salvar(iv);
    }

    public void remover(ItemVenda iv) throws NegocioException {
        dao.remover(ItemVenda.class, iv.getId());
    }

    public List<ItemVenda> listarTodos() {
        return dao.buscarTodos("select iv from ItemVenda iv order by iv.id");
    }

    public ItemVenda buscarPorId(Long id) {
        return dao.buscarPorId(ItemVenda.class, id);
    }
    //método novo:

    public ItemVenda buscarPorNome(String nome) {
        System.out.println("entrei no buscar por nome");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("nome", nome);
        List<ItemVenda> resultados = dao.buscarPorJPQL("SELECT iv FROM ItemVenda iv WHERE iv.nomeproduto = :nome", parametros);

        if (!resultados.isEmpty()) {
            System.out.println("tou no if");
            return resultados.get(0); // Retorna o primeiro resultado encontrado
        } else {
            System.out.println("tou no else");
            return null; // Retorna null se nenhum cliente for encontrado com o nome especificado
        }
    }

    public DAO<ItemVenda> getDao() {
        return dao;
    }

    public void setDao(DAO<ItemVenda> dao) {
        this.dao = dao;
    }

}
