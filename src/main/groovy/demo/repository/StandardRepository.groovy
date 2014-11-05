package demo.repositoryimport org.springframework.data.mongodb.repository.MongoRepositoryimport org.springframework.data.rest.core.annotation.RepositoryRestResourceimport demo.domain.Customerimport demo.domain.Standard@RepositoryRestResource(path = "standards")
public interface StandardRepository extends MongoRepository<Standard, String> {
	}
