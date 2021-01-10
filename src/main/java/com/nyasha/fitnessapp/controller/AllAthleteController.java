package com.nyasha.fitnessapp.controller;

import com.minimum.local.ActionResult;
import com.nyasha.fitnessapp.models.Athlete;
import com.nyasha.fitnessapp.service.AthleteService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/allAthletes")
public class AllAthleteController {

	private final AthleteService athleteService;

	public AllAthleteController(AthleteService athleteService) {
		this.athleteService = athleteService;
	}

	@ApiOperation(value = "", response = Iterable.class)
	@PostMapping()
	public ResponseEntity<ActionResult> save(@Valid @RequestBody Athlete athlete) {
		ActionResult result = new ActionResult();
		try {
			athleteService.create(athlete);
			result.setMessage("Success");
			return ResponseEntity.ok().body(result);
		} catch (Exception exception) {
			result.setMessage(exception.getMessage());
			return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_GATEWAY);
		}
	}
	
	@ApiOperation(value = "", response = Iterable.class)
	@GetMapping()
	public ResponseEntity<Iterable<Athlete>> findAll() {
		try {
			Collection<Athlete> athletes = athleteService.findAll();
			return ResponseEntity.ok().body(athletes);
		} catch (Exception exception) {
			Iterable<Athlete> iterable = null;
			return new ResponseEntity<Iterable<Athlete>>(iterable, HttpStatus.BAD_GATEWAY);
		}
	}

	@ApiOperation(value = "", response = Iterable.class)
	@GetMapping("/findByLoggedInUser/{userId}")
	public ResponseEntity<Iterable<Athlete>> findByLoggedInUser(@PathVariable long userId) {
		System.out.println("findByLoggedInUser: " + userId);
		try {
			List<Athlete> athletes = athleteService.findByLoggedInUser(userId);
			return ResponseEntity.ok().body(athletes);
		} catch (Exception exception) {
			Iterable<Athlete> iterable = null;
			return new ResponseEntity<Iterable<Athlete>>(iterable, HttpStatus.BAD_GATEWAY);
		}
	}
	
	@ApiOperation(value = "", response = Iterable.class)
	@GetMapping("/findByTeam/{id}")
    public List<Athlete> findByTeamId(@PathVariable long id) {
		return athleteService.findByTeamId(id);
	}

	@ApiOperation(value = "", response = Iterable.class)
	@GetMapping("{id}")
	public ResponseEntity<Athlete> findOne(@PathVariable long id) {
		Athlete athlete = athleteService.findById(id);
		if (athlete == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok().body(athlete);
	}

	@ApiOperation(value = "", response = Iterable.class)
	@PutMapping("/{id}")
	public ResponseEntity<ActionResult> update(@PathVariable int id, @Valid @RequestBody Athlete athlete) {
		ActionResult result = new ActionResult();
		try {
			if (id != athlete.getId()) {
				result.setMessage("Invalid request.");
				return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_REQUEST);
			}
			athleteService.update(athlete);
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
		if (athleteService.findById(id) != null) {
			athleteService.delete(id);
			result.setMessage("Success");
			return ResponseEntity.ok().body(result);
		}
		result.setMessage("Cannot delete the Athlete");
		return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_REQUEST);
	}

	
}
