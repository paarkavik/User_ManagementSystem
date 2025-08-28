import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserService user = new UserService();

        while (true) {
            System.out.println(ConsoleColors.CYAN_BOLD+"\n===== MENU ====="+ConsoleColors.RESET);
            System.out.println(ConsoleColors.PURPLE+"1. Register"+ConsoleColors.RESET);
            System.out.println(ConsoleColors.PURPLE+"2. Login"+ConsoleColors.RESET);
            System.out.println(ConsoleColors.PURPLE+"3. Update User"+ConsoleColors.RESET);
            System.out.println(ConsoleColors.PURPLE+"4. Exit"+ConsoleColors.RESET);
            System.out.print(ConsoleColors.YELLOW_BOLD+"Enter choice: "+ConsoleColors.RESET);
            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1:
                        User newUser = new User(choice,null,null,null);
                        System.out.print(ConsoleColors.PURPLE_BOLD+"Enter name: "+ConsoleColors.RESET);
                        newUser.setName(sc.nextLine());
                        System.out.print(ConsoleColors.PURPLE_BOLD+"Enter email: "+ConsoleColors.RESET);
                        newUser.setEmail(sc.nextLine());
                        System.out.print(ConsoleColors.PURPLE_BOLD+"Enter password: "+ConsoleColors.RESET);
                        newUser.setPassword(sc.nextLine());
                        new ThreadTask(ConsoleColors.YELLOW_BOLD+"Registering user"+ConsoleColors.RESET).start();
                        user.registerUser(newUser);
                        break;

                    case 2:                   
                        System.out.print(ConsoleColors.PURPLE_BOLD+"Enter email: "+ConsoleColors.RESET);
                        String email = sc.nextLine();
                        System.out.print(ConsoleColors.PURPLE_BOLD+"Enter password: "+ConsoleColors.RESET);
                        String password = sc.nextLine();

                        if (user.loginUser(email, password)) {
                            System.out.println(ConsoleColors.GREEN+"✅ Login successful!"+ConsoleColors.RESET);
                            User profile =user.getUserProfile(email);
                            if (profile != null) {
                                System.out.println(ConsoleColors.CYAN+"----- User Profile -----"+ConsoleColors.RESET);
                                System.out.println(ConsoleColors.PINK+"ID: " + profile.getId()+ConsoleColors.RESET);
                                System.out.println(ConsoleColors.PINK+"Name: " + profile.getName()+ConsoleColors.RESET);
                                System.out.println(ConsoleColors.PINK+"Email: " + profile.getEmail()+ConsoleColors.RESET);
                            }
                        } else {
                            throw new CustomException(ConsoleColors.RED+"❌ Invalid email or password!"+ConsoleColors.RESET);
                        }
                        break;


                    case 3:
                        User updateUser = new User(choice,null,null,null);
                        System.out.print(ConsoleColors.PURPLE_BOLD+"Enter user ID to update: "+ConsoleColors.RESET);
                        updateUser.setId(sc.nextInt());
                        sc.nextLine();
                        System.out.print(ConsoleColors.PURPLE_BOLD+"Enter new name: "+ConsoleColors.RESET);
                        updateUser.setName(sc.nextLine());
                        System.out.print(ConsoleColors.PURPLE_BOLD+"Enter new email: "+ConsoleColors.RESET);
                        updateUser.setEmail(sc.nextLine());
                        System.out.print(ConsoleColors.PURPLE_BOLD+"Enter new password: "+ConsoleColors.RESET);
                        updateUser.setPassword(sc.nextLine());
                        new ThreadTask(ConsoleColors.YELLOW_BOLD+"Updating user"+ConsoleColors.RESET).start();
                        user.updateUser(updateUser);
                        break;

                    case 4:
                        System.out.println(ConsoleColors.YELLOW_BOLD+"Exiting..."+ConsoleColors.RESET);
                        sc.close();
                        return;

                    default:
                        throw new CustomException(ConsoleColors.RED+"Invalid choice! Please try again."+ConsoleColors.RESET);
                }
            } catch (CustomException | SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

