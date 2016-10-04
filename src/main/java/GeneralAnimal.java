import java.util.List;
import org.sql2o.*;
import java.sql.Timestamp;

public abstract class GeneralAnimal {
  public String name;
  public String type;
  public String age;
  public int id;


  public String getName() {
    return name;
  }

  public String getAge() {
    return age;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object otherGeneralAnimal) {
    if (!(otherGeneralAnimal instanceof GeneralAnimal)) {
      return false;
    } else {
      GeneralAnimal newGeneralAnimal = (GeneralAnimal) otherGeneralAnimal;
      return this.getName().equals(newGeneralAnimal.getName());
    }
  }

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO general_animals (name, type, age) VALUES (:name, :type, :age)";
      this.id = (int) con.createQuery(sql, true)
                         .addParameter("name", this.name)
                         .addParameter("type", this.type)
                         .addParameter("age", this.age)
                         .executeUpdate()
                         .getKey();
    }
  }

  // public List<Sightings> getSightings() {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "SELECT sightings.* FROM general_animals " +
  //                  "JOIN animals_sightings ON (general_animals.id = animals_sightings.general_animal_id) " +
  //                  "JOIN sightings ON (animals_sightings.sighting_id = sightings.id) " +
  //                  "WHERE general_animals.id = :id";
  //     return con.createQuery(sql)
  //               .addParameter("id", this.id)
  //               .executeAndFetch(Sightings.class);
  //   }
  // }
  //
  // public void leaveSightings(Sightings sighting) {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "DELETE FROM animals_sightings WHERE sighting_id = :sighting_id AND general_animal_id = :general_animal_id";
  //     con.createQuery(sql)
  //       .addParameter("sighting_id", sighting.getId())
  //       .addParameter("general_animal_id", this.getId())
  //       .executeUpdate();
  //   }
  // }

  // public void delete() {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "DELETE FROM general_animals WHERE id = :id";
  //     con.createQuery(sql)
  //       .addParameter("id", this.id)
  //       .executeUpdate();
  //     String joinDeleteQuery = "DELETE FROM animals_sightings WHERE general_animal_id = :newid";
  //     con.createQuery(joinDeleteQuery)
  //       .addParameter("newid", this.id)
  //       .executeUpdate();
  //   }

}
