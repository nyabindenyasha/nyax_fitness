package com.nyasha.fitnessapp.controller;

import com.minimum.local.ActionResult;
import com.nyasha.fitnessapp.local.Utils;
import com.nyasha.fitnessapp.models.Athlete;
import com.nyasha.fitnessapp.models.SitAndReachTest;
import com.nyasha.fitnessapp.service.AthleteService;
import com.nyasha.fitnessapp.service.SitAndReachTestService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/sitAndReachTest")
public class SitAndReachTestController {

	@Autowired
	SitAndReachTestService sitAndReachTestService;

	@Autowired
	AthleteService athleteService;

	@ApiOperation(value = "", response = Iterable.class)
	@GetMapping()
	public ResponseEntity<Iterable<SitAndReachTest>> findAll() {
		try {
			List<SitAndReachTest> sitAndReachTests = sitAndReachTestService.findAll();

			for (SitAndReachTest x : sitAndReachTests) {
				x.setResult(Utils.getSitAndReachTest(x.getSitAndReachTest()));
			}

			return ResponseEntity.ok().body(sitAndReachTests);
		} catch (Exception exception) {
			Iterable<SitAndReachTest> iterable = null;
			return new ResponseEntity<Iterable<SitAndReachTest>>(iterable, HttpStatus.BAD_GATEWAY);
		}
	}

	@ApiOperation(value = "", response = Iterable.class)
	@GetMapping("{id}")
	public ResponseEntity<SitAndReachTest> findOne(@PathVariable int id) {
		SitAndReachTest sitAndReachTest = sitAndReachTestService.findOne(id);
		if (sitAndReachTest == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok().body(sitAndReachTest);
	}

	@ApiOperation(value = "", response = Iterable.class)
	@PutMapping("/{id}/{athleteId}/{sitAndReachTest}")
	public ResponseEntity<ActionResult> update(@PathVariable(required = false) int id, @PathVariable int athleteId,
			@PathVariable int sitAndReachTest) {
		ActionResult result = new ActionResult();
		try {

			Athlete athleteInDb = athleteService.findOne(athleteId);
			if (athleteInDb == null) {
				result.setMessage("Invalid request.");
				return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_REQUEST);
			}

			SitAndReachTest sitAndReachTestInDB = sitAndReachTestService.findOne(id);

			if (sitAndReachTestInDB == null) {
				SitAndReachTest sitAndReachTestNew = new SitAndReachTest();
				sitAndReachTestNew.setSitAndReachTest(sitAndReachTest);
				sitAndReachTestNew.setAthlete(athleteService.findOne(athleteId));
				sitAndReachTestService.save(sitAndReachTestInDB);
			}

			else {

				if (sitAndReachTestInDB.getAthlete().getId() != athleteId) {
					result.setMessage("Invalid request.");
					return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_REQUEST);
				}

				sitAndReachTestInDB.setSitAndReachTest(sitAndReachTest);
				sitAndReachTestInDB.setAthlete(athleteService.findOne(athleteId));
				sitAndReachTestService.save(sitAndReachTestInDB);
			}

			result.setMessage("Success");
			return ResponseEntity.ok().body(result);
		} catch (Exception exception) {
			result.setMessage(exception.getMessage());
			return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_GATEWAY);
		}
	}

	@ApiOperation(value = "", response = Iterable.class)
	@DeleteMapping("{id}")
	public ResponseEntity<ActionResult> delete(@PathVariable int id) {
		ActionResult result = new ActionResult();
		if (sitAndReachTestService.findOne(id) != null) {
			sitAndReachTestService.delete(id);
			result.setMessage("Success");
			return ResponseEntity.ok().body(result);
		}
		result.setMessage("Cannot delete the SitAndReachTest");
		return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_REQUEST);
	}

}
