
package util;

import DAO.ReservaDAO;
import Model.Reserva;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ExportadorCSV {
    public void exportarInventario (String caminhoArquivo) throws IOException, SQLException{
        
       
        ReservaDAO dao = new ReservaDAO(); 
        List<Reserva> reservas = dao.listar();
        
        if(reservas.isEmpty()){
            System.out.println("O acervo está vazio.");
            return;
        }
        
       
         try(BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))){
            
             
             bw.write("ID;CheckIn;CheckOut;Inquilino;Apartamento;Ar_Condicionado;ValorTotal;Pagantes;Criancas\n");
       
             for(Reserva r : reservas){
                 
                 String nomeInquilino = r.getInquilino().getNome();
                 String Arcond = "";
                 if(r.isArCondicionado()){
                     Arcond = "sim";
                }else{
                     Arcond = "não";
                 }
         
                 String linha = r.getId() + ";"+
                                r.getDataCheckin()+ ";"+
                                r.getDataCheckout()+ ";"+
                                nomeInquilino+ ";"+
                                r.getApartamento().getId()+ ";"+
                                Arcond+ ";"+
                                r.getValorTotal()+";"+
                                r.getPagantes()+";"+
                                r.getCriancas()+"\n";
                 
                 bw.write(linha);
             }
        }catch (IOException e){
             System.out.println("Erro ao exportar" + e.getMessage());
        }
        
        
        
    }
}
