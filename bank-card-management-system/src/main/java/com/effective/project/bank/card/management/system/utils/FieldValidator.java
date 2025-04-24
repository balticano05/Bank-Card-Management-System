package com.effective.project.bank.card.management.system.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class FieldValidator {

    public boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

}