package TelemedVG.HeartbeatApplication;

import TelemedVG.HeartbeatApplication.model.HealthRecord;
import TelemedVG.HeartbeatApplication.model.AppUser;
import TelemedVG.HeartbeatApplication.model.AppUserRepository;
import TelemedVG.HeartbeatApplication.model.HealthRecordRepository;
import org.h2.engine.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Service("DocDeskController")
@Controller
@RequestMapping("docdesk")
public class DocDeskController {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    HealthRecordRepository healthRecordRepository;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("app_users", appUserRepository.findByTypeOrderByLastNameAsc(1));

        return "docdesk/list";
    }

    @GetMapping("/add")
    public String add(AppUser userForm){
        if(userForm.getId() == 0) {
            System.out.println("Adding new user....");
        } else {
            System.out.println("Changing existing user...");
        }

        appUserRepository.save(userForm);
        return "redirect:/docdesk/list";
    }

    @GetMapping("/edit")
    public String showEditForm(Integer userId, Model model){
        model.addAttribute("user", appUserRepository.findById(userId));

        model.addAttribute("users", appUserRepository.findAll());
        return "docdesk/show_add_form";
    }

    @GetMapping("/show_add_form")
    public String showAddForm(Model model) {

        model.addAttribute("user", new AppUser());

        return "docdesk/show_add_form";
    }

    @GetMapping("/show_patient_information")
    public String showPatientInformationById(Model model, int userId) {
        model.addAttribute("app_users", appUserRepository.findAllById(userId));
        model.addAttribute("record", healthRecordRepository.findAllRecordsByAppUserId(userId));

        return "docdesk/show_patient_information";
    }

    @GetMapping("/show_pressure_add_form")
    public String showPressureAddForm(Model model) {
        model.addAttribute("newPressure", new HealthRecord());

        return "docdesk/show_pressure_add_form";
    }

    @GetMapping("/save_pressure")
    public String savePressure(HealthRecord pressureForm) {
        healthRecordRepository.save(pressureForm);

        return "redirect:/docdesk/show_patient_information";
    }
}
