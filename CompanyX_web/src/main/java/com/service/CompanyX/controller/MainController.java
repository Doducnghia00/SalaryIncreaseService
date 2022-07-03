package com.service.CompanyX.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.shared.Application;
import com.service.CompanyX.feign.EventClient;
import com.service.CompanyX.formSubmission.Account;
import com.service.CompanyX.model.Profile;
import com.service.CompanyX.repositories.AccountRepository;
import com.service.CompanyX.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MainController {

   private EventClient eventClient;

   @Autowired
   private AccountRepository accountRepository;

   @Autowired
   private ProfileRepository profileRepository;

//   @Autowired
//   private RestTemplate restTemplate;

   @Autowired
   private WebClient.Builder webClientBuilder;


//    @Autowired
//    private DiscoveryClient discoveryClient;

    private DiscoveryClient discoveryClient;




    @RequestMapping("/")
  //  @ResponseBody
    public String index(Model model, HttpSession session){
        String emailUser = (String) session.getAttribute("email");
        if(emailUser != null && !emailUser.equals("")){
            String name = (String) session.getAttribute("name");
            model.addAttribute("name",name);
            return "index";

        }else return "redirect:/login";

    }

    @RequestMapping("/test")
    public String test(Model model){
        model.addAttribute("text","");
        return "test";
    }

//    @RequestMapping("/login")
//    public class login{
//
//
//    }

    //LOGIN
    @GetMapping("/login")
    public String login1(Model model, HttpSession session){
        model.addAttribute("accountForm", new Account());
        model.addAttribute("error","");
        session.removeAttribute("email");
        return "pages-sign-in";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String checkLogin(@ModelAttribute("accountForm") Account account, Model model, HttpSession session){
        Account foundAccount = accountRepository.findAccountByEmailAndPassword(account.getEmail(),account.getPassword());
        if (foundAccount != null) {
            System.out.println("Login success");
            session.setAttribute("email",account.getEmail());


            try{
                Profile profile = profileRepository.findProfileByEmail(account.getEmail());
                session.setAttribute("name", profile.getName());
                session.setAttribute("id",profile.getId());
            }catch (Exception e){
                session.setAttribute("name", "Hà Lan");
                e.printStackTrace();
            }


            return "redirect:/";
        }
        else {
            System.out.println("login Failure");
            model.addAttribute("error","Sai email hoặc mật khẩu");
            //return "redirect:/login";
            return "pages-sign-in";
        }

    }



    @RequestMapping("/blank")
    public String blank(Model model, HttpSession session){
        String emailUser = (String) session.getAttribute("email");
        if(emailUser != null && !emailUser.equals("")){
            String name = (String) session.getAttribute("name");
            model.addAttribute("name",name);
            return "pages-blank";

        }else return "redirect:/login";

    }

    @RequestMapping("/events")
    public String events(Model model, HttpSession session){
        String emailUser = (String) session.getAttribute("email");
        if(emailUser != null && !emailUser.equals("")){
            String name = (String) session.getAttribute("name");
            model.addAttribute("name",name);
            return "pages-events";

        }else return "redirect:/login";

    }

    @RequestMapping("/profile")
    public String profile(Model model, HttpSession session){

        String emailUser = (String) session.getAttribute("email");
        if(emailUser != null && !emailUser.equals("")){
            String name = (String) session.getAttribute("name");
            model.addAttribute("name",name);

            Profile profile = profileRepository.findProfileByEmail(emailUser);
            model.addAttribute("role", profile.getRole());
            model.addAttribute("address", profile.getAddress());
            model.addAttribute("hometown", profile.getHometown());

            return "pages-profile";

        }else return "redirect:/login";




    }
    @RequestMapping("/salary-increase-form")
    public String salaryForm(Model model, HttpSession session){

        String emailUser = (String) session.getAttribute("email");
        if(emailUser != null && !emailUser.equals("")){
            String name = (String) session.getAttribute("name");
            String email = (String) session.getAttribute("email");
            model.addAttribute("name",name);

            Profile profile = profileRepository.findProfileByEmail(email);
            model.addAttribute("id", profile.getId());

            return "pages-salary-increase-form";

        }else return "redirect:/login";


    }




    @RequestMapping("/logoutButton")
    public String logoutButton(){

        return "test";
    }


    @GetMapping("/callEvent")
    public String showEvent(Model model){


        String s = webClientBuilder.build().get()
                .uri("http://event-service/testCall").retrieve()
                .bodyToMono(String.class) // convert x to instance of Timesheet
                .block();


        model.addAttribute("text",s);

        return "test";
    }


}
