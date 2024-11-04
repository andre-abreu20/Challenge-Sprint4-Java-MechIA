package br.com.fiap.bo;
import br.com.fiap.dao.ProblemaDAO;
import br.com.fiap.to.ProblemaTO;



public class ProblemaBO {

    private ProblemaDAO problemaDAO;


    public ProblemaTO inserir(String placa, ProblemaTO problema) {
        problemaDAO = new ProblemaDAO();
        // Aqui se implementa a regra de negócios
        return problemaDAO.inserir(placa, problema);

    }

    public String listarProblema(String placa){
        problemaDAO = new ProblemaDAO();
        // Aqui se implementa a regra de negócios
        return problemaDAO.listarProblema(placa);
    }


}
