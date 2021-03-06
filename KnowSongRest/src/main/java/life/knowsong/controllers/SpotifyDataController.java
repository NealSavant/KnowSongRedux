package life.knowsong.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import life.knowsong.buildgame.BuildAudioGame;
import life.knowsong.data.SpotifyDataClient;
import life.knowsong.entities.Artist;
import life.knowsong.entities.SingleplayerGame;
import life.knowsong.entities.Track;
import life.knowsong.entities.User;
import life.knowsong.repositories.UserRepository;

@RestController
@RequestMapping("spotifyData")
@CrossOrigin(origins = "http://localhost:4200")
public class SpotifyDataController {
	
	@Autowired
	SpotifyDataClient ourSpotifyData;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	BuildAudioGame buildAudio;
	
	@GetMapping("/getAllArtists")
	public List<Artist> getAllArtists(@AuthenticationPrincipal OAuth2User principal, 
			HttpServletResponse response) 
	{
		if(principal != null) {
			response.setStatus(200);
			System.out.println("getting artists");
			return ourSpotifyData.listAllArtists();
		}
		else {
			response.setStatus(401);
			return null;
		}
	}
	
	@GetMapping("/buildArtistAudioGame/{artistId}/{gameType}/{accessToken}")
	public SingleplayerGame BuildArtistAudioGame(@AuthenticationPrincipal OAuth2User principal, 
			@PathVariable("artistId") String artistId, 
			@PathVariable("gameType") String gameType,
			@PathVariable("accessToken") String accessToken, 
			HttpServletResponse response) 
	{
		if(principal != null) {
			// check if user is using a free or premium account
			Optional<User> optionalUser = userRepo.findById(principal.getName());
			User user = optionalUser.get();
			boolean premium = user.getPremium();	// premium games store information for faster future load times. free games do not.

			SingleplayerGame game = buildAudio.build(artistId, accessToken, gameType, premium);
			if(game == null) {
				response.setStatus(404);	// game was not created. artist may have too few tracks
				return null;
			}
			
			response.setStatus(200);	// game was built 
			return game;
		}
		else {
			response.setStatus(401);	// unauthorized request
			return null;
		}
	}
	
	@PostMapping("/storeSingleplayerGame")
	public boolean StoreSingleplayerGame(@AuthenticationPrincipal OAuth2User principal, 
			@RequestBody SingleplayerGame game,
			HttpServletResponse response) 
	{
		if(principal != null) {
			return ourSpotifyData.storeSingleplayerGame(game, principal.getName());
		}else {
			response.setStatus(401);	// unauthorized request
			return false;
		}
	}
	
	@GetMapping("/getSingleplayerGames")
	public List<SingleplayerGame> GetSingleplayerGames(@AuthenticationPrincipal OAuth2User principal, 
			HttpServletResponse response)
	{
		if(principal != null) {
			return ourSpotifyData.getSingleplayerGames(principal.getName());
		} else {
			response.setStatus(401);	// unauthorized request
			return null;
		}
	}

}
