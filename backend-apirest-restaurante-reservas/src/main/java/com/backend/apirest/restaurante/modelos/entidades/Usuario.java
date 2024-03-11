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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(min=2, max=20)
    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    @NotNull
    @Email
    @Size(min=2, max=60)
    @Column(name = "email", unique=true)
    private String email;

    @NotNull
    @NotBlank
    @Size(min=5, max=60)
    @Column(name = "clave")
    private String clave;
    
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
