package sheridan.pate1592.petdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PetDataRepositoryJpa extends JpaRepository<PetEntityJpa, Integer> {
    static void DeleteAll() {
    }

    static void DeleteById(int id) {
    }

    static Optional<PetEntityJpa> FindById(int id) {
        return null;
    }

    static PetEntityJpa Save(PetEntityJpa pet) {
        return pet;
    }

    static List<PetEntityJpa> FindAll() {
        return null;
    }
}

