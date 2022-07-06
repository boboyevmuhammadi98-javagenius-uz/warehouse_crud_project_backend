package uz.pdp.project.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OutputProductDto {
    private int productId;
    private int amount;
    private int price;
    private int outputId;
}
