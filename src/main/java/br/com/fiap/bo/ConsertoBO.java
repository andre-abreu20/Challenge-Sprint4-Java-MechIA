package br.com.fiap.bo;

import br.com.fiap.dao.ConsertoDAO;
import br.com.fiap.to.ConsertoTO;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;


public class ConsertoBO {

    private ConsertoDAO consertoDAO;



    public ConsertoTO inserir(String placa, LocalDate dataConserto, ConsertoTO conserto) {
        consertoDAO = new ConsertoDAO();
        System.out.println(dataConserto);
        return consertoDAO.inserir(placa, dataConserto, conserto);
    }


    public ArrayList<ConsertoTO> listarConserto(String placa){
        consertoDAO = new ConsertoDAO();
        // Aqui se implementa a regra de negócios
        return consertoDAO.listarConserto(placa);
    }

}
