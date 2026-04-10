package edu.naukma.util;

import edu.naukma.annotations.NotNullOrEmpty;
import edu.naukma.annotations.ValidPhone;

import java.lang.reflect.Field;

public class ValidationUtil {
    public static void validate(Object obj) {
        if (obj == null) return;
        Class<?> clazz = obj.getClass();

        while (clazz != null && clazz != Object.class) {
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    Object value = field.get(obj);

                    if (field.isAnnotationPresent(NotNullOrEmpty.class)) {
                        if (value == null || value.toString().trim().isEmpty()) {
                            throw new IllegalArgumentException(field.getAnnotation(NotNullOrEmpty.class).message() + ": " + field.getName());
                        }
                    }

                    if (field.isAnnotationPresent(ValidPhone.class)) {
                        if (value != null) {
                            String phone = value.toString();
                            if (!phone.matches("\\+?\\d{10,13}")) {
                                throw new IllegalArgumentException(field.getAnnotation(ValidPhone.class).message() + ": " + phone);
                            }
                        }
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Validation error during reflection", e);
                }
            }
            clazz = clazz.getSuperclass();
        }
    }
}

