package bg.softuni.shoppinglist.security.seassion;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalCurrentUserModel {

    private final CurrentUser currentUser;

    public GlobalCurrentUserModel(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @ModelAttribute("currentUser")
    public CurrentUser currentUser() {
        return currentUser;
    }
}
