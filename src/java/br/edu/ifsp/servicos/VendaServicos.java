
package br.edu.ifsp.servicos;

import br.edu.ifsp.dao.DAO;
import br.edu.ifsp.model.Venda;
import br.edu.ifsp.utility.NegocioException;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author vfmaz
 */
public class VendaServicos implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private DAO<Venda> dao;
    
    public void salvar(Venda v) throws NegocioException{
        System.out.println("passando pelo salvar");
        dao.salvar(v);
    }
    
    public void remover(Venda v) throws NegocioException{
        dao.remover(Venda.class ,v.getId());
    }
    
    public List<Venda> listarTodos(){
        return dao.buscarTodos("select v from Venda v order by v.id");
    }
}
