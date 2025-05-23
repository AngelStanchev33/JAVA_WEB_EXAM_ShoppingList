package bg.softuni.shoppinglist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bg.softuni.shoppinglist.entity.enums.CategoryNameEnum;
import bg.softuni.shoppinglist.model.dto.ProductAddDto;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import bg.softuni.shoppinglist.security.seassion.CurrentUser;
import org.springframework.web.bind.annotation.RequestMapping;
import bg.softuni.shoppinglist.service.ProductService;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/products")
public class ProductsController {

    private final CurrentUser currentUser;
    private final ProductService productService;

    public ProductsController(CurrentUser currentUser, ProductService productService) {
        this.currentUser = currentUser;
        this.productService = productService;
    }

    @GetMapping("/add")
    public String addProduct(Model model) {

        if (currentUser.isAnonymous()) {
            return "redirect:/";
        }

        if (!model.containsAttribute("productAddDto")) {
            model.addAttribute("productAddDto", new ProductAddDto());
        }

        model.addAttribute("categories", CategoryNameEnum.values());

        return "product-add";
    }

    @PostMapping("/add")
    public String addProduct(@Valid @ModelAttribute ProductAddDto productAddDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productAddDto", productAddDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productAddDto",
                    bindingResult);

            return "redirect:/products/add";
        }

        productService.addProduct(productAddDto);

        return "redirect:/home";
    }

    @PostMapping("/buy/{id}")
    public String buyProduct(@PathVariable Long id) {
        productService.buyProduct(id);
        return "redirect:/home";
    }

    @PostMapping("/buy-all")
    public String buyAllProducts() {
        productService.buyAllProducts();
        return "redirect:/home";
    }

}
