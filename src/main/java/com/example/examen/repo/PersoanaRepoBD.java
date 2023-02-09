package com.example.examen.repo;

import com.example.examen.domain.Oras;
import com.example.examen.domain.Persoana;
import com.example.examen.utils.JDBCUtils;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersoanaRepoBD {
    private final JDBCUtils jdbcUtils = new JDBCUtils();
    public List<Persoana> findAllUsers(){
        List<Persoana> persoane = new ArrayList<>();
        String query = "SELECT * FROM persoane";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()){
                long id = resultSet.getLong("id");
                String nume = resultSet.getString("nume");
                String prenume = resultSet.getString("prenume");
                String username = resultSet.getString("username");
                String parola = resultSet.getString("parola");
                Oras oras = Oras.valueOf(resultSet.getString("oras"));
                String strada = resultSet.getString("strada");
                String nrstrada = resultSet.getString("nrstrada");
                String telefon = resultSet.getString("telefon");

                Persoana persoana = new Persoana(id, nume, prenume, username, parola, oras, strada, nrstrada, telefon);
                persoane.add(persoana);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return persoane;
    }

    public Persoana save(Persoana persoana){
        String query = "INSERT INTO persoane(id, nume, prenume, username, parola, oras, strada, nrstrada, telefon) VALUES (?,?,?,?,?,?,?,?,?)";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, persoana.getId());
            statement.setString(2, persoana.getNume());
            statement.setString(3, persoana.getPrenume());
            statement.setString(4, persoana.getUsername());
            statement.setString(5, persoana.getParola());
            statement.setString(6, String.valueOf(persoana.getOras()));
            statement.setString(7, persoana.getStrada());
            statement.setString(8, persoana.getNrStrada());
            statement.setString(9, persoana.getTelefon());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return persoana;
    }

    public Persoana getOne(long id){
        String query = "SELECT * FROM persoane WHERE id = ?";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                long Pid = resultSet.getLong("id");
                String nume = resultSet.getString("nume");
                String prenume = resultSet.getString("prenume");
                String username = resultSet.getString("username");
                String parola = resultSet.getString("parola");
                Oras oras = Oras.valueOf(resultSet.getString("oras"));
                String strada = resultSet.getString("strada");
                String nrstrada = resultSet.getString("nrstrada");
                String telefon = resultSet.getString("telefon");
                return new Persoana(Pid, nume, prenume, username, parola, oras, strada, nrstrada, telefon);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Persoana getPersonOras(Oras oras){
        String query = "SELECT * FROM persoane WHERE oras = ?";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, String.valueOf(oras));
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                long Pid = resultSet.getLong("id");
                String nume = resultSet.getString("nume");
                String prenume = resultSet.getString("prenume");
                String username = resultSet.getString("username");
                String parola = resultSet.getString("parola");
                Oras orasP = Oras.valueOf(resultSet.getString("oras"));
                String strada = resultSet.getString("strada");
                String nrstrada = resultSet.getString("nrstrada");
                String telefon = resultSet.getString("telefon");
                return new Persoana(Pid, nume, prenume, username, parola, orasP, strada, nrstrada, telefon);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Oras getOrasUsername(String username){
        String query = "SELECT oras FROM persoane WHERE username = ?";
        try(Connection connection = jdbcUtils.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                return Oras.valueOf(resultSet.getString("oras"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Persoana getPersoanaByUsername(String username){
        String query = "SELECT * FROM persoane WHERE username = ?";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                long Pid = resultSet.getLong("id");
                String nume = resultSet.getString("nume");
                String prenume = resultSet.getString("prenume");
                String usernameP = resultSet.getString("username");
                String parola = resultSet.getString("parola");
                Oras orasP = Oras.valueOf(resultSet.getString("oras"));
                String strada = resultSet.getString("strada");
                String nrstrada = resultSet.getString("nrstrada");
                String telefon = resultSet.getString("telefon");
                return new Persoana(Pid, nume, prenume, usernameP, parola, orasP, strada, nrstrada, telefon);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
