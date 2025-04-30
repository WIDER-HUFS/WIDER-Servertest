package ac.kr.hufs.wider.model.Service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ac.kr.hufs.wider.model.Entity.Users;
import ac.kr.hufs.wider.model.Repository.UserRepository;
import ac.kr.hufs.wider.model.Service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Users createUser(Users user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<Users> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users updateUser(Users user) {
        if (!userRepository.existsById(user.getUserId())) {
            throw new IllegalArgumentException("User not found with id: " + user.getUserId());
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public boolean existsByUserId(String userId) {
        return userRepository.existsById(userId);
    }
} 