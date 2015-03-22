class Student {
    private String name;
    public int averageGrade;
 
    // (1) constructor fără parametri
    public Student() {
        name = "Necunoscut";
        averageGrade = 5;
    }
 
    // (2) constructor cu 2 parametri, folosit dacă cunoaștem numele și media
    public Student(String n, int avg) {
        name = n;
        averageGrade = avg;
    }
 
    // (3) constructor cu un singur parametru, folosit atunci când cunoaștem doar numele studentului
    public Student(String n) {
        this(n, 5); // Astfel, se va apela constructorul (2)
    }
 
    // (4) metoda set pentru câmpul name al clasei Student
    public void setName(String n) {
        name = n;
        averageGrade = 5;
    }
 
    // (5) metoda getter pentru câmpul name
    public String getName() { 
        return name; 
    }
}