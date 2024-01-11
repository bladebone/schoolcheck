package com.example.schoolcheck.user;

import com.example.schoolcheck.user.definition.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "tb_user")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String pwd;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String email;
}
