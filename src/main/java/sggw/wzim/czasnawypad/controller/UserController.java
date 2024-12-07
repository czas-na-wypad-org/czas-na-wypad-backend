package sggw.wzim.czasnawypad.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sggw.wzim.czasnawypad.db.dto.LoginUserDTO;
import sggw.wzim.czasnawypad.db.dto.RegisterUserDTO;
import sggw.wzim.czasnawypad.db.entity.User;
import sggw.wzim.czasnawypad.model.JwtResponse;
import sggw.wzim.czasnawypad.service.UserService;

import java.net.URI;

@RestController
@RequestMapping("users")
@AllArgsConstructor
class UserController {
    private final UserService userService;

    @PostMapping
    ResponseEntity<?> registerUser(@RequestBody @Valid RegisterUserDTO toRegister) {
        int result = userService.registerUser(toRegister);
        return ResponseEntity.created(URI.create("/" + result)).body(result);
    }

    @PostMapping("/login")
    ResponseEntity<JwtResponse> loginUser(@RequestBody @Valid LoginUserDTO toLogin) {
        return ResponseEntity.ok().body(userService.loginUser(toLogin));
    }

    @GetMapping
    ResponseEntity<?> getUserInfo(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.isBlank(bearerToken)) {
            throw new InvalidBearerTokenException("Bearer token is missing");
        }
        bearerToken = bearerToken.substring("Bearer ".length());
        User userInfo = userService.getUserFromToken(bearerToken);
        return ResponseEntity.ok(userInfo);
    }

    @PatchMapping("/edit")
    ResponseEntity<?> editUser(@RequestHeader String authorization, @RequestBody @Valid User newUserInfo) {
        userService.editUser(authorization, newUserInfo);
        return ResponseEntity.noContent().build();
    }
}
