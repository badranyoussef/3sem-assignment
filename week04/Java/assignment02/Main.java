package assignment02;

import assignment02.config.HibernateConfig;
import assignment02.dao.DriverDAOImpl;
import assignment02.dao.WasteTruckDAOImpl;
import assignment02.model.Driver;
import assignment02.model.WasteTruck;
import jakarta.persistence.EntityManagerFactory;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);
        DriverDAOImpl driverDAO = new DriverDAOImpl();
        WasteTruckDAOImpl wasteTruckDAO = new WasteTruckDAOImpl();


        //Testing all DriverDAOImpl methods:
//        String youssef = driverDAO.saveDriver("Youssef", "Badran", new BigDecimal (85.000));
//        System.out.println(youssef);

        Driver d = driverDAO.getDriverById("211128-PT-347S");
        System.out.println(d);

        d.setName("Youssef");
        driverDAO.updateDriver(d);
        Driver updD = driverDAO.getDriverById("211128-PT-347S");
        System.out.println(updD);

//        List<Driver> drivers = driverDAO.findAllDriversEmployedAtTheSameYear("2023");
//        drivers.forEach(System.out::println);
//
//        System.out.println(driverDAO.fetchAllDriversWithSalaryGreaterThan10000());
//
//        System.out.println(driverDAO.fetchHighestSalary());
//
//        System.out.println(driverDAO.fetchFirstNameOfAllDrivers());
//
//        System.out.println(driverDAO.calculateNumberOfDrivers());
//
//        System.out.println(driverDAO.fetchDriverWithHighestSalary());


        //Testing all DriverDAOImpl methods:

//        wasteTruckDAO.saveWasteTruck("MEGA TRUCK5", "AS12345", 80000);

        WasteTruck wasteT = wasteTruckDAO.getWasteTruckById(14);
        System.out.println(wasteT);

        //wasteTruckDAO.setWasteTruckAvailable(wasteT, false);

        wasteTruckDAO.removeDriverFromWasteTruck(wasteT, updD.getId());
        wasteTruckDAO.addDriverToWasteTruck(wasteT, updD);




       // List<WasteTruck> trucks = wasteTruckDAO.getAllAvailableTrucks();
        //trucks.forEach(System.out::println);



    }
}
