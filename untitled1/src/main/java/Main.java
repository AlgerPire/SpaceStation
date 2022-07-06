import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Welcom To Space Station. The position will be soon in the database.....");
        HttpFinal httpFinal=new HttpFinal();
        httpFinal.addLocationInDatabase();



    }
}