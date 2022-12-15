package in.nic.rest.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import in.nic.rest.exception.ProductNotFoundException;
import in.nic.rest.iservice.IProdService;
import in.nic.rest.model.Product;
import in.nic.rest.repo.ProductRepo;

@Service
public class ProductServiceImpl implements IProdService {

	@Autowired
	private ProductRepo repo;
	
	@Override
	public Integer saveProduct(Product p) {
		//JDK 10 : local variable type inference
		//Best DataType is selected by Java compiler
		var cost = p.getProdCost();
		var gst = cost * 12.0/100;
		var disc = cost * 20.0/100;
		
		p.setProdGst(gst);
		p.setProdDisc(disc);
		p=repo.save(p);
		return p.getProdId();
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> list=repo.findAll();
//		return repo.findAll();
		return list;
	}
	
	@Override
	@Cacheable(value = "products",key = "#id")
	public Product getOneProduct(Integer id) {
		// try to read the object from db
		Optional<Product> opt = repo.findById(id);
		// if there return the product
/*		if (opt.isPresent()) {
			Product p = opt.get();
			return p;
		} else { //else throw excpetion
			throw new ProductNotFoundException("Product "+id+" not exist");
		} */
		return repo.findById(id).orElseThrow(
				() -> new ProductNotFoundException("Product "+id+" not exist")
				);
	}
	
	@Override
	@CacheEvict(value = "products",allEntries = true)
	public void deleteProduct(Integer id) {
		Product p=getOneProduct(id);
		repo.delete(p);
	}
	
	@Override
	public boolean isProdExist(Integer id) {
		boolean result=repo.existsById(id);
		return result;
	}
	
	@Override
	@Transactional
	@CachePut(value = "products",key="#id")
	public void updateProd(Product p) {
		//get the prod cost
		var cost = p.getProdCost();
		//cal the gst and discount
		var gst = cost * 12.0/100;
		var disc = cost * 20.0/100;
		//set the values 
		p.setProdGst(gst);
		p.setProdDisc(disc);
		//save the product
		repo.save(p);
		
	}
	
	@Override
	@Transactional
	public Integer updateProdCodeById(Integer prodId, String prodCode) {
		return repo.updateProdCodeById(prodId, prodCode);
	}

}
