/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.dashboard;

import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import javax.swing.JOptionPane;

/**
 *
 * @author henri
 */
public class UserControlDashboard extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public UserControlDashboard() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    private void consultar() {
        String sql = "select * from tbusers where iduser = ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUserId.getText());
            rs = pst.executeQuery();

            if (rs.next()) {
                txtUserName.setText(rs.getString(2));
                txtUserLog.setText(rs.getString(4));
                txtUserPwd.setText(rs.getString(5));
                txtUserPho.setText(rs.getString(3));
                cboUserPerf.setSelectedItem(rs.getString(6));
            } else {
                JOptionPane.showMessageDialog(null, "Id não encontrado");
                limparCamposUser();

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void adicionar() {
        String sql = "insert into tbusers (iduser,userdb,phone,login,passwrd,profile)"
                + " values(?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUserId.getText());
            pst.setString(2, txtUserName.getText());
            pst.setString(4, txtUserLog.getText());
            pst.setString(5, txtUserPwd.getText());
            pst.setString(3, txtUserPho.getText());
            pst.setString(6, cboUserPerf.getSelectedItem().toString());
            // Validando os dados digitados, verificando se tem campo nulo:
            if ((txtUserId.getText().isEmpty()) || (txtUserName.getText().isEmpty())
                    || (txtUserLog.getText().isEmpty()) || (txtUserPwd.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos campos obrigatórios");
            } else {
                // a linha abaixo atualiza a tabela usuers com os dados do formulário.
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso");
                    limparCamposUser();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void alterar() {
        String sql = "update tbusers set userdb=?, phone=?, login=?, passwrd=?,"
                + " profile=? where iduser=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUserName.getText());
            pst.setString(2, txtUserPho.getText());
            pst.setString(3, txtUserLog.getText());
            pst.setString(4, txtUserPwd.getText());
            pst.setString(5, cboUserPerf.getSelectedItem().toString());
            pst.setString(6, txtUserId.getText());

            // Validando os dados digitados, verificando se tem campo nulo:
            if ((txtUserId.getText().isEmpty()) || (txtUserName.getText().isEmpty())
                    || (txtUserLog.getText().isEmpty()) || (txtUserPwd.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos campos obrigatórios");
            } else {
                // a linha abaixo atualiza a tabela usuers com os dados do formulário.
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null,
                            "Dados do Usuário alterado com sucesso");
                    limparCamposUser();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void remover() {
        // método para apagar todos os campos do usuário selecionado :
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que "
                + "deseja continuar ? Os dados apagados não poderão ser "
                + "resgatados", "Atenção", JOptionPane.YES_NO_OPTION);
        System.out.println(confirma);

        if (confirma == 0) {
            String sql = "delete from tbusers where iduser=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtUserId.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário Removido"
                            + " com sucesso");
                    limparCamposUser();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    private void limparCamposUser() {
        txtUserId.setText(null);
        txtUserName.setText(null);
        txtUserLog.setText(null);
        txtUserPwd.setText(null);
        txtUserPho.setText(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtUserId = new javax.swing.JTextField();
        txtUserName = new javax.swing.JTextField();
        txtUserLog = new javax.swing.JTextField();
        txtUserPho = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtUserPwd = new javax.swing.JTextField();
        cboUserPerf = new javax.swing.JComboBox<>();
        btnUserRead = new javax.swing.JButton();
        btnUserCreate = new javax.swing.JButton();
        btnUserUpdate = new javax.swing.JButton();
        btnUserDelete = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Controle de Usuário");
        setPreferredSize(new java.awt.Dimension(640, 480));

        jLabel1.setText("* Id nº");

        jLabel2.setText("* Nome");

        jLabel3.setText("* Login");

        jLabel4.setText("Fone");

        jLabel5.setText("* Senha");

        jLabel6.setText("Perfil");

        cboUserPerf.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "user1", "admin" }));

        btnUserRead.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icons/8684058_folder_file_document_search_find_icon.png"))); // NOI18N
        btnUserRead.setToolTipText("Pesquisar (READ)");
        btnUserRead.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUserRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserReadActionPerformed(evt);
            }
        });

        btnUserCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icons/8684066_folder_file_document_add_new_icon.png"))); // NOI18N
        btnUserCreate.setToolTipText("Adicionar (CREATE)");
        btnUserCreate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUserCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserCreateActionPerformed(evt);
            }
        });

        btnUserUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icons/8684055_folder_file_document_reload_refresh_icon.png"))); // NOI18N
        btnUserUpdate.setToolTipText("Atualizar (UPDATE)");
        btnUserUpdate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUserUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserUpdateActionPerformed(evt);
            }
        });

        btnUserDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icons/8684067_folder_file_document_cancel_cross_icon.png"))); // NOI18N
        btnUserDelete.setToolTipText("Apagar (DELETE)");
        btnUserDelete.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUserDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserDeleteActionPerformed(evt);
            }
        });

        jLabel7.setText("* Campos Obrigatórios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtUserId, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtUserName))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnUserRead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnUserCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtUserPho))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtUserLog, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cboUserPerf, 0, 106, Short.MAX_VALUE)
                                    .addComponent(txtUserPwd)))
                            .addComponent(jLabel6)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnUserUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnUserDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUserId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtUserLog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtUserPwd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtUserPho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboUserPerf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUserRead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUserCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUserUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUserDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUserUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserUpdateActionPerformed
        // Chamando o método alterar
        alterar();
    }//GEN-LAST:event_btnUserUpdateActionPerformed

    private void btnUserDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserDeleteActionPerformed
        // Chamando o método para excluir usuário do BD
        remover();
    }//GEN-LAST:event_btnUserDeleteActionPerformed

    private void btnUserReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserReadActionPerformed
        // Chama o metodo consultar
        consultar();
    }//GEN-LAST:event_btnUserReadActionPerformed

    private void btnUserCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserCreateActionPerformed
        // Chama o método adicionar usuário:
        adicionar();
    }//GEN-LAST:event_btnUserCreateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUserCreate;
    private javax.swing.JButton btnUserDelete;
    private javax.swing.JButton btnUserRead;
    private javax.swing.JButton btnUserUpdate;
    private javax.swing.JComboBox<String> cboUserPerf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtUserId;
    private javax.swing.JTextField txtUserLog;
    private javax.swing.JTextField txtUserName;
    private javax.swing.JTextField txtUserPho;
    private javax.swing.JTextField txtUserPwd;
    // End of variables declaration//GEN-END:variables
}
