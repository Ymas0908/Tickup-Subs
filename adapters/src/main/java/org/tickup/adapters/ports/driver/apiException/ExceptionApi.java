package org.tickup.adapters.ports.driver.apiException;


import org.tickup.domain.exceptions.*;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ExceptionApi {

	public static String getUserFriendlyMessage(Throwable ex) {
		String message = ex.getMessage();

		if (ex instanceof NullPointerException && message != null) {
			if (message.contains("because")) {
				String[] parts = message.split("because");
				if (parts.length > 1) {
					return parts[1].trim().replaceAll("\"", "");
				}
			}
			return "Une erreur est survenue : une donnée obligatoire est manquante.";
		}

		// Pour d'autres types d'exceptions
		return "Une erreur technique est survenue. Veuillez réessayer plus tard.";
	}

	@ExceptionHandler(EntityNotExistsException.class)
	public ResponseEntity<?> notFound(EntityNotExistsException error) {
		log.error(error.getMessage());
		Map<String, String> errors = new HashMap<>();
		errors.put("message", error.getMessage());
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setStatus("error");
		apiErrorResponse.setMessage("Entity not found");
		apiErrorResponse.setCode(HttpStatus.NOT_FOUND.value());
		apiErrorResponse.setErrors(errors);
		return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EntityAlreadyExistsException.class)
	public ResponseEntity<?> alreadyExist(EntityAlreadyExistsException error) {
		log.error(error.getMessage());
		Map<String, String> errors = new HashMap<>();
		errors.put("message", error.getMessage());
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setStatus("error");
		apiErrorResponse.setMessage("Entity already exists");
		apiErrorResponse.setCode(HttpStatus.CONFLICT.value());
		apiErrorResponse.setErrors(errors);
		return new ResponseEntity<>(apiErrorResponse, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(ApplicationAuthenticationException.class)
	public ResponseEntity<?> authentification(ApplicationAuthenticationException error) {
		Map<String, String> errors = new HashMap<>();
		errors.put("message", error.getMessage());
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setStatus("error");
		apiErrorResponse.setMessage("Authentication failed");
		apiErrorResponse.setCode(HttpStatus.UNAUTHORIZED.value());
		apiErrorResponse.setErrors(errors);
		log.error(error.getMessage());
		return new ResponseEntity<>(apiErrorResponse, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(ExpressionEvaluationException.class)
	public ResponseEntity<?> validation(ExpressionEvaluationException error) {
		Map<String, String> errors = new HashMap<>();
		errors.put("message", error.getMessage());
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setStatus("error");
		apiErrorResponse.setMessage(error.getMessage());
		apiErrorResponse.setCode(HttpStatus.FORBIDDEN.value());
		apiErrorResponse.setErrors(errors);
		log.error(error.getMessage());
		return new ResponseEntity<>(apiErrorResponse, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<?> badRequestException(BadRequestException error) {
		Map<String, String> errors = new HashMap<>();
		errors.put("message", error.getMessage());
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setStatus("error");
		apiErrorResponse.setMessage(error.getMessage());
		apiErrorResponse.setCode(HttpStatus.BAD_REQUEST.value());
		apiErrorResponse.setErrors(errors);
		log.error(error.getMessage());
		return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> global(Exception error) {
		Map<String, String> errors = new HashMap<>();
		errors.put("message", error.getMessage());
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setStatus("error");
		apiErrorResponse.setMessage("Internal server error");
		apiErrorResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		apiErrorResponse.setErrors(errors);
		log.error(error.getMessage());
		return new ResponseEntity<>(apiErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
		System.out.println("une erreur est survenue MethodArgumentNotValidException*************************");
		// Récupérer les messages d'erreur.
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
//        errors.put("message", "Verify your request");
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setStatus("error");
		apiErrorResponse.setMessage("Verify your request");
		apiErrorResponse.setCode(HttpStatus.BAD_REQUEST.value());
		apiErrorResponse.setErrors(errors);
		log.error(ex.getMessage());
		return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<?> handleConstraintViolation(DataIntegrityViolationException ex) {
		// Récupérer les messages d'erreur.
		Map<String, String> errors = new HashMap<>();
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setStatus("error");
		apiErrorResponse.setMessage("Verify your request");
		apiErrorResponse.setCode(HttpStatus.BAD_REQUEST.value());
		apiErrorResponse.setErrors(errors);
		log.error(ex.getMessage());
		return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex) {
		// Récupérer les messages d'erreur.
		Map<String, String> errors = new HashMap<>();
		errors.put("message", ex.getMessage());
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setStatus("error");
		apiErrorResponse.setMessage(ex.getMessage());
		apiErrorResponse.setCode(HttpStatus.BAD_REQUEST.value());
		apiErrorResponse.setErrors(errors);
		log.error(ex.getMessage());
		return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<?> httpClientErrorException(HttpClientErrorException error) {
		Map responseBodyAs = error.getResponseBodyAs(Map.class);
		String message = responseBodyAs != null ? String.valueOf(responseBodyAs.get("message")) : "Une erreur est survenue lors de la request";
		Map<String, String> errors = new HashMap<>();
		errors.put("message", error.getMessage());
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setStatus("error");
		apiErrorResponse.setMessage(message);
		int httpStatusCode = HttpStatusCode.valueOf(error.getStatusCode().value()).value();
		apiErrorResponse.setCode(httpStatusCode);
		apiErrorResponse.setErrors(errors);
		System.out.println("apiErrorResponse::: " + apiErrorResponse);
		log.error(error.getMessage());
		return new ResponseEntity<>(apiErrorResponse, HttpStatusCode.valueOf(httpStatusCode));
	}

	@ExceptionHandler(HttpServerErrorException.class)
	public ResponseEntity<?> httpServerErrorException(HttpServerErrorException error) {
		Map responseBodyAs = error.getResponseBodyAs(Map.class);
		String message = responseBodyAs != null ? String.valueOf(responseBodyAs.get("message")) : "Une erreur est survenue lors de la request";
		Map<String, String> errors = new HashMap<>();
		errors.put("message", error.getMessage());
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setStatus("error");
		apiErrorResponse.setMessage(message);
		int httpStatusCode = HttpStatusCode.valueOf(error.getStatusCode().value()).value();
		apiErrorResponse.setCode(httpStatusCode);
		apiErrorResponse.setErrors(errors);
		System.out.println("apiErrorResponse::: " + apiErrorResponse);
		log.error(error.getMessage());
		return new ResponseEntity<>(apiErrorResponse, HttpStatusCode.valueOf(httpStatusCode));
	}

	private String getClientErrorMessage(HttpClientErrorException error) {
		Map responseBodyAs = error.getResponseBodyAs(Map.class);
		System.out.println("**************************" + responseBodyAs + "**************************");
		if (responseBodyAs != null) {
			String message = (String) responseBodyAs.get("message");
			String details = (String) responseBodyAs.get("detail");
			System.out.println("**************************" + message + "**************************");
			System.out.println("**************************" + details + "**************************");
			if (message != null) {
				return message;
			}
			if (details != null) {
				return details;
			}
			return "une erreur est survenue";

		} else {
			return error.getMessage();
		}

	}

	@ExceptionHandler(ConnectException.class)
	public ResponseEntity<?> connectException(ConnectException exception) {
		System.out.println("une erreur est survenue connectException*************************");
		Map<String, String> errors = new HashMap<>();
		errors.put("message", exception.getMessage());
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setStatus("error");
		apiErrorResponse.setMessage(exception.getMessage());
		apiErrorResponse.setCode(HttpStatus.SERVICE_UNAVAILABLE.value());
		apiErrorResponse.setErrors(errors);
		log.error(exception.getMessage());
		return new ResponseEntity<>(apiErrorResponse, HttpStatus.SERVICE_UNAVAILABLE);

	}

	@ExceptionHandler(ParseException.class)
	public ResponseEntity<?> parseDateException(ParseException exception) {
		exception.printStackTrace();
		Map<String, String> errors = new HashMap<>();
		errors.put("message", "Invalid data format");
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setStatus("error");
		apiErrorResponse.setMessage("Invalid data format");
		apiErrorResponse.setCode(HttpStatus.BAD_REQUEST.value());
		apiErrorResponse.setErrors(errors);
		log.error(exception.getMessage());
		return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> runtimeException(RuntimeException exception) {
		Map<String, String> errors = new HashMap<>();
		errors.put("message", exception.getMessage());
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setStatus("error");
		apiErrorResponse.setMessage(exception.getMessage());
		apiErrorResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		apiErrorResponse.setErrors(errors);
		log.error(exception.getMessage());
		return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> illegalArgumentException(IllegalArgumentException exception) {
//        String message = getMessage(exception.getMessage());
		Map<String, String> errors = new HashMap<>();
		errors.put("message", exception.getMessage());
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setStatus("error");
		apiErrorResponse.setMessage(exception.getMessage());
		apiErrorResponse.setCode(HttpStatus.BAD_REQUEST.value());
		apiErrorResponse.setErrors(errors);
		log.error(exception.getMessage());
		return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResourceAccessException.class)
	public ResponseEntity<?> handleResourceAccessException(ResourceAccessException ex) {
		String serviceName = extractServiceName(ex);
		Map<String, String> errors = new HashMap<>();
		String message = String.format("SERVICE_UNAVAILABLE: The service '%s' is currently unavailable.", serviceName);
		errors.put("message", message);
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setStatus("error");
		apiErrorResponse.setMessage(message);
		apiErrorResponse.setCode(HttpStatus.SERVICE_UNAVAILABLE.value());
		apiErrorResponse.setErrors(errors);
		log.error(ex.getMessage());
		return new ResponseEntity<>(apiErrorResponse, HttpStatus.SERVICE_UNAVAILABLE);
	}

	private String extractServiceName(ResourceAccessException ex) {
		// Analyse l'URL à partir du message d'erreur
		String message = ex.getMessage();
		if (message.contains("http")) {
			try {
				URL url = new URL(message.substring(message.indexOf("http")));
				return url.getHost();
			} catch (MalformedURLException e) {
				return "service externe";
			}
		}
		return "service externe";
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<?> nullPointerException(NullPointerException exception) {
		System.out.println("une erreur est survenue nullPointerException*************************" + exception.getLocalizedMessage());
		exception.printStackTrace();
		Map<String, String> errors = new HashMap<>();
		errors.put("message", getUserFriendlyMessage(exception));
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setStatus("error");
		apiErrorResponse.setMessage("NullPointerException error");
		apiErrorResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		apiErrorResponse.setErrors(errors);
		log.error(exception.getMessage());
		return new ResponseEntity<>(apiErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<?> validationException(ValidationException exception) {
		System.out.println("une erreur est survenue validationException*************************");
		System.out.println(exception.getErrors());
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setStatus("error");
		apiErrorResponse.setMessage(exception.getMessage());
		apiErrorResponse.setCode(HttpStatus.BAD_REQUEST.value());
		apiErrorResponse.setErrors(exception.getErrors());
		log.error("Données invalide {}", exception.getErrors());
		return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<?> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
		Map<String, String> errors = new HashMap<>();
		errors.put(exception.getName(), "Is not valid");
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setStatus("error");
		apiErrorResponse.setMessage("Invalid data format");
		apiErrorResponse.setCode(HttpStatus.BAD_REQUEST.value());
		apiErrorResponse.setErrors(errors);
		log.error(exception.getMessage());
		return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
	}

//	@ExceptionHandler(HttpMessageNotReadableException.class)
//	public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
//		Throwable cause = ex.getCause();
//
//		//Gestion des exceptions de type InvalidFormatException (dans le json)
//
//		// Cas 1 : Mauvais format de date
//		if (cause instanceof InvalidFormatException formatEx) {
//			Class<?> targetType = formatEx.getTargetType();
//
//			if (targetType.equals(LocalDate.class)) {
//				return ResponseEntity.badRequest().body(
//						Map.of("message", "Le format de la date est invalide. Utilisez 'yyyy-MM-dd'.")
//				);
//			}
//
//			// Cas 2 : Enum invalide
//			if (targetType.isEnum()) {
//				String fieldName = formatEx.getPath().get(formatEx.getPath().size() - 1).getFieldName();
//				String allowedValues = Arrays.toString(targetType.getEnumConstants());
//
//				return ResponseEntity.badRequest().body(
//						Map.of(
//								"message", String.format("Valeur invalide pour le champ '%s'. Valeurs acceptées : %s", fieldName, allowedValues)
//						)
//				);
//			}
//		}
//
//		ResponseApi responseApi = new ResponseApi("Requête invalide. Vérifiez les données envoyées dans le corps de la requête.", HttpStatus.BAD_REQUEST.value(), null);
//		log.error(ex.getMessage());
//		return new ResponseEntity<>(responseApi, HttpStatus.BAD_REQUEST);
//	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put("message", getUserFriendlyMessage(ex));
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setStatus("error");
		apiErrorResponse.setMessage("Bad credentials");
		apiErrorResponse.setCode(HttpStatus.UNAUTHORIZED.value());
		apiErrorResponse.setErrors(errors);
		return new ResponseEntity<>(apiErrorResponse, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Object> accessDeniedException(AccessDeniedException ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put("message", getUserFriendlyMessage(ex));
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setStatus("error");
		apiErrorResponse.setMessage("Bad credentials");
		apiErrorResponse.setCode(HttpStatus.UNAUTHORIZED.value());
		apiErrorResponse.setErrors(errors);
		return new ResponseEntity<>(apiErrorResponse, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<Object> expiredJwtException(ExpiredJwtException ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put("message", getUserFriendlyMessage(ex));
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setStatus("error");
		apiErrorResponse.setMessage("Expired JWT Token");
		apiErrorResponse.setCode(HttpStatus.UNAUTHORIZED.value());
		apiErrorResponse.setErrors(errors);
		return new ResponseEntity<>(apiErrorResponse, HttpStatus.UNAUTHORIZED);
	}
}
