package org.sorvete;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

public class SorveteDao {

    public void salvar(Sorvete s) {
        if (s.getIdsorvete() > 0) {
            alterar(s);
        } else {
            inserir(s);
        }
    }

    public Retorno inserir(Sorvete p) {
        Conexao con = new Conexao();
        RetornoDao retornoDao = new RetornoDao();
        try {
            String sql = "INSERT INTO sorvete (sabor, tamanho, cobertura, marca, formato, calorias)"
                    + " VALUES (?,?,?,?,?,?)";
            PreparedStatement prep = con.getConnection().prepareStatement(sql);
            prep.setString(1, p.getSabor());
            prep.setString(2, p.getTamanho());
            prep.setString(3, p.getCobertura());
            prep.setString(4, p.getMarca());
            prep.setString(5, p.getFormato());
            prep.setString(6, p.getCalorias());
            prep.execute();

            String mensagem = "Inserido com sucesso!";
            Boolean resposta = true;

            Retorno retorno = retornoDao.RetornoJson(resposta, mensagem);

            con.desconecta();
            return retorno;

        } catch (Exception e) {
            e.printStackTrace();
            con.desconecta();
            String mensagem = "Falha ao inserir!";
            Boolean resposta = false;

            Retorno retorno = retornoDao.RetornoJson(resposta, mensagem);

            return retorno;
        }
    }

    public LinkedList<Sorvete> listar(String pesquisa) {
        LinkedList<Sorvete> lista = new LinkedList<Sorvete>();
        Conexao con = new Conexao();
        try {
            String sql = "SELECT * FROM sorvete "
                    + "WHERE sabor like ? "
                    + "ORDER BY sabor";
            PreparedStatement sta = con.getConnection().prepareStatement(sql);
            sta.setString(1, "%" + pesquisa + "%");

            ResultSet res = sta.executeQuery();
            while (res.next()) {
                Sorvete s = new Sorvete();
                s.setIdsorvete(res.getInt("idsorvete"));
                s.setSabor(res.getString("sabor"));
                s.setTamanho(res.getString("tamanho"));
                s.setCobertura(res.getString("cobertura"));
                s.setMarca(res.getString("marca"));
                s.setFormato(res.getString("formato"));
                s.setCalorias(res.getString("calorias"));
                lista.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        con.desconecta();
        return lista;
    }

    public Retorno alterar(Sorvete s) {
        Conexao con = new Conexao();
        RetornoDao retornoDao = new RetornoDao();

        try {
            String sql = "UPDATE sorvete SET"
                    + " sabor = ?, tamanho = ?,"
                    + " cobertura = ?, marca = ?,"
                    + " formato = ?, calorias = ? "
                    + "WHERE idsorvete = ?";
            PreparedStatement prep = con.getConnection().prepareStatement(sql);
            prep.setString(1, s.getSabor());
            prep.setString(2, s.getTamanho());
            prep.setString(3, s.getCobertura());
            prep.setString(4, s.getMarca());
            prep.setString(5, s.getFormato());
            prep.setString(6, s.getCalorias());
            prep.setInt(7, s.getIdsorvete());
            prep.execute();

            String mensagem = "Alterado com sucesso!";
            Boolean resposta = true;

            Retorno retorno = retornoDao.RetornoJson(resposta, mensagem);

            con.desconecta();
            return retorno;

        } catch (Exception e) {
            e.printStackTrace();
            String mensagem = "Falha ao alterar!";
            Boolean resposta = false;

            Retorno retorno = retornoDao.RetornoJson(resposta, mensagem);

            con.desconecta();

            return retorno;
        }
    }

    public Retorno excluir(Sorvete s) {
        Conexao con = new Conexao();
        RetornoDao retornoDao = new RetornoDao();

        try {
            String sql = "DELETE FROM sorvete"
                    + " WHERE idsorvete = ?";
            PreparedStatement prep = con.getConnection().prepareStatement(sql);
            prep.setInt(1, s.getIdsorvete());
            prep.execute();

            String mensagem = "Excluído com sucesso!";
            Boolean resposta = true;

            Retorno retorno = retornoDao.RetornoJson(resposta, mensagem);

            con.desconecta();
            return retorno;
        } catch (Exception e) {
            e.printStackTrace();

            String mensagem = "Falha ao excluir!";
            Boolean resposta = false;

            Retorno retorno = retornoDao.RetornoJson(resposta, mensagem);

            con.desconecta();

            return retorno;
        }
    }

    public Sorvete consultar(int id) {
        Sorvete s = new Sorvete();
        Conexao con = new Conexao();
        try {
            String sql = "SELECT * FROM sorvete WHERE idsorvete = ?";
            PreparedStatement prep = con.getConnection().prepareStatement(sql);
            prep.setInt(1, id);
            ResultSet res = prep.executeQuery();
            if (res.next()) {
                s.setIdsorvete(res.getInt("idsorvete"));
                s.setSabor(res.getString("sabor"));
                s.setTamanho(res.getString("tamanho"));
                s.setCobertura(res.getString("cobertura"));
                s.setMarca(res.getString("marca"));
                s.setFormato(res.getString("formato"));
                s.setCalorias(res.getString("calorias"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        con.desconecta();
        return s;
    }
}
