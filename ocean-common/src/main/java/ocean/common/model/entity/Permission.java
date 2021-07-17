package ocean.common.model.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import ocean.common.enums.ResourceType;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-17
 */
@Data
@Entity
@Accessors(chain = true)
@EqualsAndHashCode
@Table(name = "permission")
public class Permission {
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ManulInsertIdentityGenerator")
	@GenericGenerator(name = "ManulInsertIdentityGenerator", strategy = "ocean.common.model.entity.generator.ManulInsertIdentityGenerator")
	@Id
	Long id;

	String path;

	String name;

	@Enumerated(EnumType.STRING)
	ResourceType type;
}
