package demo.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document;

@Document
class Customer {
	
	@Id
	String id
		
	String firstName, lastName
	
	@DBRef
	List<Course> courses = []
	
	@Override
	public String toString() {
		String.format("Customer[id=$id, firstName='$firstName', lastName='$lastName']")
	}
}
