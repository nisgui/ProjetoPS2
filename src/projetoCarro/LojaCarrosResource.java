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

@Path("/lojacarros")
@Produces(MediaType.APPLICATION_JSON)
public class LojaCarrosResource {

    private LojaCarrosDAO dao;

    public LojaCarrosResource() {
        dao = LojaCarrosDAO.getInstance();
    }

    @GET
    public List<LojaCarros> read() {
        return dao.read();
    }

    @GET
    @Path("{id}")
    public LojaCarros read2(@PathParam("idLoja") IntParam identifier) {
        List<LojaCarros> lojacarros = this.read();

        for (LojaCarros l : lojacarros) {
            if (l.getIdLoja() == identifier.get()) {
                return l;
            }
        }
        throw new WebApplicationException(404);
    }

    @POST
    public LojaCarros create(LojaCarros novaLojaCarros) {
        LojaCarros l = dao.create(novaLojaCarros);

        if (l != null) {
            return l;
        }

        throw new WebApplicationException(500);
    }
}
