package com.employee.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ModelMapper modelMapper;

	public static final int EMPLOYEE_PER_PAGE = 4;

	// List all employees
	public List<EmployeeDto> listAll() {
		List<Employee> listAllEmployee = employeeRepository.findAll();

		List<EmployeeDto> employeeDtos = new ArrayList<>();

		listAllEmployee.forEach(e -> {
			employeeDtos.add(convertToDto(e));
		});

		return employeeDtos;
	}

	public Page<Employee> listByPage(int pageNum, String sortField, String sortDir, String keyword) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNum - 1, EMPLOYEE_PER_PAGE, sort);

		if (keyword != null) {
			return employeeRepository.findAll(keyword, pageable);
		}

		return employeeRepository.findAll(pageable);
	}

	// Save employee details
	public void saveEmployee(EmployeeDto employeeDto) {
		employeeRepository.save(convertToEntity(employeeDto));
	}

	// Get employee by id
	public EmployeeDto getEmployeeById(Integer id) {
		Employee findById = employeeRepository.findById(id).get();
		return convertToDto(findById);
	}

	// Delete employee
	public void deleteEmployee(Integer id) {
		employeeRepository.deleteById(id);
	}

	// Convert entity to dto
	private EmployeeDto convertToDto(Employee employee) {
		EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
		return employeeDto;
	}

	// Convert dto to entity
	private Employee convertToEntity(EmployeeDto employeeDto) {
		Employee employee = modelMapper.map(employeeDto, Employee.class);
		return employee;
	}

}
