/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pspro.dll;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nitro
 */
public class ConexionDB {
    static Connection conn = null;
    static ResultSet rs = null;
    static PreparedStatement stmt = null;
    static String bd = "data_base_name";
    static String login = "root";
    static String password = "";
    static String url = "jdbc:mysql://localhost/" + bd;
    
    //OBTENER NOMBRE DE LAS TABLAS EN JAVA
    /*
    ResultSet rs = stmt.executeQuery("SELECT a, b, c FROM TABLE2");
    ResultSetMetaData rsmd = rs.getMetaData();
    int columnCount = rsmd.getColumnCount();
    String name = rsmd.getColumnName(1);
    */
    
    //ESTABLECE LA CONEXIÓN CON LA DB
    private static void enlace() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            System.out.println("Conexión exitosa");
        } catch (SQLException ex) {
            System.out.println("Excepicon en la conexión");
        } catch (ClassNotFoundException ex) {
            System.out.println("No se encuentra la clase");
        } catch (Exception ex){
            System.err.println(ex.getMessage());
        }
    }
    
    public static int insertarObjeto(Object object) {
        
        enlace();
        
        try {
            
            String sql = "INSERT INTO tabla (campo1, campo2, campo3) VALUES (?, ?, ?);";
            stmt = conn.prepareStatement(sql);
            //stmt.setString(1, object.getCampo1());
            //stmt.setString(2, object.getCampo2());
            //stmt.setString(3, object.getCampo3());
            System.out.println(stmt.toString());
            stmt.execute();
            
        } catch (SQLIntegrityConstraintViolationException ex){
            System.out.println("Error SQL: " + ex.toString());
            return 0;
        } 
        catch (SQLException ex) {
            System.out.println("Error SQL: " + ex.toString());
            return -1;
        }
        
        cerrarSesion();
        
        return 1;
    }
    
    public static ArrayList<Object> getObjects() {
        
        ArrayList<Object> listaObjetos = new ArrayList<Object>();
        
        enlace();
        
        try {
            
            String sql = "SELECT * FROM tabla";
            stmt = conn.prepareStatement(sql);
            System.out.println(stmt.toString());
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                //listaObjetos.add(new Object(rs.getString("Campo1"), rs.getString("Campo2"), rs.getString("Campo3")));
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL: " + ex.getMessage());
            //ex.printStackTrace();
        }
        
        cerrarSesion();
        
        return listaObjetos;

    }
    
    public static Object getObject(String id) {
        
        Object object = null;
        enlace();
        
        try {
            
            String sql = "SELECT * FROM tabla WHERE campo1=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            System.out.println(stmt.toString());
            rs = stmt.executeQuery();
            
            if(rs.next()){
                //object = new Object(rs.getString("Campo1"), rs.getString("Campo2"), rs.getString("Campo3"));
            }
            
        } catch (SQLException ex) {
            System.out.println("Error SQL: " + ex.getMessage());
            //ex.printStackTrace();
        }
        
        cerrarSesion();
        
        return object;
    }
    
    public static Object updateObject(String id_ant, String id_nuev, String campo2, String campo3) {
        enlace();
        
        try {
            
            String sql = "UPDATE vehiculo SET Marca = ?, Modelo = ?, Matricula = ? WHERE Matricula = ?;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, campo2);
            stmt.setString(2, campo3);
            stmt.setString(3, id_nuev);
            stmt.setString(4, id_ant);
            System.out.println(stmt.toString());
            stmt.executeUpdate();
            
            //return new Object(campo2, campo3, id_nuev);
            return new Object();
            
        } catch (SQLException ex) {
            System.out.println("Error SQL: " + ex.getMessage());
            //ex.printStackTrace();
        }
        
        cerrarSesion();
        
        return null;
    }
    
    public static Boolean deleteObject(String id) {
        
        enlace();
        
        try {
            
            String sql = "DELETE FROM tabla WHERE campo=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            System.out.println(stmt.toString());
            stmt.execute();
            
            return true;
            
        } catch (SQLException ex) {
            System.out.println("Error SQL: " + ex.getMessage());
            //ex.printStackTrace();
        }
        
        cerrarSesion();
        
        return false;
    }
    
    //CIERRA LA CONEXION CON LA DB
    private static void cerrarSesion() {
        try {
            rs.close();
            stmt.close();
            conn.close();
            System.out.println("Conexión cerrada \n");
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static DefaultTableModel buildTableModel(String query) {
        
        Vector<Vector<Object>> data = null;
        Vector<String> columnNames = null;
        
        try {
            enlace();
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            
            ResultSetMetaData metaData = rs.getMetaData();
            
            // names of columns
            columnNames = new Vector<String>();
            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }

            // data of the table
            data = new Vector<Vector<Object>>();
            while (rs.next()) {
                Vector<Object> vector = new Vector<Object>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(rs.getObject(columnIndex));
                }
                data.add(vector);
            }
            
            cerrarSesion();
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new DefaultTableModel(data, columnNames);

    }
    
    public void fillTable(JTable table, String Query)
    {
        try
        {
            enlace();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(Query);

            //To remove previously added rows
            while(table.getRowCount() > 0) 
            {
                ((DefaultTableModel) table.getModel()).removeRow(0);
            }
            int columns = rs.getMetaData().getColumnCount();
            while(rs.next())
            {  
                Object[] row = new Object[columns];
                for (int i = 1; i <= columns; i++)
                {  
                    row[i - 1] = rs.getObject(i);
                }
                ((DefaultTableModel) table.getModel()).insertRow(rs.getRow()-1,row);
            }

            cerrarSesion();
        }
        catch(SQLException ex)
        {
            System.out.println("Error SQL: " + ex.getMessage());
        }
    }
    
    public static void resultSetToTableModel(String query, JTable table) {
        
        try {
            enlace();
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            
            //Create new table model
            DefaultTableModel tableModel = new DefaultTableModel();
            
            //Retrieve meta data from ResultSet
            ResultSetMetaData metaData = rs.getMetaData();
            
            //Get number of columns from meta data
            int columnCount = metaData.getColumnCount();
            
            //Get all column names from meta data and add columns to table model
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++){
                tableModel.addColumn(metaData.getColumnLabel(columnIndex));
            }
            
            //Create array of Objects with size of column count from meta data
            Object[] row = new Object[columnCount];
            
            //Scroll through result set
            while (rs.next()){
                //Get object from column with specific index of result set to array of objects
                for (int i = 0; i < columnCount; i++){
                    row[i] = rs.getObject(i+1);
                }
                //Now add row to table model with that array of objects as an argument
                tableModel.addRow(row);
            }
            
            //Now add that table model to your table and you are done :D
            table.setModel(tableModel);
            
            cerrarSesion();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
