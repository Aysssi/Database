import model.Address;
import model.Employee;
import model.Project;
import model.Town;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.*;

public class Engine implements  Runnable{

    private final EntityManager entityManager;
    private  Scanner scanner;
    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run() {

        //Ex2
       // changeCasingEx2();
        //Ex3
       // containsEmployeeEx3();
        //Ex4
       // employeesWithSalaryOver50000Ex4();
        //Ex5
      //  employeesFromDepartmentEx5();
        //Ex6
      //   addingANewAddressAndUpdatingEmployeeEx6();
        //Ex7
     //   addressWithEmployeeCountEx7();
        //Ex8
     //   getEmployeeWithProjects();
        //Ex10
    //    increaseSalaries();



    }

    private void increaseSalaries() {
        entityManager.getTransaction().begin();

        int effectedRows = entityManager.createQuery(
                        "UPDATE Employee e " +
                                "SET e.salary = e.salary * 1.2 " +
                                "WHERE e.department.id IN :ids")
                .setParameter("ids", Set.of(1, 2, 4, 11))
                .executeUpdate();

        entityManager.getTransaction().commit();

        System.out.println(effectedRows);
    }

    private void getEmployeeWithProjects() {

        System.out.println("Enter valid employee ID:");
        int employeeId = Integer.parseInt(scanner.nextLine());

        Employee employee = entityManager.find(Employee.class, employeeId);

        System.out.printf("%s %s - %s%n",
                employee.getFirstName(),
                employee.getLastName(),
                employee.getJobTitle());

        employee.getProjects()
                .stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(project -> {
                    System.out.printf("\t%s%n",project.getName());
                });

    }
    private void addressWithEmployeeCountEx7() {

        List<Address> addresses = entityManager.createQuery(
                "SELECT a FROM Address a " +
                       "ORDER BY a.employees.size DESC", Address.class)
                .setMaxResults(10)
                .getResultList();

        addresses.forEach(address -> {
            System.out.printf("%s, %s - %d employees%n",
                    address.getText(),
                    address.getTown(),
                    address.getEmployees().size());
        });

    }

    private void addingANewAddressAndUpdatingEmployeeEx6() {
        System.out.println("Enter employee last name:");
        String lastName = scanner.nextLine();

        Employee employee = entityManager.createQuery(
                        "SELECT e FROM Employee e " +
                                "WHERE e.lastName = :l_name", Employee.class)
                .setParameter("l_name", lastName)
                .getSingleResult();

        Address address = createAddress("Vitoshka 15");

        entityManager.getTransaction().begin();
        employee.setAddress(address);
        entityManager.getTransaction().commit();
    }

    private Address createAddress(String addressText) {
        Address address = new Address();
        address.setText(addressText);

        entityManager.getTransaction().begin();
        entityManager.persist(address);
        entityManager.getTransaction().commit();

        return address;

    }

    private void employeesFromDepartmentEx5() {
        entityManager.createQuery(
                "SELECT e FROM Employee e   " +
                        "WHERE e.department.name = 'Research and Development' " +
                        "ORDER BY e.salary, e.id", Employee.class)
                .getResultStream()
                .forEach(employee -> {
                    System.out.printf("%s %s from %s - $%.2f%n",
                            employee.getFirstName(),
                            employee.getLastName(),
                            employee.getDepartment().getName(),
                            employee.getSalary());
                });


    }


    private void employeesWithSalaryOver50000Ex4() {
        entityManager.createQuery(
                        "SELECT e FROM Employee e " +
                                "WHERE e.salary > 50000", Employee.class)
                .getResultStream()
                .map(Employee::getFirstName)
                .forEach(System.out::println);
    }

    private void containsEmployeeEx3() {
        System.out.println("Enter employee full name: ");
        String fullName = scanner.nextLine();

        List<Employee> employees = entityManager.createQuery(
                "SELECT e FROM Employee AS e " +
                        "WHERE concat(e.firstName,' ',e.lastName) = :name ",Employee.class)
                .setParameter("name" , fullName)
                .getResultList();
        System.out.println();
    }

    private void changeCasingEx2() {
        System.out.println("Enter Full Name:");

        String [] fullName = scanner.nextLine().split("\\s+");
        String firstName = fullName[0];
        String lastName = fullName[1];

        Long singleResult = entityManager.createQuery(
                "SELECT count(e) FROM Employee AS e " +
                        "WHERE e.firstName = :f_name AND e.lastName = :l_name",Long.class)
                .setParameter("f_name",firstName)
                .setParameter("l_name",lastName)
                .getSingleResult();

        System.out.println(singleResult == 0 ? "NO" : "YES");
    }


}
