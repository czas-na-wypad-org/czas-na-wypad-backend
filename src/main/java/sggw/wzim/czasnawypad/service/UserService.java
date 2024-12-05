package sggw.wzim.czasnawypad.service;

import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sggw.wzim.czasnawypad.db.UserRepository;
import sggw.wzim.czasnawypad.db.dto.LoginUserDTO;
import sggw.wzim.czasnawypad.db.dto.RegisterUserDTO;
import sggw.wzim.czasnawypad.db.entity.User;
import sggw.wzim.czasnawypad.mapper.UserDTOMapper;
import sggw.wzim.czasnawypad.model.JwtResponse;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TokenService tokenService;
    private AuthenticationManager authenticationManager;

    public JwtResponse loginUser(LoginUserDTO loginUserDto) {
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginUserDto.getLogin(), loginUserDto.getPassword()
                        ));

        return new JwtResponse(tokenService.generateToken(authentication));
    }

    @Transactional(rollbackFor = Exception.class)
    public int registerUser(RegisterUserDTO registerUserDto) {
        if (userRepository.findByLogin(registerUserDto.getLogin()).isPresent()) {
            throw new AccessDeniedException("User already exists");
        }

        User user = userDTOMapper.fromRegisterUserDTO(registerUserDto);

        setUserToSave(user);
        return userRepository.save(user).getId();
    }

    public User getUserFromToken(String token) {
        String login = tokenService.getLoginFromToken(token);

        return userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found" + login));
    }

    public int getUserIdFromToken(final String token) {
        User user = getUserFromToken(token);
        return user.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    public void editUser(final String token, final User newUserInfo) {
        User user = getUserFromToken(token);
        user.updateFrom(newUserInfo);

        setUserToSave(user);
        userRepository.save(user);
    }

    void setUserToSave(User userToSave) {
        userToSave.setRoles("ROLE_USER");
        userToSave.setPassword(bCryptPasswordEncoder.encode(userToSave.getPassword()));
    }
}
