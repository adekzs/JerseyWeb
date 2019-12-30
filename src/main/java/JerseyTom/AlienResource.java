package JerseyTom;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Path("aliens")
public class AlienResource {
    AlienRepository repo = new AlienRepository();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Alien> getAlien(){
        System.out.println("Getting called");
            return repo.getAliens();
    }
    @GET
    @Path("alien/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Alien getAlin(@PathParam("id") int id){
        Alien val = repo.getAlien(id);
        close();
        return val;
    }

    @POST
    @Path("alien")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Alien createAlien(Alien a1){
        repo.create(a1);
        System.out.println(a1);
        close();
        return a1;

    }

    @PUT
    @Path("alienupdate")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Alien updateAlien(Alien a1){
        if(repo.getAlien(a1.getId()).getId() == 0){
            repo.create(a1);
        }else {
            repo.update(a1);
        }
        System.out.println(a1);
       close();
        return a1;
    }

    @DELETE
    @Path("alien/{id}")
    public Alien deleteAlien(@PathParam("id") int id){
            Alien a=repo.getAlien(id);
                if(a.getId()!= 0)
            repo.delete(id);
        return a;
    }

    public void close(){
        try {
            repo.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
