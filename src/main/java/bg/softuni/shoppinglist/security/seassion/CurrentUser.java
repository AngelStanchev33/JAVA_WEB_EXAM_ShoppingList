package bg.softuni.shoppinglist.security.seassion;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
@SessionScope
public class CurrentUser {

    private Long id;
    private String username;
    private String email;
    private boolean isAnonymous = true;
}
