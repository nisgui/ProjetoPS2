/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import projetoapp.DesenvolvedorResource;
import projetoapp.AplicativoResource;
import projetoCarro.LojaCarrosResource;
import projetoCarro.CarroResource;

public class RestApp2 extends Application<Configuration> {
    public static void main(String[] args) throws Exception {
        new RestApp2().run(new String[] { "server" });
    }

    @Override
    public void run(Configuration configuration, Environment environment) {
        environment.jersey().register(new DesenvolvedorResource());
        environment.jersey().register(new AplicativoResource());
        environment.jersey().register(new LojaCarrosResource());
        environment.jersey().register(new CarroResource());
    }
}

