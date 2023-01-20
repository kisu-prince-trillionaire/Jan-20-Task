package com.demo.Demo_Spring_Core_Jdbc;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.demo.bean.JdbcConfig;
import com.demo.bean.Student;
import com.demo.bean.StudentDao;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfig.class);
		StudentDao dao = (StudentDao) context.getBean("edao");

		// Insert into student
		Student s = new Student();
		s.setRollNo(129);
		s.setName("Ramu Sharma");
		s.setMarks(87);
		int status1 = dao.insertStudent(s);
		System.out.println(status1);

		// Update student
		int status2 = dao.updateStudent(new Student(102, "Rahul Kumar", 98));
		System.out.println(status2);

		// Delete student
		int status3 = dao.deleteStudent(101);
		System.out.println(status3);

		// Select All Student using ResultSet interface
		List<Student> list = dao.getAllStudents();
		for (Student e : list)
			System.out.println(e);

		// Select all students using RowMapper
		List<Student> list1 = dao.getAllStudentsRowMapper();
		for (Student e : list1)
			System.out.println(e);
		
		//Select All students
		List<Student> students = dao.getAllStudent();
		for(Student sl : students)
			System.out.println(sl);
		
		//Select one student using RowMapper
		Student student = dao.getStudent(102);
		System.out.println(student);
		((AbstractApplicationContext) context).close();
	}
}
