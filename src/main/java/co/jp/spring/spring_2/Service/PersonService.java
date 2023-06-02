package co.jp.spring.spring_2.Service;

import co.jp.spring.spring_2.entity.Person;
import co.jp.spring.spring_2.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    public List<Person> getPersonList(){
        List<Person> pList = new ArrayList<Person>();
        pList = this.personRepository.findAll();
        return pList;
    }

    public void addPerson(Person p){
        try{
            this.personRepository.save(p);
        }catch (Exception e){
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
        }
    }

    public Person getPerson(int id){
        Optional<Person> p = this.personRepository.findById(id);
        return p.get();
    }

    public void updatePerson(Person p){
        try{
            this.personRepository.save(p);
        }catch (Exception e){
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
        }
    }

    public void deletePerson(int id){
        try{
            this.personRepository.deleteById(id);
        }catch (Exception e){
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
        }
    }
}
