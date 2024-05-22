package com.example.hms.model;
import com.example.hms.model.role.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import java.util.Collection;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder(toBuilder = true)
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @GenericGenerator(name="uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "uuid", updatable = false, nullable = false, unique = true)
    private UUID uuid = UUID.randomUUID();

    @Column(length = 100)
    private String name;

    @Column(unique = true, length = 50, nullable = false)
    private String username;

    @Column(name = "email", unique = true)
    private String email;

    private String password;

    @Column(name = "enabled", nullable = false, columnDefinition = "boolean default false")
    private boolean enabled;

    @Column(name = "is_email_verified", nullable = false, columnDefinition = "boolean default false")
    private boolean isEmailVerified;

    @Column(name = "token_expired", nullable = false, columnDefinition = "boolean default true")
    private boolean tokenExpired;

    @Column(name = "user_type", length = 100)
    private String userType;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(
                    name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(
                    name = "role_id", referencedColumnName = "id")})
    private Collection<Role> roles;

    private String deviceToken;
}