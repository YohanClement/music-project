package fr.formation.inti.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "employee")
public class Employees {
	private Integer empid;
	private LocalDateTime enddate;
	private String firstname;
	private String lastname;
	private Date startdate;
	private String title;
	private Integer assignedid;
	private Integer deptid;
	private Integer superiorid;
	
	
	
	public Employees() {
		// TODO Auto-generated constructor stub
	}





	@Override
	public String toString() {
		return "Employees [empid=" + empid + ", enddate=" + enddate + ", firstname=" + firstname + ", lastname="
				+ lastname + ", startdate=" + startdate + ", title=" + title + ", assignedid=" + assignedid
				+ ", deptid=" + deptid + ", superiorid=" + superiorid + "]";
	}





	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMP_ID")
	public Integer getEmpid() {
		return empid;
	}



	public void setEmpid(Integer empid) {
		this.empid = empid;
	}


	
	@Column(name = "END_DATE")
	public LocalDateTime getEnddate() {
		return enddate;
	}



	public void setEnddate(LocalDateTime enddate) {
		this.enddate = enddate;
	}


	@Column(name = "FIRST_NAME")
	public String getFirstname() {
		return firstname;
	}



	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	@Column(name = "LAST_NAME")
	public String getLastname() {
		return lastname;
	}



	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "START_DATE")
	public Date getStartdate() {
		return startdate;
	}



	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}


	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	@Column(name = "ASSIGNED_BRANCH_ID")
	public Integer getAssignedid() {
		return assignedid;
	}


	public void setAssignedid(Integer assignedid) {
		this.assignedid = assignedid;
	}


	@Column(name = "DEPT_ID")
	public Integer getDeptid() {
		return deptid;
	}



	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}


	@Column(name = "SUPERIOR_EMP_ID")
	public Integer getSuperiorid() {
		return superiorid;
	}



	public void setSuperiorid(Integer superiorid) {
		this.superiorid = superiorid;
	}
	
	
}



