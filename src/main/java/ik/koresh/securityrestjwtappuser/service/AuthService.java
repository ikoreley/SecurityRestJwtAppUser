package ik.koresh.securityrestjwtappuser.service;

import ik.koresh.securityrestjwtappuser.dto.ReqResDTO;
import ik.koresh.securityrestjwtappuser.entity.Role;
import ik.koresh.securityrestjwtappuser.entity.UserApp;
import ik.koresh.securityrestjwtappuser.repository.RoleRepository;
import ik.koresh.securityrestjwtappuser.repository.UserAppRepository;
import ik.koresh.securityrestjwtappuser.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    private final UserAppRepository userAppRepository;
    private final RoleRepository roleRepository;

    private final JwtUtils jwtUtils;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(UserAppRepository userAppRepository, RoleRepository roleRepository, JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.userAppRepository = userAppRepository;
        this.roleRepository = roleRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    public ReqResDTO signUp(ReqResDTO registrationRequest){
        ReqResDTO response = new ReqResDTO();
        try {
            UserApp userApp = new UserApp();
            userApp.setUsername(registrationRequest.getUsername());
            userApp.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            userApp.setEmail(registrationRequest.getEmail());
            userApp.setRoles(List.of(roleRepository.findByName("ROLE_USER").get()));

            UserApp userAppResult = userAppRepository.save(userApp);
            if (userAppResult.getId()>0){
                response.setUserApp(userAppResult);
                response.setMessage("User Saved Successfully");
                response.setStatusCode(200);
            }
        }catch (Exception e){
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }
    public ReqResDTO signIn(ReqResDTO signInRequest){
        ReqResDTO response = new ReqResDTO();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                            signInRequest.getUsername(),
                            signInRequest.getPassword()));
            var user = userAppRepository.findByUsername(signInRequest.getUsername()).orElseThrow();
            System.out.println("USER IS: " + user);
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setMessage("Successfully Signed In");
        }catch (Exception e){
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }

    public ReqResDTO refreshToken(ReqResDTO refreshTokenRequest){
        ReqResDTO response = new ReqResDTO();
        String username = jwtUtils.extractUsername(refreshTokenRequest.getToken());
        UserApp userApp = userAppRepository.findByUsername(username).orElseThrow();
        if (jwtUtils.isTokenValid(refreshTokenRequest.getToken(), userApp)){
            var jwt = jwtUtils.generateToken(userApp);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshTokenRequest.getToken());
            response.setMessage("Successfully Refreshed Token");
        }else{
            response.setStatusCode(500);
        }

        return response;

    }
}
