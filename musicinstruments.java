package fr.formation.inti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "musicinstruments")
public class musicinstruments {
	private Integer instrid;
	private String instrname;
	private String instrtype;
	
	
	
	
	
	
	

@Override
	public String toString() {
		return "musicinstruments [instrid=" + instrid + ", instrname=" + instrname + ", instrtype=" + instrtype + "]";
	}
public musicinstruments() {
	// TODO Auto-generated constructor stub
}
	public musicinstruments(String instrname) {
		super();
		this.instrname = instrname;
	}
	public musicinstruments(String instrname, String instrtype) {
		super();
		this.instrname = instrname;
		this.instrtype = instrtype;
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Instr_id")
	public Integer getInstrid() {
		return instrid;
	}
	public void setInstrid(Integer instrid) {
		this.instrid = instrid;
	}
	
	@Column(name = "Instr_Name")
	public String getInstrname() {
		return instrname;
	}
	public void setInstrname(String instrname) {
		this.instrname = instrname;
	}
	
	@Column(name = "Instr_type")
	public String getInstrtype() {
		return instrtype;
	}
	public void setInstrtype(String instrtype) {
		this.instrtype = instrtype;
	}
	
	
	
}
