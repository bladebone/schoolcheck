package com.example.schoolcheck.testdomain.validator;

import com.example.schoolcheck.testdomain.dto.TestDomainGetReqDto;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TestDomainGetReqDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return TestDomainGetReqDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        TestDomainGetReqDto dto = (TestDomainGetReqDto) target;

        if (null == dto.getType()) {
            errors.rejectValue("type", "MP001", "type을 확인하세요.");
        }

        if (!StringUtils.hasLength(dto.getWord())) {
            errors.rejectValue("word", "MP002", "word을 확인하세요.");
        }
    }
}
