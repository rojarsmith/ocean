package ocean.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ocean.common.model.vo.ResultVO;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-17
 */
public class VOTest {
	@Test
	public void resultVOTest() {
		ResultVO<Object> mr1 = new ResultVO<Object>("OK");
		String json1 = mr1.toString();
		assertEquals("{\"message\":\"OK\",\"data\":null}", json1);
	}
}
