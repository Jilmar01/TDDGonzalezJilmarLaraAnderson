/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.p2lab2tddgonzalezjilmarlaraanderson;

/**
 *
 * @author Usuario
 */
public class Validations {
    public  boolean validEmpty(String camp){
        return camp.isEmpty();
    }
    public  boolean validNumber(String camp){
        return camp.matches("^[0-9]+$");
    }
    public  boolean validAmougInt(String camp, Double init,Double end){
        return Double.parseDouble(camp)<init||Double.parseDouble(camp)>end;
    }
    public  boolean validAmougDouble(Double camp, Double init,Double end){
        return camp<init||camp>end;
    }
    public  boolean validEmptyDouble(Double camp){
        return (camp+"").isEmpty();
    }
    
}
