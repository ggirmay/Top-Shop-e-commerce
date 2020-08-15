package com.top.shop.user.api.server;


import com.top.shop.user.api.request.LoginRequest;
import com.top.shop.user.api.response.ErrorMessage;
import com.top.shop.user.api.response.LoginResponse;
import com.top.shop.user.command.service.UserCommandService;
import com.top.shop.user.command.service.VerificationTokenCommandService;
import com.top.shop.user.domain.*;
import com.top.shop.user.exception.UserExist;
import com.top.shop.user.query.service.RegisterduserQueryService;
import com.top.shop.user.query.service.UserAccountQueryService;
import com.top.shop.user.query.service.UserQueryService;
import com.top.shop.user.repository.UserRepository;
import com.top.shop.user.repository.VerificationTokenRepository;
import com.top.shop.user.util.OnRegistrationCompleteEvent;
import com.top.shop.user.util.PlainText;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;


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
    private RegisterduserQueryService regUserService;

    @CrossOrigin(origins = "*")
    @PostMapping
    @Operation(summary = "User Account registration", description = "Account registration and email activation")
    public ResponseEntity registerUserAccount(@RequestBody RegisteredUser user, HttpServletRequest request){
        log.debug(user.toString());
        String appUrl = request.getContextPath();

        try {

           RegisteredUser registeredUser = (RegisteredUser) userCommandservice.registerUser((RegisteredUser) user);
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registeredUser.getUserAccount(),
                    request.getLocale(), appUrl));
        }
        catch (UserExist e){
            ErrorMessage response = new ErrorMessage(messageSource.getMessage("auth.email.exist",null,null), "Make sure your input value is correct");
            return ResponseEntity.badRequest().body(response);
        }
        PlainText message = new PlainText(messageSource.getMessage("auth.account.created.activate",null,null));
        return ResponseEntity.ok().body(message);

    }
    @CrossOrigin
    @PostMapping("/login")
    @Operation(summary = "User Login",description = "Use your email and password to login")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successfully logged in")})

    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok().body(userAccountQueryService.authenticate(loginRequest.getEmail(),loginRequest.getPassword()));
    }

    @CrossOrigin
    @GetMapping(value = "/findByUserName/{userName}")
    @Operation(summary = "User return details",description = "Return User Account")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successfully logged in")})

    public UserAccount login(@PathVariable String userName){
       UserAccount userAccount = userAccountQueryService.finsByUsername(userName);

        return userAccount;
    }


    @CrossOrigin
    @GetMapping
    @Operation(summary = "Grt All users", description = "Retrive all Users")
    public ResponseEntity<List<RegisteredUser>> getAllREgisteredUsers(){
        return ResponseEntity.ok().body(userQueryService.getAllUsers());
    }
    @CrossOrigin
    @DeleteMapping("/{id}")
    @Operation(summary = "delete user", description = "delete User")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id){
        userQueryService.deleteUser(id);
        return ResponseEntity.ok("deleted");
    }
    @CrossOrigin
    @GetMapping("/{id}")
    @Operation(summary = "Grt by user id", description = "get by user id")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
//        userQueryService.deleteUser(id);
        return ResponseEntity.ok().body(userQueryService.getUerById(id));
    }

    @CrossOrigin
    @GetMapping("getByAccountId/{id}")
    @Operation(summary = "Get user by account id", description = "get by account id")
    public ResponseEntity<User> getUserBy_accountId(@PathVariable Long id){
//        userQueryService.deleteUser(id);
        return ResponseEntity.ok().body(userQueryService.getUserBy_accountId(id));
    }

    @CrossOrigin
    @PutMapping("update/{id}")
    @Operation(summary = "update Registred user", description = "Update User")
    public ResponseEntity<User> editUserById(@RequestBody RegisteredUser registeredUser){
//        userQueryService.deleteUser(id);
        return ResponseEntity.ok().body(userCommandservice.update(registeredUser));
    }

    @CrossOrigin
    @PostMapping("/create_guest")
    @Operation(summary = "create guest user", description = "create User")
    public ResponseEntity<User> create(@RequestBody GuestUser guestUser){
//        userQueryService.deleteUser(id);
        return ResponseEntity.ok().body(userCommandservice.createGuest(guestUser));
    }

    @CrossOrigin
    @GetMapping("/get_guest/{id}")
    @Operation(summary = "create guest user", description = "create User")
    public ResponseEntity<User> getGuest(@PathVariable Long id){
//        userQueryService.deleteUser(id);
        return ResponseEntity.ok().body(userQueryService.getGuestUerById(id));
    }

}
