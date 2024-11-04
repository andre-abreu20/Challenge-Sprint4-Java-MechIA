package br.com.fiap.bo;

import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.to.ClienteTO;


public class ClienteBO {

    private ClienteDAO clienteDAO;

    public ClienteTO inserir(ClienteTO cliente) {
        clienteDAO = new ClienteDAO();
        // Aqui se implementa a regra de negócios
        return clienteDAO.inserir(cliente);

    }

    public String alterar(String email, ClienteTO cliente) {
        clienteDAO = new ClienteDAO();
        // Aqui se implementa a regra de negócios
        return clienteDAO.alterar(email, cliente);
    }

    public boolean excluir(String email) {
        clienteDAO = new ClienteDAO();
        // Aqui se implementa a regra de negócios
        return clienteDAO.excluir(email);
    }

    public ClienteTO listarCliente(String email){
        clienteDAO = new ClienteDAO();
        // Aqui se implementa a regra de negócios
        return clienteDAO.listarCliente(email);
    }

}
