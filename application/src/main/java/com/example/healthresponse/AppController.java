package com.example.healthresponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;


@Controller
public class AppController {

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private AmbulanceRequestRepository ambulanceRequestRepository;

    @Autowired
    private ERPatientRepository erPatientRepository;

    @Autowired
    private StaffRepository staffRepository;

    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("login", new Login());
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute Login login, Model model){
        model.addAttribute("login", login);
        List<User> userList = registerRepository.findAll();
        Integer userCount = userList.size();
        Boolean found = false;
        for (int i = 0; i < userCount; i++) {
            if(userList.get(i).getUsername().equals(login.getUsername()) && userList.get(i).getPassword().equals(login.getPassword())){
                found = true;
            }
        }
        if(found == true){
            return "usermenu";
        }else{
        return "loginfail";
        }
    }

    @GetMapping("/usermenu")
    public String userMenuLoad() {
        return "userMenu";}

    @GetMapping("/ambulancerequest")
    public String ambulanceRequestForm(Model model){
        model.addAttribute("ambulancerequest", new AmbulanceRequest());
        return "ambulancerequest";
    }

    @PostMapping("/ambulancerequest")
    public String ambulanceRequestSubmit(@ModelAttribute AmbulanceRequest ambulancerequest, Model model){
        model.addAttribute("ambulancerequest", ambulancerequest);
        ambulanceRequestRepository.save(ambulancerequest);
        return "requested";}

    @GetMapping("/ercapacity")
    public String erCapacityForm(Model model){
        model.addAttribute("ercapacity", new ERCapacity());
        return "ercapacity";
    }

    @PostMapping("/ercapacity")
    public String erCapacitySubmit(@ModelAttribute ERCapacity ercapacity, Model model){
        model.addAttribute("ercapacity", ercapacity);
        if(ercapacity.getName().equals("Royal Infirmary")){
            List<ERPatient> erPatientList = erPatientRepository.findAll();
            Integer registerSize = erPatientList.size();
            ercapacity.setRegisterSize(registerSize);
        }else if(ercapacity.getName().equals("Queen Elizabeth")){
            ercapacity.setRegisterSize(20);
        }
        return "ercapacityresults";
    }

    @GetMapping("/stafflogin")
    public String staffLoginForm(Model model){
        model.addAttribute("stafflogin", new Staff());
        return "stafflogin";
    }

    @PostMapping("/stafflogin")
    public String staffLoginSubmit(@ModelAttribute Staff staff, Model model){
        model.addAttribute("staff", staff);
        List<Staff> staffList = staffRepository.findAll();
        Integer staffCount = staffList.size();
        Integer staffType = 2;
        Boolean found = false;
        for(int i=0; i<staffCount; i++){
            if(staffList.get(i).getUsername().equals(staff.getUsername()) && staffList.get(i).getPassword().equals(staff.getPassword())){
                if(staffList.get(i).getStaffType() == 0){
                    staffType = 0;
                    found = true;
                }else if(staffList.get(i).getStaffType() == 1){
                    staffType = 1;
                    found = true;
                }
            }
        }
        if(found == true && staffType == 0){
            return "receivermenu";
        }else if(found == true && staffType == 1){
            return "staffmenu";
        }else{
            return "staffloginfail";
        }
    }

    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("register", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute User register, Model model){
        model.addAttribute("register", register);
        registerRepository.save(register);
        return "registered";
    }

    @GetMapping("/receivermenu")
    public String receiverMenuLoad() {
        return "receivermenu";
    }

    @GetMapping("/setactive")
    public String answerRequestLoad(Model model) {
        List<AmbulanceRequest> ambulanceRequestList = ambulanceRequestRepository.findAll();
        List<AmbulanceRequest> incomingRequestList = new ArrayList<AmbulanceRequest>();
        for(int i=0; i<ambulanceRequestList.size();i++){
            if(ambulanceRequestList.get(i).getActiveStatus() != 1){
                incomingRequestList.add(ambulanceRequestList.get(i));
            }
        }
        model.addAttribute("incomingRequestList", incomingRequestList);
        model.addAttribute("setactive", new SetActive());
        return "setactive";}

    @PostMapping("/setactive")
    public String setActiveSubmit(@ModelAttribute SetActive setActive, Model model) {
        model.addAttribute("setactive", setActive);
        List<AmbulanceRequest> ambulanceRequestList = ambulanceRequestRepository.findAll();
        Boolean success = false;
        for(int i=0; i<ambulanceRequestList.size(); i++){
            if(ambulanceRequestList.get(i).getId().equals(setActive.getId())){
                AmbulanceRequest activeRequest = ambulanceRequestList.get(i);
                activeRequest.setActiveStatus(1);
                activeRequest.setAmbulance(setActive.getAmbulance());
                activeRequest.setHospital(setActive.getHospital());
                ambulanceRequestRepository.save(activeRequest);
                success=true;
            }
        }
        if(success==true){
            return "setactivesuccess";
        }else{
            return "setactivefail";
        }
    }

    @GetMapping("/staffmenu")
    public String staffMenuLoad() {
        return "staffmenu";}

    @GetMapping("/checkin")
    public String checkInForm(Model model) {
        model.addAttribute("checkin", new ERPatient());
        return "checkin";}

    @PostMapping("/checkin")
    public String checkInSubmit(@ModelAttribute ERPatient checkIn, Model model){
        model.addAttribute("checkin", checkIn);
        erPatientRepository.save(checkIn);
        return "checkedin";
    }

    @GetMapping("/checkout")
    public String checkOutForm(Model model) {
        List<ERPatient> erPatientList = erPatientRepository.findAll();
        model.addAttribute("erPatientList", erPatientList);
        model.addAttribute("checkout", new CheckOut());
        return "checkout";}

    @PostMapping("/checkout")
    public String checkOutSubmit(@ModelAttribute CheckOut checkOut, Model model) {
        model.addAttribute("checkout", checkOut);
        List<ERPatient> erPatientList = erPatientRepository.findAll();
        Boolean found = false;
        for(int i=0; i<erPatientList.size(); i++){
            if(checkOut.getId().equals(erPatientList.get(i).getId())){
                found = true;
            }
        }
        if(found == true) {
            erPatientRepository.deleteById(checkOut.getId());
            return "checkedout";
        }else{
            return "checkoutfail";
        }
    }

    @GetMapping("/activeambulances")
    public String activeAmbulancesForm(Model model) {
        List<AmbulanceRequest> ambulanceRequestList = ambulanceRequestRepository.findAll();
        List<AmbulanceRequest> activeRequestList = new ArrayList<AmbulanceRequest>();
        for(int i = 0; i<ambulanceRequestList.size(); i++){
            if(ambulanceRequestList.get(i).getActiveStatus() == 1){
                activeRequestList.add(ambulanceRequestList.get(i));
            }
        }
        model.addAttribute("activeRequestList", activeRequestList);
        model.addAttribute("cancelAmbulance", new CancelAmbulance());
        return "activeambulances";}

    @PostMapping("/activeambulances")
    public String activeAmbulancesSubmit(@ModelAttribute CancelAmbulance cancelAmbulance, Model model) {
        model.addAttribute("cancelAmbulance", cancelAmbulance);
        List<AmbulanceRequest> ambulanceRequestList = ambulanceRequestRepository.findAll();
        List<AmbulanceRequest> activeRequestList = new ArrayList<AmbulanceRequest>();
        for(int i = 0; i<ambulanceRequestList.size(); i++){
            if(ambulanceRequestList.get(i).getActiveStatus() == 1){
                activeRequestList.add(ambulanceRequestList.get(i));
            }
        }
        Boolean found = false;
        for(int i = 0; i<activeRequestList.size(); i++){
            if(cancelAmbulance.getId().equals(activeRequestList.get(i).getId())){
                found = true;
            }
        }
        if(found==true){
            ambulanceRequestRepository.deleteById(cancelAmbulance.getId());
            return "ambulancecancelled";
        }else{
            return "ambulancecancelfail";
        }
    }
}
