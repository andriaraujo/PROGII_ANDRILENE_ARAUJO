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
    
    public List<Perfil> buscarPerfil() {
        List<Perfil> listaParaRetorno = new ArrayList<Perfil>();
        String sql = "select * from tb_perfil";

        try {
            PreparedStatement instrucaoSelecao = conexao.prepareStatement(sql);
            ResultSet resultado = instrucaoSelecao.executeQuery();

            while (resultado.next()) {
                Perfil perfil = new Perfil();
                perfil.setId_perfil(resultado.getInt("id_perfil"));
                perfil.setNome(resultado.getString("nome"));
                perfil.setDescricao(resultado.getString("descricao"));
                listaParaRetorno.add(perfil);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoPerfil.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return listaParaRetorno;
    }
    
     public void alterarPerfil(Perfil perfil) {
        String sql = "update tb_perfil "
                + "set nome=?, descricao=? "
                + "where id_perfil=?";
        try {
            PreparedStatement preparacaoDaInstrucao = conexao.prepareStatement(sql);
            preparacaoDaInstrucao.setString(1, perfil.getNome());
            preparacaoDaInstrucao.setString(2, perfil.getDescricao());
            preparacaoDaInstrucao.setInt(3, perfil.getId_perfil());
            preparacaoDaInstrucao.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
     }
        public void excluirPerfil(int id_Perfil) {
        String sql = "delete from tb_perfil "
                + "where id_perfil=?";
        try {
            PreparedStatement preparacaoDaInstrucao = conexao.prepareStatement(sql);
            preparacaoDaInstrucao.setInt(1, id_Perfil);
            
            preparacaoDaInstrucao.executeUpdate();
            preparacaoDaInstrucao.close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoPerfil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
