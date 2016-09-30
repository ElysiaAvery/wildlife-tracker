import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import org.sql2o.*;
import java.sql.Timestamp;
import java.util.Date;
import java.text.DateFormat;

public class SightingTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void sighting_instantiatesCorrectly_true() {
    Sighting testSighting = new Sighting("Quadrant A", "Naomi");
    assertTrue(testSighting instanceof Sighting);
  }

  @Test
  public void getLocation_instantiatesCorrectlyWithLocation_true() {
    Sighting testSighting = new Sighting("Quadrant A", "Naomi");
    assertEquals("Quadrant A", testSighting.getLocation());
  }

  @Test
  public void getRangerName_instantiatesCorrectlyWithRangerName_true() {
    Sighting testSighting = new Sighting("Quadrant A", "Naomi");
    assertEquals("Naomi", testSighting.getRangerName());
  }

  @Test
  public void equals_returnsTrueIfLocationAndRangerNameAreSame_True() {
    Sighting testSighting = new Sighting("Quadrant A", "Naomi");
    Sighting anotherSighting = new Sighting("Quadrant A", "Naomi");
    assertTrue(testSighting.equals(anotherSighting));
  }

  @Test
  public void save_addsSightingToDatabase() {
    Sighting testSighting = new Sighting("Quadrant A", "Naomi");
    testSighting.save();
    assertTrue(Sighting.all().get(0).equals(testSighting));
  }

  @Test
  public void save_assignsIdToObject_true() {
    Sighting testSighting = new Sighting("Quadrant A", "Naomi");
    testSighting.save();
    Sighting savedSighting = Sighting.all().get(0);
    assertEquals(savedSighting.getId(), testSighting.getId());
  }

  @Test
  public void all_returnsAllInstancesOfSighting_true() {
    Sighting firstSighting = new Sighting("Quadrant A", "Naomi");
    firstSighting.save();
    Sighting secondSighting = new Sighting("Sparkles", 1);
    secondSighting.save();
    assertEquals(true, Sighting.all().get(0).equals(firstSighting));
    assertEquals(true, Sighting.all().get(1).equals(secondSighting));
  }

  @Test
  public void find_returnsSightingWithSameId_secondSighting() {
    Sighting firstSighting = new Sighting("Quadrant A", "Naomi");
    firstSighting.save();
    Sighting secondSighting = new Sighting("Quadrant C", "Peter");
    secondSighting.save();
    assertEquals(Sighting.find(secondSighting.getId()), secondSighting);
  }

   @Test
   public void save_recordsTimeOfCreationInDatabase() {
     Sighting testSighting = new Sighting("Quadrant A", "Naomi");
     testSighting.save();
     Timestamp savedSighting = Sighting.find(testSighting.getId()).getCreation();
     Timestamp rightNow = new Timestamp(new Date().getTime());
     assertEquals(rightNow.getDay(), savedSighting.getDay());
   }

   @Test
   public void delete_deletesSighting_true() {
     Sighting testSighting = new Sighting("Quadrant A", "Naomi");
     testSighting.save();
     testSighting.delete();
     assertEquals(null, Sighting.find(testSighting.getId()));
   }
}
