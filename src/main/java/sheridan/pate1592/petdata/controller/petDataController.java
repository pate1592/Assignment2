package sheridan.pate1592.petdata.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sheridan.pate1592.petdata.model.petForm;
import sheridan.pate1592.petdata.service.PetDataService;

import java.util.List;

public class petDataController {

    private final Logger logger = LoggerFactory.getLogger(petDataController.class);

    private static final String[] petKind = {
            "--- Select Pet Kind ---",
            "Dog", "Cat","Rabbit"};

    private final PetDataService petDataService;


    public petDataController(PetDataService petDataService) {
        this.petDataService = petDataService;
    }

    @GetMapping(value={"/", "/Index"})
    public String index(){
        logger.trace("index() is called");
        return "Index";
    }

    @GetMapping("/AddPet")
    public ModelAndView addPet(){
        logger.trace("addPet() is called");
        ModelAndView modelAndView =
                new ModelAndView("AddPet",
                        "form", new petForm());
        modelAndView.addObject("petKind", petKind);
        return modelAndView;
    }

    @PostMapping("/InsertPet")
    public String insertPet(
            @Validated @ModelAttribute("form") petForm form,
            BindingResult bindingResult,
            Model model){
        logger.trace("insertPet() is called");

        if (bindingResult.hasErrors()) {
            logger.trace("input validation errors");

            model.addAttribute("petKind", petKind);
            return "AddPet";
        } else {
            logger.trace("the user inputs are correct");
            petDataService.insertpetForm(form);
            return "redirect:ConfirmInsert/" + form.getId();
        }
    }

    @GetMapping("/ConfirmInsert/{id}")
    public String confirmInsert(@PathVariable(name = "id") String strId, Model model){
        logger.trace("confirmInsert() is called");
        try {
            int id = Integer.parseInt(strId);
            logger.trace("looking for the data in the database");
            petForm form = PetDataService.getpetForm(id);
            if (form == null) {
                logger.trace("no data for this id=" + id);
                return "DataNotFound";
            } else {
                logger.trace("showing the data");
                model.addAttribute("form", form);
                return "ConfirmInsert";
            }
        } catch (NumberFormatException e) {
            logger.trace("the id in not an integer");
            return "DataNotFound";
        }
    }

    @GetMapping("/PetsList")
    public ModelAndView PetsList() {
        logger.trace("PetsList() is called");
        List<petForm> list = PetDataService.getAllpetForms();
        return new ModelAndView("PetsList",
                "pets", list);
    }

    @GetMapping("/DeleteAll")
    public String deleteAll(){
        logger.trace("deleteAll() is called");
        petDataService.deleteAllpetForms();
        return "redirect:PetsList";
    }
    @GetMapping("PetDetails/{id}")
    public String PetDetails(@PathVariable String id, Model model){
        logger.trace("PetDetails() is called");
        try {
            petForm form = PetDataService.getpetForm(Integer.parseInt(id));
            if (form != null) {
                model.addAttribute("pet", form);
                return "PetDetails"; // show the pet data in the form to edit
            } else {
                logger.trace("no data for this id=" + id);
                return "DataNotFound";
            }
        } catch (NumberFormatException e) {
            logger.trace("the id is missing or not an integer");
            return "DataNotFound";
        }
    }

    @GetMapping("/DeletePet")
    public String deletePet(@RequestParam String id, Model model) {
        logger.trace("deletePet() is called");
        try {
            petForm form = PetDataService.getpetForm(Integer.parseInt(id));
            if (form != null) {
                model.addAttribute("pet", form);
                return "DeletePet";
            } else {
                return "redirect:PetsList";
            }
        } catch (NumberFormatException e) {
            return "redirect:PetsList";
        }
    }

    @PostMapping("/RemovePet")
    public String removePet(@RequestParam String id) {
        logger.trace("removePet() is called");
        try {
            petDataService.deletepetForm(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            logger.trace("the id is missing or not an integer");
        }
        return "redirect:PetsList";
    }

    @GetMapping("/EditPet")
    public String editPet(@RequestParam String id, Model model) {
        logger.trace("editPet() is called");
        try {
            petForm form = PetDataService.getpetForm(Integer.parseInt(id));
            if (form != null) {
                model.addAttribute("form", form);
                model.addAttribute("petKind", petKind);
                return "EditPet";
            } else {
                logger.trace("no data for this id=" + id);
                return "redirect:PetsList";
            }
        } catch (NumberFormatException e) {
            logger.trace("the id is missing or not an integer");
            return "redirect:PetsList";
        }
    }

    @PostMapping("/UpdatePet")
    public String updatePet(
            @Validated @ModelAttribute("form") petForm form,
            BindingResult bindingResult,
            Model model) {
        logger.trace("updatePet() is called");
        if (bindingResult.hasErrors()) {
            logger.trace("input validation errors");
            model.addAttribute("petKind", petKind);
            return "EditPet";
        } else {
            logger.trace("the user inputs are correct");
            PetDataService.updatepetForm(form);
            logger.debug("id = " + form.getId());
            return "redirect:PetDetails/" + form.getId();
        }
    }

}
