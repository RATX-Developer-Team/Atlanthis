package com.daw.atlanthis.beans.admin;

import com.daw.atlanthis.DAO.exceptions.NonexistentEntityException;
import com.daw.atlanthis.DTO.Categorias;
import com.daw.atlanthis.DTO.Hilos;
import com.daw.atlanthis.DTO.Respuestas;
import com.daw.atlanthis.DTO.Subcategorias;
import com.daw.atlanthis.DTO.Usuarios;
import com.daw.atlanthis.utils.Utilidades;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
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
import javax.faces.model.SelectItem;
import static javax.mail.Part.ATTACHMENT;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

@ManagedBean(name="beanAdmin")
@SessionScoped 
public class beanAdmin {
    
    @ManagedProperty("#{beanLogin.usu}")
    private Usuarios user;
    private String newCategoriaNombre;
    private int newSubcategoriaCategoria;
    private String newSubcategoriaNombre;
    private String newSubcategoriaDescrip;
    private ArrayList listaCategorias;
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

    public String getNewCategoriaNombre() {
        return newCategoriaNombre;
    }

    public void setNewCategoriaNombre(String newCategoriaNombre) {
        this.newCategoriaNombre = newCategoriaNombre;
    }

    public String getNewSubcategoriaDescrip() {
        return newSubcategoriaDescrip;
    }

    public void setNewSubcategoriaDescrip(String newSubcategoriaDescrip) {
        this.newSubcategoriaDescrip = newSubcategoriaDescrip;
    }

    public int getNewSubcategoriaCategoria() {
        return newSubcategoriaCategoria;
    }

