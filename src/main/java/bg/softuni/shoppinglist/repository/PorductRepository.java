package bg.softuni.shoppinglist.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import bg.softuni.shoppinglist.entity.ProductEntity;
import java.util.List;
import bg.softuni.shoppinglist.entity.CategoryEntity;


public interface PorductRepository extends JpaRepository<ProductEntity, Long> {
    
    List<ProductEntity> findByCategory(CategoryEntity category);
}
