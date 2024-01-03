package com.example.schoolcheck.testdomain.dto;

import com.example.schoolcheck.testdomain.TestDomain;
import com.example.schoolcheck.testdomain.definition.TestDomainType;
import lombok.Getter;

@Getter
public class TestDomainGetResDto {

    private final Long domainId;
    private final TestDomainType type;
    private final String word;

    public TestDomainGetResDto(TestDomain testDomain) {
        // .getId()를 사용하는 이유는 뭘까..?
        this.domainId = testDomain.getId();
        this.type = testDomain.getTestDomainType();
        this.word = testDomain.getWord();
    }
}
