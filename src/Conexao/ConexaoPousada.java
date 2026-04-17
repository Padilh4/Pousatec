
package Conexao;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoPousada {
    private static Connection conexaoUnica;
    
    public Connection getConnection(){
        try{
    if(conexaoUnica == null || conexaoUnica.isClosed()){
        conexaoUnica = DriverManager.getConnection("jdbc:mysql://localhost:3306/pousatec", "root", "root");
        System.out.println("[LOG] Nova conexao aberta com o banco. ");
    }
    return conexaoUnica;
}catch(SQLException e){
    throw new RuntimeException (e);
}
    }
}
