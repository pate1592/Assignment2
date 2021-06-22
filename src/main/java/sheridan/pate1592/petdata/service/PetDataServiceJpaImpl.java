package sheridan.pate1592.petdata.service;

import sheridan.pate1592.petdata.model.petForm;
import sheridan.pate1592.petdata.repository.PetDataRepositoryJpa;
import sheridan.pate1592.petdata.repository.PetEntityJpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PetDataServiceJpaImpl implements PetDataService{

    private final PetDataRepositoryJpa petDataRepositoryJpa;

    public PetDataServiceJpaImpl(PetDataRepositoryJpa petDataRepositoryJpa) {
        this.petDataRepositoryJpa = petDataRepositoryJpa;
    }

    private static void copyFormToEntity(petForm form, PetEntityJpa pet){
        //student.setId(form.getId());
        pet.setpetName(form.getpetName());
        pet.setpetKind(form.getpetKind());
        pet.setpetGender(form.getpetGender());
        pet.setVaccinated(form.isVaccinated());
    }

    private static void copyEntityToForm(PetEntityJpa pet, petForm form){
        form.setId(pet.getId());
        form.setpetName(pet.getpetName());
        form.setpetKind(pet.getpetKind());
        form.setpetGender(pet.getpetGender());
        form.setVaccinated(pet.isVaccinated());
    }


    public void insertpetForm(petForm form) {
        PetEntityJpa pet = new PetEntityJpa();
        copyFormToEntity(form, pet);
        pet = PetDataRepositoryJpa.Save(pet);
        form.setId(pet.getId());
    }

    public List<petForm> getAllpetForms() {
        List<petForm> formList = new ArrayList<>();
        List<PetEntityJpa> PetsList = PetDataRepositoryJpa.FindAll();
        for(PetEntityJpa pet: PetsList){
            petForm form = new petForm();
            copyEntityToForm(pet, form);
            formList.add(form);
        }
        return formList;
    }

    public void deleteAllpetForms() {
        PetDataRepositoryJpa.DeleteAll();
    }

    public void deletepetForm(int id) {
        PetDataRepositoryJpa.DeleteById(id);
    }

    public petForm getpetForm(int id) {
        Optional<PetEntityJpa> result = PetDataRepositoryJpa.FindById(id);
        if(result.isPresent()){
            petForm form = new petForm();
            PetEntityJpa pet = result.get();
            copyEntityToForm(pet, form);
            return form;
        }
        return null;
    }

    public void updatepetForm(petForm form) {
        Optional<PetEntityJpa> result = PetDataRepositoryJpa.FindById(form.getId());
        if(result.isPresent()){
            PetEntityJpa pet = result.get();
            copyFormToEntity(form, pet);
            PetDataRepositoryJpa.Save(pet);
            //PetRepository.flush();
        }
    }

}
