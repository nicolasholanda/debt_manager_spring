package com.github.nicolasholanda.debt.service;

import com.github.nicolasholanda.debt.model.Product;
import com.github.nicolasholanda.debt.model.dto.ProductListItemDTO;
import com.github.nicolasholanda.debt.model.enuns.Gender;
import com.github.nicolasholanda.debt.repository.ProductRepository;
import com.github.nicolasholanda.debt.repository.StoreItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

import java.math.BigDecimal;
import java.util.List;

import static java.lang.String.format;

@Service
public class ProductService {

    private ProductRepository repository;
    private StoreItemRepository storeItemRepository;

    @Autowired
    public ProductService(ProductRepository repository, StoreItemRepository storeItemRepository) {
        this.repository = repository;
        this.storeItemRepository = storeItemRepository;
    }

    public Page<ProductListItemDTO> findPaginatedToStore(Integer page, Integer linesPerPage, BigDecimal maxPrice, Integer storeId,
                                                         List<Integer> brands, List<Integer> genders, List<Integer> categories) {
        var filter = PageRequest.of(page, linesPerPage);
        return storeItemRepository.findPaginated(storeId, maxPrice, brands, genders, categories, filter).map(this::toDTO);
    }

    public Product findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new NoResultException(format("O produto com o id %s não foi encontrado.", id));
        });
    }

    public Product save(Product product) {
        return repository.save(product);
    }

    public ProductListItemDTO toDTO(Product product) {
        return new ProductListItemDTO(product);
    }
}
