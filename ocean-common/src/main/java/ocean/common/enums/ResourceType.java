package ocean.common.enums;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-17
 */
public enum ResourceType implements BaseEnum {
	MENU, API;

	@Override
	public int getOrdinal() {
		return this.ordinal();
	}
}
