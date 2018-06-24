/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alumno
 */
public class traerDatos {
    
    public String[][] datosAlumnos(){
        ArrayList<String[]> arr = new ArrayList<>();
        baseDeDatos bd = new baseDeDatos();
        try{
            bd.conectar();
            ResultSet rs = bd.ejecuta("select * from vwAlumnos;");
            while(rs.next()){
                String junto[] = new String[5];
                junto[0] = rs.getString("boleta");
                junto[1] = rs.getString("nombre");
                junto[2] = rs.getString("paterno");
                junto[3] = rs.getString("materno");
                junto[4] = rs.getString("estado");
                arr.add(junto);
            }
            bd.cierraConexion();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        int tamanio =arr.size();
        String ret[][] = new String[tamanio][5];
        for(int i = 0; i<tamanio; i++){
            ret[i] = arr.get(i);
        }
        return ret;
    }
    
    public String[][] datosAlumnosRegulare(){
        ArrayList<String[]> arr = new ArrayList<>();
        baseDeDatos bd = new baseDeDatos();
        try{
            bd.conectar();
            ResultSet rs = bd.ejecuta("select * from vwAlumnos where estado = 'regular';");
            while(rs.next()){
                String junto[] = new String[5];
                junto[0] = rs.getString("boleta");
                junto[1] = rs.getString("nombre");
                junto[2] = rs.getString("paterno");
                junto[3] = rs.getString("materno");
                junto[4] = rs.getString("estado");
                arr.add(junto);
            }
            bd.cierraConexion();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        int tamanio =arr.size();
        String ret[][] = new String[tamanio][5];
        for(int i = 0; i<tamanio; i++){
            ret[i] = arr.get(i);
        }
        return ret;
    }
    
    public String[][] datosProfesor(){
        ArrayList<String[]> arr = new ArrayList<>();
        baseDeDatos bd = new baseDeDatos();
        try{
            bd.conectar();
            ResultSet rs = bd.ejecuta("select * from vwTrabajadores where (idTipo = 3 and idPersona > 0);");
            while(rs.next()){
                String junto[] = new String[5];
                junto[0] = rs.getString("numTrabajador");
                junto[1] = rs.getString("nombre");
                junto[2] = rs.getString("paterno");
                junto[3] = rs.getString("materno");
                junto[4] = rs.getString("genero");
                arr.add(junto);
            }
            bd.cierraConexion();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        int tamanio =arr.size();
        String ret[][] = new String[tamanio][5];
        for(int i = 0; i<tamanio; i++){
            ret[i] = arr.get(i);
        }
        return ret;
    }
    
    public String[] datoIndividual(String boleta){
        String ret[] = new String[7];
        String genero="";
        baseDeDatos bd = new baseDeDatos();
        try{
            bd.conectar();
            ResultSet rs = bd.ejecuta("call spTraerDatos('" + boleta + "', 'Pol');");
            while(rs.next()){
                ret[0] = rs.getString("existe");
                ret[1] = rs.getString("nom");
                ret[2] = rs.getString("pat");
                ret[3] = rs.getString("mat");
                genero = rs.getString("gen");
                switch(genero){
                    case "masculino":
                        ret[4] = "1";
                        break;
                    case "femenino":
                        ret[4] = "2";
                        break;
                    case "otro":
                        ret[4] = "3";
                        break;
                    default:
                        ret[4] = "0";
                }
                ret[5] = rs.getString("bol");
                ret[6] = rs.getString("fec");
            }
            bd.cierraConexion();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return ret;
    }
    
    public String[] datoIndividualProf(String boleta){
        String ret[] = new String[7];
        String genero="";
        baseDeDatos bd = new baseDeDatos();
        try{
            bd.conectar();
            ResultSet rs = bd.ejecuta("call spTraerDatosProf('" + boleta + "', 'Pol');");
            while(rs.next()){
                ret[0] = rs.getString("existe");
                ret[1] = rs.getString("nom");
                ret[2] = rs.getString("pat");
                ret[3] = rs.getString("mat");
                genero = rs.getString("gen");
                switch(genero){
                    case "masculino":
                        ret[4] = "1";
                        break;
                    case "femenino":
                        ret[4] = "2";
                        break;
                    case "otro":
                        ret[4] = "3";
                        break;
                    default:
                        ret[4] = "0";
                }
                ret[5] = rs.getString("bol");
                ret[6] = rs.getString("fec");
            }
            bd.cierraConexion();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return ret;
    }
    
    public String[] datoIndividualGrupo(String boleta){
        String ret[] = new String[5];
        baseDeDatos bd = new baseDeDatos();
        try{
            bd.conectar();
            ResultSet rs = bd.ejecuta("select * from vwGrupos where grupo = '" + boleta + "';");
            while(rs.next()){
                ret[0] = rs.getString("idGrupo");
                ret[1] = rs.getString("grupo");
                ret[2] = rs.getString("semestre");
                ret[3] = rs.getString("idArea");
                ret[4] = rs.getString("idTurno");
            }
            bd.cierraConexion();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return ret;
    }
    
    public String[] datoIndividualMateria(String materia){
        String ret[] = new String[4];
        baseDeDatos bd = new baseDeDatos();
        try{
            bd.conectar();
            ResultSet rs = bd.ejecuta("select * from vwMaterias where materia = '" + materia + "';");
            while(rs.next()){
                ret[0] = rs.getString("idMateria");
                ret[1] = rs.getString("materia");
                ret[2] = rs.getString("idArea");
                ret[3] = rs.getString("semestre");
            }
            bd.cierraConexion();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return ret;
    }
    
    public String[][] materias(){
        ArrayList<String[]> arr = new ArrayList<>();
        try{
            baseDeDatos bd = new baseDeDatos();
            bd.conectar();
            ResultSet rs = bd.ejecuta("select * from Materias;");
            while(rs.next()){
                String unidad[] = new String[3];
                unidad[0] = rs.getString("idArea");
                unidad[1] = rs.getString("materia");
                unidad[2] = rs.getString("semestre");
                arr.add(unidad);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        int tamanio = arr.size();
        String ret[][] = new String[tamanio][3];
        for (int i = 0; i < tamanio; i++) {
            ret[i] = arr.get(i);
        }
        return ret;
    }
    
    public String[][] materiasC(){
        ArrayList<String[]> arr = new ArrayList<>();
        try{
            baseDeDatos bd = new baseDeDatos();
            bd.conectar();
            ResultSet rs = bd.ejecuta("select * from vwMaterias;");
            while(rs.next()){
                String unidad[] = new String[3];
                unidad[0] = rs.getString("materia");
                unidad[1] = rs.getString("semestre");
                unidad[2] = rs.getString("area");
                arr.add(unidad);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        int tamanio = arr.size();
        String ret[][] = new String[tamanio][3];
        for (int i = 0; i < tamanio; i++) {
            ret[i] = arr.get(i);
        }
        return ret;
    }
    
    public int[][][] unidadesHorarioSinFormato(String numTrab){
        ArrayList<int[]> arr1 = new ArrayList<>();
        ArrayList<int[]> arr2 = new ArrayList<>();
        try{
            baseDeDatos bd = new baseDeDatos();
            bd.conectar();
            ResultSet rs = bd.ejecuta("select * from vwunidadeshorarios where numTrabajador = '"+ numTrab + "';");
            int contador=0;
            int idUnidad=0;
            int dato1[]=new int[5];
            int dato2[]=new int[5];
            while(rs.next()){
                if(rs.getInt("idUnidad") == idUnidad){
                    int dia = rs.getInt("idDia")-1;
                    dato1[dia] = rs.getInt("idHorarioI");
                    dato2[dia] = rs.getInt("idHorarioF");
                    if(rs.isLast()){
                        arr1.add(dato1); 
                        arr2.add(dato2); 
                    }
                }else{
                    if(contador==1){
                        arr1.add(dato1); 
                        arr2.add(dato2);
                    }
                    idUnidad = rs.getInt("idUnidad");
                    contador=1;
                    dato1=new int[5];
                    dato2=new int[5];
                    int dia = rs.getInt("idDia")-1;
                    dato1[dia]= rs.getInt("idHorarioI");
                    dato1[dia] = rs.getInt("idHorarioF");
                    if(rs.isLast()){
                        arr1.add(dato1); 
                        arr2.add(dato2); 
                    }
                }                
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        int tamanio = arr1.size();
        int ret[][][] = new int[2][tamanio][5];
        for (int i = 0; i < tamanio; i++) {
            ret[0][i] = arr1.get(i);
            ret[1][i] = arr2.get(i);
        }
        return ret;
    }
    
    public String[][] unidadesHorario(){
        String horas[] = {"---", "07:00", "08:00", "09:00", "10:00", "11:00",
        "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00"};
        ArrayList<String[]> arr = new ArrayList<>();
        try{
            baseDeDatos bd = new baseDeDatos();
            bd.conectar();
            ResultSet rs = bd.ejecuta("select * from vwunidadeshorarios where idProfesor <0;");
            int contador=0;
            int idUnidad=0;
            String dato[]=new String[8];
            while(rs.next()){
                if(rs.getInt("idUnidad") == idUnidad){
                    dato[6] = rs.getString("grupo");
                    dato[0] = rs.getString("materia");
                    int dia = rs.getInt("idDia");
                    int horIn = rs.getInt("idHorarioI"), horaF = rs.getInt("idHorarioF");
                    String horario = horas[horIn] + " - " + horas[horaF];
                    dato[dia] = horario;
                    if(rs.isLast()){
                        dato[7] = idUnidad+"";
                        dato[0] = rs.getString("materia");
                        dato[6] = rs.getString("grupo");
                        for(int i = 1; i<6; i++){
                            if(dato[i] == null)
                                dato[i] = "---";
                        }
                        arr.add(dato); 
                    }
                }else{
                    if(contador==1){
                        dato[7] = idUnidad+"";
                        for(int i = 1; i<6; i++){
                            if(dato[i] == null)
                                dato[i] = "---";
                        }
                        arr.add(dato); 
                    }
                    idUnidad = rs.getInt("idUnidad");
                    contador=1;
                    dato = new String[8];
                    int dia = rs.getInt("idDia");
                    int horIn = rs.getInt("idHorarioI"), horaF = rs.getInt("idHorarioF");
                    String horario = horas[horIn] + " - " + horas[horaF];
                    dato[dia] = horario;
                    dato[0] = rs.getString("materia");
                    dato[6] = rs.getString("grupo");
                    if(rs.isLast()){
                        dato[7] = idUnidad+"";
                        dato[0] = rs.getString("materia");
                        dato[6] = rs.getString("grupo");
                        for(int i = 1; i<6; i++){
                            if(dato[i] == null)
                                dato[i] = "---";
                        }
                        arr.add(dato); 
                    }
                }                
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        int tamanio = arr.size();
        String ret[][] = new String[tamanio][8];
        for (int i = 0; i < tamanio; i++) {
            ret[i] = arr.get(i);
        }
        return ret;
    }
    
    public String[][] unidadesHorarioProfesor(String numTrab){
        String horas[] = {"---", "07:00", "08:00", "09:00", "10:00", "11:00",
        "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00"};
        ArrayList<String[]> arr = new ArrayList<>();
        try{
            baseDeDatos bd = new baseDeDatos();
            bd.conectar();
            ResultSet rs = bd.ejecuta("select * from vwunidadeshorarios where numTrabajador = '"+ numTrab + "';");
            int contador=0;
            int idUnidad=0;
            String dato[]=new String[8];
            while(rs.next()){
                if(rs.getInt("idUnidad") == idUnidad){
                    dato[0] = rs.getString("materia");
                    dato[6] = rs.getString("grupo");
                    int dia = rs.getInt("idDia");
                    int horIn = rs.getInt("idHorarioI"), horaF = rs.getInt("idHorarioF");
                    String horario = horas[horIn] + " - " + horas[horaF];
                    dato[dia] = horario;
                    if(rs.isLast()){
                        dato[7] = idUnidad+"";
                        dato[6] = rs.getString("grupo");
                        dato[0] = rs.getString("materia");
                        for(int i = 1; i<6; i++){
                            if(dato[i] == null)
                                dato[i] = "---";
                        }
                        arr.add(dato); 
                    }
                }else{
                    if(contador==1){
                        dato[7] = idUnidad+"";
                        for(int i = 1; i<6; i++){
                            if(dato[i] == null)
                                dato[i] = "---";
                        }
                        arr.add(dato); 
                    }
                    idUnidad = rs.getInt("idUnidad");
                    contador=1;
                    dato = new String[8];
                    int dia = rs.getInt("idDia");
                    int horIn = rs.getInt("idHorarioI"), horaF = rs.getInt("idHorarioF");
                    String horario = horas[horIn] + " - " + horas[horaF];
                    dato[dia] = horario;
                    dato[0] = rs.getString("materia");
                    dato[6] = rs.getString("grupo");
                    dato[7] = idUnidad+"";
                    if(rs.isLast()){
                        dato[7] = idUnidad+"";
                        dato[6] = rs.getString("grupo");
                        dato[0] = rs.getString("materia");
                        for(int i = 1; i<6; i++){
                            if(dato[i] == null)
                                dato[i] = "---";
                        }
                        arr.add(dato); 
                    }
                }                
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        int tamanio = arr.size();
        String ret[][] = new String[tamanio][8];
        for (int i = 0; i < tamanio; i++) {
            ret[i] = arr.get(i);
        }
        return ret;
    }
    
    public String[][]grupos(){
        ArrayList<String[]> arr = new ArrayList<>();
        try{
            baseDeDatos bd = new baseDeDatos();
            bd.conectar();
            ResultSet rs = bd.ejecuta("select * from vwgrupos where idGrupo > 0;");
            while(rs.next()){
                String unidad[] = new String[4];
                unidad[0] = rs.getString("grupo");
                unidad[1] = rs.getString("semestre");
                unidad[2] = rs.getString("turmo");
                unidad[3] = rs.getString("area");
                arr.add(unidad);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        int tamanio = arr.size();
        String ret[][] = new String[tamanio][4];
        for (int i = 0; i < tamanio; i++) {
            ret[i] = arr.get(i);
        }
        return ret;
    }
    
    public String[][]gruposConCupo(){
        ArrayList<String[]> arr = new ArrayList<>();
        try{
            baseDeDatos bd = new baseDeDatos();
            bd.conectar();
            ResultSet rs = bd.ejecuta("select * from vwgruposconcupo where cupo>0 order by semestre; ");
            while(rs.next()){
                String unidad[] = new String[4];
                unidad[0] = rs.getString("grupo");
                unidad[1] = rs.getString("semestre");
                unidad[2] = rs.getString("turmo");
                unidad[3] = rs.getString("area");
                arr.add(unidad);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        int tamanio = arr.size();
        String ret[][] = new String[tamanio][4];
        for (int i = 0; i < tamanio; i++) {
            ret[i] = arr.get(i);
        }
        return ret;
    }
    
    public String[][]AlumnosEnElGrupo(String grupo){
        ArrayList<String[]> arr = new ArrayList<>();
        try{
            baseDeDatos bd = new baseDeDatos();
            bd.conectar();
            ResultSet rs = bd.ejecuta("select * from vwAlumnosConGrupo where grupo = '" + grupo + "';");
            while(rs.next()){
                String unidad[] = new String[5];
                unidad[0] = rs.getString("Boleta");
                unidad[1] = rs.getString("nombre");
                unidad[2] = rs.getString("paterno");
                unidad[3] = rs.getString("materno");
                unidad[4] = rs.getString("genero");
                arr.add(unidad);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        int tamanio = arr.size();
        String ret[][] = new String[tamanio][5];
        for (int i = 0; i < tamanio; i++) {
            ret[i] = arr.get(i);
        }
        return ret;
    }
    
    public String[][]gruposAlumno(String boleta){
        ArrayList<String[]> arr = new ArrayList<>();
        try{
            baseDeDatos bd = new baseDeDatos();
            bd.conectar();
            ResultSet rs = bd.ejecuta("call spGruposPorAlumno('" + boleta + "');");
            while(rs.next()){
                String unidad[] = new String[4];
                unidad[0] = rs.getString("grupo");
                unidad[1] = rs.getString("semestre");
                unidad[2] = rs.getString("turmo");
                unidad[3] = rs.getString("area");
                arr.add(unidad);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        int tamanio = arr.size();
        String ret[][] = new String[tamanio][4];
        for (int i = 0; i < tamanio; i++) {
            ret[i] = arr.get(i);
        }
        return ret;
    }
    
    public String[][] unidadesHorarioGrupos(){
        String horas[] = {"---", "07:00", "08:00", "09:00", "10:00", "11:00",
        "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00"};
        ArrayList<String[]> arr = new ArrayList<>();
        try{
            baseDeDatos bd = new baseDeDatos();
            bd.conectar();
            ResultSet rs = bd.ejecuta("select * from vwunidadeshorarios where idGrupo <0;");
            int contador=0;
            int idUnidad=0;
            String dato[]=new String[8];
            while(rs.next()){
                if(rs.getInt("idUnidad") == idUnidad){
                    dato[6] = rs.getString("Nombre");
                    dato[0] = rs.getString("materia");
                    int dia = rs.getInt("idDia");
                    int horIn = rs.getInt("idHorarioI"), horaF = rs.getInt("idHorarioF");
                    String horario = horas[horIn] + " - " + horas[horaF];
                    dato[dia] = horario;
                    if(rs.isLast()){
                        dato[7] = idUnidad+"";
                        dato[0] = rs.getString("materia");
                        dato[6] = rs.getString("Nombre");
                        for(int i = 1; i<6; i++){
                            if(dato[i] == null)
                                dato[i] = "---";
                        }
                        arr.add(dato); 
                    }
                }else{
                    if(contador==1){
                        dato[7] = idUnidad+"";
                        for(int i = 1; i<6; i++){
                            if(dato[i] == null)
                                dato[i] = "---";
                        }
                        arr.add(dato); 
                    }
                    idUnidad = rs.getInt("idUnidad");
                    contador=1;
                    dato = new String[8];
                    int dia = rs.getInt("idDia");
                    int horIn = rs.getInt("idHorarioI"), horaF = rs.getInt("idHorarioF");
                    String horario = horas[horIn] + " - " + horas[horaF];
                    dato[dia] = horario;
                    dato[0] = rs.getString("materia");
                    dato[6] = rs.getString("Nombre");
                    if(rs.isLast()){
                        dato[7] = idUnidad+"";
                        dato[0] = rs.getString("materia");
                        dato[6] = rs.getString("Nombre");
                        for(int i = 1; i<6; i++){
                            if(dato[i] == null)
                                dato[i] = "---";
                        }
                        arr.add(dato); 
                    }
                }                
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        int tamanio = arr.size();
        String ret[][] = new String[tamanio][8];
        for (int i = 0; i < tamanio; i++) {
            ret[i] = arr.get(i);
        }
        return ret;
    }
    
    public String[][] unidadesHorarioGrupo(String grupo){
        String horas[] = {"---", "07:00", "08:00", "09:00", "10:00", "11:00",
        "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00"};
        ArrayList<String[]> arr = new ArrayList<>();
        try{
            baseDeDatos bd = new baseDeDatos();
            bd.conectar();
            ResultSet rs = bd.ejecuta("select * from vwunidadeshorarios where grupo = '"+ grupo + "';");
            int contador=0;
            int idUnidad=0;
            String dato[]=new String[8];
            while(rs.next()){
                if(rs.getInt("idUnidad") == idUnidad){
                    dato[0] = rs.getString("materia");
                    dato[6] = rs.getString("Nombre");
                    int dia = rs.getInt("idDia");
                    int horIn = rs.getInt("idHorarioI"), horaF = rs.getInt("idHorarioF");
                    String horario = horas[horIn] + " - " + horas[horaF];
                    dato[dia] = horario;
                    if(rs.isLast()){
                        dato[7] = idUnidad+"";
                        dato[6] = rs.getString("Nombre");
                        dato[0] = rs.getString("materia");
                        for(int i = 1; i<6; i++){
                            if(dato[i] == null)
                                dato[i] = "---";
                        }
                        arr.add(dato); 
                    }
                }else{
                    if(contador==1){
                        dato[7] = idUnidad+"";
                        for(int i = 1; i<6; i++){
                            if(dato[i] == null)
                                dato[i] = "---";
                        }
                        arr.add(dato); 
                    }
                    idUnidad = rs.getInt("idUnidad");
                    contador=1;
                    dato = new String[8];
                    int dia = rs.getInt("idDia");
                    int horIn = rs.getInt("idHorarioI"), horaF = rs.getInt("idHorarioF");
                    String horario = horas[horIn] + " - " + horas[horaF];
                    dato[dia] = horario;
                    dato[0] = rs.getString("materia");
                    dato[6] = rs.getString("Nombre");
                    dato[7] = idUnidad+"";
                    if(rs.isLast()){
                        dato[7] = idUnidad+"";
                        dato[6] = rs.getString("Nombre");
                        dato[0] = rs.getString("materia");
                        for(int i = 1; i<6; i++){
                            if(dato[i] == null)
                                dato[i] = "---";
                        }
                        arr.add(dato); 
                    }
                }                
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        int tamanio = arr.size();
        String ret[][] = new String[tamanio][8];
        for (int i = 0; i < tamanio; i++) {
            ret[i] = arr.get(i);
        }
        return ret;
    }
    
    public int[][][] unidadesHorarioSinFormatoGrupo(String grupo){
        ArrayList<int[]> arr1 = new ArrayList<>();
        ArrayList<int[]> arr2 = new ArrayList<>();
        try{
            baseDeDatos bd = new baseDeDatos();
            bd.conectar();
            ResultSet rs = bd.ejecuta("select * from vwunidadeshorarios where grupo = '"+ grupo + "';");
            int contador=0;
            int idUnidad=0;
            int dato1[]=new int[5];
            int dato2[]=new int[5];
            while(rs.next()){
                if(rs.getInt("idUnidad") == idUnidad){
                    int dia = rs.getInt("idDia")-1;
                    dato1[dia] = rs.getInt("idHorarioI");
                    dato2[dia] = rs.getInt("idHorarioF");
                    if(rs.isLast()){
                        arr1.add(dato1); 
                        arr2.add(dato2); 
                    }
                }else{
                    if(contador==1){
                        arr1.add(dato1); 
                        arr2.add(dato2);
                    }
                    idUnidad = rs.getInt("idUnidad");
                    contador=1;
                    dato1=new int[5];
                    dato2=new int[5];
                    int dia = rs.getInt("idDia")-1;
                    dato1[dia]= rs.getInt("idHorarioI");
                    dato1[dia] = rs.getInt("idHorarioF");
                    if(rs.isLast()){
                        arr1.add(dato1); 
                        arr2.add(dato2); 
                    }
                }                
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        int tamanio = arr1.size();
        int ret[][][] = new int[2][tamanio][5];
        for (int i = 0; i < tamanio; i++) {
            ret[0][i] = arr1.get(i);
            ret[1][i] = arr2.get(i);
        }
        return ret;
    }
    
    public String[] horarioUnidad(String idUnid){
        String ret[] = new String[15];
        baseDeDatos bd = new baseDeDatos();
        try{
            bd.conectar();
            ResultSet rs = bd.ejecuta("call spTraerDatosUnidad('" + idUnid +"');");
            int dia;
            while(rs.next()){
                ret[0] = rs.getString("idUnidad");
                dia = rs.getInt("idDia");
                ret[dia] = rs.getString("idHorarioI");
                ret[dia+5] = rs.getString("idHorarioF");
                ret[11] = rs.getString("materia");
                ret[12] = rs.getString("semestre");
                ret[13] = rs.getString("cupo");
                ret[14] = rs.getString("idMateria");
            }
            bd.cierraConexion();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        for(int i = 0; i<14; i++){
            if(ret[i] == null){
                ret[i]="0";
            }
        }
        return ret;
    }
    
    public String[][][] unidadesHorarioGrupoFacil(String grupo){
        String ret[][][] = new String[15][6][2];
        try {
            baseDeDatos bd = new baseDeDatos();
            bd.conectar();
            ResultSet rs = bd.ejecuta("select * from vwunidadeshorarios where grupo = '"+ grupo + "';");
            while(rs.next()){
                int dia = rs.getInt("idDia");
                int horIn = rs.getInt("idHorarioI"), horaF = rs.getInt("idHorarioF");
                int resta = horaF-horIn, aumento=0;
                String materia = rs.getString("materia");
                int unidadd = rs.getInt("idUnidad");
                while(resta>0){
                    resta--;
                    ret[horIn+aumento][dia][0] = materia;
                    ret[horIn+aumento][dia][1] = unidadd+"";
                    aumento++;
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(traerDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public String[] traerAreas(){
        ArrayList<String> array = new ArrayList<>();
        
        baseDeDatos bd = new baseDeDatos();
        try{
            bd.conectar();
            ResultSet rs = bd.ejecuta("select * from areas");
            while(rs.next()){
                array.add(rs.getString("area"));
            }
            bd.cierraConexion();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        String ret[] = new String[array.size()];
        for(int i = 0; i<array.size(); i++){
            ret[i] = array.get(i);
        }
        return ret;
    }
    
    public String[][] unidadesHorarioAlumnos(){
        String horas[] = {"---", "07:00", "08:00", "09:00", "10:00", "11:00",
        "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00"};
        ArrayList<String[]> arr = new ArrayList<>();
        try{
            baseDeDatos bd = new baseDeDatos();
            bd.conectar();
            ResultSet rs = bd.ejecuta("select * from vwunidadeshorarios;");
            int contador=0;
            int idUnidad=0;
            String dato[]=new String[8];
            while(rs.next()){
                if(rs.getInt("idUnidad") == idUnidad){
                    dato[6] = rs.getString("Nombre");
                    dato[0] = rs.getString("materia");
                    int dia = rs.getInt("idDia");
                    int horIn = rs.getInt("idHorarioI"), horaF = rs.getInt("idHorarioF");
                    String horario = horas[horIn] + " - " + horas[horaF];
                    dato[dia] = horario;
                    if(rs.isLast()){
                        dato[7] = idUnidad+"";
                        dato[0] = rs.getString("materia");
                        dato[6] = rs.getString("Nombre");
                        for(int i = 1; i<6; i++){
                            if(dato[i] == null)
                                dato[i] = "---";
                        }
                        arr.add(dato); 
                    }
                }else{
                    if(contador==1){
                        dato[7] = idUnidad+"";
                        for(int i = 1; i<6; i++){
                            if(dato[i] == null)
                                dato[i] = "---";
                        }
                        arr.add(dato); 
                    }
                    idUnidad = rs.getInt("idUnidad");
                    contador=1;
                    dato = new String[8];
                    int dia = rs.getInt("idDia");
                    int horIn = rs.getInt("idHorarioI"), horaF = rs.getInt("idHorarioF");
                    String horario = horas[horIn] + " - " + horas[horaF];
                    dato[dia] = horario;
                    dato[0] = rs.getString("materia");
                    dato[6] = rs.getString("Nombre");
                    if(rs.isLast()){
                        dato[7] = idUnidad+"";
                        dato[0] = rs.getString("materia");
                        dato[6] = rs.getString("Nombre");
                        for(int i = 1; i<6; i++){
                            if(dato[i] == null)
                                dato[i] = "---";
                        }
                        arr.add(dato); 
                    }
                }                
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        int tamanio = arr.size();
        String ret[][] = new String[tamanio][8];
        for (int i = 0; i < tamanio; i++) {
            ret[i] = arr.get(i);
        }
        return ret;
    }
    
    public String[][] unidadesHorarioAlumno(String grupo){
        String horas[] = {"---", "07:00", "08:00", "09:00", "10:00", "11:00",
        "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00"};
        ArrayList<String[]> arr = new ArrayList<>();
        try{
            baseDeDatos bd = new baseDeDatos();
            bd.conectar();
            ResultSet rs = bd.ejecuta("select * from vwUnidadesAlumnos where boleta = '"+ grupo + "';");
            int contador=0;
            int idUnidad=0;
            String dato[]=new String[8];
            while(rs.next()){
                if(rs.getInt("idUnidad") == idUnidad){
                    dato[0] = rs.getString("materia");
                    dato[6] = rs.getString("Nombre");
                    int dia = rs.getInt("idDia");
                    int horIn = rs.getInt("idHorarioI"), horaF = rs.getInt("idHorarioF");
                    String horario = horas[horIn] + " - " + horas[horaF];
                    dato[dia] = horario;
                    if(rs.isLast()){
                        dato[7] = idUnidad+"";
                        dato[6] = rs.getString("Nombre");
                        dato[0] = rs.getString("materia");
                        for(int i = 1; i<6; i++){
                            if(dato[i] == null)
                                dato[i] = "---";
                        }
                        arr.add(dato); 
                    }
                }else{
                    if(contador==1){
                        dato[7] = idUnidad+"";
                        for(int i = 1; i<6; i++){
                            if(dato[i] == null)
                                dato[i] = "---";
                        }
                        arr.add(dato); 
                    }
                    idUnidad = rs.getInt("idUnidad");
                    contador=1;
                    dato = new String[8];
                    int dia = rs.getInt("idDia");
                    int horIn = rs.getInt("idHorarioI"), horaF = rs.getInt("idHorarioF");
                    String horario = horas[horIn] + " - " + horas[horaF];
                    dato[dia] = horario;
                    dato[0] = rs.getString("materia");
                    dato[6] = rs.getString("Nombre");
                    dato[7] = idUnidad+"";
                    if(rs.isLast()){
                        dato[7] = idUnidad+"";
                        dato[6] = rs.getString("Nombre");
                        dato[0] = rs.getString("materia");
                        for(int i = 1; i<6; i++){
                            if(dato[i] == null)
                                dato[i] = "---";
                        }
                        arr.add(dato); 
                    }
                }                
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        int tamanio = arr.size();
        String ret[][] = new String[tamanio][8];
        for (int i = 0; i < tamanio; i++) {
            ret[i] = arr.get(i);
        }
        return ret;
    }
    
    public int[][][] unidadesHorarioSinFormatoAlumno(String grupo){
        ArrayList<int[]> arr1 = new ArrayList<>();
        ArrayList<int[]> arr2 = new ArrayList<>();
        try{
            baseDeDatos bd = new baseDeDatos();
            bd.conectar();
            ResultSet rs = bd.ejecuta("select * from vwUnidadesAlumnos where boleta = '"+ grupo + "';");
            int contador=0;
            int idUnidad=0;
            int dato1[]=new int[5];
            int dato2[]=new int[5];
            while(rs.next()){
                if(rs.getInt("idUnidad") == idUnidad){
                    int dia = rs.getInt("idDia")-1;
                    dato1[dia] = rs.getInt("idHorarioI");
                    dato2[dia] = rs.getInt("idHorarioF");
                    if(rs.isLast()){
                        arr1.add(dato1); 
                        arr2.add(dato2); 
                    }
                }else{
                    if(contador==1){
                        arr1.add(dato1); 
                        arr2.add(dato2);
                    }
                    idUnidad = rs.getInt("idUnidad");
                    contador=1;
                    dato1=new int[5];
                    dato2=new int[5];
                    int dia = rs.getInt("idDia")-1;
                    dato1[dia]= rs.getInt("idHorarioI");
                    dato1[dia] = rs.getInt("idHorarioF");
                    if(rs.isLast()){
                        arr1.add(dato1); 
                        arr2.add(dato2); 
                    }
                }                
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        int tamanio = arr1.size();
        int ret[][][] = new int[2][tamanio][5];
        for (int i = 0; i < tamanio; i++) {
            ret[0][i] = arr1.get(i);
            ret[1][i] = arr2.get(i);
        }
        return ret;
    }
}
