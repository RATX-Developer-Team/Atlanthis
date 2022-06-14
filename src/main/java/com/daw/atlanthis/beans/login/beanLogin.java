package com.daw.atlanthis.beans.login;

import com.daw.atlanthis.DTO.Usuarios;
import com.daw.atlanthis.utils.Utilidades;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.NoSuchPaddingException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class beanLogin {
    private String email;
    private String passwd;
    private String error;
    
    private Usuarios usu;
    private boolean admin;
    private String nomUsu;
    
    public beanLogin() {
    }

    public String getNomUsu() {
        return nomUsu;
    }

    public void setNomUsu(String nomUsu) {
        this.nomUsu = nomUsu;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getEmail() {
        return email;
    }

    public Usuarios getUsu() {
        if (this.usu==null) {
            redireccionar("inicio.xhtml");
        }
        return usu;
    }

    public void setUsu(Usuarios usu) {
        this.usu = usu;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String esUsuario() {
        try {
            Utilidades utils_ = new Utilidades();
            String mail_    = this.email;
            String passwd_ = this.passwd;
            error="";
            if (mail_ != null && passwd_ != null) {
                List<Usuarios> usuarios_ = utils_.getCtrUsuarios().findUsuariosEntities();
                for(Usuarios o:usuarios_) {
                    Usuarios v = o;
                    if (v.getEmail().equals(mail_)) {
                        if (utils_.decrypt(v.getPassword()).equals(passwd_)) {
                            nomUsu=v.getNombre();
                            usu=v;
                            admin = usu.getPermiso()!=null && usu.getPermiso()!=0;
                            return "true";
                        } else {
                            return error = "Contraseña Errónea";
                        }
                    }
                }
            } else {
                return error="Email y Contraseña Incorrectos";
            }
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(beanLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return error="Email y Contraseña Incorrectos";
    }

    public String logout() {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        ((HttpSession) ctx.getSession(false)).invalidate();
        redireccionar("inicio.xhtml");
        return "logout";
    }
    
    private static void redireccionar (String url) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ctx.redirect(url);
        } catch (IOException ex) {}
    }
}

