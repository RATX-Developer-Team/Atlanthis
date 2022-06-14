package com.daw.atlanthis.beans.admin;

import com.daw.atlanthis.DTO.Usuarios;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name="beanAdmin")
@SessionScoped 
public class beanAdmin {
    
    @ManagedProperty("#{beanLogin.usu}")
    private Usuarios user;

    public Usuarios getUser() {
        if (this.user==null) {
            redireccionar("inicio.xhtml");
        } else if(this.user.getPermiso()==null || this.user.getPermiso()==null) {
            redireccionar("index.xhtml");
        }
        return user;
    }

    public void setUser(Usuarios mail) {
        this.user = mail;
    }
    
    private static void redireccionar (String url) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ctx.redirect(url);
        } catch (IOException ex) {}
    }

}
