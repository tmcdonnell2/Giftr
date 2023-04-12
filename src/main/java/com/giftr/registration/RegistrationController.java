package com.giftr.registration;

import com.giftr.appuser.AppUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("api/v1/registration")
public class RegistrationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);
    private final RegistrationService registrationService;
    private final AppUserService appUserService;

    public RegistrationController(RegistrationService registrationService,
            AppUserService appUserService) {
        this.registrationService = registrationService;
        this.appUserService = appUserService;
    }

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping(path = "/confirm")
    public ModelAndView confirm(@RequestParam("token") String token, Model model) {
        try {
            registrationService.confirmToken(token);
        } catch (IllegalStateException e) {
            LOGGER.error(String.format("Something went wrong with registrations: %s", e.getMessage()));
            return new ModelAndView("error");
        }

        return new ModelAndView("confirmed");
    }
}
