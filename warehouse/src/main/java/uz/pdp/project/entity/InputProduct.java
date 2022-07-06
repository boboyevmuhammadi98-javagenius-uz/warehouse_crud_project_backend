package uz.pdp.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class InputProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Product product;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private Date expireDate;

    @ManyToOne
    private Input input;
}
