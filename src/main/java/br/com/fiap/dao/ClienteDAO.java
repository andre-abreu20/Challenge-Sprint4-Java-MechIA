package br.com.fiap.dao;


import br.com.fiap.to.ClienteTO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO extends Repository{


    public ClienteTO inserir(ClienteTO cliente) {
        String sql = "INSERT INTO t_mechia_cliente (nome,senha,dataNasc,telefone,genero,email, logradouro, complemento, cep, bairro, cidade, estado) VALUES (?,?,?,?,?,?,?,?, ?, ?, ?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getSenha());
            ps.setDate(3, java.sql.Date.valueOf(cliente.getDataNasc()));
            ps.setString(4, cliente.getTelefone());
            ps.setString(5, cliente.getGenero());
            ps.setString(6, cliente.getEmail());
            ps.setString(7, cliente.getLogradouro());
            ps.setString(8, cliente.getComplemento());
            ps.setInt(9, cliente.getCep());
            ps.setString(10, cliente.getBairro());
            ps.setString(11, cliente.getCidade());
            ps.setString(12, cliente.getEstado());

            if (ps.executeUpdate() > 0) {
                return cliente;
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

    public String alterar(String email, ClienteTO cliente) {
        String sql = "update t_mechia_cliente set nome=?,senha=?,dataNasc=?,telefone=?,genero=?,logradouro=?, complemento=?, cep=?, bairro=?, cidade=?, estado=? where email=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getSenha());
            ps.setDate(3, java.sql.Date.valueOf(cliente.getDataNasc()));
            ps.setString(4, cliente.getTelefone());
            ps.setString(5, cliente.getGenero());
            ps.setString(6, cliente.getLogradouro());
            ps.setString(7, cliente.getComplemento());
            ps.setInt(8, cliente.getCep());
            ps.setString(9, cliente.getBairro());
            ps.setString(10, cliente.getCidade());
            ps.setString(11, cliente.getEstado());
            ps.setString(12, cliente.getEmail());
            if (ps.executeUpdate() > 0) {
                return "Alterado com sucesso.";
            } else {
                return "Erro ao alterar";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        } finally {
            closeConnection();
        }
    }

    public boolean excluir(String email) {
        String sql = "delete from t_mechia_cliente where email=?";
        try(PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setString(1, email);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }

    public ClienteTO listarCliente(String email){
        String sql = "select * from t_mechia_cliente where email =?";
        ClienteTO cliente = new ClienteTO();
        cliente.setEmail(email);
        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cliente.setId(rs.getInt("idCliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setSenha(rs.getString("senha"));
                cliente.setDataNasc(rs.getDate("dataNasc").toLocalDate());
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setGenero(rs.getString("genero"));
                cliente.setLogradouro(rs.getString("logradouro"));
                cliente.setComplemento(rs.getString("complemento"));
                cliente.setCep(rs.getInt("cep"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setEstado(rs.getString("estado"));

            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        } finally {
            closeConnection();
        }
        return cliente;
    }
}
