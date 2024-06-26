package com.gadashov.instagram.model.entity;

import com.gadashov.instagram.model.enums.PrivacyType;
import com.gadashov.instagram.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/9/2024
 * Time: 8:31 PM
 */

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseModel implements UserDetails {

    private String name;

    private String username;

    private String password;

    private String passwordHash;

    private String phoneNumber;

    private String email;

    private LocalDate birthdate;

    private Integer postCount;

    private Integer followerCount;

    private Integer followingCount;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private PrivacyType privacy = PrivacyType.PUBLIC;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private ConfirmationToken confirmationToken;

    //Security
    @Builder.Default
    private boolean accountNonExpired = true;
    @Builder.Default
    private boolean isEnabled = false;
    @Builder.Default
    private boolean accountNonLocked = true;
    @Builder.Default
    private boolean credentialsNonExpired = true;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @JoinTable(name = "authorities", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Set<Role> authorities = new HashSet<>(Set.of(Role.USER));

    @Override
    public String toString() {
        return "User{" +
                "id=" + super.getId() +
                "name='" + this.getName() + '\'' +
                ", username='" + this.getUsername() + '\'' +
                ", phoneNumber='" + this.getPhoneNumber() + '\'' +
                ", email='" + this.getEmail() + '\'' +
                ", birthdate=" + this.getBirthdate() +
                ", postCount=" + this.getPostCount() +
                ", followerCount=" + this.getFollowerCount() +
                ", followingCount=" + this.getFollowingCount() +
                ", privacy=" + this.getPrivacy() +
                ", status=" + super.getStatus() +
                ", createdAt=" + super.getCreatedAt() +
                ", updatedAt=" + super.getUpdatedAt() +
                '}';
    }
}
