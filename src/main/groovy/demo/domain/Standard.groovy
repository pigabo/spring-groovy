package demo.domain

import java.util.List;

import javax.validation.constraints.NotNull

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document	
class Standard {
	
	@Id
	String _id
	
	String id
	
	String name
	
	List<Datatype> dataypes = []
	
}
