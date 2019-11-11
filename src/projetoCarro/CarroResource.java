/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoCarro;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import io.dropwizard.jersey.*;
import io.dropwizard.jersey.params.*;
import java.util.*;

@Path("/carros")
@Produces(MediaType.APPLICATION_JSON)
public class CarroResource {

    private CarroDAO dao;

    public CarroResource() {
        dao = CarroDAO.getInstance();
    }

    @GET
    public List<Carro> read() {
        return dao.read();
    }

    @GET
    @Path("{idCarro}")
    public Carro read2(@PathParam("idCarro") IntParam identifier) {
        List<Carro> carros = this.read();

        for (Carro c : carros) {
            if (c.getIdCarro() == identifier.get()) {
                return c;
            }
        }
        throw new WebApplicationException(404);
    }

    @POST
    public Carro create(Carro novoCarro) {
        Carro c = dao.create(novoCarro);

        if (c != null) {
            return c;
        }

        throw new WebApplicationException(500);
    }
}