package TelemedVG.HeartbeatApplication;

import TelemedVG.HeartbeatApplication.model.HealthRecord;
import TelemedVG.HeartbeatApplication.model.HealthRecordRepository;

import TelemedVG.HeartbeatApplication.model.AppUser;
import TelemedVG.HeartbeatApplication.model.AppUserRepository;

import lombok.extern.java.Log;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@DataJpaTest
@Log
public class HeartbeatApplicationRepositoryIntegrationTests {

    @Autowired
    HealthRecordRepository healthRecordRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @Test
    public void testFindUserByID() {
        log.info("Testiranje konekcije sa bazom");

        //given
        Date startTest = new Date();
        AppUser newUser = new AppUser();
        newUser.setDateOfBirth(startTest);
        appUserRepository.save(newUser);

        // when
        AppUser foundUser = appUserRepository.findById(newUser.getId());

        // then
        Assert.assertEquals(foundUser.getDateOfBirth().getTime(), startTest.getTime());
    }

    @Test
    public void testFindRecordByRecordWithAppUser() {
        log.info("Testiranje konekcije između AppUsera i HealthRecorda");

        //given
        AppUser newUser = new AppUser("Goran", "Jović");
        appUserRepository.save(newUser);

        HealthRecord newRecord = new HealthRecord(newUser);
        healthRecordRepository.save(newRecord);

        // when
        HealthRecord foundRecord = healthRecordRepository.findAllByAppUserId(newUser.getId()).get(0);

        // then
        Assert.assertEquals(foundRecord.getAppUser().getFirstName(), "Goran");
    }
}
