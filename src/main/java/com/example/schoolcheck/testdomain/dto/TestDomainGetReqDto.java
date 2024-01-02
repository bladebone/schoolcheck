package com.example.schoolcheck.testdomain.dto;

import com.example.schoolcheck.testdomain.definition.TestDomainType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TestDomainGetReqDto {

    private TestDomainType type;
    private String word;

    public TestDomainGetDto toTestDomainGetDto(Long domainId) {
        return new TestDomainGetDto(domainId, this.getType(), this.getWord());
    }
}
