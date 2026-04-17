package Model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {
    private int id;
    private LocalDate dataCheckin;
    private LocalDate dataCheckout;
    private Inquilino inquilino;
    private Apartamento apartamento;
    private boolean ArCondicionado;
    private double ValorTotal;
    private int Pagantes;
    private int Criancas;

    public Reserva() {
    }

    public Reserva(LocalDate dataCheckin, LocalDate dataCheckout, Inquilino inquilino, Apartamento apartamento, boolean ArCondicionado, double ValorTotal, int Pagantes, int Criancas) {
        
        this.dataCheckin = dataCheckin;
        this.dataCheckout = dataCheckout;
        this.inquilino = inquilino;
        this.apartamento = apartamento;
        this.ArCondicionado = ArCondicionado;
        this.ValorTotal = ValorTotal;
        this.Pagantes = Pagantes;
        this.Criancas = Criancas;
    }
    
    
    

    public double getValorTotal() {
        return ValorTotal;
    }

    public void setValorTotal(double ValorTotal) {
        this.ValorTotal = ValorTotal;
    }
    


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataCheckin() {
        return dataCheckin;
    }

    public void setDataCheckin(LocalDate dataCheckin) {
        this.dataCheckin = dataCheckin;
    }

    public LocalDate getDataCheckout() {
        return dataCheckout;
    }

    public void setDataCheckout(LocalDate dataCheckout) {
        this.dataCheckout = dataCheckout;
    }



    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public Apartamento getApartamento() {
        return apartamento;
    }

    public void setApartamento(Apartamento apartamento) {
        this.apartamento = apartamento;
    }

    public boolean isArCondicionado() {
        return ArCondicionado;
    }

    public void setArCondicionado(boolean ArCondicionado) {
        this.ArCondicionado = ArCondicionado;
    }

    public int getPagantes() {
        return Pagantes;
    }

    public void setPagantes(int Pagantes) {
        this.Pagantes = Pagantes;
    }

    public int getCriancas() {
        return Criancas;
    }

    public void setCriancas(int Criancas) {
        this.Criancas = Criancas;
    }

    @Override
public String toString() {
    return "------------------------------------\n" +
           "       DETALHES DA RESERVA          \n" +
           "------------------------------------\n" +
           "ID: " + id + "\n" +
           "Inquilino: " + (inquilino != null ? inquilino.getNome() : "Não informado") + "\n" +
           "Apartamento: " + (apartamento != null ? apartamento.getId(): "Não informado") + "\n" +
           "Data de check-in: " + dataCheckin + "\n" +
           "Data de check-out: " + dataCheckout + "\n" +
           "Ar-Condicionado: " + (ArCondicionado ? "Sim" : "Não") + "\n" +
           "Valor da Reserva: R$" + ValorTotal + "\n" + 
           "Total de Pagantes: " + Pagantes + "\n" +
           "Total de Crianças: " + Criancas + "\n" +
           "------------------------------------";
}
    
    
    
}
