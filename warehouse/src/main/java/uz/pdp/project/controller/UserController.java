package uz.pdp.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.project.entity.Users;
import uz.pdp.project.payload.Result;
import uz.pdp.project.payload.UserDto;
import uz.pdp.project.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public Page<Users> getUsers(@RequestParam int page) {
        return userService.getUsers(page);
    }

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }
    
    @PostMapping
    public Result addUser(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }

    @PutMapping("/{id}")
    public Result editUser(@PathVariable int id, UserDto userDto) {
        return userService.editUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }
}
