package bg.softuni.shoppinglist.service;

import org.springframework.stereotype.Service;

import bg.softuni.shoppinglist.model.dto.ProductAddDto;
import bg.softuni.shoppinglist.model.dto.ProductViewDto;
import bg.softuni.shoppinglist.repository.PorductRepository;

import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import bg.softuni.shoppinglist.entity.ProductEntity;
import bg.softuni.shoppinglist.repository.CategoryRepository;
import bg.softuni.shoppinglist.entity.CategoryEntity;
import bg.softuni.shoppinglist.entity.enums.CategoryNameEnum;
import java.util.List;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.math.BigDecimal;
import java.math.RoundingMode;



@Service
public class ProductService {

    private final PorductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public ProductService(PorductRepository productRepository, ModelMapper modelMapper,
            CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    public void addProduct(ProductAddDto productAddDto) {
        ProductEntity product = modelMapper.map(productAddDto, ProductEntity.class);
        product.setCategory(getCategory(productAddDto.getCategory()));

        productRepository.save(product);
    }

    private CategoryEntity getCategory(String categoryName) {
        Optional<CategoryEntity> category = categoryRepository.findByName(CategoryNameEnum.valueOf(categoryName));
        
        if (category.isEmpty()) {
            throw new IllegalArgumentException("Category not found");
        }
        
        return category.get();
    }

    public Map<CategoryNameEnum, List<ProductViewDto>> findAllProductsByCategory() {
        return Arrays.stream(CategoryNameEnum.values())
                .collect(Collectors.toMap(
                        category -> category,
                        category -> productRepository.findByCategory(getCategory(category.name()))
                                .stream()
                                .map(product -> modelMapper.map(product, ProductViewDto.class))
                                .collect(Collectors.toList()),
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
    }

    public BigDecimal getTotalPrice() {
        return productRepository.findAll().stream()
                .map(ProductEntity::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public void buyProduct(Long id) {
        productRepository.deleteById(id);
    }

    public void buyAllProducts() {
        productRepository.deleteAll();
    }

}
