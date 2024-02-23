package assignment01;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;
import java.util.List;

public class Main {

    /*
Be able to explain the purpose of JPQL and how it differs from SQL.
What is the difference between TypedQuery and Query and when to use them.
Be able to demonstrate and explain how to use NamedQueries.
Be able to demonstrate native queries and explain the purpose.
    */

    public static void main(String[] args) {


        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);
        DolphinDAO dolphinDAO = new DolphinDAO();

                //Adding data
//        try (EntityManager em = emf.createEntityManager()){
//            Person p1 = new Person("Tom");
//            PersonDetail d1 = new PersonDetail("Østervang", 3400, "Hillerød", 33);
//            Fee f1 = new Fee(100, LocalDate.of(2024, 10, 10));
//            Fee f2 = new Fee(150, LocalDate.of(2024, 10, 10));
//            Note n1 = new Note("Regning 1 er betalt", "Hans", LocalDate.now());
//            Note n2 = new Note("Regning 2 er endnu ikke betalt", "Hans", LocalDate.now());
//
//            p1.addPersonDetail(d1);
//            p1.addFee(f1);
//            p1.addFee(f2);
//            p1.addNote(n1);
//            p1.addNote(n2);
//
//            dolphinDAO.create(p1);
//        }



//        Person test = new Person("Ahmad");
//        dolphinDAO.create(test);
//
//
//
//        Person p2 = dolphinDAO.read(2);
//        System.out.println(p2.getName() + " " + p2.getPersonDetail().getAddress());

        //US-2
//        long totalFee = dolphinDAO.getTotalAmountPaidFees(2);
//        System.out.println(totalFee);

        //US-3
        List<Note> notes = dolphinDAO.getAllNotesPrPersonId(2);

        notes.forEach(System.out::println);

//        List<Note> notesNative = dolphinDAO.getAllNotesPrPersonIdNativeQuery(2);
//
//        notesNative.forEach(System.out::println);


        //US-4

//        List<NoteDTO> noteDTOS = dolphinDAO.getAllNotesWithPersonInfo();
//
//        noteDTOS.forEach(System.out::println);




    }
}
