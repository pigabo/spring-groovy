package demo.repositoryimport org.springframework.data.mongodb.repository.MongoRepositoryimport org.springframework.data.rest.core.annotation.RepositoryRestResourceimport demo.domain.Course@RepositoryRestResource(path = "courses")
public interface CourseRepository extends MongoRepository<Course, String> {
	}
