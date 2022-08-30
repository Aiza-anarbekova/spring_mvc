package peaksoft.repository;

import org.springframework.stereotype.Repository;
import peaksoft.model.Company;

import java.util.List;
@Repository
public interface CompanyRepository {
    List<Company> getAllCompanies();
    void addCompany(Company company);
    Company getById(long id);
    void updateCompany(Company company);
    void deleteCompany(Company company);

}
