package com.nyasha.fitnessapp.controller;

import com.minimum.local.ActionResult;
import com.minimum.local.InvalidRequestException;
import com.nyasha.fitnessapp.local.Utils;
import com.nyasha.fitnessapp.models.SitAndReachTest;
import com.nyasha.fitnessapp.models.SitAndReachTestCreateContext;
import com.nyasha.fitnessapp.service.SitAndReachTestService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin
@RequestMapping("/sitAndReachTest")
public class SitAndReachTestController {

    private final SitAndReachTestService sitAndReachTestService;

    public SitAndReachTestController(SitAndReachTestService sitAndReachTestService) {
        this.sitAndReachTestService = sitAndReachTestService;
    }

    @ApiOperation(value = "", response = Iterable.class)
    @GetMapping()
    public ResponseEntity<Iterable<SitAndReachTest>> findAll() {
        try {
            Collection<SitAndReachTest> sitAndReachTests = sitAndReachTestService.findAll();

            sitAndReachTests.parallelStream().forEach(sitAndReachTest -> {
                sitAndReachTest.setResult(Utils.getSitAndReachTest(sitAndReachTest.getSitAndReachTest()));
            });

            return ResponseEntity.ok().body(sitAndReachTests);
        } catch (Exception exception) {
            Iterable<SitAndReachTest> iterable = null;
            return new ResponseEntity<Iterable<SitAndReachTest>>(iterable, HttpStatus.BAD_GATEWAY);
        }
    }

    @ApiOperation(value = "", response = Iterable.class)
    @GetMapping("/findByLoggedInUser/{userId}")
    public ResponseEntity<Iterable<SitAndReachTest>> findByLoggedInUser(@PathVariable long userId) {
        try {
            Collection<SitAndReachTest> sitAndReachTests = sitAndReachTestService.findByLoggedInUser(userId);
            return ResponseEntity.ok().body(sitAndReachTests);
        } catch (Exception exception) {
            Iterable<SitAndReachTest> iterable = null;
            return new ResponseEntity<Iterable<SitAndReachTest>>(iterable, HttpStatus.BAD_GATEWAY);
        }
    }

    @ApiOperation(value = "", response = Iterable.class)
    @GetMapping("{id}")
    public ResponseEntity<SitAndReachTest> findOne(@PathVariable long id) {
        SitAndReachTest sitAndReachTest = sitAndReachTestService.findById(id);
        if (sitAndReachTest == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(sitAndReachTest);
    }

//    @ApiOperation(value = "", response = Iterable.class)
//    @PutMapping("/{id}/{athleteId}/{sitAndReachTest}")
//    public ResponseEntity<ActionResult> update(@PathVariable(required = false) long id, @PathVariable long athleteId,
//                                               @PathVariable int sitAndReachTest) {
//        ActionResult result = new ActionResult();
//        try {
//
//            Athlete athleteInDb = athleteService.findById(athleteId);
//            if (athleteInDb == null) {
//                result.setMessage("Invalid request.");
//                return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_REQUEST);
//            }
//
//            SitAndReachTest sitAndReachTestInDB = sitAndReachTestService.findOne(id);
//
//            if (sitAndReachTestInDB == null) {
//                SitAndReachTest sitAndReachTestNew = new SitAndReachTest();
//                sitAndReachTestNew.setSitAndReachTest(sitAndReachTest);
//                sitAndReachTestNew.setAthlete(athleteService.findById(athleteId));
//                sitAndReachTestService.save(sitAndReachTestInDB);
//            } else {
//
//                if (sitAndReachTestInDB.getAthlete().getId() != athleteId) {
//                    result.setMessage("Invalid request.");
//                    return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_REQUEST);
//                }
//
//                sitAndReachTestInDB.setSitAndReachTest(sitAndReachTest);
//                sitAndReachTestInDB.setAthlete(athleteService.findById(athleteId));
//                sitAndReachTestService.save(sitAndReachTestInDB);
//            }
//
//            result.setMessage("Success");
//            return ResponseEntity.ok().body(result);
//        } catch (Exception exception) {
//            result.setMessage(exception.getMessage());
//            return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_GATEWAY);
//        }
//    }

    @ApiOperation(value = "", response = Iterable.class)
    @PostMapping("")
    public ResponseEntity<ActionResult> create(@RequestBody SitAndReachTestCreateContext sitAndReachTest) {
        ActionResult result = new ActionResult();
        try {
            sitAndReachTestService.create(sitAndReachTest);
            result.setMessage("Success");
            return ResponseEntity.ok().body(result);
        } catch (Exception exception) {
            result.setMessage(exception.getMessage());
            return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_GATEWAY);
        }
    }

    @ApiOperation(value = "", response = Iterable.class)
    @PutMapping("{id}")
    public ResponseEntity<ActionResult> update(@RequestBody SitAndReachTestCreateContext sitAndReachTest, @PathVariable long id) {
        ActionResult result = new ActionResult();
        try {

            if (sitAndReachTest.getId() != id) {
                throw new InvalidRequestException("Record not found!");
            }

            sitAndReachTestService.update(sitAndReachTest);
            result.setMessage("Success");
            return ResponseEntity.ok().body(result);
        } catch (Exception exception) {
            result.setMessage(exception.getMessage());
            return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_GATEWAY);
        }
    }

    @ApiOperation(value = "", response = Iterable.class)
    @DeleteMapping("{id}")
    public ResponseEntity<ActionResult> delete(@PathVariable long id) {
        ActionResult result = new ActionResult();
        if (sitAndReachTestService.findById(id) != null) {
            sitAndReachTestService.delete(id);
            result.setMessage("Success");
            return ResponseEntity.ok().body(result);
        }
        result.setMessage("Cannot delete the SitAndReachTest");
        return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_REQUEST);
    }

}
