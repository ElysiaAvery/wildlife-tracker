# _Wildlife Tracker_


#### By _[**Elysia Avery Nason**](https://github.com/elysiaavery)_

## Description
A web app to be used by a hypothetical forest service to aid in the tracking of animals.
## Specs

| Behaviour                                                    | Input                                                                                             | Output                                                                                            |
|--------------------------------------------------------------|---------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------|
| Initiates Sighting & Animal Objects.                         | n/a                                                                                               | n/a                                                                                               |
| Sighting & Animal object returns property values.            | Animal: String "name", String "health, int age - Sighting: String "location", String "rangerName" | Animal: String "name", String "health, int age - Sighting: String "location", String "rangerName" |
| Sighting & Animal return all instances of Sighting & Animal. | Sighting1, Sighting2...                                                                           | "Quadrant A", "Matilda" , "Quadrant C" , "Naomi"                                                  |
| Instantiates instances of Sighting & Animal with ID's.       | sighting.getId() , animal.getId()                                                                 | 1 & 1                                                                                             |
| Join Sighting and Animal through join table.                 | sighting.getAnimals(), animal.getSightings()                                                      | Animal: String "name", String "health, int age - Sighting: String "location", String "rangerName" |
| Add updates to instances of Sighting & Animal.               | sighting.update()                                                                                 | location: "Quadrant A" , location: "10 yards from Tunnel Falls sign post"                         |
| Able to delete instances of Sighting & Animal.               | sighting.delete()                                                                                 | location: "Quadrant A" rangerName: Naomi -> null                                                  |

## Setup/Installation Requirements

* In your first terminal window:
  * Start postgres: `$ postgres`
* In your second terminal window:
  * Start psql: `$ psql`
  * Create database: `# CREATE DATABASE wildlife_tracker;`
* In your third terminal window:
  * Clone this repository to your desktop: `$ cd Desktop; git clone https://github.com/elysiaavery/wildlife_tracker`
  * Navigate to the forest-service directory: `$ cd wildlife_tracker`
  * Create database schema from wildlife_tracker.sql: `$ psql wildlife_tracker < wildlife_tracker.sql`
* Back in your second terminal window:
  * Confirm the database has been restored correctly:
    * Connect to wildlife_tracker database: `# \c wildlife_tracker;`
    * Print out database tables: `# \dt;`
    <br>
    NOTE: You should see `animals` and `sightings` tables in the `wildlife_tracker` database.
* Back in your third terminal window:
  * Run the server: `$ gradle run`
* In the browser of your choosing, navigate to "localhost:4567" (tested in Chrome).

## Known Bugs

None

## Support and contact details

Elysia Nason: _elysia.avery@gmail.com_

## Technologies Used

_Java,
Spark,
Velocity,
SQL_

### License

This webpage is licensed under the GPL license.

Copyright &copy; 2016 **_Elysia Avery Nason_**
