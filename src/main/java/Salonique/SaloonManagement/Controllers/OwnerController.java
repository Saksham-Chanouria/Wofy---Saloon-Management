package Salonique.SaloonManagement.Controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OwnerController {

    @GetMapping("/OwnerSignupPage")
    public String signuppage() {
        return "OwnerSignup";
    }

    @GetMapping("/OwnerLogin")
    public String ownerlogin() {
        return "OwnerLogin";
    }

    @GetMapping("/OwnerHome")
    public String openownerhome(HttpSession session) {

        Integer userid = (Integer) session.getAttribute("ownerid");

        if (userid == null || userid == 0) {
            return "redirect:/OwnerLogin";
        }

        return "OwnerHome";
    }

    @GetMapping("/ManagePackage")
    public String addpackagepage(HttpSession session) {

        Integer userid = (Integer) session.getAttribute("ownerid");

        if (userid == null || userid == 0) {
            return "redirect:/OwnerLogin";
        }

        return "OwnerManagePackage";
    }

    @GetMapping("/EditPackage")
    public String editpackagepage(HttpSession session) {

        Integer userid = (Integer) session.getAttribute("ownerid");

        if (userid == null || userid == 0) {
            return "redirect:/OwnerLogin";
        }

        return "OwnerEditPackage";
    }

    @GetMapping("/AddPhotos")
    public String addphotobyownerpage(HttpSession session) {
        Integer userid = (Integer) session.getAttribute("ownerid");

        if (userid == null || userid == 0) {
            return "redirect:/OwnerLogin";
        }

        return "OwnerManagePhoto";
    }

    @GetMapping("/OwnerManageBookings")
    public String OwnerManageBookings(HttpSession session) {

        Integer userid = (Integer) session.getAttribute("ownerid");

        if (userid == null || userid == 0) {
            return "redirect:/OwnerLogin";
        }

        return "OwnerManageBookings";
    }

    @GetMapping("/OLogout")
    public String OLogout(HttpSession session) {
        session.removeAttribute("ownername");
        session.removeAttribute("ownerid");
        return "redirect:/";
    }

    @GetMapping("/OwnerChangePassword")
    public String OwnerChangePassword(HttpSession session) {

        Integer userid = (Integer) session.getAttribute("ownerid");

        if (userid == null || userid == 0) {
            return "redirect:/OwnerLogin";
        }

        return "OwnerChangePassword";
    }
}
