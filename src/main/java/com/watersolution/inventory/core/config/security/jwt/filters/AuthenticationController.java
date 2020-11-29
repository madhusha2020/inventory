package com.watersolution.inventory.core.config.security.jwt.filters;

import com.watersolution.inventory.component.common.exception.ResponseCreator;
import com.watersolution.inventory.component.entity.user.model.api.CustomerUser;
import com.watersolution.inventory.component.entity.user.model.db.User;
import com.watersolution.inventory.core.config.security.service.AuthenticationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Login user", response = User.class)
    @CrossOrigin
    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<User> login(@Valid @RequestBody User user) {
        return ResponseCreator.successfulResponse(authenticationService.login(user));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Logout user", response = User.class)
    @CrossOrigin
    @PostMapping(value = "/logout", produces = "application/json")
    public ResponseEntity<User> logout(@RequestBody User user) {
        return ResponseCreator.successfulResponse(authenticationService.logout(user));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Register user", response = CustomerUser.class)
    @CrossOrigin
    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity registerUser(@Valid @RequestBody CustomerUser customerUser) {
        return ResponseCreator.successfulResponse(authenticationService.registerUser(customerUser));
    }
}
