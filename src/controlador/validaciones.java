/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Alumno
 */
public class validaciones {
    private String error;
    public boolean soloLet(String cad, String donde, int tamanio){
        boolean ret = false;
        if(cad.length() != 0)
            if(cad.length()<=tamanio)
                if(cad.matches("([a-z]|[A-Z]|\\s)+"))
                    if(sinEsp(cad)){
                        error = "Todo bien en " + donde;
                        ret = true;
                    }
                    else
                        error = "Ingrese " + donde;
                else
                    error = "Solo letras en " + donde;
            else
                error = "No ingrese mas de " + tamanio + "en " + donde;
        else
            error = "Ingrese " + donde;
        return ret;
    }
    
    public boolean sinVacios(String cad, String donde, int tamanio){
        boolean ret = false;
        if(cad.length() != 0)
            if(sinEsp(cad)){
                if(cad.length()<=tamanio)
                    if(cad.matches("([a-z]|[A-Z]|[0-9]|\\s)+")){
                        error = "Todo bien en " + donde;
                        ret = true;
                    }else
                        error = "Solo caracteres en " + donde;
                else
                    error = "No sobrepase el limite de " + tamanio + "en " + donde;
            }
            else
                error = "Ingrese " + donde;
        else
            error = "Ingrese " + donde;
        return ret;
    }
    
    public boolean boleta(String bol){
        boolean ret = false;
        if(bol.length() == 10)
            if(bol.matches("[P][M]([0-9])+") || bol.matches("([0-9]|\\s)+"))
                ret = true;
            else
                error = "Introduzca una boleta o PM valido, ejemplo \"2016090412\" o \"PM16010916\""; 
        else
            error = "Boleta o PM de 10 digitos";
        return ret;
    }
    
    public boolean validarFecha(String fecha){
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    public String err(){
        return error;
    }
    
    private boolean sinEsp(String cad){
        int errores=0;
        for(int i = 0; i<cad.length(); i++){
            if(cad.charAt(i) == ' ')
                errores++;
        }
        return errores!=cad.length();
    }
}
