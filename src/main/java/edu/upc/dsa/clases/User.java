package edu.upc.dsa.clases;

import edu.upc.dsa.GameManagerImpl;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class User {
    private String nombre;
    private String apellido;
    private String id;

    static final Logger log = Logger.getLogger(GameManagerImpl.class.getName());


    List<Objeto> objetoList = null;



    //Constructor vacio para la magia de API
    public User() {
    }

    //Constructor
    public User(String nombre, String apellido, String id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
        this.objetoList = new LinkedList<>();
    }


    //Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Objeto> getObjetoList() {
        return objetoList;
    }

    public void setObjetoList(List<Objeto> objetoList) {
        this.objetoList = objetoList;
    }

    public void muestraObjetosUser(){
        for(Objeto p: this.objetoList){
            log.info("Nombre Objeto: "+ p.getName());
        }
    }

}
