package uz.pdp.project.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.project.entity.WareHouse;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private Set<Integer> wareHousesId;
}
