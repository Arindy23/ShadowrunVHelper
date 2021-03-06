package de.arindy.sharv.character;

import de.arindy.sharv.IdFormatter;
import lombok.Data;

import java.util.Formattable;
import java.util.Formatter;
import java.util.Objects;

@Data
public class Sex implements Formattable {

    private String id;
    private String name;

    public Sex() {
        this("");
    }

    public Sex(final String id) {
        this.id = id;
        this.name = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sex sex = (Sex) o;
        return Objects.equals(getId(), sex.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void formatTo(Formatter formatter, int i, int i1, int i2) {
        new IdFormatter(formatter, i, i1, i2).formatTo(name, id);
    }
}
