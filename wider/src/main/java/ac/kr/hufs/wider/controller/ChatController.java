package ac.kr.hufs.wider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.Data;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @PostMapping
    public ResponseEntity<?> chat(@RequestBody ChatRequest request, 
                                @RequestHeader("Authorization") String token) {
        // FastAPI 서버 호출
        String fastApiUrl = "http://localhost:8000/chat";
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token); // 이미 Bearer가 포함된 토큰을 그대로 전달
        
        HttpEntity<ChatRequest> requestEntity = new HttpEntity<>(request, headers);
        
        ResponseEntity<ChatResponse> response = restTemplate.exchange(
            fastApiUrl,
            HttpMethod.POST,
            requestEntity,
            ChatResponse.class
        );
        
        return response;
    }
}

@Data
class ChatRequest {
    private String sessionId;
    private String userAnswer;
}

@Data
class ChatResponse {
    private String sessionId;
    private String topicPrompt;
    private String question;
    private String message;
}