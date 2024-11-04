package br.com.fiap.dao;

import br.com.fiap.to.MecanicoTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class MecanicoDAO extends Repository {

    public ArrayList<MecanicoTO> listarMecanico(LocalDate dataConserto){

        ConsertoDAO consertoDAO = new ConsertoDAO();
        int idMecanico = Integer.parseInt(consertoDAO.buscarMecanico(dataConserto));
        System.out.println("IDMecanico: " + idMecanico);
        String sql = "select * from t_mechia_mecanico where idMecanico=?";
        ArrayList<MecanicoTO> listaMecanico = new ArrayList<MecanicoTO>();
        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ps.setInt(1, idMecanico);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                if (rs.next()) {
                    MecanicoTO mecanico = new MecanicoTO();
                    mecanico.setId(idMecanico);
                    mecanico.setNome(rs.getString("nome"));
                    mecanico.setEmail(rs.getString("email"));
                    mecanico.setSenha(rs.getString("senha"));
                    mecanico.setDataNasc(rs.getDate("dataNasc").toLocalDate());
                    mecanico.setTelefone(rs.getString("telefone"));
                    mecanico.setGenero(rs.getString("genero"));
                    mecanico.setEspecialidade(rs.getString("especialidade"));
                    mecanico.setEndereco(rs.getString("endereco"));
                    listaMecanico.add(mecanico);
                }
                return listaMecanico;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }

    }
}
