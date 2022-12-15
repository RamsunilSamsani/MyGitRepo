package in.nic.rest.iservice;

import java.util.List;

import in.nic.rest.model.Product;

public interface IProdService {
	Integer saveProduct(Product p);
	List<Product> getAllProducts();
	Product getOneProduct(Integer id);
	void deleteProduct(Integer id);
	boolean isProdExist(Integer id);
	void updateProd(Product p);
	Integer updateProdCodeById(Integer prodId,String prodCode);
}
