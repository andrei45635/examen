package com.example.examen.repo;

import com.example.examen.domain.Nevoie;
import com.example.examen.domain.Oras;
import com.example.examen.domain.Persoana;
import com.example.examen.utils.JDBCUtils;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NevoieRepoBD {
    private final JDBCUtils jdbcUtils = new JDBCUtils();

    public List<Nevoie> findAllNevoi(){
        List<Nevoie> nevoi = new ArrayList<>();
        String query = "SELECT * FROM nevoi";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()){
                long id = resultSet.getLong("id");
                String titlu = resultSet.getString("titlu");
                String descriere = resultSet.getString("descriere");
                LocalDateTime deadline = resultSet.getTimestamp("deadline").toLocalDateTime();
                long omInNevoie = resultSet.getLong("omnevoie");
                long omSalvator = resultSet.getLong("omsalvator");
                String status = resultSet.getString("status");

                Nevoie nevoie = new Nevoie(id, titlu, descriere, deadline, omInNevoie, omSalvator, status);
                nevoi.add(nevoie);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nevoi;
    }

    public void saveNevoie(long salvatorID, long omNevoieID){
        String query = "UPDATE nevoi SET omsalvator = ?, status = 'Erou gasit!' WHERE omnevoie = ?";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, salvatorID);
            statement.setLong(2, omNevoieID);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveNevoieFormular(Nevoie nevoie){
        String query = "insert into nevoi(id, titlu, descriere, deadline, omnevoie, omsalvator, status) values (?, ?, ?, ?, ?, ?, ?)";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, nevoie.getId());
            statement.setString(2, nevoie.getTitlu());
            statement.setString(3, nevoie.getDescriere());
            statement.setTimestamp(4, Timestamp.valueOf(nevoie.getDeadline()));
            statement.setLong(5, nevoie.getOmInNevoie());
            statement.setLong(6, nevoie.getOmSalvator());
            statement.setString(7, nevoie.getStatus());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
