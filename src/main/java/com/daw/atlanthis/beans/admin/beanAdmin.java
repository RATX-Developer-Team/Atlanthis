package com.daw.atlanthis.beans.admin;

import com.daw.atlanthis.DAO.exceptions.IllegalOrphanException;
import com.daw.atlanthis.DAO.exceptions.NonexistentEntityException;
import com.daw.atlanthis.DTO.Respuestas;
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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name="beanAdmin")
@SessionScoped 
public class beanAdmin {
    
    @ManagedProperty("#{beanLogin.usu}")
    private Usuarios user;
      /*Tablas Respaldo*/
    private HtmlDataTable tablaRespuestas;
    private HtmlDataTable tablaSubCategorias;
    private HtmlDataTable tablaCategorias;
    private HtmlDataTable tablaHilos;
    private HtmlDataTable tablaUsuarios;
    
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

    public HtmlDataTable getTablaRespuestas() {
        return tablaRespuestas;
    }

    public void setTablaRespuestas(HtmlDataTable tablaRespuestas) {
        this.tablaRespuestas = tablaRespuestas;
    }

    public HtmlDataTable getTablaSubCategorias() {
        return tablaSubCategorias;
    }

    public void setTablaSubCategorias(HtmlDataTable tablaSubCategorias) {
        this.tablaSubCategorias = tablaSubCategorias;
    }

    public HtmlDataTable getTablaCategorias() {
        return tablaCategorias;
    }

    public void setTablaCategorias(HtmlDataTable tablaCategorias) {
        this.tablaCategorias = tablaCategorias;
    }

    public HtmlDataTable getTablaHilos() {
        return tablaHilos;
    }

    public void setTablaHilos(HtmlDataTable tablaHilos) {
        this.tablaHilos = tablaHilos;
    }

    public HtmlDataTable getTablaUsuarios() {
        return tablaUsuarios;
    }

    public void setTablaUsuarios(HtmlDataTable tablaUsuarios) {
        this.tablaUsuarios = tablaUsuarios;
    }
    
    public String respuSolu(int v) {
        if (v==0) {
            return "NO";
        } else if (v==1){
            return "SI";
        }
        return "NO";
    }
    
    public String reponseUsu(int v) {
        try {
            final Utilidades utils_ = new Utilidades();
            return utils_.getCtrUsuarios().findById_(v).getNombre();
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException  ex) {
            Logger.getLogger(beanAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void eliminarRespu() {
        try {
            final Utilidades utils_ = new Utilidades();
            Respuestas o_ = (Respuestas) tablaRespuestas.getRowData();
            utils_.getCtrRespuestas().destroy(o_.getCodRespuesta());
        } catch (NonexistentEntityException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException  ex) {
            Logger.getLogger(beanAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void redireccionar (String url) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ctx.redirect(url);
        } catch (IOException ex) {}
    }
       
    public List<Respuestas> ctrRespu() {
        try {
            Utilidades utils_ = new Utilidades();
            return utils_.getCtrRespuestas().findRespuestasEntities();
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(beanAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
