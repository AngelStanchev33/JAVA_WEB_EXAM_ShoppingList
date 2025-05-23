package bg.softuni.shoppinglist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bg.softuni.shoppinglist.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    
    
}
