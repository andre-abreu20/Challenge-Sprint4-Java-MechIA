package br.com.fiap.dao;


import br.com.fiap.to.ConsertoTO;
import br.com.fiap.to.ProblemaTO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ConsertoDAO extends Repository{

    public String buscarMecanico(LocalDate dataConserto) {
        String testeData = String.valueOf(dataConserto); //
        System.out.println("date no buscar: " + dataConserto);
        String sql = "SELECT idMecanico FROM t_mechia_agenda WHERE dataAgenda = TO_DATE(?, 'YYYY-MM-DD')";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, testeData);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int idMecanico = rs.getInt("idMecanico");
                System.out.println("ID Mecanico: " + idMecanico);
                return String.valueOf(idMecanico);
            } else {
                System.out.println("sem dados");
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }



    public String buscarVeiculo(String placa) {
        System.out.println("Placa do carro com problema: " + placa);
        String sql = "SELECT idVeiculo FROM t_mechia_veiculo WHERE placa=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, placa);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idVeiculo = rs.getInt("idVeiculo");
                ProblemaTO problema = new ProblemaTO();
                problema.setIdVeiculo(idVeiculo);
                System.out.println("ID Veiculo: " + idVeiculo);
                return String.valueOf(idVeiculo);
            } else {
                System.out.println("Sem dados");
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }


    public ConsertoTO inserir(String placa, LocalDate dataConserto, ConsertoTO conserto) {


        conserto.setIdMecanico(Integer.parseInt(buscarMecanico(dataConserto)));
        conserto.setIdVeiculo(Integer.parseInt(buscarVeiculo(placa)));

        System.out.println("IDMecanico: " + conserto.getIdMecanico() + "\n IDVeiculo: " + conserto.getIdVeiculo());
        String sql = "INSERT INTO t_mechia_conserto (idVeiculo, idMecanico, dataConserto, horaConserto) VALUES (?,?,?,?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ps.setInt(1, conserto.getIdVeiculo());
            ps.setInt(2, conserto.getIdMecanico());
            ps.setDate(3, java.sql.Date.valueOf(conserto.getDataConserto()));
            ps.setString(4, conserto.getHoraConserto());

            if (ps.executeUpdate() > 0) {
                System.out.println("Adicionado");
                return conserto;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println( "Erro de SQL: " + e.getMessage());
        }catch (Exception e){
            System.out.println("erro: " +e.getMessage());
        }finally {
            closeConnection();
        }
        return null;
    }

    public ArrayList<ConsertoTO> listarConserto(String placa){

        ConsertoTO conserto = new ConsertoTO();
        conserto.setIdVeiculo(Integer.parseInt(buscarVeiculo(placa)));
        String sql = "select * from t_mechia_conserto where idVeiculo =?";
        ArrayList<ConsertoTO> listaConserto = new ArrayList<>();
        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ps.setInt(1, conserto.getIdVeiculo());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                conserto.setDataConserto(rs.getDate("dataConserto").toLocalDate());
                conserto.setHoraConserto(rs.getString("horaConserto"));
                conserto.setIdMecanico(rs.getInt("idMecanico"));

                listaConserto.add(conserto);
            }
            return listaConserto;
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }

    }


}
