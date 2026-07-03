package org.example.swcsystem.controller;

import org.example.swcsystem.model.User;
import org.example.swcsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    /**
     * Show login page
     */
    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error,
                               @RequestParam(value = "logout", required = false) String logout,
                               Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password!");
        }
        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }
        return "login";
    }
    
    /**
     * Show registration page
     */
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    
    /**
     * Process registration form
     */
    @PostMapping("/register")
    public String processRegistration(@Valid @ModelAttribute("user") User user,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes,
                                    Model model) {
        
        if (bindingResult.hasErrors()) {
            return "register";
        }
        
        try {
            // Check if username already exists
            if (userService.usernameExists(user.getUsername())) {
                model.addAttribute("error", "Username already exists!");
                return "register";
            }
            
            // Check if email already exists
            if (userService.emailExists(user.getEmail())) {
                model.addAttribute("error", "Email already exists!");
                return "register";
            }
            
            // Register the user
            userService.registerUser(user);
            redirectAttributes.addFlashAttribute("message", "Registration successful! Please login.");
            return "redirect:/login";
            
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }
    
    /**
     * Show dashboard after successful login
     */
    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        User user = userService.findByUsername(username).orElse(null);
        if (user != null) {
            model.addAttribute("user", user);
        }
        
        return "dashboard";
    }
    
    /**
     * Show freshmart page after successful login
     */
    @GetMapping("/freshmart")
    public String showFreshmart(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        User user = userService.findByUsername(username).orElse(null);
        if (user != null) {
            model.addAttribute("user", user);
        }
        
        return "freshmart";
    }
    
    /**
     * Cart page for users
     */
    @GetMapping("/cart")
    public String showCart(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        User user = userService.findByUsername(username).orElse(null);
        if (user != null) {
            model.addAttribute("user", user);
        }
        
        return "cart";
    }
    
    /**
     * Show Products&Categories page for fresh.com users
     */
    @GetMapping("/products-categories")
    public String showProductsCategories(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        User user = userService.findByUsername(username).orElse(null);
        if (user != null) {
            model.addAttribute("user", user);
        }
        
        return "Products&Categories";
    }
    
    /**
     * Home page redirect
     */
    @GetMapping("/")
    public String home() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && 
            !authentication.getName().equals("anonymousUser")) {
            return "redirect:/freshmart";
        }
        return "redirect:/login";
    }
    
    /**
     * Logout success page
     */
    @GetMapping("/logout-success")
    public String logoutSuccess(Model model) {
        model.addAttribute("message", "You have been logged out successfully.");
        return "login";
    }
}

