import static org.junit.Assert.*;

import org.junit.Test;

public class TennisGameTest {
	
// Here is the format of the scores: "player1Score - player2Score"
// "love - love"
// "15 - 15"
// "30 - 30"
// "deuce"  v
// "15 - love", "love - 15"
// "30 - love", "love - 30"
// "40 - love", "love - 40"
// "30 - 15", "15 - 30"
// "40 - 15", "15 - 40"
// "player1 has advantage" v
// "player2 has advantage" v
// "player1 wins" v
// "player2 wins" v
	@Test
	public void testTennisGame_Start() {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Initial score incorrect", "love - love", score);		
	}
	
	@Test
	public void testTennisGame_EahcPlayerWin4Points_Score_Deuce() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player2Scored();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Tie score incorrect", "deuce", score);		
	}
	
	@Test
	public void testTennisGame_EahcPlayerWin4Points_Score_Deuce2() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Tie score incorrect", "deuce", score);		
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player1WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		//Act
		// This statement should cause an exception
		game.player1Scored();			
	}		
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player2WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		//Act
		// This statement should cause an exception
		game.player2Scored();			
	}		
	
	@Test
	public void testTennisGame_Player1Wins() throws TennisGameException {
	    // Arrange
	    TennisGame game = new TennisGame();
	    // Act - Player 1 scores 4 times, which should end the game with Player 1 as the winner
	    game.player1Scored();
	    game.player1Scored();
	    game.player1Scored();
	    game.player1Scored();
	    // Assert
	    assertEquals("Player 1 should win", "player1 wins", game.getScore());
	}
	
	@Test
	public void testTennisGame_Player1WinsEdgecase() throws TennisGameException {
	    // Arrange
	    TennisGame game = new TennisGame();
	    // Act - Player 1 scores 4 times, which should end the game with Player 1 as the winner
	    game.player1Scored();
	    game.player1Scored();
	    game.player2Scored();
	    game.player2Scored();
	    game.player1Scored();
	    game.player1Scored();
	    // Assert
	    assertEquals("Player 1 should win", "player1 wins", game.getScore());
	}

	@Test
	public void testTennisGame_Player2Wins() throws TennisGameException {
	    // Arrange
	    TennisGame game = new TennisGame();
	    // Act - Player 2 scores 4 times, which should end the game with Player 2 as the winner
	    game.player2Scored();
	    game.player2Scored();
	    game.player2Scored();
	    game.player2Scored();
	    // Assert
	    assertEquals("Player 2 should win", "player2 wins", game.getScore());
	}
	
	@Test
	public void testTennisGame_Player2WinsEdgecase() throws TennisGameException {
	    // Arrange
	    TennisGame game = new TennisGame();
	    // Act - Player 2 scores 4 times, which should end the game with Player 2 as the winner
	    game.player2Scored();
	    game.player2Scored();
	    game.player1Scored();
	    game.player1Scored();
	    game.player2Scored();
	    game.player2Scored();
	    // Assert
	    assertEquals("Player 2 should win", "player2 wins", game.getScore());
	}

	@Test
	public void testTennisGame_Player1Advantage() throws TennisGameException {
	    // Arrange
	    TennisGame game = new TennisGame();
	    // Act - Both players score 3 points, leading to deuce
	    game.player1Scored();
	    game.player1Scored();
	    game.player1Scored();
	    
	    game.player2Scored();
	    game.player2Scored();
	    game.player2Scored();
	    
	    // Player 1 scores one more point, leading to an advantage
	    game.player1Scored();
	    // Assert
	    assertEquals("Player 1 should have advantage", "player1 has advantage", game.getScore());
	}

	@Test
	public void testTennisGame_Player2Advantage() throws TennisGameException {
	    // Arrange
	    TennisGame game = new TennisGame();
	    // Act - Both players score 3 points, leading to deuce
	    game.player1Scored();
	    game.player1Scored();
	    game.player1Scored();
	    
	    game.player2Scored();
	    game.player2Scored();
	    game.player2Scored();
	    
	    // Player 2 scores one more point, leading to an advantage
	    game.player2Scored();
	    // Assert
	    assertEquals("Player 2 should have advantage", "player2 has advantage", game.getScore());
	}
	
	@Test
	public void testTennisGame_15_Love() throws TennisGameException {
	    // Arrange
	    TennisGame game = new TennisGame();
	    // Act
	    game.player1Scored();
	    // Assert
	    assertEquals("15 - love", game.getScore());
	}

	@Test
	public void testTennisGame_Love_15() throws TennisGameException {
	    // Arrange
	    TennisGame game = new TennisGame();
	    // Act
	    game.player2Scored();
	    // Assert
	    assertEquals("love - 15", game.getScore());
	}

	@Test
	public void testTennisGame_30_Love() throws TennisGameException {
	    // Arrange
	    TennisGame game = new TennisGame();
	    // Act
	    game.player1Scored();
	    game.player1Scored();
	    // Assert
	    assertEquals("30 - love", game.getScore());
	}

	@Test
	public void testTennisGame_Love_30() throws TennisGameException {
	    // Arrange
	    TennisGame game = new TennisGame();
	    // Act
	    game.player2Scored();
	    game.player2Scored();
	    // Assert
	    assertEquals("love - 30", game.getScore());
	}

	@Test
	public void testTennisGame_40_Love() throws TennisGameException {
	    // Arrange
	    TennisGame game = new TennisGame();
	    // Act
	    game.player1Scored();
	    game.player1Scored();
	    game.player1Scored();
	    // Assert
	    assertEquals("40 - love", game.getScore());
	}

	@Test
	public void testTennisGame_Love_40() throws TennisGameException {
	    // Arrange
	    TennisGame game = new TennisGame();
	    // Act
	    game.player2Scored();
	    game.player2Scored();
	    game.player2Scored();
	    // Assert
	    assertEquals("love - 40", game.getScore());
	}

	@Test
	public void testTennisGame_30_15() throws TennisGameException {
	    // Arrange
	    TennisGame game = new TennisGame();
	    // Act
	    game.player1Scored();
	    game.player1Scored();
	    game.player2Scored();
	    // Assert
	    assertEquals("30 - 15", game.getScore());
	}

	@Test
	public void testTennisGame_15_30() throws TennisGameException {
	    // Arrange
	    TennisGame game = new TennisGame();
	    // Act
	    game.player1Scored();
	    game.player2Scored();
	    game.player2Scored();
	    // Assert
	    assertEquals("15 - 30", game.getScore());
	}

	@Test
	public void testTennisGame_40_15() throws TennisGameException {
	    // Arrange
	    TennisGame game = new TennisGame();
	    // Act
	    game.player1Scored();
	    game.player1Scored();
	    game.player1Scored();
	    game.player2Scored();
	    // Assert
	    assertEquals("40 - 15", game.getScore());
	}

	@Test
	public void testTennisGame_15_40() throws TennisGameException {
	    // Arrange
	    TennisGame game = new TennisGame();
	    // Act
	    game.player1Scored();
	    game.player2Scored();
	    game.player2Scored();
	    game.player2Scored();
	    // Assert
	    assertEquals("15 - 40", game.getScore());
	}

	@Test
	public void testTennisGame_Love_Love() {
	    // Arrange
	    TennisGame game = new TennisGame();
	    // Act & Assert
	    assertEquals("love - love", game.getScore());
	}

	@Test
	public void testTennisGame_15_15() throws TennisGameException {
	    // Arrange
	    TennisGame game = new TennisGame();
	    // Act
	    game.player1Scored();
	    game.player2Scored();
	    // Assert
	    assertEquals("15 - 15", game.getScore());
	}

	@Test
	public void testTennisGame_30_30() throws TennisGameException {
	    // Arrange
	    TennisGame game = new TennisGame();
	    // Act
	    game.player1Scored();
	    game.player1Scored();
	    game.player2Scored();
	    game.player2Scored();
	    // Assert
	    assertEquals("30 - 30", game.getScore());
	}
	


}

