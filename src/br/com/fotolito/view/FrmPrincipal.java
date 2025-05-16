/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fotolito.view;

import br.com.fotolito.dao.ClientesDAO;
import br.com.fotolito.dao.FilmeFacaDAO;
import br.com.fotolito.dao.FilmeHotDAO;
import br.com.fotolito.dao.SetorDAO;
import br.com.fotolito.dao.TurnoDAO;
import br.com.fotolito.model.Clientes;
import br.com.fotolito.model.FilmeFaca;
import br.com.fotolito.model.FilmeHot;
import br.com.fotolito.model.Setor;
import br.com.fotolito.model.Turno;
import br.com.fotolito.model.Utilitarios;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author gmg
 */
public class FrmPrincipal extends javax.swing.JFrame {

    //Metodo mostrar cardLayout do jPanelPrincipal
    public void MostraCard_jPanelPrincipal(String card) {
        CardLayout cl = (CardLayout) jPanelPrincipal.getLayout();
        cl.show(jPanelPrincipal, card);
    }

    //Metodo listar FilmeHot
    public void listarFilmeHot() {
        FilmeHotDAO dao = new FilmeHotDAO();
        List<FilmeHot> lista = dao.listarFilmeHot();
        DefaultTableModel dados = (DefaultTableModel) tb_FilmeHot.getModel();
        dados.setNumRows(0);
        for (FilmeHot c : lista) {
            dados.addRow(new Object[]{
                c.getId(),
                c.getRp(),
                c.getCliente(),
                c.getFichaTecnica(),
                c.getFaca(),
                c.getTurno(),
                c.getOcorrencia(),
                c.getObs()
            });
        }
    }

    //Metodo listar FilmeFaca
    public void listarFilmeFaca() {
        FilmeFacaDAO dao = new FilmeFacaDAO();
        List<FilmeFaca> lista = dao.listarFilmeFaca();
        DefaultTableModel dados = (DefaultTableModel) tb_FilmeFaca.getModel();
        dados.setNumRows(0);
        for (FilmeFaca c : lista) {
            dados.addRow(new Object[]{
                c.getId(),
                c.getRp(),
                c.getCliente(),
                c.getFichaTecnica(),
                c.getSetor(),
                c.getTurno(),
                c.getOcorrencia(),
                c.getObs()
            });
        }
    }


