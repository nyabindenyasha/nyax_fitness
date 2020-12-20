package com.nyasha.fitnessapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nyasha.fitnessapp.models.Teams;
import com.nyasha.fitnessapp.service.TeamsService;
import com.minimum.local.ActionResult;
import com.minimum.local.InvalidRequestException;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping("/teams")
public class TeamsController {

	@Autowired
	TeamsService teamsService;

	@ApiOperation(value = "", response = Iterable.class)
	@PostMapping()
	public ResponseEntity<ActionResult> save(@Valid @RequestBody Teams teams) {
		ActionResult result = new ActionResult();
		try {
			Teams teamsAlreadySaved = teamsService.findByName(teams.getName());

			if (teamsAlreadySaved != null)
				throw new InvalidRequestException("Team already exist");

			teamsService.save(teams);
			result.setMessage("Success");
			return ResponseEntity.ok().body(result);
		} catch (Exception exception) {
			result.setMessage(exception.getMessage());
			return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_GATEWAY);
		}
	}

	@ApiOperation(value = "", response = Iterable.class)
	@GetMapping()
	public ResponseEntity<Iterable<Teams>> findAll() {
		try {
			List<Teams> teamss = teamsService.findAll();
			return ResponseEntity.ok().body(teamss);
		} catch (Exception exception) {
			Iterable<Teams> iterable = null;
			return new ResponseEntity<Iterable<Teams>>(iterable, HttpStatus.BAD_GATEWAY);
		}
	}

	@ApiOperation(value = "", response = Iterable.class)
	@GetMapping("{id}")
	public ResponseEntity<Teams> findOne(@PathVariable int id) {
		Teams teams = teamsService.findOne(id);
		if (teams == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok().body(teams);
	}

	@ApiOperation(value = "", response = Iterable.class)
	@DeleteMapping("{id}")
	public ResponseEntity<ActionResult> delete(@PathVariable int id) {
		ActionResult result = new ActionResult();
		if (teamsService.findOne(id) != null) {
			teamsService.delete(id);
			result.setMessage("Success");
			return ResponseEntity.ok().body(result);
		}
		result.setMessage("Cannot delete the Teams");
		return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_REQUEST);
	}

}
