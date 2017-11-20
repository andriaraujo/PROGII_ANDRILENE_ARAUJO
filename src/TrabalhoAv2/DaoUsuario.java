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
public class DaoUsuario {

    Connection conexao = ConexaoBanco.criarConexao();

    public int salvarUsuario(Usuario usuario) {
        String sql = "insert into tb_usuario"
                + "(nome, sobrenome)"
                + "values(?,?)";
        try {
            PreparedStatement preparacaoDaInstrucao = conexao.prepareStatement(sql);
            preparacaoDaInstrucao.setString(1, usuario.getNome());
            preparacaoDaInstrucao.setString(2, usuario.getSobrenome());
            return preparacaoDaInstrucao.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }

    }

    public List<Usuario> buscarUsuario() {
        List<Usuario> listaParaRetorno = new ArrayList<Usuario>();
        String sql = "select * from tb_usuario";

        try {
            PreparedStatement instrucaoSelecao = conexao.prepareStatement(sql);
            ResultSet resultado = instrucaoSelecao.executeQuery();

            while (resultado.next()) {
                Usuario usuario = new Usuario();
                usuario.setId_usuario(resultado.getInt("id_usuario"));
                usuario.setNome(resultado.getString("nome"));
                usuario.setSobrenome(resultado.getString("sobrenome"));
                listaParaRetorno.add(usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return listaParaRetorno;
    }

  public void alterarUsuario(Usuario usuario) {
        String sql = "update tb_usuario "
                + "set nome=?, sobrenome=? "
                + "where id_usuario=?";
        try {
            PreparedStatement preparacaoDaInstrucao = conexao.prepareStatement(sql);
            preparacaoDaInstrucao.setString(1, usuario.getNome());
            preparacaoDaInstrucao.setString(2, usuario.getSobrenome());
            preparacaoDaInstrucao.setInt(3, usuario.getId_usuario());
            preparacaoDaInstrucao.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }


	public void excluirUsuario(int id) {
        String sql = "delete from tb_usuario "
                + "where id_usuario=?";
        try {
            PreparedStatement preparacaoDaInstrucao = conexao.prepareStatement(sql);
            preparacaoDaInstrucao.setInt(1, id);
            
            preparacaoDaInstrucao.executeUpdate();
            preparacaoDaInstrucao.close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
