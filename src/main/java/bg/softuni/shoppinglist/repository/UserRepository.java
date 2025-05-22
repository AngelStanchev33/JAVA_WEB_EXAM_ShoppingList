package bg.softuni.shoppinglist.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bg.softuni.shoppinglist.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
}
