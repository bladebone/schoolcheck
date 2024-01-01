package com.example.schoolcheck.testdomain;

import com.example.schoolcheck.testdomain.definition.TestDomainType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
//@Entity
public class TestDomain {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TestDomainType testDomainType;

    @Column(length = 100)
    private String word;
}
