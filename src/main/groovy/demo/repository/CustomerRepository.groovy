package demo.repositoryimport org.springframework.data.mongodb.repository.MongoRepositoryimport org.springframework.data.rest.core.annotation.RepositoryRestResourceimport demo.domain.Customer@RepositoryRestResource(path = "people")
public interface CustomerRepository extends MongoRepository<Customer, String> {
	Customer findByFirstName(String firstName)	List<Customer> findByLastName(String lastName)}
