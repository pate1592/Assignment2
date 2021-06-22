package sheridan.pate1592.petdata.service;

import sheridan.pate1592.petdata.model.petForm;

import java.util.List;

public interface PetDataService {

    void insertpetForm(petForm form);

    static List<petForm> getAllpetForms() {


        return null;
    }

    void deleteAllpetForms();

    void deletepetForm(int id);

    static petForm getpetForm(int id) {
        return null;
    }

    static void updatepetForm(petForm form) {
    }
}
