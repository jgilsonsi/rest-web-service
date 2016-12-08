package com.jjdev.restwebservice;

import com.jjdev.restwebservice.pojo.JUser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author jgilson
 */
@Path("/users")
public class JUserService {

    //Simulation data
    private static final Map<Integer, JUser> users = new HashMap<>();

    static {
        for (int i = 0; i < 3; i++) {
            int id = i + 1;

            JUser user = new JUser();
            user.setId(id);
            user.setName("Usuario " + id);
            user.setAge(new Random().nextInt(40) + 1);

            users.put(id, user);
        }
    }

    // get user
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public JUser getUserByIdJSON(@PathParam("id") int id) {
        return users.get(id);
    }

    // get users
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<JUser> getAllUsersInJSON() {
        return new ArrayList<>(users.values());
    }

    // create user
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postUserJSON(JUser user) {
        users.put(user.getId(), user);
        String result = "Usuário " + user.getName() + " criado com sucesso!";
        return Response.status(200).entity(result).build();
    }

    // alter user
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putUserJSON(JUser user) {
        users.put(user.getId(), user);
        String result = "Usuário " + user.getName() + " alterado com sucesso!";
        return Response.status(200).entity(result).build();
    }

    // delete user
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public JUser deleteUserJSON(@PathParam("id") int id) {
        JUser userTemp = users.get(id);
        users.remove(id);
        return userTemp;
    }

    /* 
        Formato do objeto JSON:
       {user:{"id": "10","name": "testename","age": "33"}}
     */
}
