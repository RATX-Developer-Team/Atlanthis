package com.daw.atlanthis.beans.register;

import com.daw.atlanthis.DTO.Newsletter;
import com.daw.atlanthis.DTO.Usuarios;
import com.daw.atlanthis.utils.EmailUtils;
import com.daw.atlanthis.utils.Utilidades;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.NoSuchPaddingException;
import javax.faces.context.FacesContext;

public class beanRegister {
    
    private String email;
    private String fecha_naci;
    private String pais;
    private String nombre;
    private String apellidos;
    private String passwd;
    private String emailNews;
    
    public beanRegister() {
    }

    public String getEmail() {
        return email;
    }

    public String getEmailNews() {
        return emailNews;
    }

    public void setEmailNews(String emailNews) {
        this.emailNews = emailNews;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFecha_naci() {
        return fecha_naci;
    }

    public void setFecha_naci(String fecha_naci) {
        this.fecha_naci = fecha_naci;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    
    public void guardarMail() {
        try {
            Utilidades utils_ = new Utilidades();
            Newsletter newletter = new Newsletter(null, emailNews);
            utils_.getCtrNewsletter().create(newletter);
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(beanRegister.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
    
    public String guardarUsuario() {
        try {
            Utilidades utils_ = new Utilidades();
            SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
            Date newDate = formatDate.parse(fecha_naci);
            Usuarios v = new Usuarios(
                    email,
                    utils_.lastId()+1,
                    newDate,
                    pais,
                    nombre,
                    apellidos,
                    utils_.encrypt(passwd),
                    "default.png"
            );
            utils_.getCtrUsuarios().create(v);
            String motivo = "Bienvenido a Atlanthis";
            String contenido;
            String root = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
            try(BufferedReader br = new BufferedReader(new FileReader(root+"email.html"))) {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();
                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                contenido = sb.toString();
            }
            EmailUtils.enviarMail(utils_.getSTMP_host(), utils_.getSTMP_port(), utils_.getSTMP_user(), utils_.getSTMP_pass(), email, motivo, contenido);
            return "true";
        } catch (ParseException ex) {
            Logger.getLogger(beanRegister.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(beanRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "volver";
    }
}
