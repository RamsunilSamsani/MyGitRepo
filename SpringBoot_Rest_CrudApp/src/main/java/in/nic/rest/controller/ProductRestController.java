package in.nic.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nic.rest.exception.ProductNotFoundException;
import in.nic.rest.iservice.IProdService;
import in.nic.rest.model.Product;
import in.nic.rest.util.ProductUtil;

@RestController
@RequestMapping("/products")
public class ProductRestController {

	@Autowired
	private IProdService service;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveProduct(@RequestBody Product prod) {
		Integer id=service.saveProduct(prod);
		return new ResponseEntity<String>("Product saved ::"+id,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity< List<Product>> getAllProducts() {
		List<Product> list=service.getAllProducts();
		return new ResponseEntity<List<Product>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/one/{id}")
	public ResponseEntity<?> getOneProduct(@PathVariable Integer id) {
		ResponseEntity<?> resp = null;
		try {
			Product p = service.getOneProduct(id);
			resp = new ResponseEntity<Product>(p, HttpStatus.OK);
		} catch (ProductNotFoundException pne) {
			// send custom exception handler
			throw pne;
		} catch (Exception e) {
			resp = new ResponseEntity<String>("unable to fetch Product", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
		ResponseEntity<String> resp=null;
		try {
		service.deleteProduct(id);
		resp= ResponseEntity.ok("Product deleted");
		} catch (Exception e) {
			resp=new ResponseEntity<String>("Unable to Delete product",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<String> updateProd(@PathVariable Integer id,@RequestBody Product p) {
		
			Product db = service.getOneProduct(id);
			ProductUtil.copyNonNullValues(db, p);
			service.updateProd(db);

			return ResponseEntity.ok("Product Updated !!");
	}
	
	@PatchMapping("/updatePcode/{id}/{code}")
	public ResponseEntity<String> updateProdCode(@PathVariable Integer id,
																									@PathVariable String code) {
		service.updateProdCodeById(id, code);
		return ResponseEntity.ok("Product Code updated !!");
	}
	
}
