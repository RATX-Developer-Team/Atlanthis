package com.daw.atlanthis.DTO;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "respuestas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Respuestas.findAll", query = "SELECT r FROM Respuestas r"),
    @NamedQuery(name = "Respuestas.findByCodRespuesta", query = "SELECT r FROM Respuestas r WHERE r.codRespuesta = :codRespuesta"),
    @NamedQuery(name = "Respuestas.findByCodUsuario", query = "SELECT r FROM Respuestas r WHERE r.codUsuario = :codUsuario"),
    @NamedQuery(name = "Respuestas.findByCodHilo", query = "SELECT r FROM Respuestas r WHERE r.codHilo = :codHilo"),
    @NamedQuery(name = "Respuestas.findByFechaPublic", query = "SELECT r FROM Respuestas r WHERE r.fechaPublic = :fechaPublic"),
    @NamedQuery(name = "Respuestas.findByContenido", query = "SELECT r FROM Respuestas r WHERE r.contenido = :contenido"),
    @NamedQuery(name = "Respuestas.findByVotos", query = "SELECT r FROM Respuestas r WHERE r.votos = :votos"),
    @NamedQuery(name = "Respuestas.findBySolucion", query = "SELECT r FROM Respuestas r WHERE r.solucion = :solucion")})
public class Respuestas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_respuesta")
    private Integer codRespuesta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_usuario")
    private int codUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_hilo")
    private int codHilo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_public")
    @Temporal(TemporalType.DATE)
    private Date fechaPublic;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "contenido")
    private String contenido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "votos")
    private int votos;
    @Column(name = "solucion")
    private Integer solucion;

    public Respuestas() {
    }

    public Respuestas(Integer codRespuesta) {
        this.codRespuesta = codRespuesta;
    }

    public Respuestas(Integer codRespuesta, int codUsuario, int codHilo, Date fechaPublic, String contenido, int votos) {
        this.codRespuesta = codRespuesta;
        this.codUsuario = codUsuario;
        this.codHilo = codHilo;
        this.fechaPublic = fechaPublic;
        this.contenido = contenido;
        this.votos = votos;
    }

    public Integer getCodRespuesta() {
        return codRespuesta;
    }

    public void setCodRespuesta(Integer codRespuesta) {
        this.codRespuesta = codRespuesta;
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public int getCodHilo() {
        return codHilo;
    }

    public void setCodHilo(int codHilo) {
        this.codHilo = codHilo;
    }

    public Date getFechaPublic() {
        return fechaPublic;
    }

    public void setFechaPublic(Date fechaPublic) {
        this.fechaPublic = fechaPublic;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getVotos() {
        return votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }

    public Integer getSolucion() {
        return solucion;
    }

    public void setSolucion(Integer solucion) {
        this.solucion = solucion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codRespuesta != null ? codRespuesta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Respuestas)) {
            return false;
        }
        Respuestas other = (Respuestas) object;
        return !((this.codRespuesta == null && other.codRespuesta != null) || (this.codRespuesta != null && !this.codRespuesta.equals(other.codRespuesta)));
    }

    @Override
    public String toString() {
        return "com.daw.atlanthis.DTO.Respuestas[ codRespuesta=" + codRespuesta + " ]";
    }

}
