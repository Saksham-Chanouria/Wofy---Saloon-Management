package Salonique.SaloonManagement.Controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    @GetMapping("/AdminLogin")
    public String adminlogin()
    {
        return "AdminLogin";
    }
    @GetMapping("/AdminHome")
    public String adminhomeget()
    {
        return "AdminHome";
    }
    
     @GetMapping("/admincities")
    public String addcitiespage()
    {
        return "AdminManageCities";
    }
    
    @GetMapping("/ManageOwners")
    public String manageownerpage()
    {
        return "AdminManageOwner";
    }
    
    
            
    @GetMapping("/ALogout")
    public String ALogout(HttpSession session)
    {
        session.removeAttribute("aname");
        return "redirect:/AdminLogin";
    }
    
//    hello 
//    world
}
