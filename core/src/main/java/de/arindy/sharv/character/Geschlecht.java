package de.arindy.sharv.character;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.arindy.sharv.Language;
import de.arindy.sharv.character.json.GeschlechtDeserializer;
import de.arindy.sharv.character.json.GeschlechtSerializer;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonDeserialize(using = GeschlechtDeserializer.class)
@JsonSerialize(using = GeschlechtSerializer.class)
@JsonRootName("geschlecht")
public enum Geschlecht {

    WEIBLICH("w"),
    MAENLICH("m");

    private final String value;

    Geschlecht(String s) {
        value = s;
    }

    public static Geschlecht fromValue(final String value) {
        if (value != null) {
            for (Geschlecht type : Geschlecht.values()) {
                if (value.equalsIgnoreCase(type.value)) {
                    return type;
                }
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Language.getString("geschlecht." + value);
    }
}