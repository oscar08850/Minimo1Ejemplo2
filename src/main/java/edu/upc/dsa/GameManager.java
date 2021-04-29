package edu.upc.dsa;

import edu.upc.dsa.clases.Objeto;
import edu.upc.dsa.clases.User;

import java.util.List;

public interface GameManager {
    public List<User> usuariosAlfabeticamente();
    public void addUser(String nombre, String apellido, String id);
    public User modificarUser(String nombre, String apellido, String id);
    public int numeroUsuarios();
    public User consultaUsuario(String id);
    public List<Objeto> ConsultarObjetos(String id);
    public int numeroObjetosUsuario(String id);

    public void addObjectToUserUsandoIdObjeto(String idUser, String idObjeto);
    public void addObjectALista(Objeto objeto);

    public Objeto getObjetoByName(String idObjeto);




        public void clear();
    public User getUserById(String id);
    public int numeroObjetoListAPI();
    public List<User> findAll();






    }
