package bg.softuni.shoppinglist.init;

import bg.softuni.shoppinglist.entity.CategoryEntity;
import bg.softuni.shoppinglist.entity.enums.CategoryNameEnum;
import bg.softuni.shoppinglist.repository.CategoryRepository;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInit implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public DataInit(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            Arrays.stream(CategoryNameEnum.values())
                    .forEach(categoryName -> {
                        CategoryEntity category = new CategoryEntity();
                        category.setName(categoryName);
                        category.setDescription("Description for " + categoryName);
                        categoryRepository.save(category);
                    });
        }

    }
}