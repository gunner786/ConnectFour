package ie.gens.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ie.gens.game.service.GameService;

@RestController
@RequestMapping("/startGame")
public class MainController {
	
	@Autowired
	GameService gameService;  
	
	
	@GetMapping(value = "/start", consumes = {"application/json"}, produces = {"application/json"})
	public String startGame(@RequestParam(value = "player")  String player) {
		return gameService.startGame(player);
		
	}
	
	@GetMapping(value = "/play", consumes = {"application/json"}, produces = {"application/json"})
	public String playGame(@RequestParam String player, @RequestParam int move ) {
		return gameService.playGame(player,move);
		
	}
	
	@GetMapping(value = "/end", consumes = {"application/json"}, produces = {"application/json"})
	public String playGame(@RequestParam String player) {
		return gameService.endGame(player);
		
	}

}
