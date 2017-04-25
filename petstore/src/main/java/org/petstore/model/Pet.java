package org.petstore.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Pet")
@Entity
public class Pet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long petId;
	@NotNull(message = "Categoty can not be null")
	@ManyToOne // (cascade = {CascadeType.ALL})
	@JoinColumn(name = "category_categoryid", nullable = false, updatable = false)
	@Valid
	private Category category;
	@NotNull(message = "Name can not be null")
	@Size(min = 3, max = 30, message = "Name has to be between 3 to 30 charecters long.")
	private String name;

	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name = "PET_IMAGES", joinColumns = @JoinColumn(name = "petId"))
	@Column(name = "IMAGE_URL")
	private List<String> photoUrls;

	@NotNull(message = "Status can not be null")
	private String status;
	
	@NotNull(message="Pet description has to be provided")
	@Size(min = 5, max = 100, message = "Description has to be between 5 to 100 charecters long.")
	private String description;

	public Pet() {
	}

	public Pet(Category category, String name, String status) {
		this.category = category;
		this.name = name;
		this.status = status;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
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

	public List<String> getPhotoUrls() {
		return photoUrls;
	}

	public void setPhotoUrls(List<String> photoUrls) {
		this.photoUrls = photoUrls;
	}

	@ApiModelProperty(value = "Pet status in the store", allowableValues = "available,sold")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Pet [petId=" + petId + ", category=" + category + ", name=" + name + ", status=" + status + "]";
	}

}