/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fotolito.dao;

import br.com.fotolito.jdbc.ConnectionFactory;
import br.com.fotolito.model.Clientes;
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
public class ClientesDAO {
    private Connection con;

    public ClientesDAO() {
        this.con = new ConnectionFactory().getConnection();
    }
    
    // Metodo Cadastrar Clientes
    public void CadastrarClientes(Clientes obj){
        try {
            
            //1 passo  - criar o comando sql
            String sql = "insert into tb_clientes (nomeCliente) "
                    + " values (?)";
            
            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);            
            stmt.setString(1, obj.getNomeCliente());
            
            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");
        } 
        catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            
        }
    }
    
    //Metodo AlterarClientes
    public void alterarClientes(Clientes obj){
        try {

            //1 passo  - criar o comando sql
            String sql = "update tb_clientes set nomeCliente=? where id=?";

            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);            
            stmt.setString(1, obj.getNomeCliente()); 
            stmt.setInt(2, obj.getId());

            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }
   
    }
    
    //Metodo ExcluirClientes
    public void excluirClientes(Clientes obj){
        try {

            //1 passo  - criar o comando sql
            String sql = "delete from tb_clientes where id=?";

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
    
    //Metodo ListarClientes
    public List<Clientes> listarClientes() {

        try {

            // Passo 1 criar a lista
            List<Clientes> lista = new ArrayList<>();

            // Passo 2 criar o comando sql, organizar e executar
            String sql = "select * from tb_clientes";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Clientes obj = new Clientes();

                obj.setId(rs.getInt("id"));
                obj.setNomeCliente(rs.getString("nomeCliente"));                

                lista.add(obj);

            }
            return lista;

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro:" + erro);
            return null;
        }

    }
    
}
