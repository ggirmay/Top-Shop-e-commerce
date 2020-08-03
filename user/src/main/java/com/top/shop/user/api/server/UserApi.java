package com.top.shop.user.api.server;


import com.top.shop.user.api.request.LoginRequest;
import com.top.shop.user.api.response.LoginResponse;
import com.top.shop.user.command.service.UserCommandService;
import com.top.shop.user.command.service.VerificationTokenCommandService;
import com.top.shop.user.domain.RegisteredUser;
import com.top.shop.user.domain.User;
import com.top.shop.user.domain.VerificationToken;
import com.top.shop.user.exception.UserExist;
import com.top.shop.user.query.service.UserAccountQueryService;
import com.top.shop.user.query.service.UserQueryService;
import com.top.shop.user.repository.UserRepository;
import com.top.shop.user.repository.VerificationTokenRepository;
import com.top.shop.user.util.OnRegistrationCompleteEvent;
import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;

@RestController
@RequestMapping("api/user")
public class UserApi {
    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Autowired
    UserQueryService userQueryService;
    @Autowired
    UserCommandService userCommandservice;
    final Logger log = LoggerFactory.getLogger(UserApi.class);
    @Autowired
    UserAccountQueryService userAccountQueryService;
    @Autowired
    MessageSource messageSource;
    @PostMapping
    @Operation(summary = "User Account registration", description = "Account registration and email activation")
    public ResponseEntity registerUserAccount(@RequestBody RegisteredUser user, HttpServletRequest request, Object object){
        log.debug(user.toString());

        String appUrl = request.getContextPath();

        try {
           RegisteredUser registeredUser =(RegisteredUser) userCommandservice.registerUser((RegisteredUser) user);
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registeredUser.getUserAccount(),
                    request.getLocale(), appUrl));
        }
        catch (UserExist e){
            log.error(e.toString());
            return ResponseEntity.ok(messageSource.getMessage("auth.email.exist",null,null));
        }

        return ResponseEntity.ok(messageSource.getMessage("auth.account.created.activate",null,null));
    }

    @PostMapping("/login")
    @Operation(summary = "User Login",description = "Use your email and password to login")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successfully logged in")})

    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok().body(userAccountQueryService.authenticate(loginRequest.getEmail(),loginRequest.getPassword()));
    }


}
