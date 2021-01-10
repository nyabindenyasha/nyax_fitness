package com.nyasha.fitnessapp.controller;

import com.minimum.local.ActionResult;
import com.nyasha.fitnessapp.local.Update5MDashRequest;
import com.nyasha.fitnessapp.local.Utils;
import com.nyasha.fitnessapp.models.Athlete;
import com.nyasha.fitnessapp.models.FiveMetreDash;
import com.nyasha.fitnessapp.service.AthleteService;
import com.nyasha.fitnessapp.service.FiveMetreDashService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/fiveMetreDash")
public class FiveMetreDashController {

    @Autowired
    FiveMetreDashService fiveMetreDashService;

    @Autowired
    AthleteService athleteService;

    @ApiOperation(value = "")
    @GetMapping()
    public ResponseEntity<Iterable<FiveMetreDash>> findAll() {
        try {
            List<FiveMetreDash> fiveMetreDashs = fiveMetreDashService.findAll();

            for (FiveMetreDash x : fiveMetreDashs) {
                x.setResult(Utils.get5mDashResult(x.getReaction()));
            }

            return ResponseEntity.ok().body(fiveMetreDashs);
        } catch (Exception exception) {
            Iterable<FiveMetreDash> iterable = null;
            return new ResponseEntity<Iterable<FiveMetreDash>>(iterable, HttpStatus.BAD_GATEWAY);
        }
    }

    @ApiOperation(value = "", response = Iterable.class)
    @GetMapping("{id}")
    public ResponseEntity<FiveMetreDash> findOne(@PathVariable int id) {
        FiveMetreDash fiveMetreDash = fiveMetreDashService.findOne(id);
        if (fiveMetreDash == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(fiveMetreDash);
    }

//	@ApiOperation(value = "", response = Iterable.class)
//	@PutMapping("/{id}")
//	public ResponseEntity<ActionResult> update(@PathVariable int id, @Valid @RequestBody FiveMetreDash fiveMetreDash) {
//		ActionResult result = new ActionResult();
//		try {
//			if (id != fiveMetreDash.getId()) {
//				result.setMessage("Invalid request.");
//				return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_REQUEST);
//			}
//			fiveMetreDashService.save(fiveMetreDash);
//			result.setMessage("Success");
//			return ResponseEntity.ok().body(result);
//		} catch (Exception exception) {
//			result.setMessage(exception.getMessage());
//			return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_GATEWAY);
//		}
//	}

//	@ApiOperation(value = "", response = Iterable.class)
//	@PutMapping("/{id}/{athleteId}/{reaction}")
//	public ResponseEntity<ActionResult> update(@PathVariable(required = false) int id, @PathVariable int athleteId, @PathVariable String reaction) {
//		ActionResult result = new ActionResult();
//		
//		System.out.println(reaction);
//		
//		try {
//
//			Athlete athleteInDb = athleteService.findOne(athleteId);
//			if (athleteInDb == null) {
//				result.setMessage("Invalid request.");
//				return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_REQUEST);
//			}
//
//			FiveMetreDash fiveMetreDashInDB = fiveMetreDashService.findOne(id);
//
//			if (fiveMetreDashInDB == null) {
//				FiveMetreDash fiveMetreDash = new FiveMetreDash();
//				fiveMetreDash.setReaction(Double.parseDouble(reaction));
//				fiveMetreDash.setAthlete(athleteService.findOne(athleteId));
//				fiveMetreDashService.save(fiveMetreDash);
//			}
//
//			else {
//
//				if (fiveMetreDashInDB.getAthlete().getId() != athleteId) {
//					result.setMessage("Invalid request.");
//					return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_REQUEST);
//				}
//
//				fiveMetreDashInDB.setReaction(Double.parseDouble(reaction));
//				fiveMetreDashInDB.setAthlete(athleteService.findOne(athleteId));
//				fiveMetreDashService.save(fiveMetreDashInDB);
//			}
//
//			result.setMessage("Success");
//			return ResponseEntity.ok().body(result);
//		} catch (Exception exception) {
//			result.setMessage(exception.getMessage());
//			return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_GATEWAY);
//		}
//	}

    @ApiOperation(value = "", response = Iterable.class)
    @PutMapping("/{id}")
    public ResponseEntity<ActionResult> update(@PathVariable(required = false) long id, @RequestBody Update5MDashRequest request) {
        ActionResult result = new ActionResult();

        System.out.println(request);

        try {

            Athlete athleteInDb = athleteService.findById(request.getAthleteId());
            if (athleteInDb == null) {
                result.setMessage("Invalid request.");
                return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_REQUEST);
            }

            FiveMetreDash fiveMetreDashInDB = fiveMetreDashService.findOne(id);

            if (fiveMetreDashInDB == null) {
                FiveMetreDash fiveMetreDash = new FiveMetreDash();
                fiveMetreDash.setReaction(request.getReaction());
                fiveMetreDash.setAthlete(athleteService.findById(request.getAthleteId()));
                fiveMetreDashService.save(fiveMetreDash);
            } else {

                if (fiveMetreDashInDB.getAthlete().getId() != request.getAthleteId()) {
                    result.setMessage("Invalid request.");
                    return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_REQUEST);
                }

                fiveMetreDashInDB.setReaction(request.getReaction());
                fiveMetreDashInDB.setAthlete(athleteService.findById(request.getAthleteId()));
                fiveMetreDashService.save(fiveMetreDashInDB);
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
        if (fiveMetreDashService.findOne(id) != null) {
            fiveMetreDashService.delete(id);
            result.setMessage("Success");
            return ResponseEntity.ok().body(result);
        }
        result.setMessage("Cannot delete the FiveMetreDash");
        return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_REQUEST);
    }

}
