package in.be.service;

import in.be.model.ImageEntity;

public interface IImageService {
	String uploadImage(byte[] photo);
	public byte[] getImageById(Integer id)throws Exception;
}
