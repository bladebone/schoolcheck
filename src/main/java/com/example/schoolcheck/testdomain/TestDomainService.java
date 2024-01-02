package com.example.schoolcheck.testdomain;

import com.example.schoolcheck.testdomain.dto.TestDomainGetDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestDomainService {

//    private final TestDomainRepository testDomainRepository;

    public TestDomain get(TestDomainGetDto testDomainGetDto) {
        return testDomainGetDto.toTestDomain();
    }
}
