package org.example.Lab3_BLPS.common.object;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageResponse<T> extends Page {
    private Integer totalPages;
    private Long totalElements;
    private List<T> content;
}
