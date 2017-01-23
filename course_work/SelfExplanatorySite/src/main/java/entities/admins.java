package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class admins {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	@NotNull
	@Column(name = "registered_users_id")
	Long registered_users_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRegistered_users_id() {
		return registered_users_id;
	}

	public void setRegistered_users_id(Long registered_users_id) {
		this.registered_users_id = registered_users_id;
	}
	
	
}
