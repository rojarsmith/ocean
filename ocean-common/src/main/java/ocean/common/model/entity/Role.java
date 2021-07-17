package ocean.common.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-17
 */
@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "role", uniqueConstraints = { @UniqueConstraint(columnNames = "code") })
public class Role implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Role code
	 */
	@NotNull
	private String code;

	/**
	 * Role name
	 */
	@NotNull
	private String name;

	/**
	 * Role's permission
	 */
	@ManyToMany(targetEntity = Permission.class, fetch = FetchType.LAZY)
	private List<Permission> permissionList;

	public Role() {
	}

	public Role(String code, String name, List<Permission> permissionList) {
		this.code = code;
		this.name = name;
		this.permissionList = permissionList;
	}
}
