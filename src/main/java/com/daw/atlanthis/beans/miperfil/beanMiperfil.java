package com.daw.atlanthis.beans.miperfil;

import com.daw.atlanthis.DTO.Usuarios;
import com.daw.atlanthis.utils.Utilidades;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.NoSuchPaddingException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.shaded.commons.io.IOUtils;

@ManagedBean(name="beanMiperfil")
@SessionScoped 
public class beanMiperfil {
    
    private Date fecha_naci;
    private String pais;
    private String nombre;
    private String apellidos;
    private UploadedFile file;
    @ManagedProperty("#{beanLogin.usu}")
    private Usuarios user;

    public Usuarios getUser() {
        return user;
    }

    public void setUser(Usuarios mail) {
        this.user = mail;
    }

    public Date getFecha_naci() {
        return fecha_naci;
    }

    public void setFecha_naci(Date fecha_naci) {
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

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile imagen) {
        this.file = imagen;
    }
    
    public void handleFileUpload(FileUploadEvent event) {
        try {
            UploadedFile file1 = event.getFile();
            FacesContext context = FacesContext.getCurrentInstance();
            ServletContext scontext = (ServletContext)context.getExternalContext().getContext();
            String rootpath = scontext.getRealPath("/");
            File fileImage=new File(rootpath+"resources"+File.separator+"img"+File.separator+file1.getFileName());
            InputStream inputStream=file1.getInputStream();
            SaveImage(inputStream,fileImage);
            FacesMessage message = new FacesMessage(rootpath);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (IOException ex) {
            Logger.getLogger(beanMiperfil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void guardar() {
        try {
            Utilidades utils_ = new Utilidades();
            Usuarios usu_ = user;
            usu_.setNombre(nombre);
            usu_.setApellidos(apellidos);
            usu_.setFechaNacimiento(fecha_naci);
            usu_.setImagen(file.getFileName());
            usu_.setPais(pais);
            utils_.getCtrUsuarios().edit(usu_);
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(beanMiperfil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(beanMiperfil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void SaveImage(InputStream inputStream, File ImageFile) throws IOException {
        OutputStream outputStream=new FileOutputStream(ImageFile);
        IOUtils.copy(inputStream, outputStream);
    }

}
