package resenkov.work.protrain.service;


import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import resenkov.work.protrain.repository.UserRepository;
import resenkov.work.protrain.entity.User;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.debug("Попытка аутентификации для email: {}", email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    logger.debug("Пользователь не найден: {}", email);
                    return new UsernameNotFoundException("Пользователь не найден");
                });

        logger.debug("Пользователь найден: {}", email);
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPasswordHash())
                .roles("USER")
                .build();
    }
}