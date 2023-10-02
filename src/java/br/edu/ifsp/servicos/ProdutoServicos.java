
package br.edu.ifsp.servicos;

import br.edu.ifsp.dao.DAO;
import br.edu.ifsp.model.Produto;
import br.edu.ifsp.utility.NegocioException;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author vfmaz
 */
public class ProdutoServicos implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private DAO<Produto> dao;
    
    public void salvar(Produto p) throws NegocioException{
        System.out.println("passando pelo salvar");
        dao.salvar(p);
    }
    
    public void remover(Produto p) throws NegocioException{
        dao.remover(Produto.class ,p.getId());
    }
    
    public List<Produto> listarTodos(){
        return dao.buscarTodos("select p from Produto p order by p.id");
    }
}
