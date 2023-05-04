package employee;


public class Dependent extends Person  {
    long empId;
    String dependentId;

    public Dependent(long empId,  String firstName, String lastName, int age) {
        super(firstName, lastName, age);
        this.empId=empId;
    }
}
enum DEPENDENT_TYPE{
    SPOUSE,
    CHILD,
    SELF;
}
