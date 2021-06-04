package uz.mehrojbek.apptvkdbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mehrojbek.apptvkdbot.entity.Patient;
import uz.mehrojbek.apptvkdbot.entity.Price;

import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Integer> {
}