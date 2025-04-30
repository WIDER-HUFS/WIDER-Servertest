package ac.kr.hufs.wider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ac.kr.hufs.wider.model.DTO.SignInDTO;
import ac.kr.hufs.wider.model.DTO.SignUpDTO;
import ac.kr.hufs.wider.model.Entity.Users;
import ac.kr.hufs.wider.model.Service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public Users signup(@RequestBody SignUpDTO signUpDTO) {
        return userService.createUser(signUpDTO);
    }

    @PostMapping("/signin")
    public Users signin(@RequestBody SignInDTO signInDTO) {
        return userService.signin(signInDTO.getUserId(), signInDTO.getPassword())
                .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다."));
    }
    
    @GetMapping("/{userId}")
    public Users getUser(@PathVariable String userId) {
        return userService.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestBody SignInDTO signInDTO) {
        userService.deleteUser(signInDTO.getUserId());
        return "삭제 완료";
    }
}
