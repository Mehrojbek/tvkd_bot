package uz.mehrojbek.apptvkdbot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.mehrojbek.apptvkdbot.entity.enums.Status;

import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private Long chatId;

    @Enumerated(EnumType.STRING)
    private Status status;

}
