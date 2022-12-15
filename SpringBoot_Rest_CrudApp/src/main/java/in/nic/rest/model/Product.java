package in.nic.rest.model;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "prod_rest")
public class Product implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer prodId;

	private String prodCode;
	private Double prodCost;

	private Double prodGst;
	private Double prodDisc;
}
