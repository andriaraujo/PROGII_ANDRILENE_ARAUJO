/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabalhoAv2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Stefano
 */
public class DaoPerfil {
    Connection conexao = ConexaoBanco.criarConexao();

    public int salvarPerfil(Perfil perfil) {
        String sql = "insert into tb_perfil"
                + "(nome, descricao)"
                + "values(?,?)";
        try {
            PreparedStatement preparacaoDaInstrucao = conexao.prepareStatement(sql);
            preparacaoDaInstrucao.setString(1, perfil.getNome());
            preparacaoDaInstrucao.setString(2, perfil.getDescricao());
            return preparacaoDaInstrucao.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
}
