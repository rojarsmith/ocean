package ocean.common.model.entity;

import java.time.Instant;
import java.util.EnumSet;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

//import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import ocean.common.enums.MemberStatus;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-14
 */
@Data
@Entity
public class Member {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	Long id;

	@NotBlank
	@Column(unique = true)
	String username;

	/**
	 * login password
	 */
	@JsonIgnore
	@NotBlank
	String password;

	/**
	 * fund password
	 */
	@JsonIgnore
	String fundPassword;

	@Column(unique = true)
	String email;

	@Column(unique = true)
	String emailRescue;

//	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
	Instant registTime;

	@Column(name = "status")
	EnumSet<MemberStatus> memberStatus = EnumSet.noneOf(MemberStatus.class);

	@ManyToMany(targetEntity = Role.class, fetch = FetchType.LAZY)
	List<Role> roleList;
}
