package bg.softuni.shoppinglist.repository;

import bg.softuni.shoppinglist.entity.CategoryEntity;
import bg.softuni.shoppinglist.entity.enums.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByName(CategoryNameEnum name);


}
