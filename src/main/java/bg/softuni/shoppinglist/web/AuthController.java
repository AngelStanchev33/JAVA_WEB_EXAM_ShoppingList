package bg.softuni.shoppinglist.web;

import bg.softuni.shoppinglist.model.dto.LoginDto;
import bg.softuni.shoppinglist.model.dto.RegisterDto;
import bg.softuni.shoppinglist.security.seassion.CurrnetUserProvider;
import bg.softuni.shoppinglist.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
@RequestMapping("/users")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final CurrnetUserProvider currnetUserProvider;

    public AuthController(UserService userService, AuthenticationManager authenticationManager,
            CurrnetUserProvider currnetUserProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.currnetUserProvider = currnetUserProvider;
    }

    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("registerDto")) {
            model.addAttribute("registerDto", new RegisterDto());
        }

        return "register";
    }

    @GetMapping("/login")
    public String login(Model model) {
        if (!model.containsAttribute("loginDto")) {
            model.addAttribute("loginDto", new LoginDto());
        }

        return "login";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute RegisterDto registerDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "password.mismatch", "Passwords do not match");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerDto", registerDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerDto",
                    bindingResult);

            return "redirect:/users/register";
        }

        userService.register(registerDto);

        return "redirect:/users/login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginDto loginDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginDto", loginDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginDto",
                    bindingResult);

            return "redirect:/users/login";
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            currnetUserProvider.loadCurrentUser();

        } catch (Exception e) {
            System.out.println("Exception type: " + e.getClass().getName());
            System.out.println("Exception message: " + e.getMessage());
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/users/login"; 
        }
        return "redirect:/";
    }
}