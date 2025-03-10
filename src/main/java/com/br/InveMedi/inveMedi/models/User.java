
package com.br.InveMedi.inveMedi.models;

import com.br.InveMedi.inveMedi.models.enums.ProfileEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.apache.commons.lang3.builder.HashCodeExclude;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = User.TABLE_NAME,
        uniqueConstraints = @UniqueConstraint(columnNames = "email")
)


@NoArgsConstructor
@EqualsAndHashCode
@Data
public class User {




    public static final String TABLE_NAME = "usuario";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "username", length = 100, nullable = false, unique = true)
    @Size( min = 2, max = 100)
    @NotBlank
    private String username;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    @Size(min = 2, max = 100)
    @NotBlank
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", length = 60, nullable = false)
    @Size( min = 6, max = 60)
    @NotBlank
    private String password;


    @ElementCollection(fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @CollectionTable(name = "user_profiles")
    @Column(name = "profile", nullable = false)
    private Set<Integer> profiles = new HashSet<>();


    public Set<ProfileEnum> getProfiles() {
        return this.profiles.stream()
                .map(ProfileEnum::toEnum)
                .collect(Collectors.toSet());
    }

    public void addProfile(ProfileEnum profileEnum){
        profiles.add(profileEnum.getCode());
    }



}
