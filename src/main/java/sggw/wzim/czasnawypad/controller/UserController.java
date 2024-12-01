package sggw.wzim.czasnawypad.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sggw.wzim.czasnawypad.db.dto.RegisterUserDTO;
import sggw.wzim.czasnawypad.db.entity.User;
import sggw.wzim.czasnawypad.db.dto.LoginUserDTO;

import jakarta.validation.Valid;
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
    ResponseEntity<?> getUserInfo(@RequestHeader String authorization) {
        User userInfo = userService.getUserFromToken(authorization);
        return ResponseEntity.ok(userInfo);
    }

    @PatchMapping("/edit")
    ResponseEntity<?> editUser(@RequestHeader String authorization, @RequestBody @Valid User newUserInfo) {
        userService.editUser(authorization, newUserInfo);
        return ResponseEntity.noContent().build();
    }
}
