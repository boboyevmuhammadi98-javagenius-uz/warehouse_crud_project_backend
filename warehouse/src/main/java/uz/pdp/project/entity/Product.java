package uz.pdp.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.project.entity.template.AbsEntity;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Product extends AbsEntity {
    @ManyToOne
    private Category category;

    @OneToOne
    private Attachment photo;

    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String code;

    @ManyToOne
    private Measurement measurement;
}
