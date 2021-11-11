/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fotolito.dao;

import br.com.fotolito.jdbc.ConnectionFactory;
import br.com.fotolito.model.FilmeHot;
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
public class FilmeHotDAO {
    private Connection con;

    public FilmeHotDAO() {
        this.con = new ConnectionFactory().getConnection();
    }
    
    //Metodo cadastrarFilmeHot
    public void cadastrarFilmeHot(FilmeHot obj) {
        try {

            //1 passo  - criar o comando sql
            String sql = "insert into tb_filme_hot (rp, cliente, fichaTecnica, faca, turno, ocorrencia, obs) "
                    + " values (?,?,?,?,?,?,?)";

            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getRp());
            stmt.setString(2, obj.getCliente());
            stmt.setInt(3, obj.getFichaTecnica());     
            stmt.setString(4, obj.getFaca());
            stmt.setString(5, obj.getTurno());
            stmt.setString(6, obj.getOcorrencia());
            stmt.setString(7, obj.getObs());            

            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }

    }
    
    //Metodo AlterarFilmeFaca
    public void alterarFilmeHot(FilmeHot obj){
        try {

            //1 passo  - criar o comando sql
            String sql = "update tb_filme_hot set rp=?, cliente=?, fichaTecnica=?, faca=?, turno=?, "
                    + "ocorrencia=?, obs=? where id=? ";

            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getRp());
            stmt.setString(2, obj.getCliente());
            stmt.setInt(3, obj.getFichaTecnica());     
            stmt.setString(4, obj.getFaca());
            stmt.setString(5, obj.getTurno());
            stmt.setString(6, obj.getOcorrencia());
            stmt.setString(7, obj.getObs());           
            stmt.setInt(8, obj.getId());

            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }
   
    }
    
    //Metodo ExcluirFilmeFaca
    public void excluirFilmeHot(FilmeHot obj){
        try {

            //1 passo  - criar o comando sql
            String sql = "delete from tb_filme_hot where id=?";

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
    
    //Metodo ListarFilmeHot
    public List<FilmeHot> listarFilmeHot() {

        try {

            // Passo 1 criar a lista
            List<FilmeHot> lista = new ArrayList<>();

            // Passo 2 criar o comando sql, organizar e executar
            String sql = "select * from tb_filme_hot order by abs(rp) asc";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                FilmeHot obj = new FilmeHot();

                obj.setId(rs.getInt("id"));
                obj.setRp(rs.getString("rp"));
                obj.setCliente(rs.getString("cliente"));
                obj.setFichaTecnica(rs.getInt("fichaTecnica"));
                obj.setFaca(rs.getString("faca"));
                obj.setTurno(rs.getString("turno"));
                obj.setOcorrencia(rs.getString("ocorrencia"));
                obj.setObs(rs.getString("obs"));                

                lista.add(obj);

            }
            return lista;

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro:" + erro);
            return null;
        }

    }
    
    //Metodo PesquisarFilmeFaca
    public List<FilmeHot> pesquisarFilmeHot(String rp, String fichaTecnica) {

        try {

            // Passo 1 criar a lista
            List<FilmeHot> lista = new ArrayList<>();

            // Passo 2 criar o comando sql, organizar e executar
            String sql = "select * from tb_filme_hot where rp like? and fichaTecnica like? order by abs(rp) asc";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, rp);
            stmt.setString(2, fichaTecnica);            
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                FilmeHot obj = new FilmeHot();

                obj.setId(rs.getInt("id"));
                obj.setRp(rs.getString("rp"));
                obj.setCliente(rs.getString("cliente"));
                obj.setFichaTecnica(rs.getInt("fichaTecnica"));
                obj.setFaca(rs.getString("faca"));
                obj.setTurno(rs.getString("turno"));
                obj.setOcorrencia(rs.getString("ocorrencia"));
                obj.setObs(rs.getString("obs"));  

                lista.add(obj);

            }
            return lista;

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro:" + erro);
            return null;
        }

    }
    
}
