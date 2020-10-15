package com.watersolution.inventory.core.config.security.jwt.filters;

import com.watersolution.inventory.component.common.exception.ResponseCreator;
import com.watersolution.inventory.component.entity.user.model.User;
import com.watersolution.inventory.core.config.security.jwt.model.AuthenticationRequest;
import com.watersolution.inventory.core.config.security.jwt.model.AuthenticationResponse;
import com.watersolution.inventory.core.config.security.service.AuthenticationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "Login", response = AuthenticationResponse.class)
    @CrossOrigin
    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseCreator.successfulResponse(authenticationService.login(authenticationRequest));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Register", response = AuthenticationResponse.class)
    @CrossOrigin
    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody User user) {
        return ResponseCreator.successfulResponse(authenticationService.registerUser(user));
    }
}
