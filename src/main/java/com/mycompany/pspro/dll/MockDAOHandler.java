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
public class MockDAOHandler implements IDAOHandler {
    
    ArrayList<Object> listaObjetos;
    
    public MockDAOHandler() {
    }
    
    @Override
    public ArrayList<Object> getObjects() {
        
        if(listaObjetos == null){
            listaObjetos = new ArrayList<Object>();
            
            listaObjetos.add(new Object());
            listaObjetos.add(new Object());
            listaObjetos.add(new Object());
            listaObjetos.add(new Object());
        }
        
        return listaObjetos;
        
    }
    
}
