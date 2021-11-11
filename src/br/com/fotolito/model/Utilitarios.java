/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fotolito.model;


import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author gmg
 */
public class Utilitarios {

    // Metodo Aplicar Fundo Transparente no JFrame
    public void aplicarTransparencia(JFrame frame) {
        frame.setUndecorated(true);
        frame.setBackground(new Color(0, 18, 40, 225));
    }

    // Metodo Pegar Data e Hora (dd/mm/yyyy hh:mm)
    public String DH() {
        Date data = new Date();
        SimpleDateFormat formatar = new SimpleDateFormat();
        String dataFormatada = formatar.format(data);
        
        //JOptionPane.showMessageDialog(null, dataFormatada);
        
        return dataFormatada;
        
    }

}
