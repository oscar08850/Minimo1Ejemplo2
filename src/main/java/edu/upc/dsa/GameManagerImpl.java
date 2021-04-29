package edu.upc.dsa;

import edu.upc.dsa.clases.Objeto;
import edu.upc.dsa.clases.User;

import java.util.*;

import org.apache.log4j.Logger;


public class GameManagerImpl implements GameManager{

    //Estructura de datos
    HashMap<String, User> hashUsuarios = new HashMap<String, User>();
    List<Objeto> lisaObjetoAux = new LinkedList<>();


    static final Logger log = Logger.getLogger(GameManagerImpl.class.getName());

    private static GameManagerImpl manager;

    public static GameManagerImpl getInstance()  /*Singletone, puerta de entrada a la instancia*/ {
        if (manager == null) {
            manager = new GameManagerImpl();
        }
        return manager;
    }

    public static void delete(){
        manager = null;    //Permite reiniciar la base de datos
        log.info("Instancia ProductManagerImpl borrada");
    }

    public GameManagerImpl() {}

    public void clear(){
        lisaObjetoAux.clear();
        hashUsuarios.clear();
    }



            ///Metodos///

    @Override
    public List<User> usuariosAlfabeticamente() {
        List<User> resultado = new LinkedList<>(hashUsuarios.values());

        Collections.sort(resultado, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getNombre().compareToIgnoreCase(o2.getNombre());
            }
        });
        log.info("Lista ordenada alfabeticamente: " + resultado.toString());
        return resultado;
    }

    @Override
    public void addUser(String nombre, String apellido, String id) {
        User userTemp = new User(nombre, apellido, id);
        hashUsuarios.put(id,userTemp);
    }

    @Override
    public User modificarUser(String nombre, String apellido, String id) {
        User userTemp = this.hashUsuarios.get(id);
        if (userTemp != null){ //Si lo encuentro
            userTemp.setNombre(nombre);
            userTemp.setApellido(apellido);
            userTemp.setId(id);
            log.info("Updated User parameters:" + userTemp);
        }
        return userTemp;
    }

    //Retorna el NÚMERO de usuarios en el sistema
    @Override
    public int numeroUsuarios() {
        return this.hashUsuarios.size();
    }

    //Consultar un usuario mediante una ID
    @Override
    public User consultaUsuario(String id) {
        User userTemp = getUserById(id);
        if (userTemp != null){
            log.info("User Found: " + userTemp);
        }
        else
            log.error("User not found for id: " + id);
        return userTemp;
    }

    //Añadir un objeto a un usuario
    @Override
    public void addObjectToUserUsandoIdObjeto(String idUser, String idObjeto) {
        User userTemp = getUserById(idUser); //encuentro el user
        Objeto objetoTemp = getObjetoByName(idObjeto); //Busco el Objeto
        List<Objeto> listaTemp = userTemp.getObjetoList();
        if (userTemp != null){ //Si encuentro el usuario
            log.info("User Found");
            listaTemp.add(objetoTemp);
            userTemp.setObjetoList(listaTemp);
        }
        else
            log.error("User not found for id: " + idUser);
    }



    @Override
    public List<Objeto> ConsultarObjetos(String id) {
        User userTemp = getUserById(id);
        List<Objeto> objetoList = userTemp.getObjetoList();
        for (Objeto objeto: objetoList){
            //if(objeto.getId().compareTo(id)==0)//Si quiero un objeto en concreto
            log.info(objeto.getId());
            log.info(objeto.getName());
        }
        return objetoList;
    }

    //Número de objetos de un usuario
    @Override
    public int numeroObjetosUsuario(String id) {
        User userTemp =  getUserById(id);
        return userTemp.getObjetoList().size();
    }

    public User getUserById(String id){
        User userTemp = this.hashUsuarios.get(id);
        return userTemp;
    }

    //Busca un objeto por IdObjeto
    public Objeto getObjetoByName(String idObjeto) {
        Objeto objetoTemp = null;
        for (Objeto objeto: this.lisaObjetoAux){
            if (objeto.getId().compareTo(idObjeto)==0) {
                objetoTemp = objeto;
            }
        }
        return objetoTemp;
    }

    public void addObjectALista(Objeto objeto){
        lisaObjetoAux.add(objeto);
    }

    public int numeroObjetoListAPI() {
        return this.lisaObjetoAux.size();
    }

    public List<User> findAll(){
        if (hashUsuarios.size() != 0){
            List<User> list = new LinkedList<>(hashUsuarios.values());
            return list;
        }
        return null;
    }



}
