package ac.kr.hufs.wider.model.DAO;

import java.util.Optional;

import ac.kr.hufs.wider.model.Entity.Users;

public interface UserDao {
    Optional<Users> findById(String userId);
    Users save(Users user);
    void deleteById(String userId);
}
