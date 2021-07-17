package ocean.common.model.entity.convert;

import java.util.EnumSet;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import ocean.common.enums.MemberStatus;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-17
 */
@Converter(autoApply = true)
public class EnumSetConverterMemberStatus extends EnumSetConverter<MemberStatus>
		implements AttributeConverter<EnumSet<MemberStatus>, String> {
	public EnumSetConverterMemberStatus() {
		super(MemberStatus.class);
	}
}
