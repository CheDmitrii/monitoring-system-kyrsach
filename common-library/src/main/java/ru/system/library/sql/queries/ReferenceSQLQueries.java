package ru.system.library.sql.queries;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ReferenceSQLQueries {
    public final String CREATE_REFERENCE = """
            INSERT INTO system.reference_value
            VALUES(:sensor_id, :name, :value, :type);
            returning id;""";
    public final String CREATE_REFERENCE_HISTORY = """
            INSERT INTO system.history_reference VALUES(:id, :old_value, :new_value, :time);""";
    public final String GET_REFERENCE_BY_ID = """
            SELECT * FROM system.reference_value WHERE id=:id;""";
    public final String GET_ALL_REFERENCES = """
            SELECT * FROM system.reference_value
            ORDER BY name""";
    public final String GET_REFERENCE_HISTORY_BY_ID = """
            SELECT * FROM system.history_reference WHERE id=:id;""";
    public final String GET_ALL_REFERENCE_HISTORY = """
            SELECT * FROM system.history_reference;""";
    public final String UPDATE_VALUE = """
            UPDATE system.reference_value
            SET value=:value
            WHERE id=:id""";
    public final String EXISTS_REFERENCE = """
            SELECT EXISTS(SELECT 1 FROM system.reference_value WHERE id=:id);""";
}
