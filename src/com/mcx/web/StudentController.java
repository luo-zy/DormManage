package com.mcx.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mcx.model.Student;
import com.mcx.service.IStudentService;

@Controller
public class StudentController {

	@Autowired
	private IStudentService studentService;
	
	@RequestMapping("/studentList")
	public ModelAndView userList(Model model){
		List<Student> students = studentService.getUserList();
		model.addAttribute("studentList", students);
		return new ModelAndView("student/list","model",model);
	}
}
