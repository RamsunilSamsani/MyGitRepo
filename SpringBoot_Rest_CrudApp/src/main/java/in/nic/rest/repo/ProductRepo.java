package in.nic.rest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import in.nic.rest.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

	@Modifying
	@Query("UPDATE Product SET prodCode=:prodCode WHERE prodId=:prodId")
	public Integer updateProdCodeById(Integer prodId,String prodCode);
}
