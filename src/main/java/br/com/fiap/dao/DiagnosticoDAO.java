package br.com.fiap.dao;

import br.com.fiap.to.DiagnosticoTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DiagnosticoDAO extends Repository{

    public String buscarVeiculo(String placa) {
        System.out.println("Renavam do carro com problema: " + placa);
        String sql = "SELECT idVeiculo FROM t_mechia_veiculo WHERE placa=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, placa);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idVeiculo = rs.getInt("idVeiculo");
                DiagnosticoTO diagnostico = new DiagnosticoTO();
                diagnostico.setIdVeiculo(idVeiculo);
                System.out.println("ID Veiculo: " + idVeiculo);
                return String.valueOf(idVeiculo);
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }

    public String buscarProblema(String placa) {
        int idVeiculo = Integer.parseInt(buscarVeiculo(placa));
        System.out.println("ID do carro com problema: " + idVeiculo);
        String sql = "SELECT idProblema FROM t_mechia_problema WHERE idVeiculo=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, idVeiculo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idProblema = rs.getInt("idProblema");
                DiagnosticoTO diagnostico = new DiagnosticoTO();
                diagnostico.setIdProblema(idProblema);
                System.out.println("ID Problema: " + idProblema);
                return String.valueOf(idProblema);
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }



    public DiagnosticoTO inserir(String placa, DiagnosticoTO diagnostico) {

        diagnostico.setIdProblema( Integer.parseInt(buscarProblema(placa)));
        diagnostico.setIdVeiculo( Integer.parseInt(buscarVeiculo(placa)));
        System.out.println("ID do carro com problema: " + diagnostico.getIdVeiculo() + "ID problema " + diagnostico.getIdProblema() );
        String sql = "INSERT INTO t_mechia_diagnostico (idVeiculo,idProblema, descricao) VALUES (?,?,?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ps.setInt(1, diagnostico.getIdVeiculo());
            ps.setInt(2, diagnostico.getIdProblema());
            ps.setString(3, diagnostico.getDescricao());

            if (ps.executeUpdate() > 0) {
                System.out.println("Adicionado");
                return diagnostico;
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


    public String listarDiagnostico(String placa){

        int idProblema = Integer.parseInt(buscarProblema(placa));
        String sql = "select * from t_mechia_diagnostico where idProblema=?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ps.setInt(1, idProblema);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                DiagnosticoTO diagnostico = new DiagnosticoTO();
                diagnostico.setDescricao(rs.getString("descricao"));
                return diagnostico.getDescricao();
            }

            return "Não existe problema cadastrado";

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }

    }
}
