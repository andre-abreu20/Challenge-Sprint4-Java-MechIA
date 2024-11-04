package br.com.fiap.dao;


import br.com.fiap.to.ProblemaTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ProblemaDAO extends Repository{

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
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }



    public ProblemaTO inserir(String placa, ProblemaTO problema) {


        problema.setIdVeiculo(Integer.parseInt(buscarVeiculo(placa)));
        System.out.println("Placa: " + placa + " ID: " + problema.getIdVeiculo());
        String sql = "INSERT INTO t_mechia_problema (idVeiculo, descricao) VALUES (?,?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ps.setInt(1, problema.getIdVeiculo());
            ps.setString(2, problema.getDescricao());


            if (ps.executeUpdate() > 0) {
                System.out.println("Adicionado");
                return problema;
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






    public String listarProblema(String placa){

        System.out.println("Placa: " + placa);
        int idVeiculo = Integer.parseInt(buscarVeiculo(placa));
        String sql = "select * from t_mechia_problema where idVeiculo=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ps.setInt(1, idVeiculo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ProblemaTO problema = new ProblemaTO();
                problema.setDescricao(rs.getString("descricao"));
                return problema.getDescricao();
            }

            return "Não existe problema cadastrado";

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }

    }
}
