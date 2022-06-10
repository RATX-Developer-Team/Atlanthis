package com.daw.atlanthis.DTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "categorias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categorias.findAll", query = "SELECT c FROM Categorias c"),
    @NamedQuery(name = "Categorias.findByCodCategoria", query = "SELECT c FROM Categorias c WHERE c.codCategoria = :codCategoria"),
    @NamedQuery(name = "Categorias.findByTitulo", query = "SELECT c FROM Categorias c WHERE c.titulo = :titulo"),
    @NamedQuery(name = "Categorias.findByDescripcion", query = "SELECT c FROM Categorias c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Categorias.findByAnclado", query = "SELECT c FROM Categorias c WHERE c.anclado = :anclado")})
public class Categorias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_categoria")
    private Integer codCategoria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "anclado")
    private Integer anclado;

    public Categorias() {
    }

    public Categorias(Integer codCategoria) {
        this.codCategoria = codCategoria;
    }

    public Categorias(Integer codCategoria, String titulo, String descripcion) {
        this.codCategoria = codCategoria;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public Integer getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(Integer codCategoria) {
        this.codCategoria = codCategoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (codCategoria != null ? codCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categorias)) {
            return false;
        }
        Categorias other = (Categorias) object;
        return !((this.codCategoria == null && other.codCategoria != null) || (this.codCategoria != null && !this.codCategoria.equals(other.codCategoria)));
    }

    @Override
    public String toString() {
        return "{" +
                "\"codCategoria\":\"" + codCategoria + '\"' +
                ",\"titulo\":\"" +titulo+ '\"' +
                ",\"descripcion\":\"" + descripcion + '\"' +
                ",\"anclado\":\"" + anclado + '\"' +
                '}';
    }
}
