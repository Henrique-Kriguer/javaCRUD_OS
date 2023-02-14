package br.com.infox.dashboard;

import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import java.awt.Color;
import javax.swing.JOptionPane;

public class LoginDashboard extends javax.swing.JFrame {
    // usando a variável conexao do DAL
    Connection conexao = null;
    // Prepare Statement e ResultSet são frameworks do pacote java.sql
    // e servem para preparar e executar as instruções SQL
    PreparedStatement pst = null;
    ResultSet rs = null;

    public void logar() {
        String sql = "select * from tbusers where login = ? and passwrd = ? ";
        try {
            /*as linhas abaixo preparam a consulta ao BD em função do que foi 
        digitado nas caixas de texto ( armazenado na variavel pst, o sinal de interrogação é substituido 
        pelo conteudo das variáveis */
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUser.getText());
            String captura = new String(txtPasswrd.getPassword());
            pst.setString(2, captura);
// o riscado em getText significa que o campo "esconde " o que foi digitado.
            // a linha abaixo executa a query ( consulta ao BD)
            rs = pst.executeQuery();

            // estrutura de decisão para existência ou não do usuário e senha correspondente:
            if (rs.next()) {
                // a linha abaixo obtem o conteúdo do campo profile( 6º campo) da tabela tbusers:
                String profile = rs.getString(6);
                // System.out.println(profile);

                // a estrutura faz o tratamento do perfil do usuário:
                // se for administrador, libera (setEnabled) cadastro de usuário e relatório de serviço
                // a label lblUser é preenchida com o nome do user do BD
                // se user for tipo administrador, cor da fonte vermelha.
                // this.dispose mantem a tela principal ativa e fecha tela de login.
                
                if (profile.equals("admin")) {
                    MainDashboard dash = new MainDashboard();
                    dash.setVisible(true);
                    MainDashboard.menRelSer.setEnabled(true);
                    MainDashboard.menCadUso.setEnabled(true);
                    MainDashboard.lblUser.setText(rs.getString(2));
                    MainDashboard.lblUser.setForeground(Color.red);
                    this.dispose();
                    conexao.close();
                }else{
                MainDashboard dash = new MainDashboard();
                    dash.setVisible(true);
                    MainDashboard.lblUser.setText(rs.getString(2));
                    this.dispose();
                    conexao.close();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuário e/ou senha inválido(s).");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public LoginDashboard() {
        initComponents();
        conexao = ModuloConexao.conector();
        /*A linha abaixo serve de apoio ao status da conexão
       System.out.println(conexao);*/
        if (conexao != null) {
            lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icons/db_ok_50.png")));
        } else {
            lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icons/db_error_50.png")));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        txtPasswrd = new javax.swing.JPasswordField();
        lblStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Ordem de Serviços - Login");
        setResizable(false);

        jLabel1.setText("Usuário:");

        jLabel2.setText("Senha:");

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icons/db_error_50.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblStatus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 173, Short.MAX_VALUE)
                        .addComponent(btnLogin))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtUser)
                            .addComponent(txtPasswrd, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPasswrd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStatus)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(btnLogin)))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(412, 218));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // Chamando o método logar

        logar();
    }//GEN-LAST:event_btnLoginActionPerformed

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
            java.util.logging.Logger.getLogger(LoginDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JPasswordField txtPasswrd;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
