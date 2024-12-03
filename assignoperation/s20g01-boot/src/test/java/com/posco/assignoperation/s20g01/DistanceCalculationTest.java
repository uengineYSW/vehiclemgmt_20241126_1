package com.posco.assignoperation.s20g01;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.posco.assignoperation.s20g01.domain.vehiclePerformance.VehiclePerformance;
import com.posco.assignoperation.s20g01.domain.vehiclePerformance.VehiclePerformanceRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DistanceCalculationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(
        DistanceCalculationTest.class
    );

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    public VehiclePerformanceRepository repository;

    @Test
    @SuppressWarnings("unchecked")
    public void test0() {
        //given:
        VehiclePerformance existingEntity = new VehiclePerformance();

        existingEntity.setRegistrationId("1234");
        existingEntity.setVehicleNumber("가1234");
        existingEntity.setRegistrationDate("2022-02-01");
        existingEntity.setDeparture("서울");
        existingEntity.setDepartureTime("09:00");
        existingEntity.setAccumulatedDistanceBefore(1000L);
        existingEntity.setDestination("부산");
        existingEntity.setArrivalTime("13:00");
        existingEntity.setAccumulatedDistanceAfter(2000L);
        existingEntity.setDrivingDistance(100.5D);
        existingEntity.setPurpose("출장");
        existingEntity.setPeriod("1일");

        repository.save(existingEntity);

        //when:

        try {
            DistanceCalculationCommand command = new DistanceCalculationCommand();

            command.setRegistrationId("1234");
            command.setAccumulatedDistanceBefore(2000L);
            command.setAccumulatedDistanceAfter(3000L);

            existingEntity.distanceCalculation(command);
            //then:
            VehiclePerformance result = repository
                .findById(existingEntity.getRegistrationId())
                .get();

            LOGGER.info("Response received: {}", result);

            assertEquals(result.getRegistrationId(), "1234");
            assertEquals(result.getVehicleNumber(), "가1234");
            assertEquals(result.getRegistrationDate(), "2022-02-01");
            assertEquals(result.getDeparture(), "서울");
            assertEquals(result.getDepartureTime(), "09:00");
            assertEquals(result.getAccumulatedDistanceBefore(), 2000L);
            assertEquals(result.getDestination(), "부산");
            assertEquals(result.getArrivalTime(), "13:00");
            assertEquals(result.getAccumulatedDistanceAfter(), 3000L);
            assertEquals(result.getDrivingDistance(), 100.5D);
            assertEquals(result.getPurpose(), "출장");
            assertEquals(result.getPeriod(), "1일");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            assertTrue(e.getMessage(), false);
        }
    }
}
