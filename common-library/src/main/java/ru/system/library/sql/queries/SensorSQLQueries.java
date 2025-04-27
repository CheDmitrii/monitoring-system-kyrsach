package ru.system.library.sql.queries;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SensorSQLQueries {
    public final String GET_ALL_SENSORS = """
            SELECT *, r.id as reference_id, r.value, r.name as reference_name FROM system.sensor s
            LEFT JOIN system.reference_value r ON s.id=r.sensor_id;"""; // ref may be null
    public final String GET_SENSOR_BY_ID = """
            SELECT *, r.id as reference_id, r.value, r.name as reference_name FROM system.sensor s
            LEFT JOIN system.reference_value r ON s.id=r.sensor_id
            WHERE s.id=:id;""";
    public final String EXIST_SENSOR = """
            SELECT EXISTS(SELECT 1 FROM system.sensor WHERE id=:id);""";
    public final String CREATE_SENSOR = """
            INSERT INTO system.sensor VALUES(:name, :type, :description)
            returning id;""";
}
