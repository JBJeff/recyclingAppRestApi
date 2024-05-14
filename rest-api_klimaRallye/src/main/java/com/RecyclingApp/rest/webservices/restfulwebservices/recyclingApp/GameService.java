package com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class GameService {

	private static List<Game> games = new ArrayList<>();

	private static int gamesCount = 0;

	static {
		games.add(new Game(++gamesCount, "Admin", "Müll Sortieren Spiel",
				false));
		games.add(new Game(++gamesCount, "Admin", "Multiple Choice Fragen Spiel",
				false));
		games.add(new Game(++gamesCount, "Admin", "Komponenten trennen Spiel",
				false));
	}

	public List<Game> findByUsername(String username) {
		Predicate<? super Game> predicate = game -> game.getUsername().equalsIgnoreCase(username);
		return games.stream().filter(predicate).toList();
	}

	public Game addGame(String username, String description, boolean done) {
		Game game = new Game(++gamesCount, username, description, done);
		games.add(game);
		return game;
	}

	public void deleteById(int id) {
		Predicate<? super Game> predicate = game -> game.getId() == id;
		games.removeIf(predicate);
	}

	public Game findById(int id) {
		Predicate<? super Game> predicate = game -> game.getId() == id;
		Game game = games.stream().filter(predicate).findFirst().get();
		return game;
	}

	public void updateGame(Game game) {
		deleteById(game.getId());
		games.add(game);
	}
}