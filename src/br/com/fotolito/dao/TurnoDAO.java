/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fotolito.dao;

import br.com.fotolito.jdbc.ConnectionFactory;
import br.com.fotolito.model.Turno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author gmg
 */
public class TurnoDAO {
    private Connection con;

    public TurnoDAO() {
        this.con = new ConnectionFactory().getConnection();
    }
    
    // Metodo Cadastrar Turno
    public void CadastrarTurno(Turno obj){
        try {
            
            //1 passo  - criar o comando sql
            String sql = "insert into tb_turno (nomeTurno) "
                    + " values (?)";
            
            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);            
            stmt.setString(1, obj.getNomeTurno());
            
            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();
            con.close();
            
       JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");
        } 
        catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            
        }
    }
    
    //Metodo AlterarTurno
    public void alterarTurno(Turno obj){
        try {

            //1 passo  - criar o comando sql
            String sql = "update tb_turno set nomeTurno=? where id=?";

            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);            
            stmt.setString(1, obj.getNomeTurno()); 
            stmt.setInt(2, obj.getId());

            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();
            con.close();

            JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }
   
    }
    
    //Metodo ExcluirTurno
    public void excluirTurno(Turno obj){
        try {

            //1 passo  - criar o comando sql
            String sql = "delete from tb_turno where id=?";

            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());            

            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();
            con.close();

            JOptionPane.showMessageDialog(null, "Excluido com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }
   
    }
    
    //Metodo ListarTurno
    public List<Turno> listarTurno() {

        try {

            // Passo 1 criar a lista
            List<Turno> lista = new ArrayList<>();

            // Passo 2 criar o comando sql, organizar e executar
            String sql = "select * from tb_turno";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Turno obj = new Turno();

                obj.setId(rs.getInt("id"));
                obj.setNomeTurno(rs.getString("nomeTurno"));                

                lista.add(obj);

            }
            con.close();
            return lista;

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro:" + erro);
            return null;
        }

    }
    
}
