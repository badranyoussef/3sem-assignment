package assignment04;

import assignment04.model.Package;
import assignment04.model.Location;
import assignment04.model.Shipment;
import jakarta.persistence.EntityManagerFactory;

public class Main {

    public static void main(String[] args) {
        //EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

        GenericDAONotDoneYet dao = new GenericDAONotDoneYet();

        Package pkg = new Package ("22DD", "Hans", "Jon");
        Location sourceLocation = new Location(12342,33423,"Copenhagen");
        Location destinationLocation = new Location(5542.33,44331.554,"Berlin");
        Shipment shipment = new Shipment();

        pkg.addShipment(shipment, destinationLocation,sourceLocation);

        dao.create(pkg);







    }

}
