import java.util.List;
import org.sql2o.*;
import java.sql.Timestamp;

public abstract class GeneralAnimal {
  public String name;
  public String type;
  public int id;


  public String getName() {
    return name;
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
      String sql = "INSERT INTO general_animals (name, type) VALUES (:name, :type)";
      this.id = (int) con.createQuery(sql, true)
                         .addParameter("name", this.name)
                         .addParameter("type", this.type)
                         .executeUpdate()
                         .getKey();
    }
  }

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
