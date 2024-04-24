package com.example.issatc.Controller;

import com.example.issatc.Entities.Requests.*;
import com.example.issatc.Infrastructure.EntityMappers.*;
import com.example.issatc.Ports.AuthenticationServicePort;
import com.example.issatc.utilities.JwtAuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationServicePort authenticationService;
    private final JwtAuthenticationService jwtAuthenticationService;
    @Operation(
            responses = {
                    @ApiResponse(
                            description = "Bad Credentials",
                            responseCode = "400"
                    ),
                    @ApiResponse(
                            description = "user not allowed to authenticate",
                            responseCode = "403"

                    ),
                    @ApiResponse(
                            description = "account already exists",
                            responseCode = "202"

                    ),
                    @ApiResponse(
                            description = "success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegisterResponse.class))
                    )

            }
    )
    @PostMapping("/studentSignUp")
    ResponseEntity<?>  createStudentAccount(@RequestBody StudentAccountRequest accountRequest){
        //true user exists doesn't have account
        //false
    int result=authenticationService.createStudentAccount(accountRequest);
       AccountRequest a= new AccountRequest();
       a.setEmail(accountRequest.getEmail()); a.setPassword(accountRequest.getPassword());

    switch (result){
        case 1:         return ResponseEntity.ok().body(jwtAuthenticationService.register(a));

        case 2: return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad credentials");

        case 0: return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        default:return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("unknow error");

    }

    }
    @PostMapping("/teacherSignUp")
    ResponseEntity<?>  createTeacherAccount(@RequestBody TeacherAccountRequest accountRequest){
        //true user exists doesn't have account
        //false
        int result=authenticationService.createTeacherAccount(accountRequest);
        AccountRequest a= new AccountRequest();
        a.setEmail(accountRequest.getEmail()); a.setPassword(accountRequest.getPassword());
        switch (result){
            case 1:         return ResponseEntity.ok().body(jwtAuthenticationService.register(a));

            case 2: return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad credentials");

            case 0: return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
            default:return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("unknow error");

        }

    }

    @Operation(
            responses = {
                    @ApiResponse(
                            description = "Bad Credentials",
                            responseCode = "400"
                    ),
                    @ApiResponse(
                            description = "user not allowed to authenticate",
                            responseCode = "403"

                    ),
                    @ApiResponse(
                            description = "success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationResponse.class))
                    )

            }
    )

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {


            return ResponseEntity.ok().body(jwtAuthenticationService.authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        }
        catch (DisabledException e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("user not allowed to authenticate");


        }
        catch (BadCredentialsException e)
        {
            System.out.println("Bad Credentials ");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad credentials");


        }
    }

    @Operation(
            responses = {
                    @ApiResponse(
                            description = "success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = RefreshTokenResponse.class))
                    )

            }
    )
    //RefreshToken
    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        jwtAuthenticationService.refreshToken(request, response);
    }

//Reset Password
    @PostMapping("/resetPassword")
    ResponseEntity<?> resetPassword(@RequestBody ResetRequest request){
        int result;
        result= this.authenticationService.resetPassword(request.getEmail());
        switch (result){
            case 1 : return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad credentials");
            case 2:  return ResponseEntity.status(HttpStatus.OK).body("a reset code is sent to your email");
            default:  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("unknown error");
        }
    }
 // send new password with recovery code
 @PutMapping("/newPassword")
 ResponseEntity<?> newPassword(@RequestBody NewPasswordRequest newPasswordRequest){

        try{
            if(this.authenticationService.changePassword(newPasswordRequest))
            return ResponseEntity.status(HttpStatus.OK).body("password have changed successfully");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("code expired or wrong email");


        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("code expired or wrong email");

        }

 }



    @PostMapping("/saveUser")
    ResponseEntity<Boolean> saveUser(@RequestBody UserRequest userRequest){
        boolean result=  this.authenticationService.saveUser(userRequest);

        if(result==true) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
}
