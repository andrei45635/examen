package com.example.examen.service;

import com.example.examen.domain.Nevoie;
import com.example.examen.domain.Oras;
import com.example.examen.domain.Persoana;
import com.example.examen.repo.NevoieRepoBD;
import com.example.examen.repo.PersoanaRepoBD;
import com.example.examen.utils.validator.Validator;
import com.example.examen.utils.validator.ValidatorExcept;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Service {
    private NevoieRepoBD nevoieRepoBD;
    private PersoanaRepoBD persoanaRepoBD;
    private Validator<Persoana> persoanaValidator;

    public Service(NevoieRepoBD nevoieRepoBD, PersoanaRepoBD persoanaRepoBD, Validator<Persoana> persoanaValidator) {
        this.nevoieRepoBD = nevoieRepoBD;
        this.persoanaRepoBD = persoanaRepoBD;
        this.persoanaValidator = persoanaValidator;
    }

    public void savePersoana(Persoana persoana) throws ValidatorExcept{
        persoanaValidator.validate(persoana);
        try{
            persoanaRepoBD.save(persoana);
        } catch (ValidatorExcept ve){
            System.out.println(ve.getMessage());
        }
    }

    public void addNevoie(Nevoie nevoie){
        nevoieRepoBD.saveNevoieFormular(nevoie);
    }
    public List<Persoana> getAllPersoane(){
        return persoanaRepoBD.findAllUsers();
    }

    public List<String> getAllUsernames(){
        List<String> username = new ArrayList<>();
        for(Persoana persoana: this.getAllPersoane()){
            username.add(persoana.getUsername());
        }
        return username;
    }

    public List<Nevoie> getAllNevoiOras(Oras oras){
        List<Nevoie> nevoiOras = new ArrayList<>();
        for(Nevoie nevoie: nevoieRepoBD.findAllNevoi()){
            for(Persoana persoana: persoanaRepoBD.findAllUsers()){
                if(nevoie.getOmInNevoie() == persoana.getId()){
                    nevoiOras.add(nevoie);
                }
            }
        }
        return nevoiOras;
    }

    public Set<String> getAllOrase(){
        Set<String> orase = new HashSet<>();
        for(Persoana persoana: this.getAllPersoane()){
            orase.add(String.valueOf(persoana.getOras()));
        }
        return orase;
    }

    public Persoana getPersoana(long id){
        return persoanaRepoBD.getOne(id);
    }

    public Persoana getPersoanaOras(Oras oras){
        return persoanaRepoBD.getPersonOras(oras);
    }

    public Oras getOrasByUsername(String username){
        return persoanaRepoBD.getOrasUsername(username);
    }

    public Persoana getIdByPersoana(String username){
        return persoanaRepoBD.getPersoanaByUsername(username);
    }

    public void saveNevoie(long salvator, long omNevoie){
        nevoieRepoBD.saveNevoie(salvator, omNevoie);
    }
}
