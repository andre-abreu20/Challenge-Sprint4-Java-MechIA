package br.com.fiap.bo;

import br.com.fiap.dao.AgendaDAO;
import br.com.fiap.dao.AgendaDAO;
import br.com.fiap.to.AgendaTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class AgendaBO {

    private AgendaDAO agendaDAO;

    public ArrayList<LocalDate> listarAgenda(){
        agendaDAO = new AgendaDAO();
        // Aqui se implementa a regra de negócios
        return agendaDAO.listarAgenda();
    }


}
