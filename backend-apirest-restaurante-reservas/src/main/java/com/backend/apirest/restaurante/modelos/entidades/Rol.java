package com.backend.apirest.restaurante.modelos.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="roles")
public class Rol {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name="nombre", unique=true)
        private String nombre;

        public Rol() {
        }
        public Rol(String nombre) {
            this.nombre = nombre;
        }


}
