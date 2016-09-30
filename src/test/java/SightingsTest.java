import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import org.sql2o.*;
import java.sql.Timestamp;
import java.util.Date;
import java.text.DateFormat;

public class SightingsTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void sighting_instantiatesCorrectly_true() {
    Sightings testSightings = new Sightings("Quadrant A", "Naomi");
    assertTrue(testSightings instanceof Sightings);
  }

  @Test
  public void getLocation_instantiatesCorrectlyWithLocation_true() {
    Sightings testSightings = new Sightings("Quadrant A", "Naomi");
    assertEquals("Quadrant A", testSightings.getLocation());
  }

  @Test
  public void getRangerName_instantiatesCorrectlyWithRangerName_true() {
    Sightings testSightings = new Sightings("Quadrant A", "Naomi");
    assertEquals("Naomi", testSightings.getRangerName());
  }

  @Test
  public void equals_returnsTrueIfLocationAndRangerNameAreSame_True() {
    Sightings testSightings = new Sightings("Quadrant A", "Naomi");
    Sightings anotherSightings = new Sightings("Quadrant A", "Naomi");
    assertTrue(testSightings.equals(anotherSightings));
  }

  @Test
  public void save_addsSightingsToDatabase() {
    Sightings testSightings = new Sightings("Quadrant A", "Naomi");
    testSightings.save();
    assertTrue(Sightings.all().get(0).equals(testSightings));
  }

  @Test
  public void save_assignsIdToObject_true() {
    Sightings testSightings = new Sightings("Quadrant A", "Naomi");
    testSightings.save();
    Sightings savedSightings = Sightings.all().get(0);
    assertEquals(savedSightings.getId(), testSightings.getId());
  }

  @Test
  public void all_returnsAllInstancesOfSightings_true() {
    Sightings firstSightings = new Sightings("Quadrant A", "Naomi");
    firstSightings.save();
    Sightings secondSightings = new Sightings("Quadrant C", "Peter");
    secondSightings.save();
    assertEquals(true, Sightings.all().get(0).equals(firstSightings));
    assertEquals(true, Sightings.all().get(1).equals(secondSightings));
  }

  @Test
  public void find_returnsSightingsWithSameId_secondSightings() {
    Sightings firstSightings = new Sightings("Quadrant A", "Naomi");
    firstSightings.save();
    Sightings secondSightings = new Sightings("Quadrant C", "Peter");
    secondSightings.save();
    assertEquals(Sightings.find(secondSightings.getId()), secondSightings);
  }

   @Test
   public void save_recordsTimeOfCreationInDatabase() {
     Sightings testSightings = new Sightings("Quadrant A", "Naomi");
     testSightings.save();
     Timestamp savedSightings = Sightings.find(testSightings.getId()).getSpotted();
     Timestamp rightNow = new Timestamp(new Date().getTime());
     assertEquals(rightNow.getDay(), savedSightings.getDay());
   }

   @Test
   public void delete_deletesSightings_true() {
     Sightings testSightings = new Sightings("Quadrant A", "Naomi");
     testSightings.save();
     testSightings.delete();
     assertEquals(null, Sightings.find(testSightings.getId()));
   }
}
