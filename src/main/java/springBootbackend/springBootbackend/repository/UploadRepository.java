package springBootbackend.springBootbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springBootbackend.springBootbackend.model.FileUploadResponse;

public interface UploadRepository extends JpaRepository<FileUploadResponse, Integer>{

}
