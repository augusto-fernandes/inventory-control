package br.com.inventorycontrol.service;

import br.com.inventorycontrol.model.User;
import br.com.inventorycontrol.repository.UserRepository;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }
    public User findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElseThrow(() -> new NoResultException("Nenhum usuário encontrado com o ID: " + id));
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
