package br.com.infox.dashboard;

import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 * 10/2/2023
 *
 * @author Henrique Kriguer
 */
public class ClientDashboard extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public ClientDashboard() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    private void adicionar() {
        String sql = "insert into tbclients (nameclient,adressclient,phoneclient,emailclient) "
                + "values (?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCliName.getText());
            pst.setString(2, txtCliAdress.getText());
            pst.setString(3, txtCliPho.getText());
            pst.setString(4, txtCliEmail.getText());

            if ((txtCliName.getText().isEmpty()) || (txtCliPho.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso");
                    limparCampos();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // método para pesquisar clientes pelo nome ou letra:
    private void pesquisarCliente() {
        String sql = "select idclient as Id, nameclient as Nome, adressclient as"
                + " Endereço, phoneclient as Telefone, emailclient as Email from"
                + " tbclients where nameclient like ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCliSearch.getText() + "%");
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar para preencher a tabela
            tblCli.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    // método para setar os campos do formulário com o conteúdo da tabela.

    public void setarCampos() {
        int setar = tblCli.getSelectedRow();
        txtCliId.setText(tblCli.getModel().getValueAt(setar, 0).toString());
        txtCliName.setText(tblCli.getModel().getValueAt(setar, 1).toString());
        txtCliAdress.setText(tblCli.getModel().getValueAt(setar, 2).toString());
        txtCliPho.setText(tblCli.getModel().getValueAt(setar, 3).toString());
        txtCliEmail.setText(tblCli.getModel().getValueAt(setar, 4).toString());
        btnCliCreate.setEnabled(false);
    }

    public void alterarCliente() {
        String sql = "update tbclients set nameclient = ?,adressclient = ?,"
                + " phoneclient = ?, emailclient = ? where idclient = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCliName.getText());
            pst.setString(2, txtCliAdress.getText());
            pst.setString(3, txtCliPho.getText());
            pst.setString(4, txtCliEmail.getText());
            pst.setString(5, txtCliId.getText());

            if (txtCliName.getText().isEmpty() || txtCliPho.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do Cliente atualizados com sucesso");
                    limparCampos();
                    btnCliCreate.setEnabled(true);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void excluirCliente() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza"
                + " que deseja apagar os dados deste cliente? Os dados apagados "
                + "não poderão ser recuperados !", "Atençao", JOptionPane.YES_NO_OPTION);
        if (confirma == 0) {
            String sql = "delete from tbclients where idclient = ?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtCliId.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente apagado com sucesso");
                    limparCampos();
                    btnCliCreate.setEnabled(true);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    private void limparCampos() {
        txtCliId.setText(null);
        txtCliName.setText(null);
        txtCliAdress.setText(null);
        txtCliPho.setText(null);
        txtCliEmail.setText(null);
        txtCliSearch.setText(null);
        ((DefaultTableModel) tblCli.getModel()).setRowCount(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCliName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCliAdress = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCliPho = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCliEmail = new javax.swing.JTextField();
        btnCliCreate = new javax.swing.JButton();
        btnCliUpdate = new javax.swing.JButton();
        btnCliDelete = new javax.swing.JButton();
        txtCliSearch = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCli = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtCliId = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setClosable(true);
        setIconifiable(true);
        setTitle("Clientes");
        setToolTipText("");
        setPreferredSize(new java.awt.Dimension(640, 480));

        jLabel1.setText("* Campos obrigatórios");

        jLabel2.setText("* Nome");

        jLabel3.setText("Endereço");

        jLabel4.setText("* Telefone");

        jLabel5.setText("email");

        btnCliCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icons/8684066_folder_file_document_add_new_icon.png"))); // NOI18N
        btnCliCreate.setText("jButton1");
        btnCliCreate.setToolTipText("Cadastrar");
        btnCliCreate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnCliCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliCreateActionPerformed(evt);
            }
        });

        btnCliUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icons/8684055_folder_file_document_reload_refresh_icon.png"))); // NOI18N
        btnCliUpdate.setText("jButton2");
        btnCliUpdate.setToolTipText("Alterar");
        btnCliUpdate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnCliUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliUpdateActionPerformed(evt);
            }
        });

        btnCliDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icons/8684067_folder_file_document_cancel_cross_icon.png"))); // NOI18N
        btnCliDelete.setText("jButton3");
        btnCliDelete.setToolTipText("Excluir");
        btnCliDelete.setPreferredSize(new java.awt.Dimension(80, 80));
        btnCliDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliDeleteActionPerformed(evt);
            }
        });

        txtCliSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliSearchKeyReleased(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icons/Search_icon_50x50.png"))); // NOI18N

        tblCli = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblCli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id Cliente", "Nome", "Endereço", "Telefone", "Email"
            }
        ));
        tblCli.setFocusable(false);
        tblCli.getTableHeader().setReorderingAllowed(false);
        tblCli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCliMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCli);

        jLabel7.setText("Id Cliente");

        txtCliId.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(btnCliCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(120, 120, 120)
                        .addComponent(btnCliUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82)
                        .addComponent(btnCliDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCliPho, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCliAdress, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
                            .addComponent(txtCliEmail)
                            .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCliName, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txtCliSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(27, 27, 27)
                            .addComponent(jLabel6)
                            .addGap(51, 51, 51)
                            .addComponent(jLabel1))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6)
                        .addComponent(txtCliSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCliName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCliAdress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCliPho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCliDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCliUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCliCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCliCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliCreateActionPerformed
        // Chama o metodo adicionar clientes
        adicionar();
    }//GEN-LAST:event_btnCliCreateActionPerformed

    private void txtCliSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliSearchKeyReleased
        // Este evento permite executar o metodo abaixo em tempo real, conforme 
        //vamos digitando o texto na caixa de pesquisa a pesquisa de cliente vai sendo feita.
        pesquisarCliente();


    }//GEN-LAST:event_txtCliSearchKeyReleased

    private void tblCliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCliMouseClicked
        //Chamar o metodo para setar os campos da tabela para as caixas d texto
        setarCampos();
    }//GEN-LAST:event_tblCliMouseClicked

    private void btnCliUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliUpdateActionPerformed
        // Chama o método de atualizar os campos do cliente na tabela BD
        alterarCliente();
    }//GEN-LAST:event_btnCliUpdateActionPerformed

    private void btnCliDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliDeleteActionPerformed
        // Chamando o metodo para deletar o cliente selecionado
        excluirCliente();
    }//GEN-LAST:event_btnCliDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCliCreate;
    private javax.swing.JButton btnCliDelete;
    private javax.swing.JButton btnCliUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tblCli;
    private javax.swing.JTextField txtCliAdress;
    private javax.swing.JTextField txtCliEmail;
    private javax.swing.JTextField txtCliId;
    private javax.swing.JTextField txtCliName;
    private javax.swing.JTextField txtCliPho;
    private javax.swing.JTextField txtCliSearch;
    // End of variables declaration//GEN-END:variables
}
