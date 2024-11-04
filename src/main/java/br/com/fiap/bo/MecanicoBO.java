package br.com.fiap.bo;
import br.com.fiap.dao.MecanicoDAO;
import br.com.fiap.to.MecanicoTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class MecanicoBO {


    private MecanicoDAO mecanicoDAO;

    public ArrayList<MecanicoTO> listarMecanico(LocalDate dataConserto){
        mecanicoDAO = new MecanicoDAO();
        // Aqui se implementa a regra de negócios
        return mecanicoDAO.listarMecanico(dataConserto);
    }


}
