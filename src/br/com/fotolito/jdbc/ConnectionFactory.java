/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fotolito.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author mrs_a
 */
public class ConnectionFactory {

    public Connection getConnection() {

        try {

            return DriverManager.getConnection("jdbc:mysql://10.0.72.61:3306/bdfotolito?useSSL=false", "carton", "2574");

        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }

    }

    
     

  
}