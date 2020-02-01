package ro.stad.online.gesint.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import ro.stad.online.gesint.persistence.entities.enums.RolEnum;

/**
 * Adaptador para el enum RolEnum.
 * 
 * @see ro.stad.online.gesint.persistence.entities.enums.RolEnum
 * 
 * @author STAD
 *
 */
@Converter(autoApply = true)
public class RolEnumAdaptor implements AttributeConverter<RolEnum, String> {

        /**
         * Método que recibe un valor RolEnum y devuelve su nombre.
         */
        @Override
        public String convertToDatabaseColumn(final RolEnum role) {
                String nombre = null;
                if (role != null) {
                        nombre = role.name();
                }
                return nombre;
        }

        /**
         * Método que recibe un nombre y devuelve su correspondiente RolEnum.
         */
        @Override
        public RolEnum convertToEntityAttribute(final String dbData) {
                return RolEnum.valueOf(dbData);
        }

}
