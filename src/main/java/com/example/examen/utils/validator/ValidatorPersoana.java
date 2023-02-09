package com.example.examen.utils.validator;

import com.example.examen.domain.Persoana;

public class ValidatorPersoana implements Validator<Persoana>{
    @Override
    public void validate(Persoana persoana) {
        String errs = "";
        if(persoana.getNume() == null){
            errs += "Numele nu poate fi null!\n";
        }
        if(persoana.getPrenume() == null){
            errs += "Prenumele nu poate fi null!\n";
        }
        if(persoana.getUsername() == null){
            errs += "Username-ul nu poate fi null!\n";
        }
        if(persoana.getParola() == null || persoana.getParola().length() < 2){
            errs += "Parola nu poate fi null sau mai mica de 2 caractere!\n";
        }
        if(persoana.getStrada() == null || persoana.getNrStrada() == null){
            errs += "Strada sau numarul strazii nu pot fi null!\n";
        }
        if(persoana.getTelefon() == null){
            errs += "Telefonul nu poate fi null!\n";
        }
        if(!errs.isEmpty()){
            throw new ValidatorExcept(errs);
        }
    }
}
