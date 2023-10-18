package me.dio.santanderdevweek2023.service.implementations;

import me.dio.santanderdevweek2023.domain.model.User;
import me.dio.santanderdevweek2023.domain.repository.UserRepository;
import me.dio.santanderdevweek2023.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService implements IUserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(User user) {
        if(repository.existsByAccountNumber(user.getAccount().getNumber())){
            throw new IllegalArgumentException("This account number already exists");
        }

        return repository.save(user);
    }
}
