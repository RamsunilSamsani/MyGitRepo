package in.nic.mp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import in.nic.mp.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
 
	@Query("SELECT COUNT(email) FROM Employee WHERE email=:email")
	public Integer getEmailCount(String email);
	
}
