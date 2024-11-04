package br.com.fiap.bo;

import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.dao.VeiculoDAO;
import br.com.fiap.to.VeiculoTO;

import java.util.ArrayList;


public class VeiculoBO {

    private VeiculoDAO veiculoDAO;


    public VeiculoTO inserir(String email, VeiculoTO veiculo) {
        veiculoDAO = new VeiculoDAO();
        if(!veiculoDAO.verificaPlaca(email, veiculo.getPlaca())){
            return veiculoDAO.inserir(email, veiculo);
        }
        // Aqui se implementa a regra de negócios
        return null;

    }

    public String alterar(String email, VeiculoTO veiculo) {
        veiculoDAO = new VeiculoDAO();
        // Aqui se implementa a regra de negócios
        return veiculoDAO.alterar(email, veiculo);
    }

    public boolean excluir(String email, String placa) {
        veiculoDAO = new VeiculoDAO();
        // Aqui se implementa a regra de negócios
        return veiculoDAO.excluir(email, placa);
    }


    public ArrayList<VeiculoTO> listarTodos(String email){
        veiculoDAO = new VeiculoDAO();
        // Aqui se implementa a regra de negócios
        return veiculoDAO.listarTodos(email);
    }

    public VeiculoTO listarVeiculo(String email,String placa) {
        veiculoDAO = new VeiculoDAO();
        return veiculoDAO.listarVeiculo(email, placa);
    }

}
