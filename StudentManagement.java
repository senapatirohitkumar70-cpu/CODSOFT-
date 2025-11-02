import java.io.*;
import java.util.*;


class Student implements Serializable {
    String name;
    int roll;
    String grade;

    Student(String name, int roll, String grade){
        this.name = name;
        this.roll = roll;
        this.grade = grade;
    }

    public String toString(){
        return "Roll: " + roll + ", Name: " + name + ", Grade: " + grade;
    }
}


class StudentSystem {
    ArrayList<Student> students;
    final String fileName = "students.dat";
    Scanner sc = new Scanner(System.in);

    StudentSystem() {
        students = loadFromFile();
    }

    private ArrayList<Student> loadFromFile(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))){
            return (ArrayList<Student>) ois.readObject();
        } catch(Exception e){
            return new ArrayList<>();
        }
    }

    private void saveToFile(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))){
            oos.writeObject(students);
        } catch(Exception e){
            System.out.println("Error saving file!");
        }
    }

    void addStudent(){
        System.out.print("Enter Name: ");
        String name = sc.nextLine().trim();
        if(name.isEmpty()){ System.out.println("Name cannot be empty!\n"); return; }

        System.out.print("Enter Roll Number: ");
        int roll;
        try { roll = Integer.parseInt(sc.nextLine()); }
        catch(NumberFormatException e){ System.out.println("Invalid roll number!\n"); return; }

        for(Student s : students){
            if(s.roll == roll){
                System.out.println("Roll number already exists!\n");
                return;
            }
        }

        System.out.print("Enter Grade: ");
        String grade = sc.nextLine().trim();
        if(grade.isEmpty()){ System.out.println("Grade cannot be empty!\n"); return; }

        students.add(new Student(name, roll, grade));
        saveToFile();
        System.out.println("Student added successfully!\n");
    }

    void removeStudent(){
        System.out.print("Enter Roll Number to remove: ");
        int roll;
        try { roll = Integer.parseInt(sc.nextLine()); }
        catch(NumberFormatException e){ System.out.println("Invalid input!\n"); return; }

        boolean removed = students.removeIf(s -> s.roll == roll);
        if(removed){ saveToFile(); System.out.println("Student removed!\n"); }
        else System.out.println("Student not found!\n");
    }

    void searchStudent(){
        System.out.print("Enter Roll Number to search: ");
        int roll;
        try { roll = Integer.parseInt(sc.nextLine()); }
        catch(NumberFormatException e){ System.out.println("Invalid input!\n"); return; }

        for(Student s : students){
            if(s.roll == roll){
                System.out.println("Found: " + s + "\n");
                return;
            }
        }
        System.out.println("Student not found!\n");
    }

    void editStudent(){
        System.out.print("Enter Roll Number to edit: ");
        int roll;
        try { roll = Integer.parseInt(sc.nextLine()); }
        catch(NumberFormatException e){ System.out.println("Invalid input!\n"); return; }

        for(Student s : students){
            if(s.roll == roll){
                System.out.print("Enter new Name (leave blank to keep current): ");
                String name = sc.nextLine().trim();
                if(!name.isEmpty()) s.name = name;

                System.out.print("Enter new Grade (leave blank to keep current): ");
                String grade = sc.nextLine().trim();
                if(!grade.isEmpty()) s.grade = grade;

                saveToFile();
                System.out.println("Student updated successfully!\n");
                return;
            }
        }
        System.out.println("Student not found!\n");
    }

    void displayAll(){
        if(students.isEmpty()){ System.out.println("No students found.\n"); return; }
        System.out.println("List of Students:");
        for(Student s : students){ System.out.println(s); }
        System.out.println();
    }

    
    void start() {
        int choice = 0;
        do {
            System.out.println("1. Add  2. Remove  3. Search  4. Edit  5. Display All  6. Exit");
            System.out.print("Enter your choice: ");
            try { choice = Integer.parseInt(sc.nextLine()); }
            catch(NumberFormatException e){ System.out.println("Invalid input!\n"); continue; }

            switch(choice){
                case 1 -> addStudent();
                case 2 -> removeStudent();
                case 3 -> searchStudent();
                case 4 -> editStudent();
                case 5 -> displayAll();
                case 6 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!\n");
            }
        } while(choice != 6);
    }
}


public class StudentManagement {
    public static void main(String[] args){
        new StudentSystem().start();
    }
}
