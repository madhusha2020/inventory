package com.watersolution.inventory.component.common.validator;

import com.watersolution.inventory.component.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CustomValidator {

    @Autowired
    Validator validator;

    public <E> boolean validateFoundNull(E entity, String enDescKey) throws CustomException {
        if (entity != null) {
            return true;
        } else {
            throw new CustomException("NOT_FOUND", "Could not find any data", Arrays.asList(enDescKey));
        }
    }

    public <E> boolean validateFoundNull(List<E> entity, String enDescKey) throws CustomException {
        if (entity != null) {
            return true;
        } else {
            throw new CustomException("NOT_FOUND", "Could not find any data", Arrays.asList(enDescKey));
        }
    }

    public <E> boolean validateValidNull(E entity, String enDescKey) throws CustomException {
        if (entity != null) {
            return true;
        } else {
            throw new CustomException("INVALID", "Invalid " + enDescKey.replace("_", " ").toLowerCase(), Arrays.asList(enDescKey));
        }
    }

    public boolean validateNullOrEmpty(String entity, String enDescKey) throws CustomException {
        if (entity != null && !entity.trim().isEmpty()) {
            return true;
        } else {
            throw new CustomException("INVALID", "Invalid " + enDescKey.replace("_", " ").toLowerCase(), Arrays.asList(enDescKey));
        }
    }

    public boolean validateNullOrEmptyWithErrorCode(String entity, String errorCode) throws CustomException {
        if (entity != null && !entity.trim().isEmpty()) {
            return true;
        } else {
            throw new CustomException(errorCode, "", new ArrayList());
        }
    }

    public <E> boolean validateNullWithErrorCode(E entity, String errorCode) throws CustomException {
        if (entity != null) {
            return true;
        } else {
            throw new CustomException(errorCode, "", new ArrayList());
        }
    }

    public <E> boolean validateNullWithErrorCode(E entity, String errorCode, List<String> values) throws CustomException {
        if (entity != null) {
            return true;
        } else {
            throw new CustomException(errorCode, "", values);
        }
    }

    public <E> boolean validateNullOrEmpty(List<E> entity, String enDescKey) throws CustomException {
        if (entity != null && !entity.isEmpty()) {
            return true;
        } else {
            throw new CustomException("INVALID", "Invalid " + enDescKey.replace("_", " ").toLowerCase(), Arrays.asList(enDescKey));
        }
    }

    public <E> boolean validateAlreadyExists(E entity, String enDescKey) throws CustomException {
        if (entity != null) {
            throw new CustomException("ALREADY_EXISTS", enDescKey.replace("_", " ").toLowerCase() + " already exists", Arrays.asList(enDescKey));
        } else {
            return true;
        }
    }
}
