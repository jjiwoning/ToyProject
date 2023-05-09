package jiwon.board.valid;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) // 어노테이션을 적용할 위치 -> 필드로 지정 (변수에 사용하기 때문에)
@Retention(RetentionPolicy.RUNTIME) // 어노테이션의 유지 범위 -> 런타임
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface PhoneNumber {

    String message() default "유효하지 않은 번호입니다.";
}
