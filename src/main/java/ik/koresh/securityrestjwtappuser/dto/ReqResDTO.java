package ik.koresh.securityrestjwtappuser.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import ik.koresh.securityrestjwtappuser.entity.Event;
import ik.koresh.securityrestjwtappuser.entity.Product;
import ik.koresh.securityrestjwtappuser.entity.Role;
import ik.koresh.securityrestjwtappuser.entity.UserApp;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.Collection;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReqResDTO {

    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    @Value("${jwt.lifetime}")
    private String lifetimeToken;
    private String username;
    private String email;
    private String password;
    private Collection<Role> roles;
    private String name;
    private List<Product> productList;
    private List<Event> eventList;
    private UserApp userApp;

}
