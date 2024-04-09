package org.mars.issplacesofinterest.exceptionhandler;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mars.issplacesofinterest.BaseTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class GlobalExceptionHandlerTest extends BaseTest {
    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Mock
    private WebRequest webRequest;

    @Test
    public void test_handleISSLocationException_ReturnsInternalServerError() {
        ISSLocationException exception = new ISSLocationException("ISSLocationException Test exception message");

        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleISSLocationException(exception, webRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("ISSLocationException Test exception message", response.getBody().getMessage());
    }

    @Test
    public void test_handlePlacesOfInterestException_ReturnsNotFound() {
        PlacesOfInterestException exception = new PlacesOfInterestException("PlacesOfInterestException Test exception message");

        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handlePlacesOfInterestException(exception, webRequest);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("PlacesOfInterestException Test exception message", response.getBody().getMessage());
    }

    @Test
    public void test_handleConstraintViolationException_ReturnsNotFound() {
        // Create a Set of ConstraintViolation instances
        Set<ConstraintViolation<?>> constraintViolations = new HashSet<>();
        ConstraintViolationException exception = new ConstraintViolationException("ConstraintViolationException Test exception message", constraintViolations);

        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleConstraintViolationException(exception, webRequest);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("ConstraintViolationException Test exception message", response.getBody().getMessage());
    }
}
