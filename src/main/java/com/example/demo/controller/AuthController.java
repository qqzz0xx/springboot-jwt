package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired private UserRepository userRepository;
  @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

  //    @Autowired
  //    private AuthenticationManager authenticationManager;
  //
  //    @RequestMapping("/login")
  //    Response login(@RequestBody Request request) {
  //        UsernamePasswordAuthenticationToken info = new
  // UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
  //        Authentication authenticate = authenticationManager.authenticate(info);
  //
  //        if (authenticate.isAuthenticated()) {
  //            String token = JwtHelper.createToken(request.getUsername());
  //
  //            return new Response(20000, "success", token);
  //        }
  //
  //        return new Response(-1, "failed", "");
  //    }

  @RequestMapping("/register")
  public String register(@RequestBody Request request) {
    User user = new User();
    user.setUsername(request.getUsername());
    // 记得注册的时候把密码加密一下
    user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
    user.setRole("ROLE_USER");
    User save = userRepository.save(user);
    return save.toString();
  }
}

class Request {
  private String username;
  private String password;

  public Request(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
