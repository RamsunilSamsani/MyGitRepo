package in.nic.mp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import in.nic.mp.exception.EmployeeNotFoundException;
import in.nic.mp.iservice.IEmpService;
import in.nic.mp.model.Employee;
import in.nic.mp.validator.EmployeeValidator;

@Controller
public class EmpController {
	
	@Autowired
	private IEmpService service;
	@Autowired
	private EmployeeValidator validator;
	//home page
	@GetMapping("/")
	public String showHome() {
//		System.out.println("EmpController.showHome()");
		return "Home";
	}
	
	//1. Show Register Page
		@GetMapping("/register")
		public String showReg() {
			System.out.println("EmpController.showReg()");
			return "EmpReg";
		}
		
		
		//2. Save Employee Data
		@PostMapping("/save")
		public String saveEmp(
				@ModelAttribute Employee employee,
				Model model,BindingResult errors) 
		{
			
			if(validator.supports(employee.getClass()))
			    validator.validate(employee, errors);
			//call service layer
			Integer eid = service.saveEmp(employee);
			String message = " Employee '"+eid+"' saved ";
			model.addAttribute("msg", message);
			return "EmpReg";
		}
		
		@GetMapping("/all")
		public String showAllEmps(Model model) {
			//call service to fetch data
			List<Employee> list = service.getAllEmps();
			//send to UI
			model.addAttribute("list", list);
			return "EmpData";
		}
		
		@GetMapping("/delete")
		public String removeEmp(@RequestParam Integer eid, Model model) {
			//delete data based on Id
			service.deleteEmp(eid);
			String message = " Employee '"+eid+"' deleted ";
			
			//fetch latest data and display a ui
			List<Employee> list=service.getAllEmps();
			model.addAttribute("list",list);
			model.addAttribute("msg", message);
			return "EmpData";
		//	return "redirect:all";
		}
		
		@GetMapping("/edit")
		public String editEmp(@RequestParam Integer eid,Model model) {
			//call service method
			try {
			Employee emp =  service.getOneEmp(eid);
			//send the results to ui
			model.addAttribute("employee", emp);
			} catch (EmployeeNotFoundException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "EmpEditPage";
		}
		
		@PostMapping("/update")
		public String showUpdEmps(
				//read form data
				@ModelAttribute Employee e,
				Model model) {
			//call service to fetch data
			service.updateEmp(e);
			//get all emps data
			List<Employee> list=service.getAllEmps();
			model.addAttribute("list",list);
			//send message to ui page
			String message = " Employee '"+e.getEid()+"' updated ";
			model.addAttribute("msg",message);
			//back to data page
			return "EmpData";
		}
		
		@GetMapping("/checkEmail")
		public @ResponseBody String validateEmail(@RequestParam String email)
		{
			System.out.println("EmpController.validateEmail()");
			String message = "";
			if(service.isEmployeeExistByEmail(email)) {
				message = email+" Already exist";
			}
			return message;
		}
		
}
