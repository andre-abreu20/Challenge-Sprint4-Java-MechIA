package br.com.fiap.dao;


import br.com.fiap.to.AgendaTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;



public class AgendaDAO extends Repository{

    public ArrayList<LocalDate> listarAgenda(){

        String sql = "select dataAgenda from t_mechia_agenda where disponibilidade='D'";
        ArrayList<LocalDate> listaAgenda = new ArrayList<>();
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            System.out.println("Antes do while");

            while (rs.next()) {
                AgendaTO agenda = new AgendaTO();
                agenda.setDataDisponivel(rs.getDate("dataAgenda").toLocalDate());
                listaAgenda.add(agenda.getDataDisponivel());
                System.out.println("DATA: " + agenda.getDataDisponivel());
            }
            System.out.println("Lista completa: " + listaAgenda);

            return listaAgenda;

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }

    }
}
