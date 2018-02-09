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
public class unidades {
    private String msj;
    public String getMsj(){
        return this.msj;
    }
    //call spUnidadHorario('ALGEBRA', 1, 2, 1);
    public int guardarUnidadHorario(int idUnid, int horaI, int horaF, int dia){
        int ret = 0;
        try{
            baseDeDatos bd = new baseDeDatos();
            bd.conectar();
            ResultSet rs = bd.ejecuta("call spUnidadHorario(" + idUnid + ", " + 
                    horaI + ", " + horaF + ", " + dia + ");");
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
    public int guardarUnidad(String materia, int cupo){
        int ret=0;
        try{
            baseDeDatos bd = new baseDeDatos();
            bd.conectar();
            ResultSet rs = bd.ejecuta("call spNuevaUnidad('" + materia + "', " + cupo + ");");
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
    //spNuevaMateria(in idAr int, in nomMat nvarchar(300), in semes int(11))
    public int nuevaUnidad(int idAr, String nomMat, int semes){
        int ret = 0;
        try{
            baseDeDatos bd = new baseDeDatos();
            bd.conectar();
            ResultSet rs = bd.ejecuta("call spNuevaMateria("+idAr + ", '" + nomMat + "', " + semes + ");");
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
    
    public int modificaUnidad(String antNom, int idAr, String nomMat, int semes){
        int ret = 0;
        try{
            baseDeDatos bd = new baseDeDatos();
            bd.conectar();
            ResultSet rs = bd.ejecuta("call spEditaMateria('"+antNom + "', '" + nomMat + "', " + semes + ", "+idAr + ");");
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
    
    public String horarioProfesor(int idUnidad, String numTra){
        String ret = "";
        //create procedure spGuardaUnidadesProfesor(in unidad int, in profesor nvarchar(200))
        try{
            baseDeDatos bd = new baseDeDatos();
            bd.conectar();
            ResultSet rs = bd.ejecuta("call spGuardaUnidadesProfesor("+idUnidad + ", '" + numTra + "');");
            while(rs.next()){
                ret = rs.getString("msj");
            }
            bd.cierraConexion();
        }catch(Exception e){
            ret = e.getMessage();
        }
        return ret;
    }
    
    public String quitarHorarioProfesor(int idUnidad, String numTrab){
        String ret = "";
        //create procedure spBorraProfesorHorario(in idUni int, in numTrab nvarchar(200))
        try{
            baseDeDatos bd = new baseDeDatos();
            bd.conectar();
            ResultSet rs = bd.ejecuta("call spBorraProfesorHorario("+idUnidad + ", '" + numTrab + "');");
            while(rs.next()){
                ret = rs.getString("msj");
            }
            bd.cierraConexion();
        }catch(Exception e){
            ret = e.getMessage();
        }
        return ret;
    }
    
    public String horarioGrupo(int idUnidad, String numTra){
        String ret = "";
        //create procedure spGuardaUnidadesProfesor(in unidad int, in profesor nvarchar(200))
        try{
            baseDeDatos bd = new baseDeDatos();
            bd.conectar();
            ResultSet rs = bd.ejecuta("call spGuardaUnidadesGrupo("+idUnidad + ", '" + numTra + "');");
            while(rs.next()){
                ret = rs.getString("msj");
            }
            bd.cierraConexion();
        }catch(Exception e){
            ret = e.getMessage();
        }
        return ret;
    }
    
    public String quitarHorarioGrupo(int idUnidad, String numTrab){
        String ret = "";
        //create procedure spBorraProfesorHorario(in idUni int, in numTrab nvarchar(200))
        try{
            baseDeDatos bd = new baseDeDatos();
            bd.conectar();
            ResultSet rs = bd.ejecuta("call spBorraGrupoHorario("+idUnidad + ", '" + numTrab + "');");
            while(rs.next()){
                ret = rs.getString("msj");
            }
            bd.cierraConexion();
        }catch(Exception e){
            ret = e.getMessage();
        }
        return ret;
    }
    
    public String quitaHorario(int idUnidad){
        String ret="NO";
        try{
            baseDeDatos bd = new baseDeDatos();
            bd.conectar();
            ResultSet rs = bd.ejecuta("call spBorraHorario(" + idUnidad + ", 'POL'  );");
            while(rs.next()){
                ret= rs.getString("msj");
            }
            bd.cierraConexion();
        }catch(Exception e){
            this.msj = e.getMessage();
        }
        return ret;
    }
    
}
