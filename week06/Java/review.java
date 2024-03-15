import org.mindrot.jbcrypt.BCrypt;

public class review {

//    Password Hashing
//    Explain why password hashing is important in securing user credentialsd, and how it works.
//    Implement password hashing and salt generation in a Javalin application using bcrypt.

//    BCrypt --> designed to be slow. Thats why this type of security is great in important against hackers
//    MD5 & SHA1 --> are designed to be fast.
//    By using this type of security the password will be encrypted (hashedcode). It is easy to encrypt but not to decrypt


    public static void main(String[] args) {
        String password = "1234Marts";
        String hackerPassword = "1234Marts";

//      Prøv at undersøge hvordan man implementerer pepper sikkerheds lag også

        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());


        if(BCrypt.checkpw(hackerPassword, hashed)){
            System.out.println("The password is correct");
            System.out.println("The password is:" + hashed);
        }else{
            System.out.println("Wrong password");
        }
    }
}
