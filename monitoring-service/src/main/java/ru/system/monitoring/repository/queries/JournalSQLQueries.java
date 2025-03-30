package ru.system.monitoring.repository.queries;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JournalSQLQueries {
    public final String WRITE_JOURNAL = """
            INSERT INTO system.journal
            VALUES(:id_sensor, :type, :value, :time);
            """;

    public final String GET_JOURNALS_BY_ID = """
            SELECT id_sensor, value, s.type, time
            FROM system.journal
            JOIN system.sensor s ON s.id = s.id_sensor
            WHERE id_sensor=:id_sensor;
            """;
    public final String GET_ALL_JOURNALS = """
            SELECT j.sensor_id as id_sensor, value, s.type, time
            FROM system.journal j
            JOIN system.sensor s ON j.id_sensor=s.id;""";
}