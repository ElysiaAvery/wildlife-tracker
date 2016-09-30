import java.util.List;
import org.sql2o.*;
import java.sql.Timestamp;

public class Sightings implements DatabaseManager {
  private int id;
  private String location;
  private String ranger_name;

  public Sightings(String location, String ranger_name) {
    this.location = location;
    this.ranger_name = ranger_name;
  }

  public String getLocation() {
    return location;
  }

  public String getRangerName() {
    return ranger_name;
  }

  @Override
  public boolean equals(Object otherSighting) {
    if (!(otherSighting instanceof Sightings)) {
      return false;
    } else {
      Sightings newSighting = (Sightings) otherSighting;
      return this.getLocation().equals(newSighting.getLocation()) &&
             this.getRangerName().equals(newSighting.getRangerName());
    }
  }

  

}
