package com.github.nicolasholanda.debt.utils;

import com.github.nicolasholanda.debt.model.dto.StoreListItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceUtils {
    public static Page<StoreListItemDTO> orderByDistance(Page<StoreListItemDTO> page, String direction) {
        var listResult = new ArrayList<>(page.toList());
        Collections.sort(listResult);
        if (direction.equals("DESC")) {
            Collections.reverse(listResult);
        }
        return listToPage(listResult, page);
    }

    public static Page<StoreListItemDTO> filterByRadius(Page<StoreListItemDTO> page, Integer radius) {
        var listResult = new ArrayList<>(page.toList()).stream().filter(s -> s.getDistance().intValue() <= radius).collect(Collectors.toList());
        return listToPage(listResult, page);
    }

    public static Page<StoreListItemDTO> listToPage(List<StoreListItemDTO> list, Page<StoreListItemDTO> page) {
        var pageable = page.getPageable();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }
}
