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
//		employeeRepository.save(convertToEntity(employeeDto));

		// Saving dummy data
		Employee dto1 = convertToEntity(new EmployeeDto("Ajit1", "Kumar", "akn1@mail.com", "1234567890"));
		Employee dto2 = convertToEntity(new EmployeeDto("Ajit2", "Kumar", "akn2@mail.com", "1234567890"));
		Employee dto3 = convertToEntity(new EmployeeDto("Ajit3", "Kumar", "akn3@mail.com", "1234567890"));
		Employee dto4 = convertToEntity(new EmployeeDto("Ajit4", "Kumar", "akn4@mail.com", "1234567890"));
		Employee dto5 = convertToEntity(new EmployeeDto("Ajit5", "Kumar", "akn5@mail.com", "1234567890"));
		Employee dto6 = convertToEntity(new EmployeeDto("Ajit6", "Kumar", "akn6@mail.com", "1234567890"));
		Employee dto7 = convertToEntity(new EmployeeDto("Ajit7", "Kumar", "akn7@mail.com", "1234567890"));
		Employee dto8 = convertToEntity(new EmployeeDto("Ajit8", "Kumar", "akn8@mail.com", "1234567890"));
		Employee dto9 = convertToEntity(new EmployeeDto("Ajit9", "Kumar", "akn9@mail.com", "1234567890"));
		Employee dto10 = convertToEntity(new EmployeeDto("Ajit10", "Kumar", "akn10@mail.com", "1234567890"));

		employeeRepository.saveAll(List.of(dto1, dto2, dto3, dto4, dto5, dto6, dto7, dto8, dto9, dto10));
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
