/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ARTHURCARDOSOPADILHA
 */
public class Inquilino {
    private int id;
    private String nome;
    private String Telefone;

    public Inquilino(String nome, String Telefone) {
        this.nome = nome;
        this.Telefone = Telefone;
    }

    public Inquilino() {
    }
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }

   @Override
public String toString() {
    return "====================================\n" +
           "       DADOS DO INQUILINO           \n" +
           "====================================\n" +
           "ID: " + id + "\n" +
           "Nome: " + (nome != null ? nome : "Não cadastrado") + "\n" +
           "Telefone: " + (Telefone != null ? Telefone : "Sem telefone") + "\n" +
           "====================================";
}
    
    
}
