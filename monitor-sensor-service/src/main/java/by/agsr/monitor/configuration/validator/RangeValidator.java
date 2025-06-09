package by.agsr.monitor.configuration.validator;

import by.agsr.monitor.dto.RangeDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class RangeValidator implements ConstraintValidator<ValidRange, RangeDto> {

    private String message;

    @Override
    public boolean isValid(RangeDto rangeDto, ConstraintValidatorContext constraintValidatorContext) {
        Short from = rangeDto.getFrom();
        if (Objects.isNull(from)) {
            return true;
        }

        Short to = rangeDto.getTo();
        if (to <= from) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(message)
              .addConstraintViolation();
            return false;
        }
        return true;
    }

    @Override
    public void initialize(ValidRange constraintAnnotation) {
        message = constraintAnnotation.message();
    }
}
