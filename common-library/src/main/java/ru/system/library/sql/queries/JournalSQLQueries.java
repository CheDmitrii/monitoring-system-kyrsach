package ru.system.library.sql.queries;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JournalSQLQueries {
    public final String WRITE_JOURNAL = """
            INSERT INTO system.journal
            VALUES(:sensor_id, :value, :time);
            """;

    public final String GET_JOURNALS_BY_ID = """
            SELECT * FROM system.journal
            WHERE sensor_id=:sensor_id;
            """;
    public final String GET_ALL_JOURNALS = """
            SELECT * FROM system.journal;""";
}