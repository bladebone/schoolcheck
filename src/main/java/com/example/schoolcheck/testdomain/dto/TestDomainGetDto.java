package com.example.schoolcheck.testdomain.dto;

import com.example.schoolcheck.testdomain.TestDomain;
import com.example.schoolcheck.testdomain.definition.TestDomainType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TestDomainGetDto {

    private final Long domainId;
    private final TestDomainType type;
    private final String word;

    public TestDomain toTestDomain() {
        return new TestDomain(this.getDomainId(), this.getType(), this.getWord());
    }
}
