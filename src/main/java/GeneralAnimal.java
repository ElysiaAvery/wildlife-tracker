import java.util.List;
import org.sql2o.*;
import java.sql.Timestamp;

public abstract class GeneralAnimal {
  public String name;
  public String type;
  public int id;
  // public int sightingId;


  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  // public int getSightingId() {
  //   return personId;
  // }

  @Override
  public boolean equals(Object otherGeneralAnimal) {
    if (!(otherGeneralAnimal instanceof GeneralAnimal)) {
      return false;
    } else {
      GeneralAnimal newGeneralAnimal = (GeneralAnimal) otherGeneralAnimal;
      return this.getName().equals(newGeneralAnimal.getName());
            //  this.getSightingId() == newGeneralAnimal.getSightingId();
    }
  }

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO general_animals (name, type) VALUES (:name, :type)";
      this.id = (int) con.createQuery(sql, true)
                         .addParameter("name", this.name)
                        //  .addParameter("sightingId", this.sightingId)
                         .addParameter("type", this.type)
                         .executeUpdate()
                         .getKey();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM general_animals WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }
}
