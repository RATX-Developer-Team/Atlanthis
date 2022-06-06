package com.daw.atlanthis.beans.subcategoria;

import com.daw.atlanthis.DTO.Hilos;
import com.daw.atlanthis.DTO.Respuestas;
import com.daw.atlanthis.DTO.Subcategorias;
import com.daw.atlanthis.utils.Utilidades;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.NoSuchPaddingException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name="beanSubcategoria")
@SessionScoped 
public class beanSubcategoria {
    
    private String titulo;
    private String contenidoPrincipal;
    @ManagedProperty("#{beanLogin.email}")
    private String mail;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
    public String getContenidoPrincipal() {
        return contenidoPrincipal;
    }

    public void setContenidoPrincipal(String contenidoPrincipal) {
        this.contenidoPrincipal = contenidoPrincipal;
    }
    
    public void guardarHilo() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String idSubcate = request.getParameter("codigoSubcategoria");
        if (idSubcate!=null) {
            try {
                Utilidades utils_ = new Utilidades();
                Subcategorias subcate_ = utils_.getCtrSubcategorias().findSubcategorias(Integer.parseInt(idSubcate));
                Date fecha = new Date();
                int hiloID = utils_.lastId_hilos()+1;
                Hilos newHilo = new Hilos(
                        hiloID,
                        subcate_.getCodCategoria(),
                        subcate_.getCodSubcategoria(),
                        titulo,
                        fecha,
                        0,
                        0,
                        utils_.getCtrUsuarios().findUsuarios(mail).getCodUsuario()
                );
                utils_.getCtrHilos().create(newHilo);
                
                Respuestas respuesta_ = new Respuestas(
                        null,
                        utils_.getCtrUsuarios().findUsuarios(mail).getCodUsuario(),
                        hiloID,
                        fecha,
                        contenidoPrincipal,
                        0
                );
                utils_.getCtrRespuestas().create(respuesta_);
            } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException ex) {
                Logger.getLogger(beanSubcategoria.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
 
}
