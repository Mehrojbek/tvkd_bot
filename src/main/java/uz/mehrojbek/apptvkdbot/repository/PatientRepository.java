package uz.mehrojbek.apptvkdbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mehrojbek.apptvkdbot.entity.Patient;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    List<Patient> findAllBySectionNumber(Integer sectionNumber);

    List<Patient> findAllBySectionNumberAndIdIsGreaterThan(Integer sectionNumber, Integer id);
}