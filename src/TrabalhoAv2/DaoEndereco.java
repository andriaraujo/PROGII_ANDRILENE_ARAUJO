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
public class DaoEndereco {

    Connection conexao = ConexaoBanco.criarConexao();

    public int salvarEndereco(Endereco endereco) {
        String sql = "insert into tb_endereco"
                + "(logradouro, complemento, bairro, numero, cep)"
                + "values(?,?,?,?,?)";
        try {
            PreparedStatement preparacaoDaInstrucao = conexao.prepareStatement(sql);
            preparacaoDaInstrucao.setString(1, endereco.getLogradouro());
            preparacaoDaInstrucao.setString(2, endereco.getComplemento());
            preparacaoDaInstrucao.setString(3, endereco.getBairro());
            preparacaoDaInstrucao.setString(4, endereco.getNumero());
            preparacaoDaInstrucao.setString(5, endereco.getCep());
            return preparacaoDaInstrucao.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }

    }
}
