package com.company;

import java.sql.*;

public class Main {

    public static void main(String[] args) {

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:D:\\db2\\movies.db");
            //conn.setAutoCommit(false);
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS movies (name TEXT, actor TEXT, actress TEXT, year INTEGER, director TEXT)");

            // Inserting movies to database
            statement.execute("INSERT INTO movies (name, actor, actress, year, director) " +
                    "VALUES('Pushpa', 'Allu Arjun', 'Rashmika Mandana', 2022, 'Sukumar')");

            statement.execute("INSERT INTO movies (name, actor, actress, year, director) " +
                    "VALUES('Kick', 'Salman Khan', 'Jaquiline Fernandes', 2015, 'Sajid Nadiadwala')");

            // Update the movies
             statement.execute("UPDATE movies SET year=2014 WHERE name='Kick'");

            // Delete movie
             statement.execute("DELETE FROM movies WHERE name='Pushpa'");

            // Getting all the movies
            statement.execute("SELECT * FROM movies");
            ResultSet results = statement.getResultSet();
            while (results.next()) {
                System.out.println(results.getString("name") + " " +
                        results.getString("actor") + " " +
                        results.getString("actress") + " " +
                        results.getInt("year") + " " +
                        results.getString("director"));
            }

            results.close();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
