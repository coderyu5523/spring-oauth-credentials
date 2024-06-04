package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.utils.ApiUtil;


@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final HttpSession session;

    @GetMapping("/oauth/naver/callback")
    public ResponseEntity<?> oauthNaverCallback(@RequestParam("accessToken") String naverAccessToken){
        System.out.println("스프링에서 받은 카카오토큰 : "+naverAccessToken);

        String blogAccessToken = userService.네이버로그인(naverAccessToken);

        return ResponseEntity.ok().header("Authorization", "Bearer "+blogAccessToken).body(new ApiUtil(null));
    }

    @GetMapping("/oauth/kakao/callback")
    public ResponseEntity<?> oauthKakaoCallback(@RequestParam("accessToken") String kakaoAccessToken){
        System.out.println("스프링에서 받은 카카오토큰 : "+kakaoAccessToken);

        String blogAccessToken = userService.카카오로그인(kakaoAccessToken);

        return ResponseEntity.ok().header("Authorization", "Bearer "+blogAccessToken).body(new ApiUtil(null));
    }



    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody UserRequest.JoinDTO reqDTO) {
        UserResponse.DTO respDTO = userService.회원가입(reqDTO);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest.LoginDTO reqDTO) {
        String jwt = userService.로그인(reqDTO);
        return ResponseEntity.ok().header("Authorization", "Bearer "+jwt).body(new ApiUtil(null));
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        session.invalidate();
        return ResponseEntity.ok(new ApiUtil(null));
    }
}
