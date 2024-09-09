package com.hayan.fintech.aop;

import com.hayan.fintech.common.annotation.Encrypt;
import com.hayan.fintech.domain.ProductInfo;
import com.hayan.fintech.domain.UserInfo;
import com.hayan.fintech.util.EncryptionUtil;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Optional;

@Aspect
@Component
@RequiredArgsConstructor
public class EncryptionAspect {

    private final EncryptionUtil encryptionUtil;

    @Around("execution(* com.hayan.fintech.repository.*.save(..)) && args(entity)")
    public Object encryptFields(ProceedingJoinPoint joinPoint, Object entity) throws Throwable {
        handleFields(entity, true);
        return joinPoint.proceed();
    }

    @Around("execution(* com.hayan.fintech.repository.*.find*(..))")
    public Object decryptFields(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();

        if (result instanceof Optional) {
            Optional<?> optionalResult = (Optional<?>) result;
            if (optionalResult.isPresent()) {
                Object entity = optionalResult.get();
                handleFields(entity, false);
            }
        } else if (result != null) {
            handleFields(result, false);
        }

        return result;
    }

    @Around("execution(* com.hayan.fintech.repository.*.exists*(..)) && args(value)")
    public Object encryptValueForExists(ProceedingJoinPoint joinPoint, String value) throws Throwable {
        String key = getEncryptionKeyForMethod(joinPoint);
        String encryptedValue = encryptionUtil.encrypt(value, key);

        return joinPoint.proceed(new Object[]{encryptedValue});
    }

    private void handleFields(Object entity, boolean needsEncryption) throws Exception {
        Field[] fields = entity.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Encrypt.class)) {
                field.setAccessible(true);
                String value = (String) field.get(entity);
                if (value != null) {
                    String key = getEncryptionKeyForEntity(entity);

                    field.set(entity, needsEncryption ? encryptionUtil.encrypt(value, key) : encryptionUtil.decrypt(value, key));
                }
            }
        }
    }

    private String getEncryptionKeyForEntity(Object entity) {
        if (entity instanceof UserInfo) {
            return "user-specific-key";
        } else if (entity instanceof ProductInfo) {
            return "product-specific-key";
        }

        return "default-key";
    }

    private String getEncryptionKeyForMethod(ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();

        if (methodName.contains("User")) {
            return "user-specific-key";
        } else if (methodName.contains("Product")) {
            return "product-specific-key";
        }

        return "default-key";
    }

}
