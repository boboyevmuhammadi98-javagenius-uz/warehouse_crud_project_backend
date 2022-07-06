package uz.pdp.project.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InputProductDto {
    private int productId;
    private int amount;
    private int price;
    private Date expireDate;
    private int inputId;
}
