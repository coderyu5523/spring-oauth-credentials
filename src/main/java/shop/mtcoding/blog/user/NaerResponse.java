package shop.mtcoding.blog.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

public class NaerResponse {
    @Data
    public static class TokenDTO {
        @JsonProperty("access_token")
        private String accessToken;

        @JsonProperty("refresh_token")
        private String refreshToken;

        @JsonProperty("token_type")
        private String tokenType;

        @JsonProperty("expires_in")
        private String expiresIn;

    }

    @Data
    public static class NaverUserDTO {
        private String resultcode;
        private String message;
        private ResponseDTO response;

        @Data
        class ResponseDTO {
            private String id;
            private String email;
        }
    }

}
