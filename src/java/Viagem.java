/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author giova
 */
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Viagem {

    private int id;
    private String origem;
    private String destino;
    private Date data;
    private BigDecimal preco;

    // Construtores, getters e setters

    public Viagem() {
    }

    public Viagem(int id, String origem, String destino, Date data, BigDecimal preco) {
        this.id = id;
        this.origem = origem;
        this.destino = destino;
        this.data = data;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    // Métodos DAO para manipulação de dados

    public static List<Viagem> getAllViagens() {
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

    public boolean salvar() {
        String sql = "INSERT INTO Viagens (origem, destino, data, preco) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, this.origem);
            stmt.setString(2, this.destino);
            stmt.setDate(3, new java.sql.Date(this.data.getTime()));
            stmt.setBigDecimal(4, this.preco);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizar() {
        String sql = "UPDATE Viagens SET origem = ?, destino = ?, data = ?, preco = ? WHERE id = ?";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, this.origem);
            stmt.setString(2, this.destino);
            stmt.setDate(3, new java.sql.Date(this.data.getTime()));
            stmt.setBigDecimal(4, this.preco);
            stmt.setInt(5, this.id);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluir() {
        String sql = "DELETE FROM Viagens WHERE id = ?";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, this.id);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
