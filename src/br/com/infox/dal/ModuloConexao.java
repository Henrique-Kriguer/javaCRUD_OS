
package br.com.infox.dal;

import java.sql.*;

public class ModuloConexao {
    //método responsável em conectar com BD
    public static Connection conector(){
        // variavel conexao é do tipo java.sql.Connection
        Connection conexao = null;
        //a linha abaixo "chama" o driver
        String driver = "com.mysql.cj.jdbc.Driver";
        //armazenando info ref ao DB
        String url = "jdbc:mysql://localhost:3306/dbinfox";
        String user = "root";
        String password = "Krigu3r2022#";
        //Estabelecendo conexao com o BD
        
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            /*A linha abaixo serve de apoio ao status da conexão
           System.out.println(e);*/
           
            return null;
        }
    }
}
