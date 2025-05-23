package bg.softuni.shoppinglist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import bg.softuni.shoppinglist.security.seassion.CurrentUser;
import bg.softuni.shoppinglist.service.ProductService;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final ProductService productService;

    public HomeController(CurrentUser currentUser, ProductService productService) {
        this.currentUser = currentUser;
        this.productService = productService;
    }

    @GetMapping("/")
    public String index() {

        if (currentUser.isAnonymous()) {
            return "index";
        }

        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model) {

        if (currentUser.isAnonymous()) {
            return "redirect:/";
        }

        model.addAttribute("totalPrice", productService.getTotalPrice());
        model.addAttribute("productsByCategory", productService.findAllProductsByCategory());

        return "home";
    }
}
