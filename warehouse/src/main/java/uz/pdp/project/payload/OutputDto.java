package uz.pdp.project.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OutputDto {
    private Timestamp date;
    private int wareHouseId;
    private int clientId;
    private int currencyId;
    private String factureNumber;
}
