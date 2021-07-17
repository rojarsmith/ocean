package ocean.common.model.entity.generator;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-17
 */
public class ManulInsertIdentityGenerator extends IdentityGenerator {
	@Override
	public Serializable generate(SharedSessionContractImplementor s, Object obj) throws HibernateException {
		Serializable id = s.getEntityPersister(null, obj).getClassMetadata().getIdentifier(obj, s);

		if (id != null && Integer.valueOf(id.toString()) > 0) {
			return id;
		} else {
			return super.generate(s, obj);
		}
	}
}
