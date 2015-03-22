
class test_referinta {
    static void modificaReferinta(Student st) {
 //       st = new Student("Gigel", 10);
    	st.setName("CODENAME_GICU") ;
    	st.averageGrade = 10;
    }
 
    static void modificaObiect(Student s) {
        s.averageGrade = 15;
    }
 
    public static void main(String[] args) {
        Student s = new Student("Andrei", 5);
        modificaReferinta(s); // 1
        System.out.println(s.getName()); // 1'
 
        modificaObiect(s); // 2
        System.out.println(s.averageGrade); // 2'
    }
}