package com.daw.atlanthis.beans.hilo;

import com.daw.atlanthis.DTO.Usuarios;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="beanHilo")
@SessionScoped 
public class beanHilo {
    
    private String contenidoPrincipal;
    @ManagedProperty("#{beanLogin.usu}")
    private Usuarios user;

    public Usuarios getUser() {
        return user;
    }

    public void setUser(Usuarios mail) {
        this.user = mail;
    }
    
    public String getContenidoPrincipal() {
        return contenidoPrincipal;
    }

    public void setContenidoPrincipal(String contenidoPrincipal) {
        this.contenidoPrincipal = contenidoPrincipal;
    }
}
