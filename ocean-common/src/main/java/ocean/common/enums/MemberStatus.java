package ocean.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-17
 */
@AllArgsConstructor
@Getter
public enum MemberStatus implements BaseEnum {
	REGISTERD, VERIFIED_EMAIL,
	/*
	 * Can login but can not exchange.
	 */
	SUSPICIOUS,
	/**
	 * Can not login
	 */
	ILLEGAL;

	@Override
	@JsonValue
	public int getOrdinal() {
		return this.ordinal();
	}

}
