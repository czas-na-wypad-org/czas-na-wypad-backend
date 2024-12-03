package sggw.wzim.czasnawypad.service;

import sggw.wzim.czasnawypad.model.SecurityUser;
import sggw.wzim.czasnawypad.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    JpaUserDetailsService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {
        return userRepository
                .findByLogin(login)
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found" + login));
    }
}