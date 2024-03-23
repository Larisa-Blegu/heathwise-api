package com.healthwise.HealthwiseApp.util;

import java.lang.reflect.Field;

public class ServiceUtils {
    public static <T> void updateFields(T existingEntity, T updatedData) {
        Field[] fields = existingEntity.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object newValue = field.get(updatedData);
                if (newValue != null) {
                    field.set(existingEntity, newValue);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
