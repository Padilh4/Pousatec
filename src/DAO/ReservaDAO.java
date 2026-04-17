/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexao.ConexaoPousada;
import Model.Apartamento;
import Model.Inquilino;

import Model.Reserva;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ARTHURCARDOSOPADILHA
 */
public class ReservaDAO {
        private Connection conn;
    
    public ReservaDAO(){
        this.conn = new ConexaoPousada().getConnection();
    }
    
    public void cadastrar(Reserva r) throws SQLException{
        String sql = "INSERT INTO Reservas (data_checkin, data_checkout, InquilinoID, apartamentoID, usa_ar_condicionado, valor_total, total_pagantes, total_criancas) "
                + "  VALUES (?,?,?,?,?,?,?,?)";
        
        try{
            
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setObject(1, r.getDataCheckin());
            stmt.setObject(2, r.getDataCheckout());
            stmt.setObject(3, r.getInquilino().getId());
            stmt.setObject(4, r.getApartamento().getId());
            if(r.isArCondicionado()){
            stmt.setString(5, "sim");
            
            }else{
             stmt.setString(5, "nao");
            }
            stmt.setDouble(6, r.getValorTotal());
            stmt.setObject(7, r.getPagantes());
            stmt.setObject(8, r.getCriancas());

            stmt.execute();
            
            System.out.println("Reserva salva com sucesso");
        }catch(SQLException e){
            throw new RuntimeException("Erro ao cadastrar Reserva " + e.getMessage());
        }
    }
    
    public Reserva buscarPorId(int id) throws SQLException {
                String sql = "SELECT * FROM Reservas WHERE ReservaID = ?";
                Reserva r = new Reserva();
                Inquilino i = new Inquilino();
                Apartamento a = new Apartamento();
                InquilinoDAO iDAO = new InquilinoDAO();
                ApartamentoDAO aDAO = new ApartamentoDAO();
                try{
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, id);
                    ResultSet rs = stmt.executeQuery();
                    
                    if (rs.next()){
                        r = new Reserva();
                        r.setId(rs.getInt("ReservaID"));
                        r.setDataCheckin(rs.getObject("data_checkin", LocalDate.class));
                        r.setDataCheckout(rs.getObject("data_checkout", LocalDate.class));
                        
                        int idInq = rs.getInt("InquilinoID");
                        i = iDAO.buscarPorId(idInq);
                        
                        r.setInquilino(i);
                        int idApt = rs.getInt("apartamentoID");
                        a = aDAO.buscarPorId(idApt);
                        r.setApartamento(a);
                        if(rs.getString("usa_ar_condicionado").equals("sim")){
                            r.setArCondicionado(true);
                        }else{
                            r.setArCondicionado(false);
                        }
                        r.setValorTotal(rs.getDouble("valor_total"));
                        r.setPagantes(rs.getInt("total_pagantes"));
                        r.setCriancas(rs.getInt("total_criancas"));
                        return r;
                    }
                    
                } catch (SQLException e){
                    throw new RuntimeException("Erro ao buscar ID: " + e.getMessage());
                }
                return null;
                
}
     public List<Reserva> listar() {
        String sql = "SELECT r.*, a.apartamentoID AS ApartamentoID, i.nome AS nome_inquilino FROM Reservas r LEFT JOIN Apartamentos a ON r.apartamentoID = a.apartamentoID LEFT JOIN Inquilinos i ON r.InquilinoID = i.InquilinoID;";

        List<Reserva> lista = new ArrayList<>();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Reserva r = new Reserva();
                r.setId(rs.getInt("ReservaID"));
                r.setDataCheckin(rs.getObject("data_checkin", LocalDate.class));
           
                r.setDataCheckout(rs.getObject("data_checkout", LocalDate.class));
                
                InquilinoDAO Idao = new InquilinoDAO();
                int idinq = rs.getInt("InquilinoID");
                r.setInquilino(Idao.buscarPorId(idinq));
                ApartamentoDAO aDAO = new ApartamentoDAO();
                int idapt = rs.getInt("apartamentoID");
                r.setApartamento(aDAO.buscarPorId(idapt));
                if(rs.getString("usa_ar_condicionado").equals("sim")){
                    r.setArCondicionado(true);
                }else{
                    r.setArCondicionado(false);
                }
                r.setValorTotal(rs.getDouble("valor_total"));
                r.setPagantes(rs.getInt("total_pagantes"));
                r.setCriancas(rs.getInt("total_criancas"));
                
                                
                lista.add(r);
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar: " + e.getMessage());
        }

        return lista;
    }
     
     public double calcularFaturamentoMensal(int mes, int ano) {
    String sql = "SELECT SUM(valor_total) FROM reservas " +
                 "WHERE MONTH(data_checkin) = ? AND YEAR(data_checkin) = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, mes);
        stmt.setInt(2, ano);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getDouble(1);
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao calcular faturamento: " + e.getMessage());
    }
    return 0;
}

     public void cancelarReserva(int id){
         String sql = "DELETE FROM Reservas where ReservaID = ?";
         try{
             PreparedStatement stmt = conn.prepareStatement(sql);
             stmt.setInt(1, id);
             stmt.executeUpdate();
             stmt.close();
             
             System.out.println("Reserva cancelada!");
         }catch(SQLException e){
             throw new RuntimeException("Erro ao cancelar Reserva: " + e.getMessage());
         }
     }
}
