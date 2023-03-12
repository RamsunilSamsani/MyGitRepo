package in.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import in.be.model.ImageEntity;
import in.be.service.IImageService;

@RestController
public class ImageController {

	@Autowired
	private IImageService service;
	
	@PostMapping("/upload")
	public String uploadImage(@RequestParam("file") MultipartFile file) {

		
		if (file.isEmpty()) {
			return "Please select a file to upload.";
		}

		try {
			byte[] bytes = file.getBytes();
			// code to save the file goes here
			service.uploadImage(bytes);
			return "File uploaded successfully!";
		} catch (Exception e) {
			return "Failed to upload file: " + e.getMessage();
		}
	}
	
	@GetMapping("/getImage")
	public String getImage() {
	return "image retrived";
	}
	
	
	@GetMapping("/getImage/{id}")
    public ResponseEntity<byte[]> getImageById(@PathVariable Integer id) throws Exception {
       byte[] image=service.getImageById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(image.length);
        return new ResponseEntity<>( headers, HttpStatus.OK);
    }
}
