import java.util.List;
import org.sql2o.*;
import java.sql.Timestamp;

public class EndangeredAnimals extends GeneralAnimal implements DatabaseManagement {
  private String health;
  private String age;
  private int amount;
  public static final String DATABASE_TYPE = "endangered";
  // public static final int MED_HEALTH = 2;
  // public static final int MAX_HEALTH = 3;
  // // public static final String MIN_AGE = 1;
  // public static final String MAX_AGE = 3;
  // public static final int MIN = 0;
  public EndangeredAnimals(String name, String health, String age, int amount) {
    this.name = name;
    this.health = health;
    this.age = age;
    this.amount = amount;
    // animalHealth = MAX_HEALTH;
    // animalAge = MAX_AGE;
    type = DATABASE_TYPE;
  }

  public String getHealth() {
    return health;
  }

  public String getAge() {
    return age;
  }

  public int getAmount() {
    return amount;
  }

  public void setHealth() {
    this.health = health;
  }

  public void setAge() {
    this.age = age;
  }

  public void setAmount() {
    this.amount = amount;
  }

  // public boolean healthCondition() {
  //   if (animalHealth < MED_HEALTH) {
  //     return false;
  //   }
  //   return true;
  // }

  public static List<EndangeredAnimals> all() {
    String sql = "SELECT * FROM general_animals WHERE type = 'endangered';";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .throwOnMappingFailure(false)
      .executeAndFetch(EndangeredAnimals.class);
    }
  }

  public static EndangeredAnimals find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM general_animals WHERE id = :id";
      EndangeredAnimals animal = con.createQuery(sql)
                                 .addParameter("id", id)
                                 .throwOnMappingFailure(false)
                                 .executeAndFetchFirst(EndangeredAnimals.class);
    return animal;
    }
  }

  // @Override
  // public void save() {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "INSERT INTO general_animals (type, name, health, age, amount) VALUES ('endangered', :name, :health, :age, :amount)";
  //     this.id = (int) con.createQuery(sql, true)
  //       .addParameter("name", this.name)
  //       .addParameter("health", this.health)
  //       .addParameter("age", this.age)
  //       .addParameter("amount", this.amount)
  //       .throwOnMappingFailure(false)
  //       .executeUpdate()
  //       .getKey();
  //   }
  // }

  public void update() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE general_animals SET name = :name, health = :health, age = :age, amount = :amount WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .addParameter("name", this.name)
        .addParameter("health", this.health)
        .addParameter("age", this.age)
        .addParameter("amount", this.amount)
        .throwOnMappingFailure(false)
        .executeUpdate();
    }
  }

  public List<Sightings> getSightings() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT sightings.* FROM general_animals " +
                   "JOIN animals_sightings ON (general_animals.id = animals_sightings.general_animal_id) " +
                   "JOIN sightings ON (animals_sightings.sighting_id = sightings.id) " +
                   "WHERE general_animals.id = :id";
      return con.createQuery(sql)
                .addParameter("id", id)
                .executeAndFetch(Sightings.class);
    }
  }

  public void leaveSightings(Sightings sighting) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM animals_sightings WHERE sighting_id = :sighting_id AND general_animal_id = :general_animal_id";
      con.createQuery(sql)
        .addParameter("sighting_id", sighting.getId())
        .addParameter("general_animal_id", this.getId())
        .executeUpdate();
    }
  }

  // @Override
  // public void delete() {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "DELETE FROM general_animals WHERE id = :id";
  //     con.createQuery(sql)
  //     .addParameter("id", this.id)
  //     .executeUpdate();
  //     String joinDeleteQuery = "DELETE FROM animals_sightings WHERE general_animals_id = :id";
  //     con.createQuery(joinDeleteQuery)
  //       .addParameter("id", this.getId())
  //       .executeUpdate();
  //   }
  // }

  @Override
  public boolean equals(Object otherEndangeredAnimal) {
    if (!(otherEndangeredAnimal instanceof EndangeredAnimals)) {
      return false;
    } else {
      EndangeredAnimals newAnimal = (EndangeredAnimals) otherEndangeredAnimal;
      return this.getName().equals(newAnimal.getName()) &&
             this.getId() == newAnimal.getId();
    }
  }

}
