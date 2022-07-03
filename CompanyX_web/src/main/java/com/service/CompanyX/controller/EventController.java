package com.service.CompanyX.controller;


import com.service.CompanyX.model.*;
import com.service.CompanyX.repositories.AccountRepository;
import com.service.CompanyX.repositories.ProfileRepository;

import com.service.CompanyX.service.SalaryIncreaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EventController {

    SalaryLevel salaryLevel;
    String salary;

    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @RequestMapping(value = "/salary-increase", method = RequestMethod.POST)
    public String check(Model model, HttpSession session, @RequestParam(value = "idEmployee", required = false) Long id){

        String emailUser = (String) session.getAttribute("email");
        if(emailUser != null && !emailUser.equals("")){
            String name = (String) session.getAttribute("name");
            model.addAttribute("name",name);

            if(id == null){
                id = (Long) session.getAttribute("id");
                Profile profile = profileRepository.findProfileByEmail(emailUser);
                model.addAttribute("id",id);
                model.addAttribute("role", profile.getRole());
//                model.addAttribute("basicSalary","$1500");

//                // TODO
//                SalaryIncreaseService.service.getSalaryLevel(id).enqueue(new Callback<SalaryLevel>() {
//                    @Override
//                    public void onResponse(Call<SalaryLevel> call, Response<SalaryLevel> response) {
//                        SalaryLevel salaryLevel1 = response.body();
//                        System.out.println(salaryLevel1.toString());
//
//                        session.setAttribute("a","a");
//                        salary = salaryLevel1.getSalary()+"";
//                        System.out.println("1 salary: " + salary);
//                        //model.addAttribute("basicSalary", salary);
//
//                    }
//
//
//                    @Override
//                    public void onFailure(Call<SalaryLevel> call, Throwable throwable) {
//
//                    }
//                });


                Mono<SalaryLevel> responseObjectMono = webClientBuilder.build().get()
                        .uri("http://salary-increase-service/task/salary/" + id).retrieve()
                        .bodyToMono(SalaryLevel.class);
                SalaryLevel responseObject = responseObjectMono.share().block();
                model.addAttribute("basicSalary", "$"+responseObject.getSalary()+"");


                Mono<ResponseTask> responseObjectMono1 = webClientBuilder.build().get()
                        .uri("http://salary-increase-service/task/check/" + id).retrieve()
                        .bodyToMono(ResponseTask.class);
                ResponseTask responseObject1 = responseObjectMono1.share().block();
                Event event = responseObject1.getEvent();
                Timesheet timesheet = responseObject1.getTimesheet();
                Condition condition = responseObject1.getCondition();
                Boolean result = responseObject1.getResult();

                //2.
                long millis=System.currentTimeMillis();
                java.sql.Date date = new java.sql.Date(millis);
                model.addAttribute("regisTime",date+"");
                model.addAttribute("eventTime", event.getEndDate()+"");
//                if(date.before(event.getEndDate())){
//                    model.addAttribute("inTime", "YES");
//                }else{
//                    model.addAttribute("inTime", "NO");
//                }
                model.addAttribute("inTime", "YES");

                //3

                model.addAttribute("cWorkingTime", timesheet.getWorkingTime()+"h");
                model.addAttribute("nWorkingTime", condition.getWorkingTime()+"h");
                if(timesheet.getWorkingTime() >= condition.getWorkingTime()){
                    model.addAttribute("rWorkingTime", "YES");
                }else{
                    model.addAttribute("rWorkingTime", "NO");
                }

                model.addAttribute("cWarnings", timesheet.getWarnings()+"");
                model.addAttribute("nWarnings", condition.getWarnings()+"");
                if(timesheet.getWarnings() <= condition.getWarnings()){
                    model.addAttribute("rWarnings", "YES");
                }else{
                    model.addAttribute("rWarnings", "NO");
                }

                model.addAttribute("cCompletedProjects", timesheet.getCompletedProjects()+"");
                model.addAttribute("nCompletedProjects", condition.getCompletedProjects()+"");
                if(timesheet.getCompletedProjects() >= condition.getCompletedProjects()){
                    model.addAttribute("rCompletedProjects", "YES");
                }else{
                    model.addAttribute("rCompletedProjects", "NO");
                }

                model.addAttribute("cPerformanceScore", timesheet.getPerformanceScore()+"");
                model.addAttribute("nPerformanceScore", condition.getPerformanceScore()+"");
                if(timesheet.getPerformanceScore() >= condition.getPerformanceScore()){
                    model.addAttribute("rPerformanceScore", "YES");
                }else{
                    model.addAttribute("rPerformanceScore", "NO");
                }

                //4
                if(id == 1){
                    if(result){
                        model.addAttribute("sumResult", "Đủ điều kiện tăng lương");
                    }else{
                        model.addAttribute("sumResult", "Không đủ điều kiện tăng lương");
                    }
                }else{
                    model.addAttribute("sumResult", "Không đủ điều kiện tăng lương");
                }



            }


            //System.out.println("2 salary: " + salary);
            return "pages-event-progress";

        }else return "redirect:/login";


    }


}
