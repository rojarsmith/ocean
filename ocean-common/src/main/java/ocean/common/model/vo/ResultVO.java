package ocean.common.model.vo;

import lombok.Data;
import ocean.common.util.JSON;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-17
 */
@Data
public class ResultVO<T> {

	String message;
	T data;

	public ResultVO(String msg) {
		// Must set null to avoid error:
		// No serializer found for class java.lang.Object
		this(msg, null);
	}

	public ResultVO(String msg, T data) {
		this.message = msg;
		this.data = data;
	}

	@Override
	public String toString() {
		return JSON.stringify(this).get();
	}

}
