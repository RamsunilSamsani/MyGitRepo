package in.nic.mp.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import in.nic.mp.exception.EmployeeNotFoundException;
import in.nic.mp.iservice.IEmpService;
import in.nic.mp.model.Employee;
import in.nic.mp.repo.EmployeeRepository;

@Service
public class EmpServiceImpl implements IEmpService {

	@Autowired
	private EmployeeRepository repo;
	
	@Override
	public Integer saveEmp(Employee e) {
		//calculations
		double esal=e.getEsal();
		double hra=esal*20/100.0;
		double da = esal * 10/100.0;
		e.setHra(hra);
		e.setDa(da);
		//save employe
		e=repo.save(e);
		return e.getEid();
	}
	
	@Cacheable(value = "employees",key = "#p0")
	@Override
	public List<Employee> getAllEmps() {
//		List<Employee> empList=repo.findAll();
//		return empList;
		return repo.findAll();
	}
	
	
	@Override
	public void deleteEmp(Integer id) {
		repo.deleteById(id);
	}
	
	
	@Override
	public Employee getOneEmp(Integer id) {
		Optional<Employee> opt=repo.findById(id);
		if(opt.isPresent()) {
			Employee e=opt.get();
			return e;
		}else {
			throw new EmployeeNotFoundException("Employee '"+id+"' Not Exist");
		}
		
	}
	
	@Override
	public void updateEmp(Employee e) {
			if(repo.existsById(e.getEid()))	 {
				//calculations
				double esal=e.getEsal();
				double hra=esal*20/100.0;
				double da = esal * 10/100.0;
				e.setHra(hra);
				e.setDa(da);
				//save employe
				repo.save(e);
			} else {
				///throw Exception;
			}
			
	}
	
	@Override
	public boolean isEmployeeExistByEmail(String email) {
//		int count = repo.getEmailCount(email);
//		if (count > 0)
//			return false;
//		else
//			return true;
		return repo.getEmailCount(email)>0;
	}

}
