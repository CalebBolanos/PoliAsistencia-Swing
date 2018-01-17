/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Alexis
 */
public class registros {
    private String msj;
    private static ByteArrayInputStream huella;
    private int size;
    //call spGuardaAlumnos(1,'islas','ortigoza','obed','97.09.19','obedisor@gmail.com','pm2016928',1,'nlasdjkflaksdjgflakenlacjnasjkdnf.jpg');
    public void registroHuella(ByteArrayInputStream hola, int si){
        this.huella = hola;
        size = si;
        System.out.println(hola + " " + si);
    }
    public ByteArrayInputStream getHuella(){
        return this.huella;
    }
    public int regAlumno(int gen, String nom, String pat, String mat, String fech, String bol){
        int id = 0;
           try{
                baseDeDatos bd = new baseDeDatos();
                bd.conectar();
                ResultSet rs = bd.ejecuta("call spGuardaAlumnos(" + gen + ", '" + pat +"', '" + mat
                        + "', '" + nom + "', '" + fech + "', 'sinasignar', '" + bol + "', 1);");
                while(rs.next()){
                    id = rs.getInt("idP");
                    msj = rs.getString("msj");
                }
                if(id>0){
                    Connection c=bd.getConexion(); 
                    //create function fGuardaHuella(idP int, hu longblob) returns nvarchar(100)
                    try (PreparedStatement guardarStmt = c.prepareStatement("call fGuardaHuella(?,?)")) {
                        //create function fGuardaHuella(idP int, hu longblob) returns nvarchar(100)
                        guardarStmt.setInt(1,id);
                        //Este si lo debes usar porque asi se guarda la huella, en la base esta como tipo BLOB
                        guardarStmt.setBlob(2, huella,size);
                        System.out.println(huella);
                        guardarStmt.execute();
                    }
                }
                
                bd.cierraConexion();
            }catch(Exception e){
                System.out.println(e.getMessage());
                this.msj = e.getMessage();
            }
        return id;
    }
    //create procedure spEditaAlumno(in Nnombre nvarchar(250), 
    //in Npaterno nvarchar(250), in Nmaterno nvarchar(250), 
    //in Nfecha date, in Nboleta nvarchar(15), in Aboleta nvarchar(15))

    public int modAlumno(String antBol, String nom, String pat, String mat, String fech, String bol){
        int id = 0;
        if(huella != null){
           try{
                baseDeDatos bd = new baseDeDatos();
                bd.conectar();
                ResultSet rs = bd.ejecuta("call spEditaAlumno('"+nom + "', '" + pat +"', '" + mat
                        + "', '" + fech + "', '" + bol + "', '" + antBol + "');");
                while(rs.next()){
                    msj = rs.getString("msj");
                    id=rs.getInt("idP");
                }
                if(huella!=null){
                    if(id>0){
                    Connection c=bd.getConexion(); 
                    //create function fGuardaHuella(idP int, hu longblob) returns nvarchar(100)
                    try (PreparedStatement guardarStmt = c.prepareStatement("call spModificaHuella(?,?)")) {
                        //create function fGuardaHuella(idP int, hu longblob) returns nvarchar(100)
                        guardarStmt.setInt(1,id);
                        //Este si lo debes usar porque asi se guarda la huella, en la base esta como tipo BLOB
                        guardarStmt.setBlob(2, huella,size);
                        System.out.println(huella);
                        guardarStmt.execute();
                    }
                }
                }
                bd.cierraConexion();
            }catch(Exception e){
                System.out.println(e.getMessage());
            } 
        }
        if(msj.equals("Datos del alumno actualizados"))
            id = 1;
        return id;
    }
    //create procedure spEditaProfesor(in idmal nvarchar(15),in idBien nvarchar(15),in nom nvarchar(200),
    //in pat nvarchar(200),in mat nvarchar(200),in fech date)
    public int modProf(String antBol, String nom, String pat, String mat, String fech, String bol){
        int id = 0;
        if(huella != null){
           try{
                baseDeDatos bd = new baseDeDatos();
                bd.conectar();
                ResultSet rs = bd.ejecuta("call spEditaProfesor('"+antBol + "', '" + bol +"', '" + nom
                        + "', '" + pat + "', '" + mat + "', '" + fech + "');");
                while(rs.next()){
                    msj = rs.getString("msj");
                    id=rs.getInt("idP");
                }
                if(huella!=null){
                    if(id>0){
                        Connection c=bd.getConexion(); 
                        //create function fGuardaHuella(idP int, hu longblob) returns nvarchar(100)
                        try (PreparedStatement guardarStmt = c.prepareStatement("call spModificaHuella(?,?)")) {
                            //create function fGuardaHuella(idP int, hu longblob) returns nvarchar(100)
                            guardarStmt.setInt(1,id);
                            //Este si lo debes usar porque asi se guarda la huella, en la base esta como tipo BLOB
                            guardarStmt.setBlob(2, huella,size);
                            System.out.println(huella);
                            guardarStmt.execute();
                        }
                    }
                }
                bd.cierraConexion();
            }catch(Exception e){
                System.out.println(e.getMessage());
            } 
        }
        if(msj.equals("ok"))
            id = 1;
        return id;
    }
    
