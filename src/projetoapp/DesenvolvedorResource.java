/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoapp;

import projetoapp.DesenvolvedorDAO;
import projetoapp.Desenvolvedor;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import io.dropwizard.jersey.*;
import io.dropwizard.jersey.params.*;
import java.util.*;

@Path("/desenvolvedores")
@Produces(MediaType.APPLICATION_JSON)
public class DesenvolvedorResource {

    private DesenvolvedorDAO dao;

    public DesenvolvedorResource() {
        dao = DesenvolvedorDAO.getInstance();
    }

    @GET
    public List<Desenvolvedor> read() {
        return dao.read();
    }

    @GET
    @Path("{id}")
    public Desenvolvedor read2(@PathParam("idDev") IntParam identifier) {
        List<Desenvolvedor> desenvolvedores = this.read();

        for (Desenvolvedor d : desenvolvedores) {
            if (d.getIdDev() == identifier.get()) {
                return d;
            }
        }
        throw new WebApplicationException(404);
    }

    @POST
    public Desenvolvedor create(Desenvolvedor novoDesenvolvedor) {
        Desenvolvedor d = dao.create(novoDesenvolvedor);

        if (d != null) {
            return d;
        }

        throw new WebApplicationException(500);
    }
}
