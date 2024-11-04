package br.com.fiap.bo;

import br.com.fiap.dao.LoginDAO;
import br.com.fiap.to.LoginTO;

public class LoginBO {

    private LoginDAO loginDAO;

    public boolean verifica(LoginTO login){
        loginDAO = new LoginDAO();
        // Aqui se implementa a regra de negócios
        return loginDAO.verifica(login);
    }

}
