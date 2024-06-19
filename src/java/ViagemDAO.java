import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViagemDAO {

    // Método para adicionar uma nova viagem no banco de dados
    public void addViagem(Viagem viagem) {
        String sql = "INSERT INTO Viagens (origem, destino, data, preco) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, viagem.getOrigem());
            stmt.setString(2, viagem.getDestino());
            stmt.setDate(3, new java.sql.Date(viagem.getData().getTime()));
            stmt.setBigDecimal(4, viagem.getPreco());

            stmt.executeUpdate(); // Executa o comando SQL de inserção

        } catch (SQLException e) {
            e.printStackTrace(); // Imprime o stack trace para identificar o erro
        }
    }

    // Método para obter todas as viagens do banco de dados
    public List<Viagem> getAllViagens() {
        List<Viagem> viagens = new ArrayList<>();
        String sql = "SELECT * FROM Viagens";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Viagem viagem = new Viagem();
                viagem.setId(rs.getInt("id"));
                viagem.setOrigem(rs.getString("origem"));
                viagem.setDestino(rs.getString("destino"));
                viagem.setData(rs.getDate("data"));
                viagem.setPreco(rs.getBigDecimal("preco"));
                viagens.add(viagem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return viagens;
    }

    // Método para atualizar uma viagem no banco de dados
    public void updateViagem(Viagem viagem) {
        String sql = "UPDATE Viagens SET origem = ?, destino = ?, data = ?, preco = ? WHERE id = ?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, viagem.getOrigem());
            stmt.setString(2, viagem.getDestino());
            stmt.setDate(3, new java.sql.Date(viagem.getData().getTime()));
            stmt.setBigDecimal(4, viagem.getPreco());
            stmt.setInt(5, viagem.getId());

            stmt.executeUpdate(); // Executa o comando SQL de atualização

        } catch (SQLException e) {
            e.printStackTrace(); // Imprime o stack trace para identificar o erro
        }
    }

    // Método para deletar uma viagem do banco de dados pelo ID
    public void deleteViagem(int id) {
        String sql = "DELETE FROM Viagens WHERE id = ?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate(); // Executa o comando SQL de exclusão

        } catch (SQLException e) {
            e.printStackTrace(); // Imprime o stack trace para identificar o erro
        }
    }
}
