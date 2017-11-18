/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabalhoAv2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Stefano
 */
public class ConexaoBanco {

    static String URL = "jdbc:postgresql://localhost:5432/trab_prog_ii";
    static String USUARIO = "postgres";
    static String SENHA = "aa062178";
    static String status;

    public static Connection criarConexao() {
        Connection conexao = null;

        try {
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (conexao != null) {
            status = "CONECTADO!!!";
        } else {
            status = "NÃO CONECTADO!!!";
        }

        return conexao;
    }

    public String getStatus() {
        return status;
    }
}
