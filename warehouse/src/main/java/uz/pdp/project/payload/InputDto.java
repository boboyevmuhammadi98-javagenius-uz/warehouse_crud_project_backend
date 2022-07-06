package uz.pdp.project.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InputDto {
    private Timestamp date;
    private int wareHouseId;
    private int supplierId;
    private int currencyId;
    private String factureNumber;
}
