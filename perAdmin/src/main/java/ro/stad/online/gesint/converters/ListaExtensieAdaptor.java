package ro.stad.online.gesint.converters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * .
 * @author STAD
 *
 */
@Converter(autoApply = false)
public class ListaExtensieAdaptor implements AttributeConverter<Object, String> {
        /**
         * Constante separador.
         */
        private static final String SEPARADOR = ", ";

        /**
         * Convert object to a String.
         */
        @SuppressWarnings("unchecked")
        @Override
        public String convertToDatabaseColumn(final Object listaExtensie) {
                return String.join(SEPARADOR, (List<String>) listaExtensie);
        }

        /**
         * Convert a String to a object.
         */
        @Override
        public List<String> convertToEntityAttribute(final String extensie) {
                return new ArrayList<>(Arrays.asList(extensie.split(SEPARADOR)));
        }

}