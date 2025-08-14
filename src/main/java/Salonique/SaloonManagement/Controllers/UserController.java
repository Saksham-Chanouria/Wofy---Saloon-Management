package Salonique.SaloonManagement.Controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/")
    public String index() {
        return "index-3";
    }

    @GetMapping("/UserShowSaloons")
    public String UserShowSaloons(HttpSession session) {
        Integer userid = (Integer) session.getAttribute("userid");

        if (userid == null || userid == 0) {
            return "redirect:/UserLogin";
        }

        System.out.println("Session User ID " + userid);

        return "UserShowSaloons";
    }

    @GetMapping("/UserShowSaloonDetail")
    public String UserShowSaloonDetail(HttpSession session) {

        Integer userid = (Integer) session.getAttribute("userid");

        if (userid == null || userid == 0) {
            return "redirect:/UserLogin";
        }

        System.out.println("Session User ID " + userid);

        return "UserShowSaloonDetail";
    }

    @GetMapping("/checkout")
    public String Checkout(HttpSession session) {
        Integer userid = (Integer) session.getAttribute("userid");

        if (userid == null || userid == 0) {
            return "redirect:/UserLogin";
        }

        System.out.println("Session User ID " + userid);

        return "checkout";
    }
    
    @GetMapping("/ULogout")
    public String PatientLogout(HttpSession session)
    {
        session.removeAttribute("userid");
        session.removeAttribute("uname");
        return "redirect:/";
    }

    @GetMapping("/UserLogin")
    public String UserLogin() {
        return "UserLogin";
    }

    @GetMapping("/UserSignup")
    public String UserSignup() {
        return "UserSignup";
    }
    
    @GetMapping("/UserChangePassword")
    public String UserChangePassword(HttpSession session) {
        
        Integer userid = (Integer) session.getAttribute("userid");

        if (userid == null || userid == 0) {
            return "redirect:/UserLogin";
        }
        
        return "UserChangePassword";
    }
    
        @GetMapping("/UserShowBookingHistory")
    public String UserShowBookingHostory(HttpSession session) {
        
        Integer userid = (Integer) session.getAttribute("userid");

        if (userid == null || userid == 0) {
            return "redirect:/UserLogin";
        }
        
        return "UserShowBookingHistory";
    }
}
