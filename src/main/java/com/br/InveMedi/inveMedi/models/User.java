
package com.br.InveMedi.inveMedi.models;

import com.br.InveMedi.inveMedi.models.enums.Cargo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = User.TABLE_NAME,
        uniqueConstraints = @UniqueConstraint(columnNames = "email")
)
public class User {


    public interface CreateUser {
    }

    public interface UpdateUser {
    }


    public static final String TABLE_NAME = "usuario";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "username", length = 100, nullable = false, unique = true)
    @NotNull(groups = CreateUser.class)
    @NotEmpty(groups = CreateUser.class)
    @Size(groups = CreateUser.class, min = 2, max = 100)
    private String userName;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    @NotNull(groups = {CreateUser.class, UpdateUser.class})
    @NotEmpty(groups = {CreateUser.class, UpdateUser.class})
    @Size(groups = CreateUser.class, min = 2, max = 100)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", length = 60, nullable = false)
    @NotNull(groups = {CreateUser.class, UpdateUser.class})
    @NotEmpty(groups = {CreateUser.class, UpdateUser.class})
    @Size(groups = {CreateUser.class, UpdateUser.class}, min = 6, max = 60)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "cargo", nullable = false)
    private Cargo cargo;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public @NotNull(groups = CreateUser.class) @NotEmpty(groups = CreateUser.class) @Size(groups = CreateUser.class, min = 2, max = 100) String getUserName() {
        return userName;
    }


    public @NotNull(groups = {CreateUser.class, UpdateUser.class}) @NotEmpty(groups = {CreateUser.class, UpdateUser.class}) @Size(groups = CreateUser.class, min = 2, max = 100) String getEmail() {
        return email;
    }

    public void setEmail(@NotNull(groups = {CreateUser.class, UpdateUser.class}) @NotEmpty(groups = {CreateUser.class, UpdateUser.class}) @Size(groups = CreateUser.class, min = 2, max = 100) String email) {
        this.email = email;
    }

    public @NotNull(groups = {CreateUser.class, UpdateUser.class}) @NotEmpty(groups = {CreateUser.class, UpdateUser.class}) @Size(groups = {CreateUser.class, UpdateUser.class}, min = 6, max = 60) String getPassword() {
        return password;
    }

    public void setPassword(@NotNull(groups = {CreateUser.class, UpdateUser.class}) @NotEmpty(groups = {CreateUser.class, UpdateUser.class}) @Size(groups = {CreateUser.class, UpdateUser.class}, min = 6, max = 60) String password) {
        this.password = password;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Comparação rápida de referência
        if (o == null || getClass() != o.getClass()) return false; // Verifique se o objeto é nulo ou se não é do mesmo tipo

        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(email, user.email); // Inclui o campo `email`
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

}
