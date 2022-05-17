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
@Table(name = "subcategorias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subcategorias.findAll", query = "SELECT s FROM Subcategorias s"),
    @NamedQuery(name = "Subcategorias.findByCodSubcategoria", query = "SELECT s FROM Subcategorias s WHERE s.codSubcategoria = :codSubcategoria"),
    @NamedQuery(name = "Subcategorias.findByCodCategoria", query = "SELECT s FROM Subcategorias s WHERE s.codCategoria = :codCategoria"),
    @NamedQuery(name = "Subcategorias.findByTitulo", query = "SELECT s FROM Subcategorias s WHERE s.titulo = :titulo"),
    @NamedQuery(name = "Subcategorias.findByDescripcion", query = "SELECT s FROM Subcategorias s WHERE s.descripcion = :descripcion"),
    @NamedQuery(name = "Subcategorias.findByAnclado", query = "SELECT s FROM Subcategorias s WHERE s.anclado = :anclado")})
public class Subcategorias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_subcategoria")
    private Integer codSubcategoria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_categoria")
    private int codCategoria;
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

    public Subcategorias() {
    }

    public Subcategorias(Integer codSubcategoria) {
        this.codSubcategoria = codSubcategoria;
    }

    public Subcategorias(Integer codSubcategoria, int codCategoria, String titulo, String descripcion) {
        this.codSubcategoria = codSubcategoria;
        this.codCategoria = codCategoria;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public Integer getCodSubcategoria() {
        return codSubcategoria;
    }

    public void setCodSubcategoria(Integer codSubcategoria) {
        this.codSubcategoria = codSubcategoria;
    }

    public int getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(int codCategoria) {
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
        hash += (codSubcategoria != null ? codSubcategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subcategorias)) {
            return false;
        }
        Subcategorias other = (Subcategorias) object;
        return !((this.codSubcategoria == null && other.codSubcategoria != null) || (this.codSubcategoria != null && !this.codSubcategoria.equals(other.codSubcategoria)));
    }

    @Override
    public String toString() {
        return "com.daw.atlanthis.DTO.Subcategorias[ codSubcategoria=" + codSubcategoria + " ]";
    }

}
