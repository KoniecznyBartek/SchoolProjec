package utils;
import service.PersonService;

import static utils.Utils.*;
public class Menu {
    public void menu(){
    Utils.printMenu();
    int option = Utils.readInt(getOptionMessage());

        if (option == 1) {
            PersonService p1 = new PersonService();
            p1.printBase();
    } else if (option == 2) {
//        int data = Utils.readInt(getDataOptionMessage());
//        if (data == 1) {
//            PersonService.editAge();
//        }
//        if (data == 2) {
//            PersonService.editMail();
//        } else menu();
    } else if (option == 3) {
            PersonService p1 = new PersonService();
            p1.addPerson();
    } else if (option == 4) {
            PersonService p1 = new PersonService();
            p1.removePerson();
    } else if (option == 5) {
        int choice = Utils.readInt(getPersonIdMessage());
        PersonService p1 = new PersonService();
        p1.getPerson(choice);
    } else if (option == 6) {
//        PersonService.findSchool(1);
    } else if (option == 0) {
        System.exit(420);
    } else {
        menu();
    }
    menu();
}
}
