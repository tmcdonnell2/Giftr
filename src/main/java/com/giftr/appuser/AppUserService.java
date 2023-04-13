package com.giftr.appuser;

import com.giftr.registration.token.ConfirmationToken;
import com.giftr.registration.token.ConfirmationTokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppUserService implements UserDetailsService {

    private static final String USER_NOT_FOUND = "User with email %s not found";
    private final GifterRepository gifterRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    public AppUserService(GifterRepository gifterRepository,
                        BCryptPasswordEncoder bCryptPasswordEncoder,
                        ConfirmationTokenService confirmationTokenService) {
        this.gifterRepository = gifterRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.confirmationTokenService = confirmationTokenService;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return gifterRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND, email)));
    }

    public ConfirmationToken signUpUser(Gifter user) {
        Optional<Gifter> optionalUser = gifterRepository.findByEmail(user.getEmail());
        if (optionalUser.isPresent()) {
            Gifter userInDatabase = optionalUser.get();
            if (user.equals(userInDatabase) &&
                !userInDatabase.isEnabled()) {

                Optional<ConfirmationToken> token = confirmationTokenService.getToken(user.getId());
                if (token.isPresent()) {
                    return token.get();
                }
            }
            throw new IllegalStateException("Email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
            token,
            LocalDateTime.now(),
            LocalDateTime.now().plusHours(24), // From a config file?
            user
        );

        gifterRepository.save(user);
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return confirmationToken;
    }

    public int enableAppUser(String email) {
        return gifterRepository.enableAppUser(email);
    }

    public void removeUser(Gifter user) {
        gifterRepository.delete(user);
    }
}
