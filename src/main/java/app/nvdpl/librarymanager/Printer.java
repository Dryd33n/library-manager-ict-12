package app.nvdpl.librarymanager;

public class Printer {

    public static void clr(){
        System.out.print("\f");
    }

    public static void title(){
        System.out.println("""
                                
                ooooo      ooo oooooo     oooo oooooooooo.   ooooooooo.   ooooo             ooooo        ooooo oooooooooo.  ooooooooo.         .o.       ooooooooo.   oooooo   oooo\s
                `888b.     `8'  `888.     .8'  `888'   `Y8b  `888   `Y88. `888'             `888'        `888' `888'   `Y8b `888   `Y88.      .888.      `888   `Y88.  `888.   .8' \s
                 8 `88b.    8    `888.   .8'    888      888  888   .d88'  888               888          888   888     888  888   .d88'     .8"888.      888   .d88'   `888. .8'  \s
                 8   `88b.  8     `888. .8'     888      888  888ooo88P'   888               888          888   888oooo888'  888ooo88P'     .8' `888.     888ooo88P'     `888.8'   \s
                 8     `88b.8      `888.8'      888      888  888          888               888          888   888    `88b  888`88b.      .88ooo8888.    888`88b.        `888'    \s
                 8       `888       `888'       888     d88'  888          888       o       888       o  888   888    .88P  888  `88b.   .8'     `888.   888  `88b.       888     \s
                o8o        `8        `8'       o888bood8P'   o888o        o888ooooood8      o888ooooood8 o888o o888bood8P'  o888o  o888o o88o     o8888o o888o  o888o     o888o    \s
                                                                                                                                                                                   \s
                """);
    }

    public static void intro(){
        System.out.println("""
                    
                    Welcome to the NVDPL Library Application
                
                Hello and welcome to the NVDPL Library application this simple tool
                lets you view our library collections, our app also allows you to keep
                track of which books you have read and which books you have borrowed.
                  
                """);
    }
    public static void signupMenu(){
        String username;

        while(true){
            printSignupMenu();

            switch (WaitFor.waitForInt(1, 3)) {
                case 1 -> LoginForm.signup();
                case 2 -> {
                    username = LoginForm.login();
                    if (username != null) {
                        Main.currentUser = Accounts.retrieveBookUserFromUsername(username);
                        Main.showWindow();
                    }
                }
                case 3 -> Main.isRunning = false;
                default -> {
                }
            }
        }
    }


    private static void printSignupMenu(){
        System.out.println("""
                    You are currently not logged in, please signup or login:
                    
                        *SIGNUP MENU*
                    _____________________
                    |                   |
                    | [1] Sign up       |
                    | [2] Log in        |
                    | [3] Quit          |
                    |___________________|
                    
                Enter your input:
                """);
    }

    public static void exit() {
        System.out.println("""
                    Thank you for using the NVDPL application
                    
                Thank-you for using the NVDPL application today we hope your experience
                was easy and seamless, we are constantly adding new features to our apps
                and hope to see you back soon. Thank you!
                """);
    }
}
