package com.base.teacher.config.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

import com.base.teacher.config.common.annotation.StringPattern;



public class StringPatternValidator implements ConstraintValidator<StringPattern, String> {
    private String pattern;
    private String message;
    
    @Override
    public void initialize(StringPattern constraintAnnotation) {
        this.pattern = constraintAnnotation.regexp();
        this.message = constraintAnnotation.message();
    }
    @Override
    public boolean isValid(String str, ConstraintValidatorContext constraintContext) {
        if (str == null) {
            return true;
        }
        String errorString = null;
        boolean isValid = true;
        if (pattern == null || "".equals(pattern.trim())) {
            isValid = false;
            if (!isValid) {
                constraintContext.disableDefaultConstraintViolation();
                constraintContext.buildConstraintViolationWithTemplate("指定的正则表达式为空")
                        .addConstraintViolation();
            }
            return isValid;
        } else {
//            Iterator<String> iter = list.iterator();
//            while (iter.hasNext()) {
//                String string = iter.next();
                if (!str.matches(pattern)) {
                    isValid = false;
                    errorString = str;
//                    break;
                }
//            }
        }
        if (!isValid) {
            constraintContext.disableDefaultConstraintViolation();
            String message = this.message;
            if (StringUtils.isEmpty(message)) {
                message = "List<String>为空，或者其中的" + errorString + "不符合正则表达式：" + pattern;;
            }
            constraintContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        }
        return isValid;
    }

}
