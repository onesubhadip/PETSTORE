package org.petstore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Pet")
@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long petId;
	private String category;
	private String name;
	//private List<String> photoUrls = new ArrayList<String>();
	private String status;
	
	public Pet() {}

	public Pet(String category, String name, String status) {
		this.category = category;
		this.name = name;
		this.status = status;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Long getPetId() {
		return petId;
	}

	public void setPetId(Long petId) {
		this.petId = petId;
	}

	@ApiModelProperty(example = "Doggie", required = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

/*	public List<String> getPhotoUrls() {
		return photoUrls;
	}

	public void setPhotoUrls(List<String> photoUrls) {
		this.photoUrls = photoUrls;
	}*/

	@ApiModelProperty(value = "Pet status in the store", allowableValues = "available,sold")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Pet [petId=" + petId + ", category=" + category + ", name=" + name + ", status=" + status + "]";
	}

}