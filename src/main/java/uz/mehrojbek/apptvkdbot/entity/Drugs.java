package uz.mehrojbek.apptvkdbot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.mehrojbek.apptvkdbot.entity.enums.Type;

import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Drugs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String measurementType;

    private double price;

    private String size;

    private Integer number;

    @Enumerated(EnumType.STRING)
    private Type type;

}
