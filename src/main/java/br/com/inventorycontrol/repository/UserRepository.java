package br.com.inventorycontrol.repository;

import br.com.inventorycontrol.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
