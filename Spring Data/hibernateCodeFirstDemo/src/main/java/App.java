import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import java.util.List;
import java.util.Map;

public class App {
    private static final String WIZARD_DEPOSITS = "wizard_deposits";
    private static final String SALES_DATABASE = "sales";
    private static final String UNIVERSITY_SYSTEM = "university_system";


    public static void main(String[] args) {


        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(UNIVERSITY_SYSTEM);

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Engine engine = new Engine(entityManager);
        engine.run();

        }
    }
