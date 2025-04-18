package ac.kr.hufs.wider.model.DAO;

import java.util.Optional;

import ac.kr.hufs.wider.model.Entity.User;

public interface UserDao {
    Optional<User> findById(String userId);
    User save(User user);
    void deleteById(String userId);
}
