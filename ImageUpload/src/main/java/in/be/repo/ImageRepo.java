package in.be.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.be.model.ImageEntity;

public interface ImageRepo extends JpaRepository<ImageEntity, Integer> {

}
