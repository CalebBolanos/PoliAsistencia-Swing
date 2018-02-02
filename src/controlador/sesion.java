/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Alumno
 */
public class sesion {
    private static int idSesion, tipo;
    private static String numT, cont;
    
    public sesion(){
        
    }
    public sesion(int idSes, int idT){
        this.idSesion = idSes;
        this.tipo = idT;
    }
    public int getID(){
        return idSesion;
    }
    public void cerrar(){
        idSesion = 0;
    }
    public String getNumT(){
        return numT;
    }
    public String getCont(){
        return cont;
    }
    public int getTipo(){
        return tipo;
    }
    public int[] iniciaSesion(String usr, String psw){
        int id[] = new int[2];
        numT = usr;
        cont = psw;
        try{
            baseDeDatos bd = new baseDeDatos();
            bd.conectar();
            ResultSet rs = bd.ejecuta("call spValidaUsr('" + usr + "', '" + psw + "');");
            while(rs.next()){
                id[0] = rs.getInt("idP");
                id[1] = rs.getInt("idT");
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        idSesion = id[0];
        tipo = id[1];
        return id;
    }
    
    public boolean checaHuella(){
        boolean ret = false;
        try{
            baseDeDatos bd = new baseDeDatos();
            bd.conectar();
            ResultSet rs = bd.ejecuta("call spPersonaConHuella(" + idSesion + ");");
            while(rs.next())
                ret = rs.getInt("ret")>0;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return ret;
    }
    
}
