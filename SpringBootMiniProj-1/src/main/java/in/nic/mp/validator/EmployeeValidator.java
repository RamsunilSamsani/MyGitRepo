package in.nic.mp.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import in.nic.mp.model.Employee;

@Component("empValidator")
public class EmployeeValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(Employee.class); //useful check whether expected model class is recieved to 
		                                                                                                   //validator class or not
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("EmployeeValidator.validate()");
		//type casting on command class obj
		Employee emp=(Employee) target;
		//write the form validation logic
		if(emp.getEname()==null || emp.getEname().isEmpty()) { //required rule
			errors.rejectValue("ename","emp.ename.required"); //error obj holds property name, error message is collected from props file
		} else if (emp.getEname().length()>5 || emp.getEname().length()<10) {
			errors.rejectValue("ename", "emp.ename.length");
		}
		
		if(emp.getEsal()==null ) { //required rule
			errors.rejectValue("esal","emp.sal.required"); //error obj holds property name, error message is collected from props file
		} else if (emp.getEsal()>10000 || emp.getEsal()<100000) {
			errors.rejectValue("esal","emp.sal.range"); 
		}
		
//		if(emp.getEname()==null || emp.getEname().isEmpty()) { //required rule
//			errors.rejectValue("",""); //error obj holds property name, error message is collected from props file
//		} else if (emp.getEname().length()<5 || emp.getEname().length()>10) {
//			errors.rejectValue("", "");
//		}
	}

}
