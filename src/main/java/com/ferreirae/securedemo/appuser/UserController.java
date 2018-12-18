package com.ferreirae.securedemo.appuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AppUserRepository userRepo;
//    @Autowired
//    private AuthenticationManager authManager;

    @GetMapping("/signup")
    public String showSignUpPage(Model m) {
        AppUser u = new AppUser();
        m.addAttribute("user", u);
        return "signup";
    }

    @PostMapping("/signup")
    public RedirectView signUp(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String fullName) {
        AppUser newUser = new AppUser(username, bCryptPasswordEncoder.encode(password), fullName);
        userRepo.save(newUser);
        return new RedirectView("/");
    }
}
