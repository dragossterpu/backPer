package ro.stad.online.gesint.persistence.entities.enums;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Defineste rolurile sistemului. Datorită utilizării versiunii 4 de Spring Security, toate rolurile trebuie să înceapă
 * cu "ROLE_", altfel funcțiile: UserInRole, ifGranted, etc. întotdeauna se întorc false
 *
 * @author STAD
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ExtensieEnum {
        /**
         * DOC.
         */
        DOC("application/msword"),

        /**
         * DOCX.
         */
        DOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document"),

        /**
         * ODT.
         */
        ODT("application/vnd.oasis.opendocument.text"),

        /**
         * PPT.
         */
        PPT("application/vnd.ms-powerpoint"),

        /**
         * PPTX.
         */
        PPTX("application/vnd.openxmlformats-officedocument.presentationml.presentation"),

        /**
         * ODP.
         */
        ODP("application/vnd.oasis.opendocument.presentation"),

        /**
         * XLS.
         */
        XLS("application/vnd.ms-excel"),

        /**
         * XLSX.
         */
        XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),

        /**
         * ODS.
         */
        ODS("application/vnd.oasis.opendocument.spreadsheet"),

        /**
         * JPEG.
         */
        JPEG("image/jpeg"),

        /**
         * PNG.
         */
        PNG("image/png"),

        /**
         * BMP.
         */
        BMP("image/bmp"),

        /**
         * PUB.
         */
        PUB("application/x-mspublisher"),

        /**
         * PDF.
         */
        PDF("application/pdf"),

        /**
         * ZIP.
         */
        ZIP("application/x-zip-compressed");

        /**
         * @return Returnează o listă cu descrierile enum.
         *
         */
        public static Map<String, String> getExtensie() {
                Map<String, String> extensie = new HashMap<String, String>();

                for (ExtensieEnum extension : ExtensieEnum.values()) {
                        extensie.put(extension.getNombre(), extension.getDescription());
                }
                return extensie;
        }

        /**
         * Descrierea enum.
         */
        private String description;

        /**
         * @return Nombre del enum sin el prefijo "ROLE_"
         */
        public String getNombre() {
                return name().substring(5);
        }
}
