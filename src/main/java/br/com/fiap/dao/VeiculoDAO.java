package br.com.fiap.dao;
import br.com.fiap.to.VeiculoTO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class VeiculoDAO extends  Repository {

    public String buscarCliente(String email) {
        System.out.println("Email usado na consulta: " + email);
        String sql = "SELECT idCliente FROM t_mechia_cliente WHERE email = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idCliente = rs.getInt("idCliente");
                VeiculoTO veiculo = new VeiculoTO();
                veiculo.setIdCliente(idCliente);
                System.out.println("ID Cliente: " + idCliente);
                return String.valueOf(idCliente);
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }

    public boolean verificaPlaca(String email, String placa) {

        int idCliente = Integer.parseInt(buscarCliente(email));
        String sql = "select placa from t_mechia_veiculo where idCliente=?";
        ArrayList<String> listaPlaca = new ArrayList<>();
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String itens = rs.getString("placa");
                listaPlaca.add(itens);
            }
            System.out.println("Lista de placas" + listaPlaca);

            for (int i = 0; i < listaPlaca.size(); i++) {

                String verificaPlaca = listaPlaca.get(i);

                if (verificaPlaca.equalsIgnoreCase(placa.toUpperCase())) {
                    System.out.println("Placa válida");
                    return true;
                } else {
                    System.out.println("Placa não é valida");
                    return false;
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return false;
    }


    public VeiculoTO inserir(String email, VeiculoTO veiculo) {

        int idCliente = Integer.parseInt(buscarCliente(email));
        String sql = "INSERT INTO t_mechia_veiculo (idCliente,modelo,marca,ano,placa,chassi) VALUES (?,?,?,?,?,?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ps.setInt(1, idCliente);
            ps.setString(2, veiculo.getModelo());
            ps.setString(3, veiculo.getMarca());
            ps.setInt(4, veiculo.getAno());
            ps.setString(5, veiculo.getPlaca());
            ps.setInt(6, veiculo.getChassi());


            if (ps.executeUpdate() > 0) {
                return veiculo;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }

    public String alterar(String email, VeiculoTO veiculo) {

        if (verificaPlaca(email, veiculo.getPlaca())) {
            System.out.println("Placa verificada");
            String sql = "update t_mechia_veiculo set modelo=?,marca=?,ano=?,chassi=? where placa=?";
            try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
                ps.setString(1, veiculo.getModelo());
                ps.setString(2, veiculo.getMarca());
                ps.setInt(3, veiculo.getAno());
                ps.setInt(4, veiculo.getChassi());
                ps.setString(5, veiculo.getPlaca());


                if (ps.executeUpdate() > 0) {
                    return "Alterado com sucesso.";
                } else {
                    return "Erro ao alterar";
                }
            } catch (SQLException e) {
                return "Erro de SQL: " + e.getMessage();
            }
        } else {
            return "Carro não cadastrado";
        }
    }

    public boolean excluir(String email, String placa) {

        if (verificaPlaca(email, placa)) {
            System.out.println("Vou deletar do banco de dados");
            String sql = "delete from t_mechia_veiculo where placa=?";
            try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
                ps.setString(1, placa);
                return ps.executeUpdate() > 0;

            } catch (SQLException e) {
                System.out.println("Erro ao excluir: " + e.getMessage());
            } finally {
                closeConnection();
            }
            return false;
        } else {
            return false;
        }

    }


    public ArrayList<VeiculoTO> listarTodos(String email) {

        int idCliente = Integer.parseInt(buscarCliente(email));
        String sql = "select * from t_mechia_veiculo where idCliente=?";
        ArrayList<VeiculoTO> listaVeiculo = new ArrayList<VeiculoTO>();
        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                VeiculoTO veiculo = new VeiculoTO();
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setMarca(rs.getString("marca"));
                veiculo.setAno(rs.getInt("ano"));
                veiculo.setChassi(rs.getInt("chassi"));
                veiculo.setPlaca(rs.getString("placa"));
                listaVeiculo.add(veiculo);
            }

            return listaVeiculo;


        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }

    }

    public VeiculoTO listarVeiculo(String email, String placa) {
        System.out.println("Vou procurar o veiculo no banco de dados");
        VeiculoTO veiculo = new VeiculoTO();
        if (verificaPlaca(email, placa)) {
            String sql = "SELECT * FROM t_mechia_veiculo where placa=?";
            try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
                ps.setString(1, placa);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    veiculo.setIdCliente(rs.getInt("idCliente"));
                    veiculo.setModelo(rs.getString("modelo"));
                    veiculo.setMarca(rs.getString("marca"));
                    veiculo.setPlaca(rs.getString("placa"));
                    veiculo.setAno(rs.getInt("ano"));
                    veiculo.setChassi(rs.getInt("chassi"));

                } else {
                    return null;
                }

            } catch (SQLException e) {
                System.out.println("Erro de SQL: " + e.getMessage());
                return null;
            } finally {
                closeConnection();
            }

        }
     return veiculo;
    }
}