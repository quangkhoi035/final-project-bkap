package com.khoithuong.controllers.admin;

import com.khoithuong.entity.Role;
import com.khoithuong.entity.User;
import com.khoithuong.exception.ResourceNotFoundException;
import com.khoithuong.repository.RoleRepository;
import com.khoithuong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Controller
@RequestMapping("/user")
public class UserController2 {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("")
    public String UserHome(Model model){
        List<User> listUser = userRepository.findAll();
        model.addAttribute("listUser",listUser);
        return "user/user-home-page";
    }
    @RequestMapping("/init-insert")
    public String InitInsertUser(Model model){
        model.addAttribute("newUser", new User());
        model.addAttribute("listRole", (List<Role>)roleRepository.findAll());
        return "user/init-insert";
    }
    @PostMapping("/insert-user")
    public String addUser(@ModelAttribute("newUser")User newUser ,
                          @RequestParam(value = "listRoleResult" , required = false) long[] listRoleResult) throws ResourceNotFoundException {
        Set<Role> listRole = new HashSet<>();
        Role role;
        try {
            for (int i = 0; i < listRoleResult.length; i++) {
                role = roleRepository.findById(listRoleResult[i])
                        .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
                listRole.add(role);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            newUser.setRoles(listRole);
        }
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);
        return "redirect:/user";
    }

    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        userRepository.delete(user);
        return "redirect:/user";
    }

    @RequestMapping("/update/{id}")
    public String InitUpdateUser(Model model,
                                 @PathVariable(value = "id") long id)throws ResourceNotFoundException{
        User updateUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        model.addAttribute("updateUser", updateUser);
        model.addAttribute("listRole", (List<Role>)roleRepository.findAll());
        return "user/init-update";
    }

    @PostMapping("/update-user")
    public String updateUser(@ModelAttribute("updateUser") User updateUser ,
                             @RequestParam(value = "listRoleResult" , required = false) long[] listRoleResult) throws ResourceNotFoundException {
        Set<Role> listRole = new HashSet<>();
        Role role;
        try {
            for (int i = 0; i < listRoleResult.length; i++) {
                role = roleRepository.findById(listRoleResult[i])
                        .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
                listRole.add(role);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            updateUser.setRoles(listRole);
        }
        System.out.println(updateUser.getId());
        userRepository.save(updateUser);
        return "redirect:/user";
    }
}
