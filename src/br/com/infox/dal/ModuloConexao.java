
package br.com.infox.dal;
/*
 * The MIT License
 *
 * Copyright 2023 henrique Kriguer.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
import java.sql.*;
/**
 * Conexão com o Banco de Dados
 * @author henrique Kriguer
 */

public class ModuloConexao {
    /**
     * Método responsável em conectar com BD
     * @return 
     */
    
    public static Connection conector(){
        // variavel conexao é do tipo java.sql.Connection
        Connection conexao = null;
        //a linha abaixo "chama" o driver
        String driver = "com.mysql.cj.jdbc.Driver";
        //armazenando info ref ao DB
        String url = "jdbc:mysql://localhost:3306/service_control?characterEncoding=utf-8";
        String user = "root";
        String password = "1971";
        //Estabelecendo conexao com o BD
        
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            System.out.println(conexao);
            return conexao;
        } catch (Exception e) {
           // A linha abaixo serve de apoio ao status da conexão
           System.out.println(e);
           
            return null;
        }
    }
}