    //Personalizar tabelas
    public void PersonalizarTabelas() {
        JTableHeader TbFaca = tb_FilmeFaca.getTableHeader();
        TbFaca.setBackground(Color.black);
        TbFaca.setFont(new Font("Dialog", 1, 12));
        ((DefaultTableCellRenderer) TbFaca.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        jScrollPaneFilmeFaca.getViewport().setBackground(Color.DARK_GRAY);
        tb_FilmeFaca.setBackground(Color.DARK_GRAY);

        JTableHeader TbHot = tb_FilmeHot.getTableHeader();
        TbHot.setBackground(Color.black);
        TbHot.setFont(new Font("Dialog", 1, 12));
        ((DefaultTableCellRenderer) TbHot.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        jScrollPaneFilmeHot.getViewport().setBackground(Color.DARK_GRAY);
        tb_FilmeHot.setBackground(Color.DARK_GRAY);
        
        JTableHeader TbClientes = tb_Clientes.getTableHeader();
        TbClientes.setBackground(Color.black);
        TbClientes.setFont(new Font("Dialog", 1, 12));
        ((DefaultTableCellRenderer) TbClientes.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        jScrollPaneClientes.getViewport().setBackground(Color.DARK_GRAY);
        tb_Clientes.setBackground(Color.DARK_GRAY);
        
        JTableHeader TbSetor = tb_Setor.getTableHeader();
        TbSetor.setBackground(Color.black);
        TbSetor.setFont(new Font("Dialog", 1, 12));
        ((DefaultTableCellRenderer) TbSetor.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        jScrollPaneSetor.getViewport().setBackground(Color.DARK_GRAY);
        tb_Setor.setBackground(Color.DARK_GRAY);
        
        JTableHeader TbTurnos = tb_Turnos.getTableHeader();
        TbTurnos.setBackground(Color.black);
        TbTurnos.setFont(new Font("Dialog", 1, 12));
        ((DefaultTableCellRenderer) TbTurnos.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        jScrollPaneTurnos.getViewport().setBackground(Color.DARK_GRAY);
        tb_Turnos.setBackground(Color.DARK_GRAY);
    }

    //Metodo Atualiza cbCliente
    public void AtualizarcbClientes() {
        ClientesDAO dao = new ClientesDAO();
        List<Clientes> lista = dao.listarClientes();

        cb_clienteFaca.removeAllItems();
        cb_clienteHot.removeAllItems();
        cb_clienteFaca.addItem("*");
        cb_clienteHot.addItem("*");

        for (Clientes c : lista) {
            cb_clienteFaca.addItem(c.getNomeCliente());
            cb_clienteHot.addItem(c.getNomeCliente());

        }
    }

    //Metodo Atualiza cbSetor
    public void AtualizarcbSetor() {
        SetorDAO dao = new SetorDAO();
        List<Setor> lista = dao.listarSetor();

        cb_setorFaca.removeAllItems();
        cb_setorFaca.addItem("*");

        for (Setor c : lista) {
            cb_setorFaca.addItem(c.getNomeSetor());
        }
    }

    //Metodo Atualiza cbTurno
    public void AtualizarcbTurno() {
        TurnoDAO dao = new TurnoDAO();
        List<Turno> lista = dao.listarTurno();

        cb_turnoFaca.removeAllItems();
        cb_turnoHot.removeAllItems();
        cb_turnoFaca.addItem("*");
        cb_turnoHot.addItem("*");

        for (Turno c : lista) {
            cb_turnoFaca.addItem(c.getNomeTurno());
            cb_turnoHot.addItem(c.getNomeTurno());
        }
    }

    // Metodo pesquisa na lista FilmeFaca RP e FT
    public void PesquisaRpFtFilmeFaca() {
        String rp = "%" + txt_rpFaca.getText() + "%";
        String fichaTecnica = "%" + txt_ftFaca.getText() + "%";
        FilmeFacaDAO dao = new FilmeFacaDAO();
        List<FilmeFaca> lista = dao.pesquisarFilmeFaca(rp, fichaTecnica);
        DefaultTableModel dados = (DefaultTableModel) tb_FilmeFaca.getModel();
        dados.setNumRows(0);
        for (FilmeFaca c : lista) {
            dados.addRow(new Object[]{
                c.getId(),
                c.getRp(),
                c.getCliente(),
                c.getFichaTecnica(),
                c.getSetor(),
                c.getTurno(),
                c.getOcorrencia(),
                c.getObs()
            });
        }
    }

    // Metodo pesquisa na lista FilmeHot RP e FT
    public void PesquisaRpFtFilmeHot() {
        String rp = "%" + txt_rpHot.getText() + "%";
        String fichaTecnica = "%" + txt_ftHot.getText() + "%";
        FilmeHotDAO dao = new FilmeHotDAO();
        List<FilmeHot> lista = dao.pesquisarFilmeHot(rp, fichaTecnica);
        DefaultTableModel dados = (DefaultTableModel) tb_FilmeHot.getModel();
        dados.setNumRows(0);
        for (FilmeHot c : lista) {
            dados.addRow(new Object[]{
                c.getId(),
                c.getRp(),
                c.getCliente(),
                c.getFichaTecnica(),
                c.getFaca(),
                c.getTurno(),
                c.getOcorrencia(),
                c.getObs()
            });
        }
    }

    public void LimparFilmeFaca() {
        txt_idFaca.setText("");
        txt_rpFaca.setText("");
        txt_ftFaca.setText("");
        txt_obsFaca.setText("");
        cb_clienteFaca.setSelectedItem("*");
        cb_turnoFaca.setSelectedItem("*");
        cb_setorFaca.setSelectedItem("*");
        btn_adicionarFaca.setEnabled(true);
        btn_ExcluirFaca.setEnabled(false);
        btn_alterarfaca.setEnabled(false);
        listarFilmeFaca();
    }

    public void LimparFilmeHot() {
        txt_idHot.setText("");
        txt_rpHot.setText("");
        txt_ftHot.setText("");
        txt_facaHot.setText("");
        txt_obsHot.setText("");
        cb_clienteHot.setSelectedItem("*");
        cb_turnoHot.setSelectedItem("*");
        btn_adicionarHot.setEnabled(true);
        btn_excluirHot.setEnabled(false);
        btn_alterarHot.setEnabled(false);
        listarFilmeHot();
    }
    
    //Metodo listar Clientes
    public void listarClientes() {
        ClientesDAO dao = new ClientesDAO();
        List<Clientes> lista = dao.listarClientes();
        DefaultTableModel dados = (DefaultTableModel) tb_Clientes.getModel();
        dados.setNumRows(0);
        for (Clientes c : lista) {
            dados.addRow(new Object[]{
                c.getId(),
                c.getNomeCliente(),});
        }
    }
    
    //Metodo listar Setor
    public void listarSetor() {
        SetorDAO dao = new SetorDAO();
        List<Setor> lista = dao.listarSetor();
        DefaultTableModel dados = (DefaultTableModel) tb_Setor.getModel();
        dados.setNumRows(0);
        for (Setor c : lista) {
            dados.addRow(new Object[]{
                c.getId(),
                c.getNomeSetor(),});
        }
    }
    
    //Metodo listar Turno
    public void listarTurno() {
        TurnoDAO dao = new TurnoDAO();
        List<Turno> lista = dao.listarTurno();
        DefaultTableModel dados = (DefaultTableModel) tb_Turnos.getModel();
        dados.setNumRows(0);
        for (Turno c : lista) {
            dados.addRow(new Object[]{
                c.getId(),
                c.getNomeTurno(),});
        }
    }

    /**
     * Creates new form FrmPrincipal
     */
    public FrmPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null);

        PersonalizarTabelas();

        AtualizarcbClientes();
        AtualizarcbSetor();
        AtualizarcbTurno();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanelPrincipal = new javax.swing.JPanel();
        jPanelFilmeFaca = new javax.swing.JPanel();
        jScrollPaneFilmeFaca = new javax.swing.JScrollPane();
        tb_FilmeFaca = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txt_idFaca = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_rpFaca = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cb_clienteFaca = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        txt_ftFaca = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cb_setorFaca = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        cb_turnoFaca = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_obsFaca = new javax.swing.JTextArea();
        btn_adicionarFaca = new javax.swing.JButton();
        btn_ExcluirFaca = new javax.swing.JButton();
        btn_alterarfaca = new javax.swing.JButton();
        btn_limparFaca = new javax.swing.JButton();
        btn_buscaRpFaca = new javax.swing.JButton();
        btn_buscaFtFaca = new javax.swing.JButton();
        jPanelFilmeHot = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txt_idHot = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_rpHot = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cb_clienteHot = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        txt_ftHot = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        cb_turnoHot = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txt_obsHot = new javax.swing.JTextArea();
        btn_adicionarHot = new javax.swing.JButton();
        btn_excluirHot = new javax.swing.JButton();
        btn_alterarHot = new javax.swing.JButton();
        btn_limparHot = new javax.swing.JButton();
        txt_facaHot = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        btn_buscaRpHot = new javax.swing.JButton();
        btn_buscaFtHot = new javax.swing.JButton();
        jScrollPaneFilmeHot = new javax.swing.JScrollPane();
        tb_FilmeHot = new javax.swing.JTable();
        jPanelConfig = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPaneClientes = new javax.swing.JScrollPane();
        tb_Clientes = new javax.swing.JTable();
        txt_idCliente = new javax.swing.JTextField();
        btn_novoCliente = new javax.swing.JButton();
        btn_alterarCliente = new javax.swing.JButton();
        btn_limparCliente = new javax.swing.JButton();
        btn_excluirCliente = new javax.swing.JButton();
        txt_nomeCliente = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jScrollPaneSetor = new javax.swing.JScrollPane();
        tb_Setor = new javax.swing.JTable();
        btn_novoSetor = new javax.swing.JButton();
        btn_alterarSetor = new javax.swing.JButton();
        btn_excluirSetor = new javax.swing.JButton();
        btn_limparSetor = new javax.swing.JButton();
        txt_nomeSetor = new javax.swing.JTextField();
        txt_idSetor = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPaneTurnos = new javax.swing.JScrollPane();
        tb_Turnos = new javax.swing.JTable();
        btn_novoTurno = new javax.swing.JButton();
        btn_alterarTurno = new javax.swing.JButton();
        btn_excluirTurno = new javax.swing.JButton();
        btn_limparTurno = new javax.swing.JButton();
        txt_idTurno = new javax.swing.JTextField();
        txt_nomeTurno = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FotolitoApp 2.1");
        setMinimumSize(new java.awt.Dimension(1100, 680));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(java.awt.Color.darkGray);

        jButton1.setBackground(new java.awt.Color(25, 25, 25));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Pesquisar_64.png"))); // NOI18N
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.setBorderPainted(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(25, 25, 25));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Pesquisar_64.png"))); // NOI18N
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.setBorderPainted(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("FILME DE FACA");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("FILME DE HOT");

        jButton3.setBackground(new java.awt.Color(25, 25, 25));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Configuração._64.png"))); // NOI18N
        jButton3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.setBorderPainted(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("CONFIGURAÇÃO");

        jPanelPrincipal.setBackground(new java.awt.Color(65, 65, 65));
        jPanelPrincipal.setLayout(new java.awt.CardLayout());

        jPanelFilmeFaca.setBackground(new java.awt.Color(65, 65, 65));
        jPanelFilmeFaca.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "FILME DE FACA", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(204, 255, 255))); // NOI18N

        jScrollPaneFilmeFaca.setBackground(new java.awt.Color(65, 65, 65));
        jScrollPaneFilmeFaca.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPaneFilmeFaca.setOpaque(false);

        tb_FilmeFaca.setBackground(new java.awt.Color(65, 65, 65));
        tb_FilmeFaca.setForeground(new java.awt.Color(255, 255, 255));
        tb_FilmeFaca.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "RP", "Cliente", "FT", "Setor", "Turno", "Data", "Observação"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_FilmeFaca.setToolTipText("Duplo clicke no registro para alterá-lo!");
        tb_FilmeFaca.setGridColor(new java.awt.Color(51, 51, 51));
        tb_FilmeFaca.setOpaque(false);
        tb_FilmeFaca.getTableHeader().setReorderingAllowed(false);
        tb_FilmeFaca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_FilmeFacaMouseClicked(evt);
            }
        });
        jScrollPaneFilmeFaca.setViewportView(tb_FilmeFaca);
        if (tb_FilmeFaca.getColumnModel().getColumnCount() > 0) {
            tb_FilmeFaca.getColumnModel().getColumn(0).setMinWidth(0);
            tb_FilmeFaca.getColumnModel().getColumn(0).setPreferredWidth(0);
            tb_FilmeFaca.getColumnModel().getColumn(0).setMaxWidth(0);
            tb_FilmeFaca.getColumnModel().getColumn(1).setMinWidth(120);
            tb_FilmeFaca.getColumnModel().getColumn(1).setPreferredWidth(120);
            tb_FilmeFaca.getColumnModel().getColumn(1).setMaxWidth(120);
            tb_FilmeFaca.getColumnModel().getColumn(2).setMinWidth(180);
            tb_FilmeFaca.getColumnModel().getColumn(2).setPreferredWidth(180);
            tb_FilmeFaca.getColumnModel().getColumn(2).setMaxWidth(180);
            tb_FilmeFaca.getColumnModel().getColumn(3).setMinWidth(70);
            tb_FilmeFaca.getColumnModel().getColumn(3).setPreferredWidth(70);
            tb_FilmeFaca.getColumnModel().getColumn(3).setMaxWidth(70);
            tb_FilmeFaca.getColumnModel().getColumn(4).setMinWidth(120);
            tb_FilmeFaca.getColumnModel().getColumn(4).setPreferredWidth(120);
            tb_FilmeFaca.getColumnModel().getColumn(4).setMaxWidth(120);
            tb_FilmeFaca.getColumnModel().getColumn(5).setMinWidth(50);
            tb_FilmeFaca.getColumnModel().getColumn(5).setPreferredWidth(50);
            tb_FilmeFaca.getColumnModel().getColumn(5).setMaxWidth(50);
            tb_FilmeFaca.getColumnModel().getColumn(6).setMinWidth(110);
            tb_FilmeFaca.getColumnModel().getColumn(6).setPreferredWidth(110);
            tb_FilmeFaca.getColumnModel().getColumn(6).setMaxWidth(110);
        }

        jPanel2.setBackground(new java.awt.Color(65, 65, 65));
        jPanel2.setMaximumSize(new java.awt.Dimension(250, 434));
        jPanel2.setMinimumSize(new java.awt.Dimension(250, 434));
        jPanel2.setPreferredSize(new java.awt.Dimension(250, 434));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Id:");

        txt_idFaca.setBackground(new java.awt.Color(255, 255, 255, 0));
        txt_idFaca.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txt_idFaca.setForeground(new java.awt.Color(255, 255, 255));
        txt_idFaca.setBorder(null);
        txt_idFaca.setEnabled(false);
        txt_idFaca.setFocusable(false);
        txt_idFaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idFacaActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("RP:");

        txt_rpFaca.setBackground(new java.awt.Color(63, 63, 63));
        txt_rpFaca.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txt_rpFaca.setForeground(new java.awt.Color(255, 255, 255));
        txt_rpFaca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_rpFacaKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("CLIENTE:");

        cb_clienteFaca.setBackground(new java.awt.Color(65, 65, 65));
        cb_clienteFaca.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cb_clienteFaca.setForeground(new java.awt.Color(255, 255, 255));
        cb_clienteFaca.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "*" }));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("FT:");

        txt_ftFaca.setBackground(new java.awt.Color(63, 63, 63));
        txt_ftFaca.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txt_ftFaca.setForeground(new java.awt.Color(255, 255, 255));
        txt_ftFaca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_ftFacaKeyPressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("SETOR:");

        cb_setorFaca.setBackground(new java.awt.Color(65, 65, 65));
        cb_setorFaca.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cb_setorFaca.setForeground(new java.awt.Color(255, 255, 255));
        cb_setorFaca.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "*" }));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("TURNO:");

        cb_turnoFaca.setBackground(new java.awt.Color(65, 65, 65));
        cb_turnoFaca.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cb_turnoFaca.setForeground(new java.awt.Color(255, 255, 255));
        cb_turnoFaca.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "*" }));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("OBSERVAÇÃO:");

        txt_obsFaca.setBackground(new java.awt.Color(63, 63, 63));
        txt_obsFaca.setColumns(20);
        txt_obsFaca.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txt_obsFaca.setForeground(new java.awt.Color(255, 255, 255));
        txt_obsFaca.setLineWrap(true);
        txt_obsFaca.setRows(3);
        txt_obsFaca.setTabSize(3);
        txt_obsFaca.setWrapStyleWord(true);
        txt_obsFaca.setAutoscrolls(false);
        jScrollPane2.setViewportView(txt_obsFaca);

        btn_adicionarFaca.setBackground(new java.awt.Color(25, 25, 25));
        btn_adicionarFaca.setForeground(new java.awt.Color(255, 255, 255));
        btn_adicionarFaca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Adicionar_32px.png"))); // NOI18N
        btn_adicionarFaca.setToolTipText("Adicionar novo!");
        btn_adicionarFaca.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_adicionarFaca.setBorderPainted(false);
        btn_adicionarFaca.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_adicionarFaca.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_adicionarFaca.setMaximumSize(new java.awt.Dimension(45, 45));
        btn_adicionarFaca.setMinimumSize(new java.awt.Dimension(45, 45));
        btn_adicionarFaca.setPreferredSize(new java.awt.Dimension(45, 45));
        btn_adicionarFaca.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_adicionarFaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_adicionarFacaActionPerformed(evt);
            }
        });

        btn_ExcluirFaca.setBackground(new java.awt.Color(25, 25, 25));
        btn_ExcluirFaca.setForeground(new java.awt.Color(255, 255, 255));
        btn_ExcluirFaca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Remover_32px.png"))); // NOI18N
        btn_ExcluirFaca.setToolTipText("Excluir!");
        btn_ExcluirFaca.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_ExcluirFaca.setBorderPainted(false);
        btn_ExcluirFaca.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_ExcluirFaca.setEnabled(false);
        btn_ExcluirFaca.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_ExcluirFaca.setMaximumSize(new java.awt.Dimension(45, 45));
        btn_ExcluirFaca.setMinimumSize(new java.awt.Dimension(45, 45));
        btn_ExcluirFaca.setPreferredSize(new java.awt.Dimension(45, 45));
        btn_ExcluirFaca.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_ExcluirFaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ExcluirFacaActionPerformed(evt);
            }
        });

        btn_alterarfaca.setBackground(new java.awt.Color(25, 25, 25));
        btn_alterarfaca.setForeground(new java.awt.Color(255, 255, 255));
        btn_alterarfaca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Alterar_32px.png"))); // NOI18N
        btn_alterarfaca.setToolTipText("Alterar!");
        btn_alterarfaca.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_alterarfaca.setBorderPainted(false);
        btn_alterarfaca.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_alterarfaca.setEnabled(false);
        btn_alterarfaca.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_alterarfaca.setMaximumSize(new java.awt.Dimension(45, 45));
        btn_alterarfaca.setMinimumSize(new java.awt.Dimension(45, 45));
        btn_alterarfaca.setPreferredSize(new java.awt.Dimension(45, 45));
        btn_alterarfaca.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_alterarfaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_alterarfacaActionPerformed(evt);
            }
        });

        btn_limparFaca.setBackground(new java.awt.Color(25, 25, 25));
        btn_limparFaca.setForeground(new java.awt.Color(255, 255, 255));
        btn_limparFaca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Limpar_32px.png"))); // NOI18N
        btn_limparFaca.setToolTipText("Limpar!");
        btn_limparFaca.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_limparFaca.setBorderPainted(false);
        btn_limparFaca.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_limparFaca.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_limparFaca.setMaximumSize(new java.awt.Dimension(45, 45));
        btn_limparFaca.setMinimumSize(new java.awt.Dimension(45, 45));
        btn_limparFaca.setPreferredSize(new java.awt.Dimension(45, 45));
        btn_limparFaca.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_limparFaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limparFacaActionPerformed(evt);
            }
        });

        btn_buscaRpFaca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Pesquisar2_16.png"))); // NOI18N
        btn_buscaRpFaca.setToolTipText("Pesquisar!");
        btn_buscaRpFaca.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_buscaRpFaca.setBorderPainted(false);
        btn_buscaRpFaca.setMaximumSize(new java.awt.Dimension(30, 30));
        btn_buscaRpFaca.setMinimumSize(new java.awt.Dimension(30, 30));
        btn_buscaRpFaca.setPreferredSize(new java.awt.Dimension(30, 30));
        btn_buscaRpFaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscaRpFacaActionPerformed(evt);
            }
        });

        btn_buscaFtFaca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Pesquisar2_16.png"))); // NOI18N
        btn_buscaFtFaca.setToolTipText("Pesquisar!");
        btn_buscaFtFaca.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_buscaFtFaca.setBorderPainted(false);
        btn_buscaFtFaca.setMaximumSize(new java.awt.Dimension(30, 30));
        btn_buscaFtFaca.setMinimumSize(new java.awt.Dimension(30, 30));
        btn_buscaFtFaca.setPreferredSize(new java.awt.Dimension(30, 30));
        btn_buscaFtFaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscaFtFacaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(98, 98, 98))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_turnoFaca, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_setorFaca, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_ftFaca, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_buscaFtFaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_clienteFaca, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_idFaca, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_rpFaca)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_buscaRpFaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 4, Short.MAX_VALUE)
                        .addComponent(btn_adicionarFaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_ExcluirFaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_alterarfaca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_limparFaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_idFaca))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txt_rpFaca)
                    .addComponent(btn_buscaRpFaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5, 5, 5))
                    .addComponent(cb_clienteFaca))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txt_ftFaca)
                    .addComponent(btn_buscaFtFaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5, 5, 5))
                    .addComponent(cb_setorFaca))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5, 5, 5))
                    .addComponent(cb_turnoFaca))
                .addGap(15, 15, 15)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btn_adicionarFaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_ExcluirFaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_alterarfaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_limparFaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(370, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(282, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelFilmeFacaLayout = new javax.swing.GroupLayout(jPanelFilmeFaca);
        jPanelFilmeFaca.setLayout(jPanelFilmeFacaLayout);
        jPanelFilmeFacaLayout.setHorizontalGroup(
            jPanelFilmeFacaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFilmeFacaLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneFilmeFaca, javax.swing.GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE))
        );
        jPanelFilmeFacaLayout.setVerticalGroup(
            jPanelFilmeFacaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFilmeFacaLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 111, Short.MAX_VALUE))
            .addComponent(jScrollPaneFilmeFaca)
        );

        jPanelPrincipal.add(jPanelFilmeFaca, "faca");
        jPanelFilmeFaca.getAccessibleContext().setAccessibleName("faca");

        jPanelFilmeHot.setBackground(new java.awt.Color(65, 65, 65));
        jPanelFilmeHot.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "FILME DE HOT", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(204, 255, 255))); // NOI18N
        jPanelFilmeHot.setForeground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(65, 65, 65));
        jPanel3.setMaximumSize(new java.awt.Dimension(250, 434));
        jPanel3.setMinimumSize(new java.awt.Dimension(250, 434));
        jPanel3.setPreferredSize(new java.awt.Dimension(250, 434));

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Id:");

        txt_idHot.setBackground(new java.awt.Color(255, 255, 255, 0));
        txt_idHot.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txt_idHot.setForeground(new java.awt.Color(255, 255, 255));
        txt_idHot.setBorder(null);
        txt_idHot.setEnabled(false);
        txt_idHot.setFocusable(false);
        txt_idHot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idHotActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("RP:");

        txt_rpHot.setBackground(new java.awt.Color(63, 63, 63));
        txt_rpHot.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txt_rpHot.setForeground(new java.awt.Color(255, 255, 255));
        txt_rpHot.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_rpHotKeyPressed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("CLIENTE:");

        cb_clienteHot.setBackground(new java.awt.Color(65, 65, 65));
        cb_clienteHot.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cb_clienteHot.setForeground(new java.awt.Color(255, 255, 255));
        cb_clienteHot.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "*" }));

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("FT:");

        txt_ftHot.setBackground(new java.awt.Color(63, 63, 63));
        txt_ftHot.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txt_ftHot.setForeground(new java.awt.Color(255, 255, 255));
        txt_ftHot.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_ftHotKeyPressed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("TURNO:");

        cb_turnoHot.setBackground(new java.awt.Color(65, 65, 65));
        cb_turnoHot.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cb_turnoHot.setForeground(new java.awt.Color(255, 255, 255));
        cb_turnoHot.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "*" }));

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("OBSERVAÇÃO:");

        txt_obsHot.setBackground(new java.awt.Color(63, 63, 63));
        txt_obsHot.setColumns(20);
        txt_obsHot.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txt_obsHot.setForeground(new java.awt.Color(255, 255, 255));
        txt_obsHot.setLineWrap(true);
        txt_obsHot.setRows(3);
        txt_obsHot.setTabSize(3);
        txt_obsHot.setWrapStyleWord(true);
        txt_obsHot.setAutoscrolls(false);
        jScrollPane3.setViewportView(txt_obsHot);

        btn_adicionarHot.setBackground(new java.awt.Color(25, 25, 25));
        btn_adicionarHot.setForeground(new java.awt.Color(255, 255, 255));
        btn_adicionarHot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Adicionar_32px.png"))); // NOI18N
        btn_adicionarHot.setToolTipText("Adicionar novo!");
        btn_adicionarHot.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_adicionarHot.setBorderPainted(false);
        btn_adicionarHot.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_adicionarHot.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_adicionarHot.setMaximumSize(new java.awt.Dimension(45, 45));
        btn_adicionarHot.setMinimumSize(new java.awt.Dimension(45, 45));
        btn_adicionarHot.setPreferredSize(new java.awt.Dimension(45, 45));
        btn_adicionarHot.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_adicionarHot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_adicionarHotActionPerformed(evt);
            }
        });

        btn_excluirHot.setBackground(new java.awt.Color(25, 25, 25));
        btn_excluirHot.setForeground(new java.awt.Color(255, 255, 255));
        btn_excluirHot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Remover_32px.png"))); // NOI18N
        btn_excluirHot.setToolTipText("Excluir!");
        btn_excluirHot.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_excluirHot.setBorderPainted(false);
        btn_excluirHot.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_excluirHot.setEnabled(false);
        btn_excluirHot.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_excluirHot.setMaximumSize(new java.awt.Dimension(45, 45));
        btn_excluirHot.setMinimumSize(new java.awt.Dimension(45, 45));
        btn_excluirHot.setPreferredSize(new java.awt.Dimension(45, 45));
        btn_excluirHot.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_excluirHot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirHotActionPerformed(evt);
            }
        });

        btn_alterarHot.setBackground(new java.awt.Color(25, 25, 25));
        btn_alterarHot.setForeground(new java.awt.Color(255, 255, 255));
        btn_alterarHot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Alterar_32px.png"))); // NOI18N
        btn_alterarHot.setToolTipText("Alterar!");
        btn_alterarHot.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_alterarHot.setBorderPainted(false);
        btn_alterarHot.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_alterarHot.setEnabled(false);
        btn_alterarHot.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_alterarHot.setMaximumSize(new java.awt.Dimension(45, 45));
        btn_alterarHot.setMinimumSize(new java.awt.Dimension(45, 45));
        btn_alterarHot.setPreferredSize(new java.awt.Dimension(45, 45));
        btn_alterarHot.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_alterarHot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_alterarHotActionPerformed(evt);
            }
        });

        btn_limparHot.setBackground(new java.awt.Color(25, 25, 25));
        btn_limparHot.setForeground(new java.awt.Color(255, 255, 255));
        btn_limparHot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Limpar_32px.png"))); // NOI18N
        btn_limparHot.setToolTipText("Limpar!");
        btn_limparHot.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_limparHot.setBorderPainted(false);
        btn_limparHot.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_limparHot.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_limparHot.setMaximumSize(new java.awt.Dimension(45, 45));
        btn_limparHot.setMinimumSize(new java.awt.Dimension(45, 45));
        btn_limparHot.setPreferredSize(new java.awt.Dimension(45, 45));
        btn_limparHot.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_limparHot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limparHotActionPerformed(evt);
            }
        });

        txt_facaHot.setBackground(new java.awt.Color(63, 63, 63));
        txt_facaHot.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txt_facaHot.setForeground(new java.awt.Color(255, 255, 255));

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("FACA:");

        btn_buscaRpHot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Pesquisar2_16.png"))); // NOI18N
        btn_buscaRpHot.setToolTipText("Pesquisar!");
        btn_buscaRpHot.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_buscaRpHot.setBorderPainted(false);
        btn_buscaRpHot.setMaximumSize(new java.awt.Dimension(30, 30));
        btn_buscaRpHot.setMinimumSize(new java.awt.Dimension(30, 30));
        btn_buscaRpHot.setPreferredSize(new java.awt.Dimension(30, 30));
        btn_buscaRpHot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscaRpHotActionPerformed(evt);
            }
        });

        btn_buscaFtHot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Pesquisar2_16.png"))); // NOI18N
        btn_buscaFtHot.setToolTipText("Pesquisar!");
        btn_buscaFtHot.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_buscaFtHot.setBorderPainted(false);
        btn_buscaFtHot.setMaximumSize(new java.awt.Dimension(30, 30));
        btn_buscaFtHot.setMinimumSize(new java.awt.Dimension(30, 30));
        btn_buscaFtHot.setPreferredSize(new java.awt.Dimension(30, 30));
        btn_buscaFtHot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscaFtHotActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(98, 98, 98))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_turnoHot, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_ftHot, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_buscaFtHot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_clienteHot, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_idHot, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(txt_rpHot)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_buscaRpHot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 4, Short.MAX_VALUE)
                        .addComponent(btn_adicionarHot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_excluirHot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_alterarHot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_limparHot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_facaHot)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_idHot))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txt_rpHot)
                    .addComponent(btn_buscaRpHot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5, 5, 5))
                    .addComponent(cb_clienteHot))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txt_ftHot)
                    .addComponent(btn_buscaFtHot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_facaHot))
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5, 5, 5))
                    .addComponent(cb_turnoHot))
                .addGap(15, 15, 15)
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btn_adicionarHot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_excluirHot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_alterarHot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_limparHot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(370, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(282, Short.MAX_VALUE))
        );

        jScrollPaneFilmeHot.setBackground(new java.awt.Color(65, 65, 65));
        jScrollPaneFilmeHot.setForeground(new java.awt.Color(255, 255, 255));

        tb_FilmeHot.setBackground(new java.awt.Color(65, 65, 65));
        tb_FilmeHot.setForeground(new java.awt.Color(255, 255, 255));
        tb_FilmeHot.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "RP", "Cliente", "FT", "Faca", "Turno", "Data", "Observação"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_FilmeHot.setToolTipText("Duplo clicke no registro para alterá-lo!");
        tb_FilmeHot.getTableHeader().setReorderingAllowed(false);
        tb_FilmeHot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_FilmeHotMouseClicked(evt);
            }
        });
        jScrollPaneFilmeHot.setViewportView(tb_FilmeHot);
        if (tb_FilmeHot.getColumnModel().getColumnCount() > 0) {
            tb_FilmeHot.getColumnModel().getColumn(0).setMinWidth(0);
            tb_FilmeHot.getColumnModel().getColumn(0).setPreferredWidth(0);
            tb_FilmeHot.getColumnModel().getColumn(0).setMaxWidth(0);
            tb_FilmeHot.getColumnModel().getColumn(1).setMinWidth(120);
            tb_FilmeHot.getColumnModel().getColumn(1).setPreferredWidth(120);
            tb_FilmeHot.getColumnModel().getColumn(1).setMaxWidth(120);
            tb_FilmeHot.getColumnModel().getColumn(2).setMinWidth(180);
            tb_FilmeHot.getColumnModel().getColumn(2).setPreferredWidth(180);
            tb_FilmeHot.getColumnModel().getColumn(2).setMaxWidth(180);
            tb_FilmeHot.getColumnModel().getColumn(3).setMinWidth(50);
            tb_FilmeHot.getColumnModel().getColumn(3).setPreferredWidth(50);
            tb_FilmeHot.getColumnModel().getColumn(3).setMaxWidth(50);
            tb_FilmeHot.getColumnModel().getColumn(4).setMinWidth(70);
            tb_FilmeHot.getColumnModel().getColumn(4).setPreferredWidth(70);
            tb_FilmeHot.getColumnModel().getColumn(4).setMaxWidth(70);
            tb_FilmeHot.getColumnModel().getColumn(5).setMinWidth(50);
            tb_FilmeHot.getColumnModel().getColumn(5).setPreferredWidth(50);
            tb_FilmeHot.getColumnModel().getColumn(5).setMaxWidth(50);
            tb_FilmeHot.getColumnModel().getColumn(6).setMinWidth(110);
            tb_FilmeHot.getColumnModel().getColumn(6).setPreferredWidth(110);
            tb_FilmeHot.getColumnModel().getColumn(6).setMaxWidth(110);
        }

        javax.swing.GroupLayout jPanelFilmeHotLayout = new javax.swing.GroupLayout(jPanelFilmeHot);
        jPanelFilmeHot.setLayout(jPanelFilmeHotLayout);
        jPanelFilmeHotLayout.setHorizontalGroup(
            jPanelFilmeHotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFilmeHotLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneFilmeHot, javax.swing.GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE))
        );
        jPanelFilmeHotLayout.setVerticalGroup(
            jPanelFilmeHotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFilmeHotLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
            .addComponent(jScrollPaneFilmeHot)
        );

        jPanelPrincipal.add(jPanelFilmeHot, "hot");
        jPanelFilmeHot.getAccessibleContext().setAccessibleName("hot");

        jPanelConfig.setBackground(new java.awt.Color(65, 65, 65));
        jPanelConfig.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CONFIGURAÇÃO", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(204, 255, 255))); // NOI18N
        jPanelConfig.setForeground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(63, 63, 63));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastro Cliente", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jScrollPaneClientes.setBackground(new java.awt.Color(65, 65, 65));
        jScrollPaneClientes.setForeground(new java.awt.Color(255, 255, 255));

        tb_Clientes.setAutoCreateRowSorter(true);
        tb_Clientes.setBackground(new java.awt.Color(65, 65, 65));
        tb_Clientes.setForeground(new java.awt.Color(255, 255, 255));
        tb_Clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Cliente"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_Clientes.setToolTipText("Duplo clicke no registro para alterá-lo!");
        tb_Clientes.getTableHeader().setReorderingAllowed(false);
        tb_Clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_ClientesMouseClicked(evt);
            }
        });
        jScrollPaneClientes.setViewportView(tb_Clientes);
        if (tb_Clientes.getColumnModel().getColumnCount() > 0) {
            tb_Clientes.getColumnModel().getColumn(0).setMinWidth(0);
            tb_Clientes.getColumnModel().getColumn(0).setPreferredWidth(0);
            tb_Clientes.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        txt_idCliente.setBackground(new java.awt.Color(255, 255, 255, 0));
        txt_idCliente.setForeground(new java.awt.Color(63, 63, 63));
        txt_idCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_idCliente.setBorder(null);
        txt_idCliente.setEnabled(false);
        txt_idCliente.setFocusable(false);
        txt_idCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idClienteActionPerformed(evt);
            }
        });

        btn_novoCliente.setBackground(new java.awt.Color(22, 22, 22));
        btn_novoCliente.setForeground(new java.awt.Color(255, 255, 255));
        btn_novoCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Adicionar_32px.png"))); // NOI18N
        btn_novoCliente.setToolTipText("Adicionar novo!");
        btn_novoCliente.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_novoCliente.setBorderPainted(false);
        btn_novoCliente.setEnabled(false);
        btn_novoCliente.setMaximumSize(new java.awt.Dimension(40, 40));
        btn_novoCliente.setMinimumSize(new java.awt.Dimension(40, 40));
        btn_novoCliente.setPreferredSize(new java.awt.Dimension(40, 40));
        btn_novoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_novoClienteActionPerformed(evt);
            }
        });

        btn_alterarCliente.setBackground(new java.awt.Color(22, 22, 22));
        btn_alterarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Alterar_32px.png"))); // NOI18N
        btn_alterarCliente.setToolTipText("Alterar!");
        btn_alterarCliente.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_alterarCliente.setBorderPainted(false);
        btn_alterarCliente.setEnabled(false);
        btn_alterarCliente.setMaximumSize(new java.awt.Dimension(40, 40));
        btn_alterarCliente.setMinimumSize(new java.awt.Dimension(40, 40));
        btn_alterarCliente.setPreferredSize(new java.awt.Dimension(40, 40));
        btn_alterarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_alterarClienteActionPerformed(evt);
            }
        });

        btn_limparCliente.setBackground(new java.awt.Color(22, 22, 22));
        btn_limparCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Limpar_32px.png"))); // NOI18N
        btn_limparCliente.setToolTipText("Limpar!");
        btn_limparCliente.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_limparCliente.setBorderPainted(false);
        btn_limparCliente.setEnabled(false);
        btn_limparCliente.setMaximumSize(new java.awt.Dimension(40, 40));
        btn_limparCliente.setMinimumSize(new java.awt.Dimension(40, 40));
        btn_limparCliente.setPreferredSize(new java.awt.Dimension(40, 40));
        btn_limparCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limparClienteActionPerformed(evt);
            }
        });

        btn_excluirCliente.setBackground(new java.awt.Color(22, 22, 22));
        btn_excluirCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Remover_32px.png"))); // NOI18N
        btn_excluirCliente.setToolTipText("Excluir!");
        btn_excluirCliente.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_excluirCliente.setBorderPainted(false);
        btn_excluirCliente.setEnabled(false);
        btn_excluirCliente.setMaximumSize(new java.awt.Dimension(40, 40));
        btn_excluirCliente.setMinimumSize(new java.awt.Dimension(40, 40));
        btn_excluirCliente.setPreferredSize(new java.awt.Dimension(40, 40));
        btn_excluirCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirClienteActionPerformed(evt);
            }
        });

        txt_nomeCliente.setBackground(new java.awt.Color(63, 63, 63));
        txt_nomeCliente.setForeground(new java.awt.Color(255, 255, 255));
        txt_nomeCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nomeCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nomeClienteKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txt_idCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nomeCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(btn_novoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_alterarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_excluirCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_limparCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPaneClientes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_idCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btn_alterarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_excluirCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_limparCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_novoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPaneClientes)))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(63, 63, 63));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastro Setor", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jScrollPaneSetor.setBackground(new java.awt.Color(65, 65, 65));
        jScrollPaneSetor.setForeground(new java.awt.Color(255, 255, 255));

        tb_Setor.setAutoCreateRowSorter(true);
        tb_Setor.setBackground(new java.awt.Color(65, 65, 65));
        tb_Setor.setForeground(new java.awt.Color(255, 255, 255));
        tb_Setor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Setor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_Setor.setToolTipText("Duplo clicke no registro para alterá-lo!");
        tb_Setor.getTableHeader().setReorderingAllowed(false);
        tb_Setor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_SetorMouseClicked(evt);
            }
        });
        jScrollPaneSetor.setViewportView(tb_Setor);
        if (tb_Setor.getColumnModel().getColumnCount() > 0) {
            tb_Setor.getColumnModel().getColumn(0).setMinWidth(0);
            tb_Setor.getColumnModel().getColumn(0).setPreferredWidth(0);
            tb_Setor.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        btn_novoSetor.setBackground(new java.awt.Color(22, 22, 22));
        btn_novoSetor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Adicionar_32px.png"))); // NOI18N
        btn_novoSetor.setToolTipText("Adicionar novo!");
        btn_novoSetor.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_novoSetor.setBorderPainted(false);
        btn_novoSetor.setEnabled(false);
        btn_novoSetor.setMaximumSize(new java.awt.Dimension(40, 40));
        btn_novoSetor.setMinimumSize(new java.awt.Dimension(40, 40));
        btn_novoSetor.setPreferredSize(new java.awt.Dimension(40, 40));
        btn_novoSetor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_novoSetorActionPerformed(evt);
            }
        });

        btn_alterarSetor.setBackground(new java.awt.Color(22, 22, 22));
        btn_alterarSetor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Alterar_32px.png"))); // NOI18N
        btn_alterarSetor.setToolTipText("Alterar!");
        btn_alterarSetor.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_alterarSetor.setBorderPainted(false);
        btn_alterarSetor.setEnabled(false);
        btn_alterarSetor.setMaximumSize(new java.awt.Dimension(40, 40));
        btn_alterarSetor.setMinimumSize(new java.awt.Dimension(40, 40));
        btn_alterarSetor.setPreferredSize(new java.awt.Dimension(40, 40));
        btn_alterarSetor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_alterarSetorActionPerformed(evt);
            }
        });

        btn_excluirSetor.setBackground(new java.awt.Color(22, 22, 22));
        btn_excluirSetor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Remover_32px.png"))); // NOI18N
        btn_excluirSetor.setToolTipText("Excluir!");
        btn_excluirSetor.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_excluirSetor.setBorderPainted(false);
        btn_excluirSetor.setEnabled(false);
        btn_excluirSetor.setMaximumSize(new java.awt.Dimension(40, 40));
        btn_excluirSetor.setMinimumSize(new java.awt.Dimension(40, 40));
        btn_excluirSetor.setPreferredSize(new java.awt.Dimension(40, 40));
        btn_excluirSetor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirSetorActionPerformed(evt);
            }
        });

        btn_limparSetor.setBackground(new java.awt.Color(22, 22, 22));
        btn_limparSetor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Limpar_32px.png"))); // NOI18N
        btn_limparSetor.setToolTipText("Limpar!");
        btn_limparSetor.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_limparSetor.setBorderPainted(false);
        btn_limparSetor.setEnabled(false);
        btn_limparSetor.setMaximumSize(new java.awt.Dimension(40, 40));
        btn_limparSetor.setMinimumSize(new java.awt.Dimension(40, 40));
        btn_limparSetor.setPreferredSize(new java.awt.Dimension(40, 40));
        btn_limparSetor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limparSetorActionPerformed(evt);
            }
        });

        txt_nomeSetor.setBackground(new java.awt.Color(63, 63, 63));
        txt_nomeSetor.setForeground(new java.awt.Color(255, 255, 255));
        txt_nomeSetor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nomeSetor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nomeSetorActionPerformed(evt);
            }
        });
        txt_nomeSetor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nomeSetorKeyPressed(evt);
            }
        });

        txt_idSetor.setBackground(new java.awt.Color(255, 255, 255, 0));
        txt_idSetor.setForeground(new java.awt.Color(63, 63, 63));
        txt_idSetor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_idSetor.setBorder(null);
        txt_idSetor.setEnabled(false);
        txt_idSetor.setFocusable(false);
        txt_idSetor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idSetorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneSetor, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btn_novoSetor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_alterarSetor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_excluirSetor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_limparSetor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txt_idSetor, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nomeSetor)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_idSetor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nomeSetor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_novoSetor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_alterarSetor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_excluirSetor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_limparSetor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneSetor, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(63, 63, 63));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastro Turno", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jScrollPaneTurnos.setBackground(new java.awt.Color(65, 65, 65));
        jScrollPaneTurnos.setForeground(new java.awt.Color(255, 255, 255));

        tb_Turnos.setAutoCreateRowSorter(true);
        tb_Turnos.setBackground(new java.awt.Color(65, 65, 65));
        tb_Turnos.setForeground(new java.awt.Color(255, 255, 255));
        tb_Turnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Turno"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_Turnos.setToolTipText("Duplo clicke no registro para alterá-lo!");
        tb_Turnos.getTableHeader().setReorderingAllowed(false);
        tb_Turnos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_TurnosMouseClicked(evt);
            }
        });
        jScrollPaneTurnos.setViewportView(tb_Turnos);
        if (tb_Turnos.getColumnModel().getColumnCount() > 0) {
            tb_Turnos.getColumnModel().getColumn(0).setMinWidth(0);
            tb_Turnos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tb_Turnos.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        btn_novoTurno.setBackground(new java.awt.Color(22, 22, 22));
        btn_novoTurno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Adicionar_32px.png"))); // NOI18N
        btn_novoTurno.setToolTipText("Adicionar novo!");
        btn_novoTurno.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_novoTurno.setBorderPainted(false);
        btn_novoTurno.setEnabled(false);
        btn_novoTurno.setMaximumSize(new java.awt.Dimension(40, 40));
        btn_novoTurno.setMinimumSize(new java.awt.Dimension(40, 40));
        btn_novoTurno.setPreferredSize(new java.awt.Dimension(40, 40));
        btn_novoTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_novoTurnoActionPerformed(evt);
            }
        });

        btn_alterarTurno.setBackground(new java.awt.Color(22, 22, 22));
        btn_alterarTurno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Alterar_32px.png"))); // NOI18N
        btn_alterarTurno.setToolTipText("Alterar!");
        btn_alterarTurno.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_alterarTurno.setBorderPainted(false);
        btn_alterarTurno.setEnabled(false);
        btn_alterarTurno.setMaximumSize(new java.awt.Dimension(40, 40));
        btn_alterarTurno.setMinimumSize(new java.awt.Dimension(40, 40));
        btn_alterarTurno.setPreferredSize(new java.awt.Dimension(40, 40));
        btn_alterarTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_alterarTurnoActionPerformed(evt);
            }
        });

        btn_excluirTurno.setBackground(new java.awt.Color(22, 22, 22));
        btn_excluirTurno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Remover_32px.png"))); // NOI18N
        btn_excluirTurno.setToolTipText("Excluir!");
        btn_excluirTurno.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_excluirTurno.setBorderPainted(false);
        btn_excluirTurno.setEnabled(false);
        btn_excluirTurno.setMaximumSize(new java.awt.Dimension(40, 40));
        btn_excluirTurno.setMinimumSize(new java.awt.Dimension(40, 40));
        btn_excluirTurno.setPreferredSize(new java.awt.Dimension(40, 40));
        btn_excluirTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirTurnoActionPerformed(evt);
            }
        });

        btn_limparTurno.setBackground(new java.awt.Color(22, 22, 22));
        btn_limparTurno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Limpar_32px.png"))); // NOI18N
        btn_limparTurno.setToolTipText("Limpar!");
        btn_limparTurno.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_limparTurno.setBorderPainted(false);
        btn_limparTurno.setEnabled(false);
        btn_limparTurno.setMaximumSize(new java.awt.Dimension(40, 40));
        btn_limparTurno.setMinimumSize(new java.awt.Dimension(40, 40));
        btn_limparTurno.setPreferredSize(new java.awt.Dimension(40, 40));
        btn_limparTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limparTurnoActionPerformed(evt);
            }
        });

        txt_idTurno.setBackground(new java.awt.Color(255, 255, 255, 0));
        txt_idTurno.setForeground(new java.awt.Color(63, 63, 63));
        txt_idTurno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_idTurno.setBorder(null);
        txt_idTurno.setEnabled(false);
        txt_idTurno.setFocusable(false);
        txt_idTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idTurnoActionPerformed(evt);
            }
        });

        txt_nomeTurno.setBackground(new java.awt.Color(63, 63, 63));
        txt_nomeTurno.setForeground(new java.awt.Color(255, 255, 255));
        txt_nomeTurno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nomeTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nomeTurnoActionPerformed(evt);
            }
        });
        txt_nomeTurno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nomeTurnoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(txt_idTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_nomeTurno))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(btn_novoTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_alterarTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_excluirTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_limparTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPaneTurnos, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_idTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nomeTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_alterarTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_novoTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_excluirTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_limparTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneTurnos, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelConfigLayout = new javax.swing.GroupLayout(jPanelConfig);
        jPanelConfig.setLayout(jPanelConfigLayout);
        jPanelConfigLayout.setHorizontalGroup(
            jPanelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelConfigLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelConfigLayout.setVerticalGroup(
            jPanelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConfigLayout.createSequentialGroup()
                .addGroup(jPanelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(219, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanelPrincipal.add(jPanelConfig, "config");
        jPanelConfig.getAccessibleContext().setAccessibleName("config");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton1)
                    .addComponent(jLabel1))
                .addGap(150, 150, 150)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(jButton2))
                .addGap(150, 150, 150)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton3)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        MostraCard_jPanelPrincipal("faca");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        MostraCard_jPanelPrincipal("hot");    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        MostraCard_jPanelPrincipal("config");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btn_adicionarFacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_adicionarFacaActionPerformed
        if ("".equals(txt_rpFaca.getText())) {
            JOptionPane.showMessageDialog(null, "RP Inválido!", "", 2);
        } else {
            if ("*".equals(cb_clienteFaca.getSelectedItem().toString())) {
                JOptionPane.showMessageDialog(null, "Cliente Iválido!", "", 2);
            } else {
                if ("".equals(txt_ftFaca.getText())) {
                    JOptionPane.showMessageDialog(null, "FT Iválido!", "", 2);
                } else {
                    if ("*".equals(cb_setorFaca.getSelectedItem().toString())) {
                        JOptionPane.showMessageDialog(null, "Setor Iválido!", "", 2);
                    } else {
                        if ("*".equals(cb_turnoFaca.getSelectedItem().toString())) {
                            JOptionPane.showMessageDialog(null, "Turno Iválido!", "", 2);
                        } else {
                            Utilitarios dh = new Utilitarios();
                            String dataHora = dh.DH();

                            FilmeFaca obj = new FilmeFaca();

                            obj.setRp(txt_rpFaca.getText());
                            obj.setCliente(cb_clienteFaca.getSelectedItem().toString());
                            obj.setFichaTecnica(Integer.parseInt(txt_ftFaca.getText()));
                            obj.setSetor(cb_setorFaca.getSelectedItem().toString());
                            obj.setTurno(cb_turnoFaca.getSelectedItem().toString());
                            obj.setOcorrencia(dataHora);
                            obj.setObs(txt_obsFaca.getText());

                            FilmeFacaDAO dao = new FilmeFacaDAO();
                            dao.cadastrarFilmeFaca(obj);

                            LimparFilmeFaca();
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_btn_adicionarFacaActionPerformed

    private void btn_ExcluirFacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ExcluirFacaActionPerformed

        String rp = txt_rpFaca.getText();
        int resposta = JOptionPane.showConfirmDialog(null, "Excluir RP " + rp + "?", "", JOptionPane.YES_NO_OPTION);

        if (resposta == 0) {
            FilmeFaca obj = new FilmeFaca();

            obj.setId(Integer.parseInt(txt_idFaca.getText()));

            FilmeFacaDAO dao = new FilmeFacaDAO();

            dao.excluirFilmeFaca(obj);

            LimparFilmeFaca();
        }
    }//GEN-LAST:event_btn_ExcluirFacaActionPerformed

    private void btn_alterarfacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_alterarfacaActionPerformed
        if ("".equals(txt_rpFaca.getText())) {
            JOptionPane.showMessageDialog(null, "RP Inválido!", "", 2);
        } else {
            if ("*".equals(cb_clienteFaca.getSelectedItem().toString())) {
                JOptionPane.showMessageDialog(null, "Cliente Iválido!", "", 2);
            } else {
                if ("".equals(txt_ftFaca.getText())) {
                    JOptionPane.showMessageDialog(null, "FT Iválido!", "", 2);
                } else {
                    if ("*".equals(cb_setorFaca.getSelectedItem().toString())) {
                        JOptionPane.showMessageDialog(null, "Setor Iválido!", "", 2);
                    } else {
                        if ("*".equals(cb_turnoFaca.getSelectedItem().toString())) {
                            JOptionPane.showMessageDialog(null, "Turno Iválido!", "", 2);
                        } else {
                            Utilitarios dh = new Utilitarios();
                            String dataHora = dh.DH();

                            FilmeFaca obj = new FilmeFaca();

                            obj.setRp(txt_rpFaca.getText());
                            obj.setCliente(cb_clienteFaca.getSelectedItem().toString());
                            obj.setFichaTecnica(Integer.parseInt(txt_ftFaca.getText()));
                            obj.setSetor(cb_setorFaca.getSelectedItem().toString());
                            obj.setTurno(cb_turnoFaca.getSelectedItem().toString());
                            obj.setOcorrencia(dataHora);
                            obj.setObs(txt_obsFaca.getText());
                            obj.setId(Integer.parseInt(txt_idFaca.getText()));

                            FilmeFacaDAO dao = new FilmeFacaDAO();
                            dao.alterarFilmeFaca(obj);

                            //this.dispose();
                        }
                    }
                }
            }

        }
    }//GEN-LAST:event_btn_alterarfacaActionPerformed

    private void txt_idFacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idFacaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idFacaActionPerformed

    private void btn_limparFacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limparFacaActionPerformed
        LimparFilmeFaca();
    }//GEN-LAST:event_btn_limparFacaActionPerformed

    private void txt_idHotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idHotActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idHotActionPerformed

    private void btn_adicionarHotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_adicionarHotActionPerformed
        if ("".equals(txt_rpHot.getText())) {
            JOptionPane.showMessageDialog(null, "RP Inválido!", "", 2);
        } else {
            if ("*".equals(cb_clienteHot.getSelectedItem().toString())) {
                JOptionPane.showMessageDialog(null, "Cliente Iválido!", "", 2);
            } else {
                if ("".equals(txt_ftHot.getText())) {
                    JOptionPane.showMessageDialog(null, "FT Iválido!", "", 2);
                } else {
                    if ("".equals(txt_facaHot.getText())) {
                        JOptionPane.showMessageDialog(null, "Setor Iválido!", "", 2);
                    } else {
                        if ("*".equals(cb_turnoHot.getSelectedItem().toString())) {
                            JOptionPane.showMessageDialog(null, "Turno Iválido!", "", 2);
                        } else {
                            Utilitarios dh = new Utilitarios();
                            String dataHora = dh.DH();

                            FilmeHot obj = new FilmeHot();

                            obj.setRp(txt_rpHot.getText());
                            obj.setCliente(cb_clienteHot.getSelectedItem().toString());
                            obj.setFichaTecnica(Integer.parseInt(txt_ftHot.getText()));
                            obj.setFaca(txt_facaHot.getText());
                            obj.setTurno(cb_turnoHot.getSelectedItem().toString());
                            obj.setOcorrencia(dataHora);
                            obj.setObs(txt_obsHot.getText());

                            FilmeHotDAO dao = new FilmeHotDAO();
                            dao.cadastrarFilmeHot(obj);

                            LimparFilmeHot();
                        }
                    }
                }
            }

        }
    }//GEN-LAST:event_btn_adicionarHotActionPerformed

    private void btn_excluirHotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirHotActionPerformed

        String rp = txt_rpHot.getText();
        int resposta = JOptionPane.showConfirmDialog(null, "Excluir RP " + rp + "?", "", JOptionPane.YES_NO_OPTION);

        if (resposta == 0) {
            FilmeHot obj = new FilmeHot();

            obj.setId(Integer.parseInt(txt_idHot.getText()));

            FilmeHotDAO dao = new FilmeHotDAO();

            dao.excluirFilmeHot(obj);

            LimparFilmeHot();
        }
    }//GEN-LAST:event_btn_excluirHotActionPerformed

    private void btn_alterarHotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_alterarHotActionPerformed
        if ("".equals(txt_rpHot.getText())) {
            JOptionPane.showMessageDialog(null, "RP Inválido!", "", 2);
        } else {
            if ("*".equals(cb_clienteHot.getSelectedItem().toString())) {
                JOptionPane.showMessageDialog(null, "Cliente Iválido!", "", 2);
            } else {
                if ("".equals(txt_ftHot.getText())) {
                    JOptionPane.showMessageDialog(null, "FT Iválido!", "", 2);
                } else {
                    if ("".equals(txt_facaHot.getText())) {
                        JOptionPane.showMessageDialog(null, "Setor Iválido!", "", 2);
                    } else {
                        if ("*".equals(cb_turnoHot.getSelectedItem().toString())) {
                            JOptionPane.showMessageDialog(null, "Turno Iválido!", "", 2);
                        } else {
                            Utilitarios dh = new Utilitarios();
                            String dataHora = dh.DH();

                            FilmeHot obj = new FilmeHot();

                            obj.setRp(txt_rpHot.getText());
                            obj.setCliente(cb_clienteHot.getSelectedItem().toString());
                            obj.setFichaTecnica(Integer.parseInt(txt_ftHot.getText()));
                            obj.setFaca(txt_facaHot.getText());
                            obj.setTurno(cb_turnoHot.getSelectedItem().toString());
                            obj.setOcorrencia(dataHora);
                            obj.setObs(txt_obsHot.getText());
                            obj.setId(Integer.parseInt(txt_idHot.getText()));

                            FilmeHotDAO dao = new FilmeHotDAO();
                            dao.alterarFilmeHot(obj);

                            //this.dispose();
                        }
                    }
                }
            }

        }
    }//GEN-LAST:event_btn_alterarHotActionPerformed

    private void btn_limparHotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limparHotActionPerformed
        LimparFilmeHot();
    }//GEN-LAST:event_btn_limparHotActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        listarFilmeFaca();
        listarFilmeHot();
        listarClientes();
        listarSetor();
        listarTurno();
    }//GEN-LAST:event_formWindowActivated

    private void txt_rpFacaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_rpFacaKeyPressed
        PesquisaRpFtFilmeFaca();
    }//GEN-LAST:event_txt_rpFacaKeyPressed

    private void txt_ftFacaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ftFacaKeyPressed
        PesquisaRpFtFilmeFaca();
    }//GEN-LAST:event_txt_ftFacaKeyPressed

    private void txt_rpHotKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_rpHotKeyPressed
        PesquisaRpFtFilmeHot();
    }//GEN-LAST:event_txt_rpHotKeyPressed

    private void txt_ftHotKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ftHotKeyPressed
        PesquisaRpFtFilmeHot();
    }//GEN-LAST:event_txt_ftHotKeyPressed

    private void tb_FilmeHotMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_FilmeHotMouseClicked

        tb_FilmeHot.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {

                    String c0 = tb_FilmeHot.getValueAt(tb_FilmeHot.getSelectedRow(), 0).toString();
                    String c1 = tb_FilmeHot.getValueAt(tb_FilmeHot.getSelectedRow(), 1).toString();
                    String c2 = tb_FilmeHot.getValueAt(tb_FilmeHot.getSelectedRow(), 2).toString();
                    String c3 = tb_FilmeHot.getValueAt(tb_FilmeHot.getSelectedRow(), 3).toString();
                    String c4 = tb_FilmeHot.getValueAt(tb_FilmeHot.getSelectedRow(), 4).toString();
                    String c5 = tb_FilmeHot.getValueAt(tb_FilmeHot.getSelectedRow(), 5).toString();
                    String c6 = tb_FilmeHot.getValueAt(tb_FilmeHot.getSelectedRow(), 7).toString();

                    txt_idHot.setText(c0);
                    txt_rpHot.setText(c1);
                    cb_clienteHot.setSelectedItem(c2);
                    txt_ftHot.setText(c3);
                    txt_facaHot.setText(c4);
                    cb_turnoHot.setSelectedItem(c5);
                    txt_obsHot.setText(c6);
                    btn_adicionarHot.setEnabled(false);
                    btn_excluirHot.setEnabled(true);
                    btn_alterarHot.setEnabled(true);
                }
            }
        });

    }//GEN-LAST:event_tb_FilmeHotMouseClicked

    private void tb_ClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_ClientesMouseClicked
        //Pega dados da tabela tb_Clientes com duplo click
        tb_Clientes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    txt_idCliente.setText(tb_Clientes.getValueAt(tb_Clientes.getSelectedRow(), 0).toString());
                    txt_nomeCliente.setText(tb_Clientes.getValueAt(tb_Clientes.getSelectedRow(), 1).toString());

                    //desabilita botões
                    btn_novoCliente.setEnabled(false);
                    btn_alterarCliente.setEnabled(true);
                    btn_excluirCliente.setEnabled(true);
                    btn_limparCliente.setEnabled(true);

                }
            }
        });
    }//GEN-LAST:event_tb_ClientesMouseClicked

    private void txt_idClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idClienteActionPerformed

    private void btn_novoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoClienteActionPerformed
        // Criar novo registro Cliente
        Clientes obj = new Clientes();

        obj.setNomeCliente(txt_nomeCliente.getText());

        ClientesDAO dao = new ClientesDAO();
        dao.CadastrarClientes(obj);

        txt_idCliente.setText("");
        txt_nomeCliente.setText("");

        btn_novoCliente.setEnabled(false);
        btn_alterarCliente.setEnabled(false);
        btn_excluirCliente.setEnabled(false);
        btn_limparCliente.setEnabled(false);
        
        AtualizarcbClientes();
    }//GEN-LAST:event_btn_novoClienteActionPerformed

    private void btn_alterarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_alterarClienteActionPerformed
        // Alterar novo registro Cliente
        Clientes obj = new Clientes();

        obj.setNomeCliente(txt_nomeCliente.getText());
        obj.setId(Integer.parseInt(txt_idCliente.getText()));

        ClientesDAO dao = new ClientesDAO();
        dao.alterarClientes(obj);

        txt_idCliente.setText("");
        txt_nomeCliente.setText("");

        btn_novoCliente.setEnabled(false);
        btn_alterarCliente.setEnabled(false);
        btn_excluirCliente.setEnabled(false);
        btn_limparCliente.setEnabled(false);
        
        AtualizarcbClientes();
    }//GEN-LAST:event_btn_alterarClienteActionPerformed

    private void btn_limparClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limparClienteActionPerformed
        // TODO add your handling code here:
        txt_idCliente.setText("");
        txt_nomeCliente.setText("");

        btn_novoCliente.setEnabled(false);
        btn_alterarCliente.setEnabled(false);
        btn_excluirCliente.setEnabled(false);
        btn_limparCliente.setEnabled(false);
    }//GEN-LAST:event_btn_limparClienteActionPerformed

    private void btn_excluirClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirClienteActionPerformed
        // botao Excluir Clientes
        Clientes obj = new Clientes();

        obj.setId(Integer.parseInt(txt_idCliente.getText()));

        ClientesDAO dao = new ClientesDAO();

        dao.excluirClientes(obj);

        //Limpa os paineis
        txt_idCliente.setText("");
        txt_nomeCliente.setText("");

        //desabilita botões
        btn_novoCliente.setEnabled(false);
        btn_alterarCliente.setEnabled(false);
        btn_excluirCliente.setEnabled(false);
        btn_limparCliente.setEnabled(false);
        
        AtualizarcbClientes();
    }//GEN-LAST:event_btn_excluirClienteActionPerformed

    private void txt_nomeClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nomeClienteKeyPressed
        if ("".equals(txt_idCliente.getText())) {
            btn_novoCliente.setEnabled(true);
            btn_alterarCliente.setEnabled(false);
            btn_excluirCliente.setEnabled(false);
            btn_limparCliente.setEnabled(true);
        }
    }//GEN-LAST:event_txt_nomeClienteKeyPressed

    private void tb_SetorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_SetorMouseClicked
        //Pega dados da tabela tb_Clientes com duplo click
        tb_Setor.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    txt_idSetor.setText(tb_Setor.getValueAt(tb_Setor.getSelectedRow(), 0).toString());
                    txt_nomeSetor.setText(tb_Setor.getValueAt(tb_Setor.getSelectedRow(), 1).toString());

                    //desabilita botões
                    btn_novoSetor.setEnabled(false);
                    btn_alterarSetor.setEnabled(true);
                    btn_excluirSetor.setEnabled(true);
                    btn_limparSetor.setEnabled(true);

                }
            }
        });
    }//GEN-LAST:event_tb_SetorMouseClicked

    private void btn_novoSetorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoSetorActionPerformed
        // Criar novo registro Setor
        Setor obj = new Setor();

        obj.setNomeSetor(txt_nomeSetor.getText());

        SetorDAO dao = new SetorDAO();
        dao.CadastrarSetor(obj);

        txt_idSetor.setText("");
        txt_nomeSetor.setText("");

        btn_novoSetor.setEnabled(false);
        btn_alterarSetor.setEnabled(false);
        btn_excluirSetor.setEnabled(false);
        btn_limparSetor.setEnabled(false);
        
        AtualizarcbSetor();
    }//GEN-LAST:event_btn_novoSetorActionPerformed

    private void btn_alterarSetorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_alterarSetorActionPerformed
        // Alterar novo registro Cliente
        Setor obj = new Setor();

        obj.setNomeSetor(txt_nomeSetor.getText());
        obj.setId(Integer.parseInt(txt_idSetor.getText()));

        SetorDAO dao = new SetorDAO();
        dao.alterarSetor(obj);

        txt_idSetor.setText("");
        txt_nomeSetor.setText("");

        btn_novoSetor.setEnabled(false);
        btn_alterarSetor.setEnabled(false);
        btn_excluirSetor.setEnabled(false);
        btn_limparSetor.setEnabled(false);
        
        AtualizarcbSetor();
    }//GEN-LAST:event_btn_alterarSetorActionPerformed

    private void btn_excluirSetorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirSetorActionPerformed
        // botao Excluir Clientes
        Setor obj = new Setor();

        obj.setId(Integer.parseInt(txt_idSetor.getText()));

        SetorDAO dao = new SetorDAO();

        dao.excluirSetor(obj);

        //Limpa os paineis
        txt_idSetor.setText("");
        txt_nomeSetor.setText("");

        btn_novoSetor.setEnabled(false);
        btn_alterarSetor.setEnabled(false);
        btn_excluirSetor.setEnabled(false);
        btn_limparSetor.setEnabled(false);
        
        AtualizarcbSetor();
    }//GEN-LAST:event_btn_excluirSetorActionPerformed

    private void btn_limparSetorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limparSetorActionPerformed
        // TODO add your handling code here:
        txt_idSetor.setText("");
        txt_nomeSetor.setText("");

        btn_novoSetor.setEnabled(false);
        btn_alterarSetor.setEnabled(false);
        btn_excluirSetor.setEnabled(false);
        btn_limparSetor.setEnabled(false);
    }//GEN-LAST:event_btn_limparSetorActionPerformed

    private void txt_nomeSetorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nomeSetorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nomeSetorActionPerformed

    private void txt_nomeSetorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nomeSetorKeyPressed
        // TODO add your handling code here:
        if ("".equals(txt_idSetor.getText())) {
            btn_novoSetor.setEnabled(true);
            btn_alterarSetor.setEnabled(false);
            btn_excluirSetor.setEnabled(false);
            btn_limparSetor.setEnabled(true);
        }
    }//GEN-LAST:event_txt_nomeSetorKeyPressed

    private void txt_idSetorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idSetorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idSetorActionPerformed

    private void tb_TurnosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_TurnosMouseClicked
        //Pega dados da tabela tb_Clientes com duplo click
        tb_Turnos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    txt_idTurno.setText(tb_Turnos.getValueAt(tb_Turnos.getSelectedRow(), 0).toString());
                    txt_nomeTurno.setText(tb_Turnos.getValueAt(tb_Turnos.getSelectedRow(), 1).toString());

                    //desabilita botões
                    btn_novoTurno.setEnabled(false);
                    btn_alterarTurno.setEnabled(true);
                    btn_excluirTurno.setEnabled(true);
                    btn_limparTurno.setEnabled(true);

                }
            }
        });
    }//GEN-LAST:event_tb_TurnosMouseClicked

    private void btn_novoTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoTurnoActionPerformed
        // Criar novo registro Cliente
        Turno obj = new Turno();

        obj.setNomeTurno(txt_nomeTurno.getText());

        TurnoDAO dao = new TurnoDAO();
        dao.CadastrarTurno(obj);

        txt_idTurno.setText("");
        txt_nomeTurno.setText("");

        btn_novoTurno.setEnabled(false);
        btn_alterarTurno.setEnabled(false);
        btn_excluirTurno.setEnabled(false);
        btn_limparTurno.setEnabled(false);
        
        AtualizarcbTurno();
    }//GEN-LAST:event_btn_novoTurnoActionPerformed

    private void btn_alterarTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_alterarTurnoActionPerformed
        // Alterar novo registro Cliente
        Turno obj = new Turno();

        obj.setNomeTurno(txt_nomeTurno.getText());
        obj.setId(Integer.parseInt(txt_idTurno.getText()));

        TurnoDAO dao = new TurnoDAO();
        dao.alterarTurno(obj);

        txt_idTurno.setText("");
        txt_nomeTurno.setText("");

        btn_novoTurno.setEnabled(false);
        btn_alterarTurno.setEnabled(false);
        btn_excluirTurno.setEnabled(false);
        btn_limparTurno.setEnabled(false);
        
        AtualizarcbTurno();
    }//GEN-LAST:event_btn_alterarTurnoActionPerformed

    private void btn_excluirTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirTurnoActionPerformed
        // botao Excluir Clientes
        Turno obj = new Turno();

        obj.setId(Integer.parseInt(txt_idTurno.getText()));

        TurnoDAO dao = new TurnoDAO();

        dao.excluirTurno(obj);

        //Limpa os paineis
        txt_idTurno.setText("");
        txt_nomeTurno.setText("");

        btn_novoTurno.setEnabled(false);
        btn_alterarTurno.setEnabled(false);
        btn_excluirTurno.setEnabled(false);
        btn_limparTurno.setEnabled(false);
        
        AtualizarcbTurno();
    }//GEN-LAST:event_btn_excluirTurnoActionPerformed

    private void btn_limparTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limparTurnoActionPerformed
        // TODO add your handling code here:
        txt_idTurno.setText("");
        txt_nomeTurno.setText("");

        btn_novoTurno.setEnabled(false);
        btn_alterarTurno.setEnabled(false);
        btn_excluirTurno.setEnabled(false);
        btn_limparTurno.setEnabled(false);
    }//GEN-LAST:event_btn_limparTurnoActionPerformed

    private void txt_idTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idTurnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idTurnoActionPerformed

    private void txt_nomeTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nomeTurnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nomeTurnoActionPerformed

    private void txt_nomeTurnoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nomeTurnoKeyPressed
        // TODO add your handling code here:
        if ("".equals(txt_idTurno.getText())) {
            btn_novoTurno.setEnabled(true);
            btn_alterarTurno.setEnabled(false);
            btn_excluirTurno.setEnabled(false);
            btn_limparTurno.setEnabled(true);
        }
    }//GEN-LAST:event_txt_nomeTurnoKeyPressed

    private void tb_FilmeFacaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_FilmeFacaMouseClicked

        tb_FilmeFaca.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String c0 = tb_FilmeFaca.getValueAt(tb_FilmeFaca.getSelectedRow(), 0).toString();
                    String c1 = tb_FilmeFaca.getValueAt(tb_FilmeFaca.getSelectedRow(), 1).toString();
                    String c2 = tb_FilmeFaca.getValueAt(tb_FilmeFaca.getSelectedRow(), 2).toString();
                    String c3 = tb_FilmeFaca.getValueAt(tb_FilmeFaca.getSelectedRow(), 3).toString();
                    String c4 = tb_FilmeFaca.getValueAt(tb_FilmeFaca.getSelectedRow(), 4).toString();
                    String c5 = tb_FilmeFaca.getValueAt(tb_FilmeFaca.getSelectedRow(), 5).toString();
                    String c6 = tb_FilmeFaca.getValueAt(tb_FilmeFaca.getSelectedRow(), 7).toString();

                    txt_idFaca.setText(c0);
                    txt_rpFaca.setText(c1);
                    cb_clienteFaca.setSelectedItem(c2);
                    txt_ftFaca.setText(c3);
                    cb_setorFaca.setSelectedItem(c4);
                    cb_turnoFaca.setSelectedItem(c5);
                    txt_obsFaca.setText(c6);
                    btn_adicionarFaca.setEnabled(false);
                    btn_ExcluirFaca.setEnabled(true);
                    btn_alterarfaca.setEnabled(true);
                }
            }
        });
    }//GEN-LAST:event_tb_FilmeFacaMouseClicked

    private void btn_buscaRpHotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscaRpHotActionPerformed
        PesquisaRpFtFilmeHot();
    }//GEN-LAST:event_btn_buscaRpHotActionPerformed

    private void btn_buscaFtHotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscaFtHotActionPerformed
        PesquisaRpFtFilmeHot();
    }//GEN-LAST:event_btn_buscaFtHotActionPerformed

    private void btn_buscaRpFacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscaRpFacaActionPerformed
        PesquisaRpFtFilmeFaca();
    }//GEN-LAST:event_btn_buscaRpFacaActionPerformed

    private void btn_buscaFtFacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscaFtFacaActionPerformed
        PesquisaRpFtFilmeFaca();
    }//GEN-LAST:event_btn_buscaFtFacaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ExcluirFaca;
    private javax.swing.JButton btn_adicionarFaca;
    private javax.swing.JButton btn_adicionarHot;
    private javax.swing.JButton btn_alterarCliente;
    private javax.swing.JButton btn_alterarHot;
    private javax.swing.JButton btn_alterarSetor;
    private javax.swing.JButton btn_alterarTurno;
    private javax.swing.JButton btn_alterarfaca;
    private javax.swing.JButton btn_buscaFtFaca;
    private javax.swing.JButton btn_buscaFtHot;
    private javax.swing.JButton btn_buscaRpFaca;
    private javax.swing.JButton btn_buscaRpHot;
    private javax.swing.JButton btn_excluirCliente;
    private javax.swing.JButton btn_excluirHot;
    private javax.swing.JButton btn_excluirSetor;
    private javax.swing.JButton btn_excluirTurno;
    private javax.swing.JButton btn_limparCliente;
    private javax.swing.JButton btn_limparFaca;
    private javax.swing.JButton btn_limparHot;
    private javax.swing.JButton btn_limparSetor;
    private javax.swing.JButton btn_limparTurno;
    private javax.swing.JButton btn_novoCliente;
    private javax.swing.JButton btn_novoSetor;
    private javax.swing.JButton btn_novoTurno;
    public javax.swing.JComboBox cb_clienteFaca;
    public javax.swing.JComboBox cb_clienteHot;
    public javax.swing.JComboBox cb_setorFaca;
    public javax.swing.JComboBox cb_turnoFaca;
    public javax.swing.JComboBox cb_turnoHot;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanelConfig;
    private javax.swing.JPanel jPanelFilmeFaca;
    private javax.swing.JPanel jPanelFilmeHot;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPaneClientes;
    private javax.swing.JScrollPane jScrollPaneFilmeFaca;
    private javax.swing.JScrollPane jScrollPaneFilmeHot;
    private javax.swing.JScrollPane jScrollPaneSetor;
    private javax.swing.JScrollPane jScrollPaneTurnos;
    private javax.swing.JTable tb_Clientes;
    private javax.swing.JTable tb_FilmeFaca;
    private javax.swing.JTable tb_FilmeHot;
    private javax.swing.JTable tb_Setor;
    private javax.swing.JTable tb_Turnos;
    public javax.swing.JTextField txt_facaHot;
    public javax.swing.JTextField txt_ftFaca;
    public javax.swing.JTextField txt_ftHot;
    private javax.swing.JTextField txt_idCliente;
    public javax.swing.JTextField txt_idFaca;
    public javax.swing.JTextField txt_idHot;
    private javax.swing.JTextField txt_idSetor;
    private javax.swing.JTextField txt_idTurno;
    private javax.swing.JTextField txt_nomeCliente;
    private javax.swing.JTextField txt_nomeSetor;
    private javax.swing.JTextField txt_nomeTurno;
    public javax.swing.JTextArea txt_obsFaca;
    public javax.swing.JTextArea txt_obsHot;
    public javax.swing.JTextField txt_rpFaca;
    public javax.swing.JTextField txt_rpHot;
    // End of variables declaration//GEN-END:variables
}
