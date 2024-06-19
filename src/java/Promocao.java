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

public class Promocao {

    private int id;
    private String origem;
    private String destino;
    private Date data;
    private BigDecimal precoOriginal;
    private BigDecimal precoPromocional;

    // Construtores, getters e setters

    public Promocao() {
    }

    public Promocao(int id, String origem, String destino, Date data, BigDecimal precoOriginal, BigDecimal precoPromocional) {
        this.id = id;
        this.origem = origem;
        this.destino = destino;
        this.data = data;
        this.precoOriginal = precoOriginal;
        this.precoPromocional = precoPromocional;
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

    public BigDecimal getPrecoOriginal() {
        return precoOriginal;
    }

    public void setPrecoOriginal(BigDecimal precoOriginal) {
        this.precoOriginal = precoOriginal;
    }

    public BigDecimal getPrecoPromocional() {
        return precoPromocional;
    }

    public void setPrecoPromocional(BigDecimal precoPromocional) {
        this.precoPromocional = precoPromocional;
    }

    // Métodos DAO para manipulação de dados

    public static List<Promocao> getAllPromocoes() {
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

    public boolean salvar() {
        String sql = "INSERT INTO Promocoes (origem, destino, data, precoOriginal, precoPromocional) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, this.origem);
            stmt.setString(2, this.destino);
            stmt.setDate(3, new java.sql.Date(this.data.getTime()));
            stmt.setBigDecimal(4, this.precoOriginal);
            stmt.setBigDecimal(5, this.precoPromocional);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizar() {
        String sql = "UPDATE Promocoes SET origem = ?, destino = ?, data = ?, precoOriginal = ?, precoPromocional = ? WHERE id = ?";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, this.origem);
            stmt.setString(2, this.destino);
            stmt.setDate(3, new java.sql.Date(this.data.getTime()));
            stmt.setBigDecimal(4, this.precoOriginal);
            stmt.setBigDecimal(5, this.precoPromocional);
            stmt.setInt(6, this.id);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluir() {
        String sql = "DELETE FROM Promocoes WHERE id = ?";

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
