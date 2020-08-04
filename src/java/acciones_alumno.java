/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrador
 */

 import java.util.*;
    import java.sql.*;
    
public class acciones_alumno {
   
    public static Connection getConnection(){
        String url, user, password;
        
        url = "jdbc:mysql:3306//localhost/Alumnos";
        user = "root";
        password = "1234";
        
        Connection con = null;
        
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
           url = "jdbc:mysql://localhost/Alumnos";
            con =DriverManager.getConnection(url, user, password);
            
            System.out.println("si conecto a la BD");
        
    }catch(Exception e){
        
        System.out.println("no conecto a la BD");
        System.out.println(e.getMessage());
        System.out.println(e.getStackTrace());
        
        
        
    }
     return con;   
    }
    
    public static int Guardar_alumno(Alumno a){
        int estatus =0;
        try{
            
            Connection con = acciones_alumno.getConnection();
            
            String q = "insert into alumnos(nom_alu, pass_alu, email_alu, pais_alu)"
                    + " values (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(q);
            
            ps.setString(1,a.getNombre());
            ps.setString(2,a.getPassword());
            ps.setString(3,a.getEmail());
            ps.setString(4,a.getPais());
            
            estatus = ps.executeUpdate();
            con.close();
            
            System.out.println("Registro Exitoso");
            
        }catch(Exception e){
            
            System.out.println("no encontro la tabla");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            
        }
        return estatus;
    }
    public static int Actualizar_alumno(Alumno a){
        int estatus =0;
        try{
            
            Connection con = acciones_alumno.getConnection();
            
            String q = "update Alumnos set nom_alu =?,"
                    + "pass_alu =?,"
                    + "email_alu =?,"
                    + "pais_alu =? "
                    + "where id_alu = ?";
            PreparedStatement ps = con.prepareStatement(q);
            
            ps.setString(1,a.getNombre());
            ps.setString(2,a.getPassword());
            ps.setString(3,a.getEmail());
            ps.setString(4,a.getPais());
            ps.setInt(5, a.getId());
            
            estatus = ps.executeUpdate();
            con.close();
            
            System.out.println("actualizacion Exitosa");
            
        }catch(Exception e){
            
            System.out.println("no encontro la tabla");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            
        }
        return estatus;
    }
    public static int Borrar_alumno(int id){
        int estatus =0;
        try{
            
            Connection con = acciones_alumno.getConnection();
            
            String q = "delete from Alumnos where id_alu =?";
            
            PreparedStatement ps = con.prepareStatement(q);
            
           ps.setInt(1, id);
            
            estatus = ps.executeUpdate();
            con.close();
            
            System.out.println("Registro Exitoso");
            
        }catch(Exception e){
            
            System.out.println("no encontro la tabla");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            
        }
        return estatus;
    }
   public static Alumno getAlumnoById(int id){
        Alumno a= new Alumno();
        try{
            
            Connection con = acciones_alumno.getConnection();
            
            String q = "select * from Alumnos where id_alu = ?";
            PreparedStatement ps = con.prepareStatement(q);
            
            ps.setInt(1, id);
            
           ResultSet rs = ps.executeQuery();
           
           if(rs.next()){
               
              a.setId(rs.getInt(1));
              a.setNombre(rs.getString(2));
              a.setPassword(rs.getString(3));
              a.setEmail(rs.getString(4));
              a.setEmail(rs.getString(5));
           }
            
            con.close();
            
            System.out.println("busqueda Exitosa");
            
        }catch(Exception e){
            
            System.out.println("no encontro la tabla");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            
        }
        return a;
    }
      public static List<Alumno> getAllAlumno(){
        List<Alumno> lista = new ArrayList<Alumno>();
        
        try{
            
            Connection con = acciones_alumno.getConnection();
            
            String q = "select * from Alumnos ";
            PreparedStatement ps = con.prepareStatement(q);
            
            
            
           ResultSet rs = ps.executeQuery();
           
           while(rs.next()){
               
               Alumno a = new Alumno();
               
              a.setId(rs.getInt(1));
              a.setNombre(rs.getString(2));
              a.setPassword(rs.getString(3));
              a.setEmail(rs.getString(4));
              a.setEmail(rs.getString(5));
              lista.add(a);
           }
            
            con.close();
            
            System.out.println("busqueda Exitosa");
            
        }catch(Exception e){
            
            System.out.println("no encontro la tabla");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            
        }
        return lista; 
        
    }
}
