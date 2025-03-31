package ru.system.library.sql.queries;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ReferenceSQLQueries {
    public final String UPDATE_VALUE = """
            UPDATE system.reference_value
            SET value=:value
            WHERE id=:id""";
    public final String EXISTS_REFERENCE = """
            SELECT EXISTS(SELECT 1 FROM system.reference_value WHERE id=:id);""";
}
