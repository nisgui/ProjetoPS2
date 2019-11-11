/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoapp;

import projetoapp.AplicativoDAO;
import projetoapp.Aplicativo;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import io.dropwizard.jersey.*;
import io.dropwizard.jersey.params.*;
import java.util.*;

@Path("/aplicativos")
@Produces(MediaType.APPLICATION_JSON)
public class AplicativoResource {

    private AplicativoDAO dao;

    public AplicativoResource() {
        dao = AplicativoDAO.getInstance();
    }

    @GET
    public List<Aplicativo> read() {
        return dao.read();
    }

    @GET
    @Path("{idApp}")
    public Aplicativo read2(@PathParam("idApp") IntParam identifier) {
        List<Aplicativo> aplicativos = this.read();

        for (Aplicativo a : aplicativos) {
            if (a.getIdApp() == identifier.get()) {
                return a;
            }
        }
        throw new WebApplicationException(404);
    }

    @POST
    public Aplicativo create(Aplicativo novoAplicativo) {
        Aplicativo a = dao.create(novoAplicativo);

        if (a != null) {
            return a;
        }

        throw new WebApplicationException(500);
    }
}