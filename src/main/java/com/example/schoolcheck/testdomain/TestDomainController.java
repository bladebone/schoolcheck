package com.example.schoolcheck.testdomain;

import com.example.schoolcheck.common.exception.ValidationIllegalArgumentException;
import com.example.schoolcheck.testdomain.dto.TestDomainGetReqDto;
import com.example.schoolcheck.testdomain.dto.TestDomainGetResDto;
import com.example.schoolcheck.testdomain.validator.TestDomainGetReqDtoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestDomainController {

    private final TestDomainService testDomainService;
    private final TestDomainGetReqDtoValidator testDomainGetReqDtoValidator;

    @GetMapping("/test-domain/{domainId}")
    public ResponseEntity<TestDomainGetResDto> getTestDomain(
            @PathVariable Long domainId,
            @RequestParam TestDomainGetReqDto reqDto,
            BindingResult bindingResult) {

        testDomainGetReqDtoValidator.validate(reqDto, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new ValidationIllegalArgumentException(bindingResult);
        }

        TestDomain testDomain = testDomainService.get(reqDto.toTestDomainGetDto(domainId));
        return ResponseEntity.ok(new TestDomainGetResDto(testDomain));
    }
}
