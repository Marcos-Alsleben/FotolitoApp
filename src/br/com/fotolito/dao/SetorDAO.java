/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fotolito.dao;

import br.com.fotolito.jdbc.ConnectionFactory;
import br.com.fotolito.model.Setor;
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
public class SetorDAO {
    private Connection con;

    public SetorDAO() {
        this.con = new ConnectionFactory().getConnection();
    }
    
    // Metodo Cadastrar Setor
    public void CadastrarSetor(Setor obj){
        try {
            
            //1 passo  - criar o comando sql
            String sql = "insert into tb_setor (nomeSetor) "
                    + " values (?)";
            
            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);            
            stmt.setString(1, obj.getNomeSetor());
            
            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();
            
         JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");
        } 
        catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            
        }
    }
    
    //Metodo AlterarSetor
    public void alterarSetor(Setor obj){
        try {

            //1 passo  - criar o comando sql
            String sql = "update tb_setor set nomeSetor=? where id=?";

            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);            
            stmt.setString(1, obj.getNomeSetor()); 
            stmt.setInt(2, obj.getId());

            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }
   
    }
    
    //Metodo ExcluirSetor
    public void excluirSetor(Setor obj){
        try {

            //1 passo  - criar o comando sql
            String sql = "delete from tb_setor where id=?";

            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());            

            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Excluido com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }
   
    }
    
    //Metodo ListarSetor
    public List<Setor> listarSetor() {

        try {

            // Passo 1 criar a lista
            List<Setor> lista = new ArrayList<>();

            // Passo 2 criar o comando sql, organizar e executar
            String sql = "select * from tb_setor";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Setor obj = new Setor();

                obj.setId(rs.getInt("id"));
                obj.setNomeSetor(rs.getString("nomeSetor"));                

                lista.add(obj);

            }
            return lista;

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro:" + erro);
            return null;
        }

    }
    
}
