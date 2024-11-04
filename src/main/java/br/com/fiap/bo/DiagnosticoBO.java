package br.com.fiap.bo;


import br.com.fiap.dao.DiagnosticoDAO;
import br.com.fiap.to.DiagnosticoTO;


public class DiagnosticoBO {

    private DiagnosticoDAO diagnosticoDAO;


    public DiagnosticoTO inserir(String placa, DiagnosticoTO diagnostico) {
        diagnosticoDAO = new DiagnosticoDAO();
        // Aqui se implementa a regra de negócios
        return diagnosticoDAO.inserir(placa, diagnostico);

    }

    public String listarDiagnostico(String placa){
        diagnosticoDAO = new DiagnosticoDAO();
        // Aqui se implementa a regra de negócios
        return diagnosticoDAO.listarDiagnostico(placa);
    }


}
