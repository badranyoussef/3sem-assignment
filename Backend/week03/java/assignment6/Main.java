package assignment6;

public class Main {

    public static void main(String[] args) {

        StudentDAO dao = new StudentDAO();

        //Student s1 = dao.read(1);
        //System.out.println(s1);
        //s1.setLastName("YOUS");
        //Student yousUpdated = dao.update(s1);
        //System.out.println(yousUpdated);

        //dao.create(new Student("hans", "hansen", "hans@hansen.dk", 30));
        Student s2 = dao.read(11);
        s2.setFirstName("Simba");
        Student s2upd = dao.update(s2);
        System.out.println(dao.read(11));

    }

}
