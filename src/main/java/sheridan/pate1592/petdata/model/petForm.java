package sheridan.pate1592.petdata.model;

import javax.validation.constraints.*;

public class petForm {
    private int Id = 0;

    @NotBlank
    @Size(max = 30)
    @Pattern(regexp = "[A-Za-z]*")
    private String petName = "";

    @NotBlank
    @Pattern(regexp = "(Dog|Cat|Rabbit)?")
    private String petKind = "Dog";

    @NotNull
    @Pattern(regexp = "(Male|Female)")
    private String petGender = "Female";

    private boolean Vaccinated = false;


    public petForm() {
    }

    public void setId(int id) {
        this.Id = id;
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

    public boolean isVaccinated() {
        return Vaccinated;
    }

    public void setVaccinated(boolean Vaccinated) {
        this.Vaccinated = Vaccinated;
    }


    public int getId() {
        return Id;
    }
}
