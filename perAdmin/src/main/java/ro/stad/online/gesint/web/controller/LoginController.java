package ro.stad.online.gesint.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.Getter;
import lombok.Setter;

/**
 * Gestiona peticiones HTTP relativas al inicio y finalización de sesiones en el sistema.
 *
 * @author STAD
 */
@Getter
@Setter
@Controller
public class LoginController {

	/**
	 * Intercepta peticiones a la ruta de login "/login" y responde de manera distinta si es una petición normal o AJAX
	 * (útil en caso de caducar la sesión actual).
	 *
	 * @param request peticion http del cliente
	 * @param response respuesta http del servidor
	 * @return acción de login mapeada en el dispatcher en caso de peticion normal, fuerza redirección en caso de
	 * petición AJAX dentro de una vista
	 * @throws IOException en caso de fallo en servlet
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public String login(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
		String respuesta = null;
		if ("partial/ajax".equals(request.getHeader("Faces-Request"))) {
			// JSF ajax request. Return special XML response which instructs JavaScript that it should in turn perform a
			// redirect.
			response.setContentType("text/xml");
			response.getWriter().append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
					.printf("<partial-response><redirect url=\"%s\"></redirect></partial-response>", "login");
		}
		else {
			// Normal request. Perform redirect as usual.
			respuesta = "login";
		}
		return respuesta;
	}

	/**
	 * Request Mapping de logout.
	 * @return @RequestMapping
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/logout")
	public String logout() {
		return "logout";
	}
}
