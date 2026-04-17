/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ARTHURCARDOSOPADILHA
 */
public class Apartamento {
    private int id;
    private String status;
    private double ValorDiaria;
    private int Capacidade;

    public Apartamento(String status, int Capacidade, double ValorDiaria) {
        this.status = status;
        this.ValorDiaria = ValorDiaria;
        this.Capacidade = Capacidade;
    }

    public Apartamento() {
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getValorDiaria() {
        return ValorDiaria;
    }

    public void setValorDiaria(double ValorDiaria) {
        this.ValorDiaria = ValorDiaria;
    }

    public int getCapacidade() {
        return Capacidade;
    }

    public void setCapacidade(int Capacidade) {
        this.Capacidade = Capacidade;
    }

    @Override
public String toString() {
    return "====================================\n" +
           "     DETALHES DO APARTAMENTO        \n" +
           "====================================\n" +
           "ID: " + id + "\n" +
           "Status: " + (status != null ? status : "N/A") + "\n" +
           "Valor da Diária: R$ " + String.format("%.2f", ValorDiaria) + "\n" +
           "Capacidade: " + Capacidade + " pessoas\n" +
           "====================================";
}
    
    
}
