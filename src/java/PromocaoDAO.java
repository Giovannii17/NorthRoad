

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PromocaoDAO {

    // Método para adicionar uma nova promoção no banco de dados
    public void addPromocao(Promocao promocao) {
        String sql = "INSERT INTO Promocoes (origem, destino, data, precoOriginal, precoPromocional) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, promocao.getOrigem());
            stmt.setString(2, promocao.getDestino());
            stmt.setDate(3, new java.sql.Date(promocao.getData().getTime()));
            stmt.setBigDecimal(4, promocao.getPrecoOriginal());
            stmt.setBigDecimal(5, promocao.getPrecoPromocional());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obter todas as promoções cadastradas no banco de dados
    public List<Promocao> getAllPromocoes() {
        List<Promocao> promocoes = new ArrayList<>();
        String sql = "SELECT * FROM Promocoes";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Promocao promocao = new Promocao();
                promocao.setId(rs.getInt("id"));
                promocao.setOrigem(rs.getString("origem"));
                promocao.setDestino(rs.getString("destino"));
                promocao.setData(rs.getDate("data"));
                promocao.setPrecoOriginal(rs.getBigDecimal("precoOriginal"));
                promocao.setPrecoPromocional(rs.getBigDecimal("precoPromocional"));
                promocoes.add(promocao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return promocoes;
    }

    // Método para atualizar uma promoção existente no banco de dados
    public void updatePromocao(Promocao promocao) {
        String sql = "UPDATE Promocoes SET origem = ?, destino = ?, data = ?, precoOriginal = ?, precoPromocional = ? WHERE id = ?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, promocao.getOrigem());
            stmt.setString(2, promocao.getDestino());
            stmt.setDate(3, new java.sql.Date(promocao.getData().getTime()));
            stmt.setBigDecimal(4, promocao.getPrecoOriginal());
            stmt.setBigDecimal(5, promocao.getPrecoPromocional());
            stmt.setInt(6, promocao.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir uma promoção do banco de dados
    public void deletePromocao(int id) {
        String sql = "DELETE FROM Promocoes WHERE id = ?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
