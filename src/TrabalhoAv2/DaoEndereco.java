/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabalhoAv2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public void alterarEndereco(Endereco endereco) {
        String sql = "update tb_endereco "
                + "set logradouro=?, complemento=?, bairro=?, numero=?, cep=? "
                + "where id_endereco=?";
        try {
            PreparedStatement preparacaoDaInstrucao = conexao.prepareStatement(sql);
            preparacaoDaInstrucao.setString(1, endereco.getLogradouro());
            preparacaoDaInstrucao.setString(2, endereco.getComplemento());
            preparacaoDaInstrucao.setString(3, endereco.getBairro());
            preparacaoDaInstrucao.setString(4, endereco.getNumero());
            preparacaoDaInstrucao.setString(5, endereco.getCep());
            preparacaoDaInstrucao.setInt(6, endereco.getId_endereco());
            preparacaoDaInstrucao.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    public void excluirEndereco(int id) {
        String sql = "delete from tb_endereco "
                + "where id_endereco=?";
        try {
            PreparedStatement preparacaoDaInstrucao = conexao.prepareStatement(sql);
            preparacaoDaInstrucao.setInt(1, id);
            preparacaoDaInstrucao.executeUpdate();
            preparacaoDaInstrucao.close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoEndereco.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
    public List<Endereco> buscarEndereco() {
        List<Endereco> listaParaRetorno = new ArrayList<Endereco>();
        String sql = "select * from tb_endereco";

        try {
            PreparedStatement instrucaoSelecao = conexao.prepareStatement(sql);
            ResultSet resultado = instrucaoSelecao.executeQuery();

            while (resultado.next()) {
                Endereco endereco = new Endereco();
                endereco.setId_endereco(resultado.getInt("id_endereco"));
                endereco.setLogradouro(resultado.getString("logradouro"));
                endereco.setComplemento(resultado.getString("complemento"));
                endereco.setBairro(resultado.getString("bairro"));
                endereco.setNumero(resultado.getString("numero"));
                endereco.setCep(resultado.getString("cep"));
                listaParaRetorno.add(endereco);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoEndereco.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return listaParaRetorno;
    }
    
}