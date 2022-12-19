package org.pizzeria.crud.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class Role {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@NotEmpty
	@Column(unique = true)
	private String name;
	
	
	public Role() {}
	public Role(String name) {
		setName(name);
	}
	
	
//  ID --------------------------------------
	public int getId() { return id; }
	
//  Name -------------------------------------
	public String getName() { return name; }
	
	public void setName(String name) {
		this.name = name;
	}

// 	Controllo tipo e hashcode ----------------
	@Override
	public int hashCode() {		
		return getId();
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Role)) return false;
		
		return obj.hashCode() == hashCode();
	}

//  ---------------------------------------------------
	
	@Override
	public String toString() {
		return "ID: " + getId() 
				+ "Name: " + getName();
	}
}
