package ro.stad.online.gesint.exceptions.anotacion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.transaction.annotation.Transactional;

import ro.stad.online.gesint.exceptions.ExcepcionRollback;

/**
 * Anotación usada para las transacciones que queramos que hagan rollback al capturar la excepción
 * ExcepcionRollback.class.
 * 
 * @author STAD
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
@Transactional(rollbackFor = ExcepcionRollback.class)
public @interface TransactionalRollback {

}
