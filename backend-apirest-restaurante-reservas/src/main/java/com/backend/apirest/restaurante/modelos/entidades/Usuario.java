package com.backend.apirest.restaurante.modelos.entidades;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "usuarios")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(min=2, max=20)
    @Column(name = "nombres")
    private String nombres;

    @NotNull
    @Email
    @Size(min=2, max=60)
    @Column(name = "email", unique=true)
    private String email;

    @Column(name = "clave")
    private String clave;

    @NotEmpty
    @Size(min=2, max=100)
    @Column(name = "apellidos")
    private String apellidos;

    @NotEmpty
    @Size(min=2, max=10)
    @Column(name = "tipo_documento")
    private String tipo_documento;

    @NotEmpty
    @Size(min=2, max=20)
    @Column(name = "identificacion")
    private String identificacion;
    
    @Column(name = "activo")
    private boolean activo = true;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean admin;


    @ManyToMany
    @JoinTable(
            name = "usuario_roles",
            joinColumns = @JoinColumn(name="usuario_id"),
            inverseJoinColumns = @JoinColumn(name="rol_id"),
            uniqueConstraints = { @UniqueConstraint(columnNames = {"usuario_id", "rol_id"})}
    )
    private List<Rol> roles;

    @PrePersist
    public void prePersist(){
        activo = true;
    }
}
