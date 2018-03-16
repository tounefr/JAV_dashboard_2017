package eu.epitech.java.controller;

/**
 * Fichier destiné à la gestion des routes via des "crontroleurs"
 *
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.eclipse.jetty.server.Request;

@Controller
public class Routing {
  @Controller
  public class bController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	  @ResponseBody
	  public String getIndex() {
		return "fack";
	  }

	@RequestMapping("/teub")
	@ResponseBody
	public String getTeub() {
	  return "teub";
	}

	@RequestMapping("/get/{userID}")
	@ResponseBody
	public String getUser(@PathVariable(value="userID") String id) {
		return ("user id  = " + id + ".");
	}
  }
}