    public ArrayList getListaCategorias() {
        try {
            final Utilidades utils_ = new Utilidades();
            listaCategorias = new ArrayList();
            for(Categorias o : utils_.getCtrCategorias().findCategoriasEntities()) {
                listaCategorias.add(new SelectItem(o.getCodCategoria(),o.getTitulo()));
            }
            return listaCategorias;
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException  ex) {
            Logger.getLogger(beanAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaCategorias;
    }

    public void setListaCategorias(ArrayList listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public void setNewSubcategoriaCategoria(int newSubcategoriaCategoria) {
        this.newSubcategoriaCategoria = newSubcategoriaCategoria;
    }

    public String getNewSubcategoriaNombre() {
        return newSubcategoriaNombre;
    }

    public void setNewSubcategoriaNombre(String newSubcategoriaNombre) {
        this.newSubcategoriaNombre = newSubcategoriaNombre;
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
    
    private static void redireccionar (String url) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ctx.redirect(url);
        } catch (IOException ex) {}
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
    
    public void eliminarHilos() {
        try {
            final Utilidades utils_ = new Utilidades();
            Hilos o_ = (Hilos) tablaHilos.getRowData();
            for (Respuestas x:utils_.getCtrRespuestas().findRespuestasEntities()) {
                if (x.getCodHilo()==o_.getCodHilo()) {
                    utils_.getCtrRespuestas().destroy(x.getCodRespuesta());
                }
            }
            utils_.getCtrHilos().destroy(o_.getCodHilo());
        } catch (NonexistentEntityException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException  ex) {
            Logger.getLogger(beanAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    public void cerrarHilos() {
        try {
            final Utilidades utils_ = new Utilidades();
            Hilos o_ = (Hilos) tablaHilos.getRowData();
            if(o_.getCerrado()==0) {
                o_.setCerrado(1);
            } else {
                o_.setCerrado(0);
            }
            utils_.getCtrHilos().edit(o_);
        } catch (NonexistentEntityException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException  ex) {
            Logger.getLogger(beanAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(beanAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void anclarHilos() {
        try {
            final Utilidades utils_ = new Utilidades();
            Hilos o_ = (Hilos) tablaHilos.getRowData();
            if(o_.getAnclado()!=0) {
                o_.setAnclado(0);
            } else {
                o_.setAnclado(1);
            }
            utils_.getCtrHilos().edit(o_);
        } catch (NonexistentEntityException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException  ex) {
            Logger.getLogger(beanAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(beanAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarCategorias() {
        try {
            final Utilidades utils_ = new Utilidades();
            Categorias o_ = (Categorias) tablaCategorias.getRowData();
            utils_.getCtrCategorias().destroy(o_.getCodCategoria());
        } catch (NonexistentEntityException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException  ex) {
            Logger.getLogger(beanAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public void eliminarSubcategorias() {
        try {
            final Utilidades utils_ = new Utilidades();
            Subcategorias o_ = (Subcategorias) tablaSubCategorias.getRowData();
            utils_.getCtrSubcategorias().destroy(o_.getCodSubcategoria());
        } catch (NonexistentEntityException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException  ex) {
            Logger.getLogger(beanAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
    public void eliminarUsuarios() {
        try {
            final Utilidades utils_ = new Utilidades();
            Usuarios o_ = (Usuarios) tablaUsuarios.getRowData();
            utils_.getCtrUsuarios().destroy(o_.getEmail());
        } catch (NonexistentEntityException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException  ex) {
            Logger.getLogger(beanAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String calculaPermiso(int v) {
        switch (v) {
            case -1:
                return "Usuario";
            case 0:
                return "Usuario";
            case 1:
                return "Soporte";
            case 2:
                return "Moderador";
            case 3:
                return "Administrador";
            default:
                break;
        }
        return "null";
    }
    
    public void subirRango() {
        try {
            final Utilidades utils_ = new Utilidades();
            Usuarios o_ = (Usuarios) tablaUsuarios.getRowData();
            if (o_.getPermiso()==null) {
                o_.setPermiso(0);
            }
            o_.setPermiso(o_.getPermiso()+1);
            utils_.getCtrUsuarios().edit(o_);
        } catch (NonexistentEntityException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException  ex) {
            Logger.getLogger(beanAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(beanAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void bajarRango() {
        try {
            final Utilidades utils_ = new Utilidades();
            Usuarios o_ = (Usuarios) tablaUsuarios.getRowData();
            o_.setPermiso(o_.getPermiso()-1);
            utils_.getCtrUsuarios().edit(o_);
        } catch (NonexistentEntityException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException  ex) {
            Logger.getLogger(beanAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(beanAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    public List<Hilos> ctrHilos() {
        try {
            Utilidades utils_ = new Utilidades();
            return utils_.getCtrHilos().findHilosEntities();
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(beanAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Categorias> ctrCategorias() {
        try {
            Utilidades utils_ = new Utilidades();
            return utils_.getCtrCategorias().findCategoriasEntities();
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(beanAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
        
    public List<Subcategorias> ctrSubcategorias() {
        try {
            Utilidades utils_ = new Utilidades();
            return utils_.getCtrSubcategorias().findSubcategoriasEntities();
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(beanAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
            
    public List<Usuarios> ctrUsuarios() {
        try {
            Utilidades utils_ = new Utilidades();
            return utils_.getCtrUsuarios().findUsuariosEntities();
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(beanAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void guardarNewCategoria() {
        try {
            Utilidades utils_ = new Utilidades();
            Categorias v = new Categorias(
                null,
                newCategoriaNombre,
                ""
            );
            utils_.getCtrCategorias().create(v);
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(beanAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void guardarNewSubcategoria() {
        try {
            Utilidades utils_ = new Utilidades();
            Subcategorias v = new Subcategorias(
                null,
                newSubcategoriaCategoria,
                newSubcategoriaNombre,
                newSubcategoriaDescrip
            );
            utils_.getCtrSubcategorias().create(v);
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(beanAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void crearPDFusu() {
        try {
            Utilidades utils_ = new Utilidades();
            Document document = new Document(PageSize.A4);
            
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
            response.setContentType("application/pdf");
            
            response.setHeader("Content-Disposition", "attachment; filename=usuarios.pdf");
            PdfWriter pdfWriter = PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
            
            Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
                    Font.BOLD);
            for(Usuarios x:utils_.getCtrUsuarios().findUsuariosEntities()) {
                document.add(new Paragraph(x.getEmail(), catFont));
            }
            document.close();
            
            response.setContentLength(byteArrayOutputStream.size());
            ServletOutputStream servletOutputStream = response.getOutputStream();
            byteArrayOutputStream.writeTo(servletOutputStream);
            byteArrayOutputStream.flush();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (DocumentException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(beanAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(beanAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
}
