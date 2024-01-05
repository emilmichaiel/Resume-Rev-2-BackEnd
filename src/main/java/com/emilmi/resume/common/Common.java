package com.emilmi.resume.common;

import com.emilmi.resume.exception.RestApiException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Common<T> {
    public Sort parseSort(String sort) {
        List<Sort.Order> sortOrders = Arrays.stream(sort.split(","))
                .map(element -> element.split("\\."))
                .filter(elements -> elements.length >= 1)
                .map(elements -> {
                    String element = elements[0];
                    String direction = elements.length == 2 ? elements[1] : "ASC";
                    Sort.Direction sortDirection = direction.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
                    return new Sort.Order(sortDirection, element);
                })
                .collect(Collectors.toList());

        return Sort.by(sortOrders);
    }

    public T getById(MongoRepository<T, String> repository, String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RestApiException(STR. "Id '\{ id }' does not exist" , HttpStatus.NOT_FOUND));
    }

    public Map<String, Object> generatePageResponse(Integer size, Page<T> page, List<?> data, Class<?> clazz) {
        PageResponse pageResponse = new PageResponse(
                size,
                page.getTotalElements(),
                page.getTotalPages(),
                (long) page.getNumber()
        );

        Map<String, Object> response = new HashMap<>();
        response.put(
                clazz.getAnnotation(Document.class)
                        .collection(),
                data);
        response.put("page", pageResponse);

        return response;
    }
}
