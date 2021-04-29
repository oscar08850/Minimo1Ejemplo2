package edu.upc.dsa.services;


import edu.upc.dsa.GameManager;

import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.clases.Objeto;
import edu.upc.dsa.clases.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Api(value = "/Juego", description = "Endpoint to Text Service")
@Path("/users")
public class GameService {

    private GameManager pm;
    private boolean inicio = true;

    public GameService() {
        this.pm = GameManagerImpl.getInstance();

        if (this.pm.numeroObjetoListAPI()==0 && inicio) {
            //
            Objeto objeto1 = new Objeto("Espada","1");
            Objeto objeto2 = new Objeto("Escudo","2");
            Objeto objeto3 = new Objeto("Moneda","3");

            //Metemos objetos en la lista
            pm.addObjectALista(objeto1);
            pm.addObjectALista(objeto2);
            pm.addObjectALista(objeto3);
        }

        if (this.pm.numeroUsuarios () == 0 && inicio) {
            //Creamos los usuarios (addUser)
            //Añadimos los usuarios al HashMap
            pm.addUser("Oscar","Vilamitjana", "1");
            pm.addUser("Alex","Moya", "2");
            pm.addUser("Toni","Oller", "3");
            pm.addObjectToUserUsandoIdObjeto("3","1"); //Toni tiene Espada

        }
        inicio = false;
    }


    @GET
    @ApiOperation(value = "get all Users", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/listaUsuarios")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {

        List<User> users = this.pm.findAll();
        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {};
        return Response.status(201).entity(entity).build();

    }


    @POST
    @ApiOperation(value = "create a new User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=User.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/addUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(User user) {

        if (user.getNombre()==null || user.getApellido() == null || user.getId() == null)
            return Response.status(500).entity(user).build();
        this.pm.addUser(user.getNombre(),user.getApellido(),user.getId());
        return Response.status(201).entity(user).build();
    }

    // Modificar un usuario
    @PUT
    @ApiOperation(value = "update a User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/updateUser")
    public Response updateUser(User user) {

        User t = this.pm.modificarUser(user.getNombre(),user.getApellido(),user.getId());
        if (t == null) return Response.status(404).build();
        return Response.status(201).build();
    }

    //consultar la información de un usuario
    @GET
    @ApiOperation(value = "get a User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/getUser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") String id) {
        User t = this.pm.getUserById(id);
        if (t == null) return Response.status(404).build();
        else  return Response.status(201).entity(t).build();
    }

    // Consultar los objetos de un usuario
    @GET
    @ApiOperation(value = "get a Objetos de Usuario", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Objeto.class),
            @ApiResponse(code = 404, message = "Lista Vacia")
    })
    @Path("/getObjetosUser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getObjetoYUser(@PathParam("id") String id) {
        User t = this.pm.getUserById(id);
        List<Objeto> objetoList = t.getObjetoList();
        if (objetoList.size() == 0) return Response.status(404).build();
        else {
            GenericEntity<List<Objeto>> entity = new GenericEntity<List<Objeto>>(objetoList) {};
            return Response.status(201).entity(entity).build();
        }
    }

    // añadir un objeto sobre un usuario
    @PUT
    @ApiOperation(value = "Añadir objeto a Usuario", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/AddObjeto/{idUser}{idObjeto}")
    public Response addObjeto(@PathParam("idUser") String idUser, @PathParam("idObjeto") String idObjeto) {
        User user = pm.getUserById(idUser);
        Objeto objeto = pm.getObjetoByName(idObjeto);
        pm.addObjectToUserUsandoIdObjeto(idUser,idObjeto);
        if (user == null || objeto == null) return Response.status(404).build();
        return Response.status(201).build();
    }





}
