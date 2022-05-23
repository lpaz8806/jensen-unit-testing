

package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class MoviesSchemaTest {
    private SchemaInformation info = new SchemaInformation();

    @Test
    public void tableHasPrimaryKey() throws SQLException {
        ColumnsList columns = info.getPrimaryKeys().filterByTableName("movies");
        Assertions.assertEquals(1, columns.count());
    }

    @Test
    public void primaryKeyIsCalledId() throws SQLException {
        Column column = info.getPrimaryKeys().find("movies", "id");
        Assertions.assertNotNull(column);
    }

    @Test
    public void titleIsString() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("movies", "title");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("varchar",column.getType());
    }

    @Test
    public void nameIsAtMost100CharactersLong() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("movies", "title");
        Assertions.assertNotNull(column);
        Assertions.assertEquals(100, column.getMaxLength());
    }


    @Test
    public void foreignKeyExists() throws SQLException {
        ForeignKeysList foreignKeysList = info.getForeignKeys().filterByChildTable("movies");
        Assertions.assertTrue(foreignKeysList.count() > 0);
    }

    @Test
    public void foreignKeyExistsBetweenMovies_Directors() throws SQLException {
        ForeignKeysList foreignKeysList = info
                .getForeignKeys()
                .filterByChildTable("movies")
                .filterByParentTable("directors");

        Assertions.assertNotNull(foreignKeysList);
    }
    @Test
    public void idDirectorReferencesDirectors() throws SQLException {
        ForeignKey fkIdDirector = info
                .getForeignKeys()
                .findByChildColumn(new Column("movies", "id_director"));

        Assertions.assertNotNull(fkIdDirector);
        Assertions.assertTrue(fkIdDirector
                .getParentColumn()
                .equals(new Column("directors", "id"))
        );
    }
    @Test
    public void foreignKeyUsesTheNamingConvention() throws SQLException {
        ForeignKey fkIdDirector = info
                .getForeignKeys()
                .findByChildColumn(new Column("movies", "id_director"));

        Assertions.assertNotNull(fkIdDirector);
        Assertions.assertTrue(fkIdDirector
                .getName()
                .equals("fk__%s__%s".formatted(
                        fkIdDirector.getChildColumn().getTable(),
                        fkIdDirector.getParentColumn().getTable()
                ))
        );
    }

    @Test
    public void id_directorIsInt() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("movies", "id_director");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("int", column.getType());
    }

        @Test
        public void release_dateIsDate() throws SQLException {
            ColumnType column = info.getTypes().findByColumn("movies", "release_date");
            Assertions.assertNotNull(column);
            Assertions.assertEquals("date",column.getType());
        }
    }


