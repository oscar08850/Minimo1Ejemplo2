package edu.upc.dsa.clases;


public class Objeto {
    private String name;
    private String id;


    //Constructor vacio para la magia de API
    public Objeto() {
    }

    //Constructor
    public Objeto(String name, String id) {
        this.name = name;
        this.id = id;
    }

    //Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
