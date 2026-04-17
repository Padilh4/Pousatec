
package DAO;

import Conexao.ConexaoPousada;
import Model.Apartamento;
import Model.Inquilino;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class InquilinoDAO {
    private Connection conn;
    
    public InquilinoDAO(){
        this.conn = new ConexaoPousada().getConnection();
    }
    
    public void cadastrar(Inquilino i) throws SQLException{
        String sql = "INSERT INTO Inquilinos (Nome, Telefone)  VALUES (?,?)";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, i.getNome());
            stmt.setString(2, i.getTelefone());
            
            stmt.execute();
            
            System.out.println("Inquilino salvo com sucesso");
        }catch(SQLException e){
            throw new RuntimeException("Erro ao cadastrar Inquilino " + e.getMessage());
        }
    }
    public Inquilino buscarPorId(int id) throws SQLException {
                String sql = "SELECT * FROM Inquilinos WHERE InquilinoID = ?";
                Inquilino i = null;
                try{
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, id);
                    ResultSet rs = stmt.executeQuery();
                    
                    if (rs.next()){
                       int ID = rs.getInt("InquilinoID");
                       String nome = rs.getString("Nome");
                       String Telefone = rs.getString("Telefone");
                       
                       i = new Inquilino(nome, Telefone);
                       i.setId(ID);
                        
                    }
                    
                } catch (SQLException e){
                    throw new RuntimeException("Erro ao buscar ID: " + e.getMessage());
                }
                return i;
                
}
     public List<Inquilino> listar() {
        String sql = "SELECT * FROM  inquilinos";

        List<Inquilino> lista = new ArrayList<>();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Inquilino i = new Inquilino();
                i.setId(rs.getInt("InquilinoID"));
                i.setNome(rs.getString("Nome"));
                i.setTelefone(rs.getString("Telefone"));
            

               
                lista.add(i);
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar: " + e.getMessage());
        }

        return lista;
    }
     
}
