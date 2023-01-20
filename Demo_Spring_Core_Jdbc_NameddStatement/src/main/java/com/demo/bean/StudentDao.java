package com.demo.bean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class StudentDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int insertStudent(Student e) {
		String query="insert into student values (:rollNo,:name,:marks)";  
		  
		Map<String,Object> map=new HashMap<String,Object>();  
		map.put("rollNo",e.getRollNo());  
		map.put("name",e.getName());  
		map.put("marks",e.getMarks());  
		  
		jdbcTemplate.execute(query,map,new PreparedStatementCallback() {  
		    @Override  
		    public Object doInPreparedStatement(PreparedStatement ps)  
		            throws SQLException, DataAccessException {  
		        return ps.executeUpdate();  
		    }  
		});  
		return 1; 
	}

	public int updateStudent(Student s) {
		String query = "update student set name=?, marks=? where rollNo=?";
		jdbcTemplate.execute(query, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException {
				ps.setString(1, s.getName());
				ps.setInt(2, s.getMarks());
				ps.setInt(3, s.getRollNo());

				return ps.execute();
			}
		});
		System.out.println("Data updated Successfully");
		return 1;
	}

	public int deleteStudent(int id) {
		String query = "delete from student where rollNo=?";
		jdbcTemplate.execute(query, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException {
				// Student s = new Student();
				ps.setInt(1, id);

				return ps.execute();
			}
		});
		System.out.println("Data deleted Successfully");
		return 1;
	}

	public List<Student> getAllStudents() {
		return jdbcTemplate.query("select * from student", new ResultSetExtractor<List<Student>>() {
			@Override
			public List<Student> extractData(ResultSet rs) throws SQLException, DataAccessException {

				List<Student> list = new ArrayList<Student>();
				while (rs.next()) {
					Student e = new Student();
					e.setRollNo(rs.getInt(1));
					e.setName(rs.getString(2));
					e.setMarks(rs.getInt(3));
					list.add(e);
				}
				return list;
			}
		});
	}

	public List<Student> getAllStudentsRowMapper() {
		return jdbcTemplate.query("select * from student", new RowMapper<Student>() {
			@Override
			public Student mapRow(ResultSet rs, int rownumber) throws SQLException {
				Student e = new Student();
				e.setRollNo(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setMarks(rs.getInt(3));
				return e;
			}
		});
	}
}
