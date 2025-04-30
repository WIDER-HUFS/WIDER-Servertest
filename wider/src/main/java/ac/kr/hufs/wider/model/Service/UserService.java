package ac.kr.hufs.wider.model.Service;

import java.util.List;
import java.util.Optional;

import ac.kr.hufs.wider.model.Entity.Users;

public interface UserService {
    // 사용자 생성
    Users createUser(Users user);
    
    // 사용자 조회
    Optional<Users> getUserById(String userId);
    
    // 모든 사용자 조회
    List<Users> getAllUsers();
    
    // 사용자 정보 업데이트
    Users updateUser(Users user);
    
    // 사용자 삭제
    void deleteUser(String userId);
    
    // 사용자 존재 여부 확인
    boolean existsByUserId(String userId);
} 