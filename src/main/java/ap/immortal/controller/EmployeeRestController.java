package ap.immortal.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ap.immortal.model.Employee;
import ap.immortal.service.EmployeeService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/v2/api")
@AllArgsConstructor
public class EmployeeRestController {
	
	private final EmployeeService employeeService;
	
	@GetMapping("/hello")
	public String greeting() {
		return "Wellcome to Spring-Boot REST-API Employee's CRUD Operation Project...";
	}
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getaAllEmployees() {
		List<Employee> allEmployee = employeeService.findAllEmployees();
		return new ResponseEntity<>(allEmployee,HttpStatus.OK);
	}
	
	@GetMapping("/employees/{employeesId}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer employeesId) {
		Employee employee = employeeService.findEmployeeById(employeesId);
		return new ResponseEntity<>(employee,HttpStatus.OK);
		
	}
	
	@PostMapping("/employees")
	public void addNewEmployee(@RequestBody @Validated Employee employee) {
		employeeService.insert(employee);		
	}
	
	@PutMapping("/employees/{employeesId}")
	public void updateEmployee(@PathVariable Integer employeesId, @RequestParam Map<String, String> params) {
		employeeService.updateEmployee(employeesId, params);
		
	}
	
	@DeleteMapping("/employees/{employeesId}")
	public void deleteEmployee(@PathVariable Integer employeesId) {
		employeeService.deleteEmployee(employeesId);
		
	}
	
	@DeleteMapping("/employees")
	public void deleteEmployee() {
		employeeService.deleteAll();
		
	}

}
