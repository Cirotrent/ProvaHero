package com.provahero.provahero.web.rest.config;

import com.provahero.provahero.web.rest.resources.HeroResources;
import com.provahero.provahero.web.rest.resources.UtenteResources;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class RestServicesConfig extends Application {
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(HeroResources.class);
        classes.add(UtenteResources.class);
        return classes;
    }
}
