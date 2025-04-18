package ac.kr.hufs.wider.model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ac.kr.hufs.wider.model.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
    
}
