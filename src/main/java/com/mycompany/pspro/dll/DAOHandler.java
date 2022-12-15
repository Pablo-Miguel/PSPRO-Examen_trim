/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pspro.dll;

import com.mycompany.pspro.interfaces.IDAOHandler;
import java.util.ArrayList;

/**
 *
 * @author Nitro
 */
public class DAOHandler implements IDAOHandler {
    
    private static IDAOHandler dao = null;
    
    private DAOHandler() {
        super();

    }
    
    public static IDAOHandler getInstance() {
        if (dao == null) {
            dao = new DAOHandler();
        }

        return dao;
    }
    
    //INSERTAR -> OK: 1, PK_DUPLICATE: 0, OTHER_ERROR: -1;
    public int insertarObject(Object object) {
        return ConexionDB.insertarObjeto(object);
    }
    
    //OBTENER TODOS LOS OBJETOS DE UNA TABLA -> OK: LIST.SIZE() > 0, ERROR/NOT_FOUND: LIST.SIZE() == 0;
    public ArrayList<Object> getObjects(){
        return ConexionDB.getObjects();
    }
    
    //CONSULTAS CON RETORNO DE UNA SOLA FILA -> OK: OBJECT, ERROR: NULL;
    public Object getObject(String id){
        return ConexionDB.getObject(id);
    }
    
    //UPDATE UN OBJETO -> OK: OBJETO UPDATEADO, ERROR: NULL;
    public static Object updateObject(String id_ant, String id_nuev, String campo2, String campo3){
        return ConexionDB.updateObject(id_ant, id_nuev, campo2, campo3);
    }
    
    //DELETE UN OBJETO -> OK: TRUE, ERROR: FALSE;
    public Boolean deleteObject(String id){
        return ConexionDB.deleteObject(id);
    }
    
}
