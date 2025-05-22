package bg.softuni.shoppinglist.security.seassion;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import bg.softuni.shoppinglist.entity.UserEntity;
import bg.softuni.shoppinglist.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class CurrnetUserProvider {

    private final CurrentUser currentUser;
    private final UserRepository userRepository;

    public CurrnetUserProvider(CurrentUser currentUser, UserRepository userRepository) {
        this.currentUser = currentUser;
        this.userRepository = userRepository;
    }

    public void loadCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserEntity> user = userRepository.findByUsername(username);
        user.ifPresent(u -> {
            currentUser.setId(u.getId());
            currentUser.setUsername(u.getUsername());
            currentUser.setEmail(u.getEmail());
            currentUser.setAnonymous(false);
        });

        if (currentUser.isAnonymous()) {
            currentUser.setAnonymous(true);
        }
    }

}
