package in.nic.mp.iservice;

import java.util.List;

import in.nic.mp.model.Employee;

public interface IEmpService {
	
	public Integer saveEmp(Employee e);
	
	public List<Employee> getAllEmps(); 
	
	public void deleteEmp(Integer id);
	
	public Employee getOneEmp(Integer id);
	
	public void updateEmp(Employee e);
	
	boolean isEmployeeExistByEmail(String email);

}
