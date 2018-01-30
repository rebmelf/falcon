package io.falcon.falcontest;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static io.falcon.falcontest.FalconSpringProfile.UNITTEST;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FalconTestApplication.class, webEnvironment = RANDOM_PORT)
@ActiveProfiles(profiles = {UNITTEST})
public abstract class AbstractServiceTest {
}
