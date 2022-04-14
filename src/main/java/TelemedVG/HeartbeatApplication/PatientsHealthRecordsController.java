package TelemedVG.HeartbeatApplication;
import TelemedVG.HeartbeatApplication.model.AppUser;
import TelemedVG.HeartbeatApplication.model.AppUserRepository;
import TelemedVG.HeartbeatApplication.model.HealthRecord;
import TelemedVG.HeartbeatApplication.model.HealthRecordRepository;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("patient")
public class PatientsHealthRecordsController {

    @Autowired
    HealthRecordRepository healthRecordRepository;


    @GetMapping("/list")
    public String list(Model model, HttpSession session) {
        AppUser user = (AppUser) session.getAttribute("user");

        if(user == null)
            return "redirect:/";

        System.out.println("Listing records for pratient ...");

        model.addAttribute("records", healthRecordRepository.findAllByAppUserId(user.getId()));
        model.addAttribute("user", user);
        model.addAttribute("record", new HealthRecord());

        return "patient/patients_health_records";
    }
}
