package Salonique.SaloonManagement.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.sql.*;
import Salonique.SaloonManagement.Connection.*;
import jakarta.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class OwnerRestController {

    @PostMapping("/OwnerDetails")
    public String addownerdetails(@RequestParam String city,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String shopname,
            @RequestParam String desc,
            @RequestParam String stime,
            @RequestParam String etime,
            @RequestParam String experience,
            @RequestParam String longitude,
            @RequestParam String latitude,
            @RequestParam MultipartFile profile,
            @RequestParam MultipartFile shopphoto) {
        try {
            ResultSet rs = Database.executeQuery("select * from owner where owneremail='" + email + "'");
            if (rs.next()) {
                return "fail";
            } else {
                int cid = Integer.parseInt(city);
                String oname = profile.getOriginalFilename();
                byte b[] = profile.getBytes();
                String abspath = "src/main/resources/static/myuploads/";
                FileOutputStream fos = new FileOutputStream(abspath + oname);
                fos.write(b);
                String oname1 = shopphoto.getOriginalFilename();
                byte b1[] = shopphoto.getBytes();
                String abspath1 = "src/main/resources/static/myuploads/";
                FileOutputStream fos1 = new FileOutputStream(abspath1 + oname1);
                fos1.write(b1);
                rs.moveToInsertRow();
                rs.updateString("ownername", name);
                rs.updateString("owneremail", email);
                rs.updateString("ownerpass", password);
                rs.updateString("ownerphoto", oname);
                rs.updateString("shopphoto", oname1);
                rs.updateString("shopname", shopname);
                rs.updateString("shopdesc", desc);
                rs.updateInt("cityid", cid);
                rs.updateString("latitude", latitude);
                rs.updateString("longitude", longitude);
                rs.updateString("status", "Pending");
                rs.updateString("starttime", stime);
                rs.updateString("endtime", etime);
                rs.updateString("experience", experience);
                rs.insertRow();
                return "success";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "exception";
        }

    }

    @PostMapping("/OwnerLogin1")
    public String ownerlogin(@RequestParam String uname,
            @RequestParam String password,
            HttpSession session) {
        try {

            ResultSet rs = Database.executeQuery("select * from owner where owneremail='" + uname + "' and ownerpass='" + password + "'");
            if (rs.next()) {
                int id1 = rs.getInt("ownerid");
                String oname = rs.getString("ownername");
                session.setAttribute("ownerid", id1);

                session.setAttribute("oname",oname);

                session.setAttribute("ownername", oname);
                return "success";
            } else {
                return "fail";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "exception";
        }
    }

    @PostMapping("/AddPackage")
    public String addnewpackage(@RequestParam String packagename,
            @RequestParam String packagedesc,
            @RequestParam MultipartFile packagephoto,
            @RequestParam String price,
            @RequestParam String offerprice,
            HttpSession session,
            @RequestParam String type) {

        try {
            ResultSet rs = Database.executeQuery("select * from packages where packagename='" + packagename + "'");
            if (rs.next()) {
                return "fail";
            } else {
                Integer ownerId = (Integer) session.getAttribute("ownerid");

                String oname = packagephoto.getOriginalFilename();
                byte b[] = packagephoto.getBytes();
                String abspath = "src/main/resources/static/myuploads/";
                FileOutputStream fos = new FileOutputStream(abspath + oname);
                fos.write(b);
                rs.moveToInsertRow();
                rs.updateString("packagename", packagename);
                rs.updateString("packagedesc", packagedesc);
                rs.updateString("packagephoto", oname);
                rs.updateString("price", price);
                rs.updateString("offerprice", offerprice);
                rs.updateString("type", type);
                rs.updateInt("ownerid", ownerId);
                rs.insertRow();
                return "success";

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "exception";
        }

    }

    @GetMapping("/GetServices")
    public String getservice(HttpSession session) {
        Integer oid = (Integer) session.getAttribute("ownerid");
        String ans = new RDBMS_TO_JSON().generateJSON("select * from packages where ownerid='" + oid + "'");
        return ans;
    }

    @PostMapping("/deleteservice")
    public String deleteservicesadded(@RequestParam String id) {
        int pid = Integer.parseInt(id);
        try {
            ResultSet rs = Database.executeQuery("select * from packages where packageid='" + pid + "'");
            if (rs.next()) {
                rs.deleteRow();
                return "success";
            } else {
                return "fail";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "exception";
        }

    }

    @GetMapping("/getcities")
    public String getadmincities() {
        String ans = new RDBMS_TO_JSON().generateJSON("select * from cities");
        return ans;
    }

    @PostMapping("/GetPackageData")
    public String getdetaileddatat(@RequestParam String pid) {
        int pid1 = Integer.parseInt(pid);

        String ans = new RDBMS_TO_JSON().generateJSON("select * from packages where packageid='" + pid1 + "'");
        return ans;
    }

    @PostMapping("/AddPhoto")
    public String addphoto(@RequestParam MultipartFile photo, HttpSession session) {
        String oname = photo.getOriginalFilename();
        try {
            Integer oid = (Integer) session.getAttribute("ownerid");
            byte b[] = photo.getBytes();
            String abspath = "src/main/resources/static/myuploads/";
            FileOutputStream fos = new FileOutputStream(abspath + oname);
            fos.write(b);
            ResultSet rs = Database.executeQuery("select * from shopphotos where ownerid='" + oid + "'");
            rs.moveToInsertRow();
            rs.updateInt("ownerid", oid);
            rs.updateString("photo", oname);
            rs.insertRow();
            return "success";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "exception";
        }
    }

    @PostMapping("/deletephoto")
    public String deletephoto(@RequestParam String id) {
        try {
            int id1 = Integer.parseInt(id);
            ResultSet rs = Database.executeQuery("select * from shopphotos where photoid='" + id1 + "'");
            if (rs.next()) {
                rs.deleteRow();
                return "success";
            } else {
                return "fail";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "exception";
        }
    }

    @GetMapping("/showphotos")
    public String getallphotos(HttpSession session) {
        Integer oid = (Integer) session.getAttribute("ownerid");
        String ans = new RDBMS_TO_JSON().generateJSON("select * from shopphotos where ownerid='" + oid + "'");
        return ans;
    }

    @GetMapping("/OShowBookings")
    public String OShowBookings(HttpSession session) {
        Integer oid = (Integer) session.getAttribute("ownerid");

        // Query to fetch bookings for a specific owner
        String query = "SELECT b.bookingid, b.status, b.username, b.useremail, b.address, b.bookingdate, b.bookingtime, b.modeofpayment "
                + "FROM booking b "
                + "JOIN packages p ON b.packageid = p.packageid "
                + "WHERE p.ownerid = " + oid;

        String ans = new RDBMS_TO_JSON().generateJSON(query);
        return ans;
    }

    @PostMapping("/UpdateBookingStatus")
    public String UpdateBookingStatus(@RequestBody Map<String, Object> requestData) {
        System.out.println("Booking ID: " + requestData.get("bookingId"));
        System.out.println("New Status: " + requestData.get("status"));

        int bookingId = (int) requestData.get("bookingId");
        String status = (String) requestData.get("status");
        status = status.trim();
        System.out.println(status);
        try {

            if (status.equalsIgnoreCase("Pending")) {
                System.out.println("in if");
                ResultSet rs = Database.executeQuery("select * from booking where bookingid= '" + bookingId + "' ");
                if (rs.next()) {
                    rs.updateString("status", "Approve");
                    rs.updateRow();
                    
                    return "success";
                }
            } else {
                System.out.println("in else");
                 ResultSet rs = Database.executeQuery("select * from booking where bookingid= '" + bookingId + "' ");
                if (rs.next()) {
                    rs.updateString("status", "Pending");
                    rs.updateRow();
                    
                    return "success";
                }
            }

        } catch (Exception ex) {
           return ex.toString();
        }
        return null;

    }
    
    
        @PostMapping("/OChangePassword")
    public String OChangePassword(HttpSession session, @RequestParam String opass, @RequestParam String npass) {
        String ans = "";

        try {
            int userid = (int) session.getAttribute("ownerid");
            ResultSet rs = Database.executeQuery("select * from owner where ownerpass='" + opass + "' and ownerid='" + userid + "'");
            if (rs.next()) {

                rs.updateString("ownerpass", npass);
                rs.updateRow();

                session.removeAttribute("ownerid");
                session.removeAttribute("ownername");
                return "success";
            } else {
                return "fail";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "exception";
        }
    }


    @PostMapping("/editpackage2")
    public String editpackage(@RequestParam String name,
             @RequestParam String desc,
             @RequestParam String price,
             @RequestParam String offerprice,
             @RequestParam MultipartFile photo,
             @RequestParam String pid,
             HttpSession session) {
        String oname=photo.getOriginalFilename();
        System.out.println("*************************func start");
        Integer oid = (Integer) session.getAttribute("ownerid");
        try {
            int pid1 = Integer.parseInt(pid);
            ResultSet rs = Database.executeQuery("select * from packages where packageid='" + pid1 + "'");
            if (rs.next()) {
                System.out.println("++++++++++++++++++++++++++++++++if mai huu");
                rs.moveToCurrentRow();
                rs.updateInt("ownerid", oid);
                
                rs.updateString("packagename", name);
                rs.updateString("packagedesc", desc);
                rs.updateString("price", price);
                
                rs.updateString("offerprice", offerprice);
                byte b[] = photo.getBytes();
                String abspath = "src/main/resources/static/myuploads/";
                FileOutputStream fos = new FileOutputStream(abspath + oname);
                fos.write(b);
                rs.updateString("packagephoto", oname);
                rs.updateRow();
                return "success";
            }
            else
            {
                System.out.println("---------------else mai huu");
                return "fail";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "exception";
        }
    }
}
