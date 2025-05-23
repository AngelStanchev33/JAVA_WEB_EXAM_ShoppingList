package bg.softuni.shoppinglist.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import bg.softuni.shoppinglist.entity.CategoryEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductViewDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDate neededBefore;
    private CategoryEntity category;
    
}
