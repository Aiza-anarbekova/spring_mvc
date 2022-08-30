package peaksoft.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Company;
import peaksoft.repository.CompanyRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CompanyRepositoryImpl implements CompanyRepository {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Company> getAllCompanies() {
        List<Company> companies = entityManager.
                createQuery("select c from Company c", Company.class).getResultList();
        return companies;
    }

    @Override
    public void addCompany(Company company) {
        entityManager.persist(company);

    }

    @Override
    public Company getById(long id) {
        return entityManager.find(Company.class, id);
    }

    @Override
    public void updateCompany(Company company) {
        entityManager.merge(company);

    }

    @Override
    public void deleteCompany(Company company) {
        entityManager.remove(entityManager.contains(company) ? company : entityManager.merge(company));

    }
}