package uz.mehrojbek.apptvkdbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.mehrojbek.apptvkdbot.entity.Patient;
import uz.mehrojbek.apptvkdbot.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(
            value = "select chat_id from users",
            nativeQuery = true
    )
    List<Long> getAllChatIds();
}