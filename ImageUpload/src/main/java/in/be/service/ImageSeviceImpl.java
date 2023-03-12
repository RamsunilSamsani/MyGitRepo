package in.be.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.be.model.ImageEntity;
import in.be.repo.ImageRepo;

@Service
public class ImageSeviceImpl implements IImageService {

	@Autowired
	 ImageRepo repo;
	
	@Override
	public String uploadImage(byte[] photo) {
		ImageEntity entity=new ImageEntity();
		entity.setPhoto(photo);
		int id=repo.save(entity).getId();
		return "Image saved succesfully :"+id;
	}
	
	@Override
	public byte[] getImageById(Integer id) throws Exception {
	    Optional<ImageEntity> imageEntityOptional = repo.findById(id);
	    if (imageEntityOptional.isPresent()) {
	      return imageEntityOptional.get().getPhoto();
	    } else {
	      throw new Exception("Image not found with id " + id);
	    }
	  }

}
