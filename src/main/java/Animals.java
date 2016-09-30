import java.util.List;
import org.sql2o.*;
import java.sql.Timestamp;

public class Animals extends GeneralAnimal implements DatabaseManager {
  public static final String DATABASE_TYPE = "animal";
  private String age;

  public EndangeredAnimals(String name, String age) {
    this.name = name;
    this.age = age;
    type = DATABASE_TYPE;
  }

  public String getAge() {
    return age;
  }

  public void setAge() {
    this.age = age;
  }

  public static List<Animals> all() {
    String sql = "SELECT * FROM general_animals WHERE type = 'animal';";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .throwOnMappingFailure(false)
      .executeAndFetch(Animals.class);
    }
  }

  public static Animals find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM general_animals WHERE id = :id";
      Animals animal = con.createQuery(sql)
                          .addParameter("id", id)
                          .throwOnMappingFailure(false)
                          .executeAndFetchFirst(Animals.class);
    return animal;
    }
  }

  @Override
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO general_animals (type, name, age) VALUES ('animal', :name, :age)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("age", this.age)
        .executeUpdate()
        .getKey();
    }
  }

  public void update() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE general_animals SET name = :name, age = :age WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .addParameter("name", this.name)
        .addParameter("age", this.age)
        .executeUpdate();
    }
  }

  @Override
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM general_animals WHERE id = :id";
      con.createQuery(sql)
      .addParameter("id", this.id)
      .executeUpdate();
    }
  }

  @Override
  public boolean equals(Object otherAnimal) {
    if (!(otherAnimal instanceof Animals)) {
      return false;
    } else {
      Animals newAnimal = (Animals) otherAnimal;
      return this.getName().equals(newAnimal.getName()) &&
             this.getId() == newAnimal.getId();
    }
  }

}