    public int regProf(int tipo, int g, String nom, String pat, String mat, String fecha, String numTrabajador){
        int id=0;
        //create procedure spGuardaDocente(in idT int,in g int, in pat nvarchar(250),in mat nvarchar(250), in nom nvarchar(250), in fech date, in mail nvarchar(250),in numT nvarchar(15),in hu longblob)
            try{
                baseDeDatos bd = new baseDeDatos();
                bd.conectar();
                ResultSet rs = bd.ejecuta("call spGuardaDocente(" + tipo + ", " + g + ", '" + pat + "', '" + mat +
                        "', '" + nom + "', '" + fecha + "', 'sinasignar', '" + numTrabajador + "');");
                while(rs.next()){
                    id = rs.getInt("idP");
                }
                if(huella!=null){
                    if(id>0){
                        Connection c=bd.getConexion(); 
                        //create function fGuardaHuella(idP int, hu longblob) returns nvarchar(100)
                        try (PreparedStatement guardarStmt = c.prepareStatement("call fGuardaHuella(?,?)")) {
                            //create function fGuardaHuella(idP int, hu longblob) returns nvarchar(100)
                            guardarStmt.setInt(1,id);
                            //Este si lo debes usar porque asi se guarda la huella, en la base esta como tipo BLOB
                            guardarStmt.setBlob(2, huella,size);
                            System.out.println(huella);
                            guardarStmt.execute();
                        }
                    }
                }
                bd.cierraConexion();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }  
        return id;
    }
    //create procedure spContrasenas(in T int,in identificador nvarchar(20),in pswA nvarchar(60),in pswN nvarchar(60))
    public void modContrasena(int tipo, String boleta, String pswA, String pswN){
        try{
            baseDeDatos bd = new baseDeDatos();
            bd.conectar();
            ResultSet rs = bd.ejecuta("call spContrasenas(" + tipo + ", '" + boleta + "', '" + pswA + "', '" + pswN + "');");
            while(rs.next()){
                this.msj = rs.getString("msj");
            }
            bd.cierraConexion();
        }catch(Exception e){
            this.msj = e.getMessage();
        }
    }
    
    public void darDeBaja(String boleta){
        try{
            baseDeDatos bd = new baseDeDatos();
            bd.conectar();
            ResultSet rs = bd.ejecuta("call spDarDeBaja('" + boleta + "');");
            while(rs.next()){
                this.msj = rs.getString("msj");
            }
        }catch(Exception e){
            this.msj = e.getMessage();
        }
    }
    
    public String getMsj(){
        return this.msj;
    }
}
