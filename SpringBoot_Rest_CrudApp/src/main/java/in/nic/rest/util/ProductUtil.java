package in.nic.rest.util;

import in.nic.rest.model.Product;

public interface ProductUtil {
	
	public static void copyNonNullValues(Product db,Product request) {
		
		//copy non-null values
		if(request.getProdCode()!=null) 
			db.setProdCode(request.getProdCode());
		if(request.getProdCost()!=null)
			db.setProdCost(request.getProdCost());
	}
}
