package Salonique.SaloonManagement.Controllers;

import java.sql.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import Salonique.SaloonManagement.Connection.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class AdminRestController {

    @PostMapping("/AdminLogin1")
    public String adminlogindetails(@RequestParam String uname, @RequestParam String password, HttpSession session) {
        try {
            ResultSet rs = Database.executeQuery("select * from admin where User='" + uname + "' and Password='" + password + "'");
            if (rs.next()) {
                session.setAttribute("aname", uname);
                return "success";
            } else {
                return "fail";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "exception";
        }
    }

    @PostMapping("/AddCity")
    public String addcitytable(@RequestParam String name, @RequestParam String desc, MultipartFile file) {
        try {
            ResultSet rs = Database.executeQuery("select * from cities where cityname='" + name + "'");
            if (rs.next()) {
                return "fail";
            } else {
                rs.moveToInsertRow();
                rs.updateString("cityname", name);
                rs.updateString("citydesc", desc);
                String oname = file.getOriginalFilename();
                byte b[] = file.getBytes();
                String abspath = "src/main/resources/static/myuploads/";
                FileOutputStream fos = new FileOutputStream(abspath + oname);
                fos.write(b);
                rs.updateString("cityphotos", oname);
                rs.insertRow();
                return "success";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "exception";
        }
    }
    
    @GetMapping("/AdminGetCities")
    public String getcity()
    {
        String ans=new RDBMS_TO_JSON().generateJSON("select * from cities");
        return ans;
    }
    @PostMapping("/DeleteCity")
    public String deleteaddedcity(@RequestParam String cid)
    {
        int cid1=Integer.parseInt(cid);
        try{
            ResultSet rs=Database.executeQuery("select * from cities where cityid='"+cid1+"'");
            if(rs.next())
            {
                rs.deleteRow();
                return "success";
            }
            else
            {
                return "fail";
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return "exception";
        }
            
    }
    @GetMapping("/PendingOwners")
    public String pendingownerslist()
    {
        //SELECT o.*, c.cityname FROM owner o JOIN cities c ON o.cityid = c.cityid WHERE o.status = 'approved';

        String status="Approve";
         String ans=new RDBMS_TO_JSON().generateJSON("SELECT o.*, c.cityname FROM owner o JOIN cities c ON o.cityid = c.cityid WHERE o.status = 'Pending'");
        return ans;
    }
    
        @GetMapping("/ApprovedOwners")
    public String ApprovedOwners()
    {
        //SELECT o.*, c.cityname FROM owner o JOIN cities c ON o.cityid = c.cityid WHERE o.status = 'approved';

        String status="Approve";
         String ans=new RDBMS_TO_JSON().generateJSON("SELECT o.*, c.cityname FROM owner o JOIN cities c ON o.cityid = c.cityid WHERE o.status = 'Approve'");
        return ans;
    }
    
    @PostMapping("/ApproveOwner")
    public String approve(@RequestParam String oid)
    {
        try {
            int oid1=Integer.parseInt(oid);
            ResultSet rs=Database.executeQuery("select * from owner where ownerid='"+oid1+"'");
            if(rs.next())
            {
                rs.moveToCurrentRow();
                rs.updateString("status", "Approve");
                rs.updateRow();
                return "success";
            }
            else
            {
                return "fail";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "exception";
        }
    }
      @PostMapping("/BlockOwner")
    public String block(@RequestParam String oid)
    {
        try {
            int oid1=Integer.parseInt(oid);
            ResultSet rs=Database.executeQuery("select * from owner where ownerid='"+oid1+"'");
            if(rs.next())
            {
                rs.moveToCurrentRow();
                rs.updateString("status", "Pending");
                rs.updateRow();
                return "success";
            }
            else
            {
                return "fail";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "exception";
        }
    }
}
