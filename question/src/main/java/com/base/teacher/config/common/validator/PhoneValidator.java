package com.base.teacher.config.common.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.base.teacher.config.common.annotation.IsPhone;



public class PhoneValidator implements ConstraintValidator<IsPhone, Long> {
	 
    private Pattern pattern = Pattern.compile("1(([38]\\d)|(5[^4&&\\d])|(4[579])|(7[0135678]))\\d{8}");
 
    @Override
    public void initialize(IsPhone phone) {
    }
 
    @Override
    public boolean isValid(Long value, ConstraintValidatorContext constraintValidatorContext) {
        return pattern.matcher(String.valueOf(value)).matches();
    }
}
