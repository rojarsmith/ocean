package ocean.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import lombok.extern.slf4j.Slf4j;
import ocean.common.model.vo.ResultVO;
import ocean.common.service.LocaleMessageSourceService;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-22
 */
@Slf4j
public class BaseController {

	@Autowired
	LocaleMessageSourceService localeMessageSourceService;

	protected String getMessageML(String message) {
		try {
			return localeMessageSourceService.getMessage(message);
		} catch (Exception e) {
			log.debug(e.toString());
			return "MESSAGE_LACK";
		}
	}

	protected ResponseEntity<?> success() {
		return success(getMessageML("SUCCESS"));
	}

	protected ResponseEntity<?> success(String msg) {
		return success(msg, null);
	}

	protected ResponseEntity<?> success(Object obj) {
		return success(getMessageML("SUCCESS"), obj);
	}

	protected ResponseEntity<?> success(String msg, Object obj) {
		return ResponseEntity.ok().body(new ResultVO<Object>(msg, obj));
	}

	protected ResponseEntity<?> error(String msg) {
		return error(msg, null);
	}

	protected ResponseEntity<?> error(String msg, Object obj) {
		return ResponseEntity.badRequest().body(new ResultVO<Object>(msg, obj));
	}

}
