package com.daw.atlanthis.DTO;

import com.daw.atlanthis.utils.Utilidades;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.NoSuchPaddingException;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "hilos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hilos.findAll", query = "SELECT h FROM Hilos h"),
    @NamedQuery(name = "Hilos.findByCodHilo", query = "SELECT h FROM Hilos h WHERE h.codHilo = :codHilo"),
    @NamedQuery(name = "Hilos.findByCodCategoria", query = "SELECT h FROM Hilos h WHERE h.codCategoria = :codCategoria"),
    @NamedQuery(name = "Hilos.findByCodSubcategoria", query = "SELECT h FROM Hilos h WHERE h.codSubcategoria = :codSubcategoria"),
    @NamedQuery(name = "Hilos.findByTitulo", query = "SELECT h FROM Hilos h WHERE h.titulo = :titulo"),
    @NamedQuery(name = "Hilos.findByFechaPubli", query = "SELECT h FROM Hilos h WHERE h.fechaPubli = :fechaPubli"),
    @NamedQuery(name = "Hilos.findByNVisitas", query = "SELECT h FROM Hilos h WHERE h.nVisitas = :nVisitas"),
    @NamedQuery(name = "Hilos.findByNRespuestas", query = "SELECT h FROM Hilos h WHERE h.nRespuestas = :nRespuestas"),
    @NamedQuery(name = "Hilos.findByCodUsuario", query = "SELECT h FROM Hilos h WHERE h.codUsuario = :codUsuario"),
    @NamedQuery(name = "Hilos.findByAnclado", query = "SELECT h FROM Hilos h WHERE h.anclado = :anclado")})
public class Hilos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_hilo")
    private Integer codHilo;
    @Column(name = "cod_categoria")
    private Integer codCategoria;
    @Column(name = "cod_subcategoria")
    private Integer codSubcategoria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_publi")
    @Temporal(TemporalType.DATE)
    private Date fechaPubli;
    @Basic(optional = false)
    @NotNull
    @Column(name = "n_visitas")
    private int nVisitas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "n_respuestas")
    private int nRespuestas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_usuario")
    private int codUsuario;
    @Column(name = "anclado")
    private Integer anclado;

    public Hilos() {
    }

    public Hilos(Integer codHilo) {
        this.codHilo = codHilo;
    }

    public Hilos(Integer codHilo, String titulo, Date fechaPubli, int nVisitas, int nRespuestas, int codUsuario) {
        this.codHilo = codHilo;
        this.titulo = titulo;
        this.fechaPubli = fechaPubli;
        this.nVisitas = nVisitas;
        this.nRespuestas = nRespuestas;
        this.codUsuario = codUsuario;
    }

    public Integer getCodHilo() {
        return codHilo;
    }

    public void setCodHilo(Integer codHilo) {
        this.codHilo = codHilo;
    }

    public Integer getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(Integer codCategoria) {
        this.codCategoria = codCategoria;
    }

    public Integer getCodSubcategoria() {
        return codSubcategoria;
    }

    public void setCodSubcategoria(Integer codSubcategoria) {
        this.codSubcategoria = codSubcategoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaPubli() {
        return fechaPubli;
    }

    public void setFechaPubli(Date fechaPubli) {
        this.fechaPubli = fechaPubli;
    }

    public int getNVisitas() {
        return nVisitas;
    }

    public void setNVisitas(int nVisitas) {
        this.nVisitas = nVisitas;
    }

    public int getNRespuestas() {
        return nRespuestas;
    }

    public void setNRespuestas(int nRespuestas) {
        this.nRespuestas = nRespuestas;
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public Integer getAnclado() {
        return anclado;
    }

    public void setAnclado(Integer anclado) {
        this.anclado = anclado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codHilo != null ? codHilo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hilos)) {
            return false;
        }
        Hilos other = (Hilos) object;
        return !((this.codHilo == null && other.codHilo != null) || (this.codHilo != null && !this.codHilo.equals(other.codHilo)));
    }

    @Override
    public String toString() {
        try {
            Utilidades utils_ = new Utilidades();
            Respuestas ultimaRespuesta = utils_.getCtrRespuestas().lastRespuesta(codHilo);
            int ultimoRespuestaCode;
            if (ultimaRespuesta==null) {
                ultimoRespuestaCode = 0;
            } else {
                ultimoRespuestaCode = ultimaRespuesta.getCodHilo();
            }
            return "{" +
                    "\"codHilo\":\"" + codHilo + '\"' +
                    ",\"codCategoria\":\"" +codCategoria+ '\"' +
                    ",\"codSubcategoria\":\"" + codSubcategoria + '\"' +
                    ",\"lastRespuesta\":\"" + ultimoRespuestaCode + '\"' +
                    ",\"titulo\":\"" + titulo + '\"' +
                    ",\"fechaPubli\":\"" + fechaPubli + '\"' +
                    ",\"nVisitas\":\"" + nVisitas + '\"' +
                    ",\"nRespuestas\":\"" + nRespuestas + '\"' +
                    ",\"codUsuario\":\"" + codUsuario + '\"' +
                    ",\"anclado\":\"" + anclado + '\"' +
                    '}';
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(Hilos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "null";
    }    
}
