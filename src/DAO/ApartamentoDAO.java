/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexao.ConexaoPousada;
import Model.Apartamento;
import Model.Inquilino;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ARTHURCARDOSOPADILHA
 */
public class ApartamentoDAO {
     private Connection conn;
    
    public ApartamentoDAO(){
        this.conn = new ConexaoPousada().getConnection();
    }
    
    public void cadastrar(Apartamento a) throws SQLException{
        String sql = "INSERT INTO Apartamentos (status_limpeza, capacidade_maxima, valor_diaria_base)  VALUES (?,?,?)";
        try{
            
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, a.getStatus());

            stmt.setInt(2, a.getCapacidade());
            stmt.setDouble(3, a.getValorDiaria());
            stmt.execute();
            
            System.out.println("Apartamento salvo com sucesso");
        }catch(SQLException e){
            throw new RuntimeException("Erro ao cadastrar Apartamento " + e.getMessage());
        }
    }
    public Apartamento buscarPorId(int id) throws SQLException {
                String sql = "SELECT * FROM Apartamentos WHERE apartamentoID = ?";
                Apartamento a = new Apartamento();
                try{
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, id);
                    ResultSet rs = stmt.executeQuery();
                    
                    if (rs.next()){
                        
                        int ID = rs.getInt("apartamentoID");
                        String status = rs.getString("status_limpeza");
                        int capacidade = rs.getInt("capacidade_maxima");
                        double ValorDiaria = rs.getDouble("valor_diaria_base");
                        
                        a = new Apartamento(status, capacidade, ValorDiaria);
                        a.setId(ID);
                        
                       return a; 
                    }
                    
                } catch (SQLException e){
                    throw new RuntimeException("Erro ao buscar ID: " + e.getMessage());
                }
                
                return null;
}
         public List<Apartamento> listar() {
        String sql = "SELECT * FROM  Apartamentos";

        List<Apartamento> lista = new ArrayList<>();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Apartamento a = new Apartamento();
                a.setId(rs.getInt("apartamentoID"));
                a.setStatus(rs.getString("status_limpeza"));
                a.setCapacidade(rs.getInt("capacidade_maxima"));
                a.setValorDiaria(rs.getDouble("valor_diaria_base"));
                
            

               
                lista.add(a);
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar: " + e.getMessage());
        }

        return lista;
    }
         
         public boolean atualizar(Apartamento a){
             String sql = "UPDATE Apartamentos SET status_limpeza = ?, valor_diaria_base = ? WHERE apartamentoID = ?";
             
             try{
                 PreparedStatement stmt = conn.prepareStatement(sql);
                 stmt.setString(1, a.getStatus());
                 stmt.setDouble(2, a.getValorDiaria());
                 stmt.setInt(3, a.getId());
                 
                 int atualizado = stmt.executeUpdate();
                 stmt.close();
                 
                 
                 return atualizado > 0;
             }catch(SQLException e){
                 throw new RuntimeException("Erro ao atualizar: " + e.getMessage());
             }
         }
}


