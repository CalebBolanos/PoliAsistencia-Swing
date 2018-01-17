/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.ResultSet;

/**
 *
 * @author Alexis
 */
public class gruposControlador {
    private String msj;
    public String getMsj(){
        return this.msj;
    }
    //create procedure spNuevoGrupo(in nombreGrupo nvarchar(10), in semes int, in idAr int, in idTur int)
    public int nuevoGrupo(int idAr, String nomGru, int semes, int turno){
        int ret = 0;
        try{
            baseDeDatos bd = new baseDeDatos();
            bd.conectar();
            ResultSet rs = bd.ejecuta("call spNuevoGrupo('" + nomGru + "', "+ semes + ", " + idAr + ", " + turno + ");");
            while(rs.next()){
                this.msj = rs.getString("msj");
                ret = rs.getInt("idN");
            }
            bd.cierraConexion();
        }catch(Exception e){
            this.msj = e.getMessage();
        }
        return ret;
    }
    public int editaGrupo(String nomAnt, int idAr, String nomGru, int semes, int turno){
        int ret = 0;
        try{
            baseDeDatos bd = new baseDeDatos();
            bd.conectar();
            //create procedure spEditaGrupo(in nomAnt nvarchar(15),in nomNue nvarchar(15),in semes int,in idAre int,in idTur int)
            ResultSet rs = bd.ejecuta("call spEditaGrupo('"+nomAnt + "', '" + nomGru + "', "+ semes + ", " + idAr + ", " + turno + ");");
            while(rs.next()){
                this.msj = rs.getString("msj");
                ret = rs.getInt("idP");
            }
            bd.cierraConexion();
        }catch(Exception e){
            this.msj = e.getMessage();
        }
        return ret;
    }
    public String inscribirAlumno(String boleta, String grupo){
        String ret = "";
        try{
            baseDeDatos bd = new baseDeDatos();
            bd.conectar();
            //create procedure spEditaGrupo(in nomAnt nvarchar(15),in nomNue nvarchar(15),in semes int,in idAre int,in idTur int)
            ResultSet rs = bd.ejecuta("call spGuardaAlumnosGrupo('"+boleta + "', '" + grupo + "');");
            while(rs.next()){
                ret = rs.getString("msj");
            }
            bd.cierraConexion();
        }catch(Exception e){
            this.msj = e.getMessage();
        }
        return ret;
    }
}
