package com.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;
import com.employee.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employee")
	public String homePage(Model model) {

		model.addAttribute("pageTitle", "Employee Management System");

		return listByPage(1, model, "firstName", "asc", null);
	}

	@GetMapping("/employee/page/{pageNum}")
	public String listByPage(@PathVariable int pageNum, Model model, @RequestParam(required = false) String sortField,
			@RequestParam(required = false) String sortDir, @RequestParam(required = false) String keyword) {

		Page<Employee> page = employeeService.listByPage(pageNum, sortField, sortDir, keyword);
		List<Employee> listAll = page.getContent();

		long startCount = (pageNum - 1) * EmployeeService.EMPLOYEE_PER_PAGE + 1;
		long endCount = startCount + EmployeeService.EMPLOYEE_PER_PAGE - 1;

		if (endCount > page.getTotalElements()) {
			endCount = page.getTotalElements();
		}

		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";

		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("listAll", listAll);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", reverseSortDir);
		model.addAttribute("keyword", keyword);

		return "employee";
	}

	@GetMapping("/employee/new")
	public String createEmployee(Model model) {

		model.addAttribute("pageTitle", "Create Employee");

		model.addAttribute("employeedto", new EmployeeDto());

		return "employee_form";
	}

	@PostMapping("/employee/save")
	public String saveEmployee(EmployeeDto employeeDto, RedirectAttributes redirectAttributes) {

		employeeService.saveEmployee(employeeDto);

		if (employeeDto.getId() == null) {
			redirectAttributes.addFlashAttribute("message", "Successfully created employee");
		} else {
			redirectAttributes.addFlashAttribute("message", "Successfully updated employee");
		}

		return "redirect:/employee";
	}

	@GetMapping("/employee/edit")
	public String editEmployee(@RequestParam Integer id, Model model) {

		EmployeeDto employeeDto = employeeService.getEmployeeById(id);
		model.addAttribute("employeedto", employeeDto);

		return "employee_form";
	}

	@GetMapping("/employee/delete")
	public String deleteEmployee(@RequestParam Integer id, RedirectAttributes redirectAttributes) {

		employeeService.deleteEmployee(id);

		redirectAttributes.addFlashAttribute("message", "Successfully deleted employee ID - " + id);

		return "redirect:/employee";
	}

}
