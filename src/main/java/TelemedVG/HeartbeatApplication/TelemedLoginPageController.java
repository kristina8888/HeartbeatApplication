package TelemedVG.HeartbeatApplication;

import TelemedVG.HeartbeatApplication.model.AppUser;
import TelemedVG.HeartbeatApplication.model.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("")
public class TelemedLoginPageController {

    @Autowired
    AppUserRepository appUserRepository;

    @GetMapping("/")
    public String showLogin() {
        return "telemed_login_page";
    }

    @RequestMapping("/login")
    public String processLogin(String eMail, String password, HttpSession session, Model model) {
        AppUser user = appUserRepository.findByeMail(eMail);
        System.out.println("usao u login page, trazi " + eMail + " + " + password);
        if(user == null || !user.getPin().equals(password)) {
            System.out.println("nepostojeci user ili nije naveden");
            model.addAttribute("errorMsg", "Neispravan email ili lozinka");
            return "telemed_login_page";
        }

        System.out.println("korisnik postoji, email: " + eMail + " pwd: " + password);

        session.setAttribute("user", user);
        return "redirect:/patient/list";
    }

    @GetMapping("/logout")
    public String processLogout(HttpSession session) {
        session.removeAttribute("user");
        return "telemed_login_page";
    }

}

