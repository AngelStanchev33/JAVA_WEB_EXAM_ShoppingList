package bg.softuni.shoppinglist.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductAddDto {

    @Size(min = 3, max = 20, message = "Name length must be between 3 and 20 characters")
    private String name;

    @Size(min = 5, message = "Description must be at least 5 characters")
    private String description;

    @Positive(message = "Price must be a positive number")
    private BigDecimal price;

    @FutureOrPresent(message = "Date cannot be in the past")
    private LocalDate neededBefore;

    @NotBlank(message = "Category cannot be empty")
    private String category;

}
