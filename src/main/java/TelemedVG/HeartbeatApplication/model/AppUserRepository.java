package TelemedVG.HeartbeatApplication.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface AppUserRepository extends CrudRepository<AppUser, Integer> {
    List<AppUser> findAll();
    List<AppUser> findAllById(int id);
    //AppUser findByPin(String pin);
    //AppUser findByEmail(String eMail);
    List<AppUser> findById(int id);
    List<AppUser> findAllByTypeOrderByIdDesc(int type);



}

