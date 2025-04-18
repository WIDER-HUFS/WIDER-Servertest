package ac.kr.hufs.wider.model.DAO.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ac.kr.hufs.wider.model.DAO.UserDao;
import ac.kr.hufs.wider.model.Entity.User;
import ac.kr.hufs.wider.model.Repository.UserRepository;

@Service
public class UserDaoImpl implements UserDao{
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findById(String userId) {
        return userRepository.findById(userId);
        
    }

    
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }


    @Override
    public void deleteById(String userId) {
        userRepository.deleteById(userId);
    }

    
}
