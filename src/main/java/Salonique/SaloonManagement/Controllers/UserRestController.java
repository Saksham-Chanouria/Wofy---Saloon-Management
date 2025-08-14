package Salonique.SaloonManagement.Controllers;

import Salonique.SaloonManagement.Connection.Database;
import Salonique.SaloonManagement.Connection.RDBMS_TO_JSON;
import jakarta.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UserRestController {

    @GetMapping("/userShowCities")
    public String userShowCities() {
        try {
            String ans = new RDBMS_TO_JSON().generateJSON("select * from cities");

            System.out.println(ans);

            return ans;
        } catch (Exception ex) {
            return ex.toString();
        }
    }

    @GetMapping("userShowAllSaloons")
    public String userShowSaloons(@RequestParam String cityid) {
        try {
            String s = "Approve";
            String ans = new RDBMS_TO_JSON().generateJSON("select * from owner where cityid= " + cityid + " and status = '"+s+"'  ");

            System.out.println(ans);

            return ans;
        } catch (Exception ex) {
            return ex.toString();
        }
    }

    @GetMapping("userShowAllSaloonDetails")
    public String userShowAllSaloonDetails(@RequestParam String ownerid) {
        try {
            String ans = new RDBMS_TO_JSON().generateJSON("select * from owner where ownerid= " + ownerid + " ");

            System.out.println(ans);

            return ans;
        } catch (Exception ex) {
            return ex.toString();
        }
    }

    @GetMapping("userShowAllSaloonPackage")
    public String userShowAllSaloonPackage(@RequestParam String ownerid) {
        try {
            String ans = new RDBMS_TO_JSON().generateJSON("select * from packages where ownerid= " + ownerid + " ");

            System.out.println(ans);

            return ans;
        } catch (Exception ex) {
            return ex.toString();
        }
    }

    @GetMapping("userShowPackageDetails")
    public String userShowPackageDetails(@RequestParam String packageid) {
        try {
            String ans = new RDBMS_TO_JSON().generateJSON("select * from packages where packageid= " + packageid + " ");

            System.out.println(ans);

            return ans;
        } catch (Exception ex) {
            return ex.toString();
        }
    }

    @GetMapping("userShowSaloonPhotos")
    public String userShowSaloonPhotos(@RequestParam String ownerid) {
        try {
            String ans = new RDBMS_TO_JSON().generateJSON("select * from shopphotos where ownerid= " + ownerid + " ");

            System.out.println(ans);

            return ans;
        } catch (Exception ex) {
            return ex.toString();
        }
    }

    @PostMapping("/booking")
    public String addBookingDetails(HttpSession session, @RequestParam String customername,
            @RequestParam String customeremail,
            @RequestParam String customeraddress,
            @RequestParam String bookingdate,
            @RequestParam String bookigntime,
            @RequestParam String modeofpayment,
            @RequestParam String packageid) {
        try {
            // Check if the customer already exists based on email
            ResultSet rs = Database.executeQuery("SELECT * FROM booking");

            int userid = (int) session.getAttribute("userid");

            // Insert new booking record into the database
            rs.moveToInsertRow();
            rs.updateString("username", customername);
            rs.updateString("useremail", customeremail);
            rs.updateString("address", customeraddress);
            rs.updateString("bookingdate", bookingdate);
            rs.updateString("bookingtime", bookigntime);
            rs.updateString("modeofpayment", modeofpayment);
            rs.updateInt("userid", userid);
            rs.updateInt("packageid", Integer.parseInt(packageid));
            rs.insertRow();

            // Return success message after booking is created
            return "success";

        } catch (Exception ex) {
            ex.printStackTrace();
            return "exception"; // If any exception occurs
        }
    }

    @GetMapping("/UserAddReview")
    public String userAddReview(HttpSession session,
            @RequestParam String thingslike,
            @RequestParam int stars,
            @RequestParam int ownerid,
            @RequestParam String summery,
            @RequestParam String review) {
        String ans = "";
        int userid = (int) session.getAttribute("userid");

        // Get current date and time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);

        try {
            ResultSet rs = Database.executeQuery("SELECT * FROM review_table");
            rs.moveToInsertRow();
            rs.updateInt("rating", stars);
            rs.updateString("thingslike", thingslike);
            rs.updateInt("ownerid", ownerid);
            rs.updateString("summery", summery);
            rs.updateString("review", review);
            rs.updateInt("userid", userid);
            rs.updateString("review_date", formattedDate); // Add current date-time
            rs.insertRow();
            ans = "success";

        } catch (Exception e) {
            ans = e.toString();
        }
        return ans;
    }

    @PostMapping("/USignup")
    public String OwnerSignup(@RequestParam String uname, @RequestParam String password, @RequestParam String uemail, @RequestParam MultipartFile uphoto) {
        try {
            ResultSet rs = Database.executeQuery("select * from user where uemail='" + uemail + "'");
            if (rs.next()) {
                return "fail";
            } else {

                String oname = uphoto.getOriginalFilename();
                byte b[] = uphoto.getBytes();
                String abspath = "src/main/resources/static/myuploads/";
                FileOutputStream fos = new FileOutputStream(abspath + oname);
                fos.write(b);

                rs.moveToInsertRow();
                rs.updateString("uname", uname);
                rs.updateString("upassword", password);
                rs.updateString("uemail", uemail);
                rs.updateString("uphoto", oname);
                rs.insertRow();
                return "success";
            }
        } catch (Exception ex) {
            return ex.toString();
        }
    }

    @PostMapping("/ULogin")
    public String ULogin(HttpSession session, @RequestParam String password, @RequestParam String uemail) {
        String ans = "";

        try {
            ResultSet rs = Database.executeQuery("select * from user where uemail='" + uemail + "' and upassword='" + password + "'");
            if (rs.next()) {
                int userid = rs.getInt("userid");
                String uname = rs.getString("uname");
                session.setAttribute("userid", userid);
                session.setAttribute("uname", uname);

                return "success";
            } else {
                return "fail";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "exception";
        }
    }

    @GetMapping("/UserShowRatings")
    public String UserShowRatings(@RequestParam int ownerid) {
        try {
            String ans = new RDBMS_TO_JSON().generateJSON(
                    "SELECT r.review_id, r.rating, r.thingslike, r.summery, r.review, r.userid, r.review_date , u.uname, u.uemail, u.uphoto "
                    + "FROM review_table r "
                    + "JOIN user u ON r.userid = u.userid "
                    + "WHERE r.ownerid = '" + ownerid + "'"
            );
            return ans;
        } catch (Exception ex) {
            return ex.toString();
        }
    }

    @PostMapping("/UChangePassword")
    public String UChangePassword(HttpSession session, @RequestParam String opass, @RequestParam String npass) {
        String ans = "";

        try {
            int userid = (int) session.getAttribute("userid");
            ResultSet rs = Database.executeQuery("select * from user where upassword='" + opass + "' and userid='" + userid + "'");
            if (rs.next()) {

                rs.updateString("upassword", npass);
                rs.updateRow();

                session.removeAttribute("userid");
                session.removeAttribute("uname");
                        
                return "success";
            } else {
                return "fail";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "exception";
        }
    }

    @GetMapping("UShowBookigHistory")
    public String UShowBookigHistory(HttpSession session) {
        try {
            int userid = (int) session.getAttribute("userid");
            String ans = new RDBMS_TO_JSON().generateJSON("select * from booking where userid= '"+userid+"' ");

            System.out.println(ans);

            return ans;
        } catch (Exception ex) {
            return ex.toString();
        }
    }
    
}
