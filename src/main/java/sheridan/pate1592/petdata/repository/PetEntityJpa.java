package sheridan.pate1592.petdata.repository;

import javax.persistence.*;

@Entity
@Table(name = "pet")
public class PetEntityJpa {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pet_name")
    private String petName = "";

    @Column(name = "pet_kind")
    private String petKind = "";

    @Column(name = "pet_gender")
    private String petGender = "Female";

    @Column(name = "Vaccinated")
    private Boolean Vaccinated = false;

    public PetEntityJpa() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getpetName() {
        return petName;
    }

    public void setpetName(String petName) {
        this.petName = petName;
    }

    public String getpetKind() {
        return petKind;
    }

    public void setpetKind(String petKind) {
        this.petKind = petKind;
    }

    public String getpetGender() {
        return petGender;
    }

    public void setpetGender(String petGender) {
        this.petGender = petGender;
    }

    public Boolean isVaccinated() {
        return Vaccinated;
    }

    public void setVaccinated(Boolean Vaccinated) {
        this.Vaccinated = Vaccinated;
    }

}
