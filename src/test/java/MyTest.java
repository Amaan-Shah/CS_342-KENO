import static org.junit.jupiter.api.Assertions.*;
import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;

class MyTest {
	private static int iter;
	private static int i;
	Integer[] matched = new Integer[] {10, 9, 8, 7, 6, 5, 0};
	Integer[] m = new Integer[] {8, 7, 6, 5, 4};

	@BeforeAll
	static void init() {
		iter = 0;
		i = 0;
	}

	@Test
	void constructorSpotsTest() {
		JavaFXTemplate game = new JavaFXTemplate();
		assertEquals(0,game.numSpots,"Incorrect default number of spots");
	}

	@Test
	void constructorDrawsTest() {
		JavaFXTemplate game = new JavaFXTemplate();
		assertEquals(0,game.numDraws,"Incorrect default number of draws");
	}

	@Test
	void constructorAutoPickTest() {
		JavaFXTemplate game = new JavaFXTemplate();
		assertFalse(game.AutoPick,"AutoPick not initially set to false");
	}

	@Test
	void constructorSceneMapTest() {
		JavaFXTemplate game = new JavaFXTemplate();
		assertEquals(0, game.sceneMap.size(),"SceneMap not initially size 0");
	}

	@Test
	void constructorButtonListTest() {
		JavaFXTemplate game = new JavaFXTemplate();
		assertEquals(0, game.buttonList.size(),"ButtonList not initially size 0");
	}

	@Test
	void constructorSelectedNumsTest() {
		JavaFXTemplate game = new JavaFXTemplate();
		assertEquals(0, game.selectedNumbers.size(),"SelectedNumbers not initially size 0");
	}

	@Test
	void constructorAutoPickedNumsTest() {
		JavaFXTemplate game = new JavaFXTemplate();
		assertEquals(0, game.autopickedNumbers.size(),"AutoPickedNumbers not initially size 0");
	}

	@Test
	void constructorTotalWonTest() {
		JavaFXTemplate game = new JavaFXTemplate();
		assertEquals(0, game.totalWon,"Total won not initially 0");
	}

	@Test
	void constructorCorrectSolsTest() {
		JavaFXTemplate game = new JavaFXTemplate();
		assertEquals(0, game.correctSelections,"Correct Selections not initially 0");
	}

	@Test
	void constructorStartRoundTest() {
		JavaFXTemplate game = new JavaFXTemplate();
		assertEquals(1, game.curRound,"Cur Round not initially 1");
	}

	@Test
	void constructorColorSchemeTest() {
		JavaFXTemplate game = new JavaFXTemplate();
		assertEquals(0, game.colorScheme,"Color Scheme not initially 0");
	}

	@Test
	void AutoPickSizeTest() {
		JavaFXTemplate game = new JavaFXTemplate();
		game.numSpots = 8;
		game.generateNums();
		assertEquals(8, game.autopickedNumbers.size(),"AutoPickedNums wrong size");
	}

	@Test
	void GenerateWinningSizeTest() {
		JavaFXTemplate game = new JavaFXTemplate();
		game.numSpots = 8;
		game.generateWinningNums();
		assertEquals(20, game.winningNumbers.size(),"WinningNums wrong size");
	}

	@Test
	void calcWinningsZeroTest() {
		JavaFXTemplate game = new JavaFXTemplate();
		game.numSpots = 0;
		game.calculateWinnings(game.numSpots, 0);
		assertEquals(0, game.totalWon,"Wrong total winnings");
	}

	@ParameterizedTest
	@ValueSource(ints = {100000, 4250, 450, 40, 15, 2, 5})
	void calcWinningsTenTest(int totalVal) {
		JavaFXTemplate game = new JavaFXTemplate();
		game.numSpots = 10;

		game.calculateWinnings(game.numSpots, matched[iter]);
		int total = game.totalWon;
		game.totalWon = 0;
		assertEquals(totalVal, total, "wrong output...");
		iter++;
	}

	@ParameterizedTest
	@ValueSource(ints = {10000, 750, 50, 12, 2})
	void calcWinningsEightTest(int totalVal) {
		JavaFXTemplate game = new JavaFXTemplate();
		game.numSpots = 8;

		game.calculateWinnings(game.numSpots, m[i]);
		int total = game.totalWon;
		game.totalWon = 0;

		assertEquals(totalVal, total, "wrong output...");
		i++;
	}


}























