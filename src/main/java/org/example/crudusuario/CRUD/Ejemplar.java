package org.example.crudusuario.CRUD;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "ejemplar")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Ignorar los proxies de Hibernate
public class Ejemplar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "isbn", nullable = false)
    @JsonBackReference
    private org.example.crudusuario.CRUD.Libro isbn;

    @ColumnDefault("'Disponible'")
    @Lob
    @Column(name = "estado")
    private String estado;

    @OneToMany(mappedBy = "ejemplar")
    @JsonBackReference
    private Set<org.example.crudusuario.CRUD.Prestamo> prestamos = new LinkedHashSet<>();

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public org.example.crudusuario.CRUD.Libro getIsbn() {
        return isbn;
    }

    public void setIsbn(org.example.crudusuario.CRUD.Libro isbn) {
        this.isbn = isbn;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Set<org.example.crudusuario.CRUD.Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(Set<org.example.crudusuario.CRUD.Prestamo> prestamos) {
        this.prestamos = prestamos;
    }
}
