package com.giftr.appuser;

import com.giftr.model.Gifter;
import com.giftr.repository.GifterRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {

    private static final String USER_NOT_FOUND = "User with email %s not found";
    private final GifterRepository gifterRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AppUserService(GifterRepository gifterRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.gifterRepository = gifterRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return gifterRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND, email)));
    }

    public String signUpUser(Gifter user) {
        if (gifterRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        gifterRepository.save(user);

        // TODO: send confirmation token

        return "it works";
    }
}
