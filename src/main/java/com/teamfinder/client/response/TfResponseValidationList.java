package com.teamfinder.client.response;

import com.google.common.collect.Lists;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TfResponseValidationList {
    private List<TfResponseValidation> errors = Lists.newArrayList();

    public TfResponseValidationList() {
    }

    public TfResponseValidationList(List<TfResponseValidation> errors) {
        this.errors = errors;
    }

    public List<TfResponseValidation> getErrors() {
        return errors;
    }

    public boolean hasErrors() {
        return !getErrors().isEmpty();
    }

    public void addError(TfResponseValidation error) {
        if (!containsMessage(error.getMessage())) {
            getErrors().add(error);
        }
    }

    public void sortErrors(){
        this.errors = this.errors.stream()
                .sorted(Comparator.comparing(TfResponseValidation::getMessage))
                .collect(Collectors.toList());
    }

    private boolean containsMessage(String message) {
        Set<String> messages = getErrors().stream().map(TfResponseValidation::getMessage).collect(Collectors.toSet());
        return messages.contains(message);
    }

    public static ResponseEntity<TfResponseValidationList> getPostResponse(TfResponseValidationList result) {
        return getResponse(result, HttpStatus.CREATED);
    }

    public static ResponseEntity<TfResponseValidationList> getPutResponse(TfResponseValidationList result) {
        return getResponse(result, HttpStatus.OK);
    }

    private static ResponseEntity<TfResponseValidationList> getResponse(TfResponseValidationList result, HttpStatus status) {
        if (result.getErrors().isEmpty()) {
            return new ResponseEntity<>(result, status);
        }

        return new ResponseEntity<>(result, HttpStatus.NOT_ACCEPTABLE);
    }
}



