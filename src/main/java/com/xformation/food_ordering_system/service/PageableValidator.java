package com.xformation.food_ordering_system.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class PageableValidator {

    private static final int PRINCIPAL_MAX_PAGE_SIZE = 100;

    /**
     * Validates the pageable object, the object must not be null, must not be unpaged,
     * page size must be greater than 0, page size must not exceed the max page size,
     * and page number must not be negative.
     *
     * @param pageable    pageable object to validate
     * @param maxPageSize maximum page size allowed, must not be greater than {@value PRINCIPAL_MAX_PAGE_SIZE}
     */
    public void validate(Pageable pageable, int maxPageSize) {
        if (pageable == null) {
            throw new IllegalArgumentException("Pageable object cannot be null");
        }

        if (pageable.isUnpaged()) {
            throw new IllegalArgumentException("Paging information is required");
        }

        if (pageable.getPageSize() <= 0) {
            throw new IllegalArgumentException("Page size must be greater than 0");
        }

        if (pageable.getPageSize() > PRINCIPAL_MAX_PAGE_SIZE) {
            throw new IllegalArgumentException("Page size must not exceed " + PRINCIPAL_MAX_PAGE_SIZE);
        }

        if (pageable.getPageNumber() < 0) {
            throw new IllegalArgumentException("Page number must not be negative");
        }
    }
}