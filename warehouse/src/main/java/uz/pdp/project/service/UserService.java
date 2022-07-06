package uz.pdp.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.project.entity.Users;
import uz.pdp.project.entity.WareHouse;
import uz.pdp.project.payload.Result;
import uz.pdp.project.payload.UserDto;
import uz.pdp.project.repositary.UserRepository;
import uz.pdp.project.repositary.WareHouseRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    WareHouseRepository wareHouseRepository;

    public Page<Users> getUsers(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return userRepository.findAllByActiveIsTrue(pageable);
    }

    public Users getUserById(int id) {
        Optional<Users> optionalUsers = userRepository.findById(id);
        if (optionalUsers.isPresent()) {
            if (optionalUsers.get().isActive()) {
                return optionalUsers.orElse(null);
            }
        }
        return optionalUsers.orElse(null);
    }

    public Result addUser(UserDto userDto) {
        if (userRepository.existsByPhoneNumber(userDto.getPhoneNumber()))
            return new Result("exists bu phoneNumber", false);
        Users users = new Users();
        users.setFirstName(userDto.getFirstName());
        users.setLastName(userDto.getLastName());
        users.setPhoneNumber(userDto.getPhoneNumber());
        users.setPassword(userDto.getPassword());
        Set<WareHouse> wareHouses = new HashSet<>(wareHouseRepository.findAllById(userDto.getWareHousesId()));
        if (userDto.getWareHousesId().size() != wareHouses.size())
            return new Result("ware house not found", false);
        users.setWareHouses(wareHouses);
        return new Result("added", true);
    }

    public Result editUser(int id, UserDto userDto) {
        Optional<Users> optionalUsers = userRepository.findById(id);
        if (!optionalUsers.isPresent())
            return new Result("user not found", false);
        if (!optionalUsers.get().isActive())
            return new Result("this user is not active", false);
        if (userRepository.existsByPhoneNumber(userDto.getPhoneNumber()))
            return new Result("exists bu phoneNumber", false);
        Set<WareHouse> wareHouses = new HashSet<>(wareHouseRepository.findAllById(userDto.getWareHousesId()));
        if (userDto.getWareHousesId().size() != wareHouses.size())
            return new Result("ware house not found", false);
        Users users = optionalUsers.get();
        users.setFirstName(userDto.getFirstName());
        users.setLastName(userDto.getLastName());
        users.setPhoneNumber(userDto.getPhoneNumber());
        users.setPassword(userDto.getPassword());
        users.setWareHouses(wareHouses);
        userRepository.save(users);
        return new Result("edited", true);
    }

    public Result deleteUser(int id) {
        Optional<Users> optionalUsers = userRepository.findById(id);
        if (optionalUsers.isPresent()) {
            if (optionalUsers.get().isActive()) {
                userRepository.deleteById(id);
                return new Result("deleted", true);
            }
            return new Result("user is not active", false);
        }
        return new Result("user not found", false);
    }
}
