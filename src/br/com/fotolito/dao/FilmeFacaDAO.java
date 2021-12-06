/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fotolito.dao;

import br.com.fotolito.jdbc.ConnectionFactory;
import br.com.fotolito.model.FilmeFaca;
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
public class FilmeFacaDAO {
    private Connection con;

    public FilmeFacaDAO() {
        this.con = new ConnectionFactory().getConnection();
    }
    
    //Metodo cadastrarFilmeFaca
    public void cadastrarFilmeFaca(FilmeFaca obj) {
        try {

            //1 passo  - criar o comando sql
            String sql = "insert into tb_filme_faca (rp, cliente, fichaTecnica, setor, turno, ocorrencia, obs) "
                    + " values (?,?,?,?,?,?,?)";

            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getRp());
            stmt.setString(2, obj.getCliente());
            stmt.setInt(3, obj.getFichaTecnica());     
            stmt.setString(4, obj.getSetor());
            stmt.setString(5, obj.getTurno());
            stmt.setString(6, obj.getOcorrencia());
            stmt.setString(7, obj.getObs());            

            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();
            con.close();

            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }

    }
    
    //Metodo AlterarFilmeFaca
    public void alterarFilmeFaca(FilmeFaca obj){
        try {

            //1 passo  - criar o comando sql
            String sql = "update tb_filme_faca set rp=?, cliente=?, fichaTecnica=?, setor=?, turno=?, "
                    + "ocorrencia=?, obs=? where id=? ";

            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getRp());
            stmt.setString(2, obj.getCliente());
            stmt.setInt(3, obj.getFichaTecnica());     
            stmt.setString(4, obj.getSetor());
            stmt.setString(5, obj.getTurno());
            stmt.setString(6, obj.getOcorrencia());
            stmt.setString(7, obj.getObs());           
            stmt.setInt(8, obj.getId());

            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();
            con.close();

            JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }
   
    }
    
    //Metodo ExcluirFilmeFaca
    public void excluirFilmeFaca(FilmeFaca obj){
        try {

            //1 passo  - criar o comando sql
            String sql = "delete from tb_filme_faca where id=?";

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
    
    //Metodo ListarFilmeFaca
    public List<FilmeFaca> listarFilmeFaca() {

        try {

            // Passo 1 criar a lista
            List<FilmeFaca> lista = new ArrayList<>();

            // Passo 2 criar o comando sql, organizar e executar
            String sql = "select * from tb_filme_faca order by abs(rp) asc";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                FilmeFaca obj = new FilmeFaca();

                obj.setId(rs.getInt("id"));
                obj.setRp(rs.getString("rp"));
                obj.setCliente(rs.getString("cliente"));
                obj.setFichaTecnica(rs.getInt("fichaTecnica"));
                obj.setSetor(rs.getString("setor"));
                obj.setTurno(rs.getString("turno"));
                obj.setOcorrencia(rs.getString("ocorrencia"));
                obj.setObs(rs.getString("obs"));                

                lista.add(obj);

            }
            con.close();
            return lista;

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro:" + erro);
            return null;
        }

    }
    
    //Metodo PesquisarFilmeFaca
    public List<FilmeFaca> pesquisarFilmeFaca(String rp, String fichaTecnica) {

        try {

            // Passo 1 criar a lista
            List<FilmeFaca> lista = new ArrayList<>();

            // Passo 2 criar o comando sql, organizar e executar
            String sql = "select * from tb_filme_faca where rp like? and fichaTecnica like? order by abs(rp) asc";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, rp);
            stmt.setString(2, fichaTecnica);            
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                FilmeFaca obj = new FilmeFaca();

                obj.setId(rs.getInt("id"));
                obj.setRp(rs.getString("rp"));
                obj.setCliente(rs.getString("cliente"));
                obj.setFichaTecnica(rs.getInt("fichaTecnica"));
                obj.setSetor(rs.getString("setor"));
                obj.setTurno(rs.getString("turno"));
                obj.setOcorrencia(rs.getString("ocorrencia"));
                obj.setObs(rs.getString("obs"));  

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
