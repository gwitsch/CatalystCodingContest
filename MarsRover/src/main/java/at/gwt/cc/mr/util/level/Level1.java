package at.gwt.cc.mr.util.level;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.gwt.cc.mr.beans.Level1Input;
import at.gwt.ccc.base.LevelSolver;
import ch.obermuhlner.math.big.BigDecimalMath;

public class Level1 implements LevelSolver {
	private static final Logger logger = LogManager.getLogger();

	private static final MathContext PRECISSION = new MathContext(2);

	@Override
	public void solve() {
		System.out.println(Math.sin(30d));
		Stream.of(new Level1Input(BigDecimal.valueOf(1.00), BigDecimal.valueOf(Math.toRadians(30.00))),
				new Level1Input(BigDecimal.valueOf(1.00), BigDecimal.valueOf(Math.toRadians(13.76))),
				new Level1Input(BigDecimal.valueOf(1.00), BigDecimal.valueOf(Math.toRadians(2.34))),
				new Level1Input(BigDecimal.valueOf(1.00), BigDecimal.valueOf(Math.toRadians(90.00))),
				new Level1Input(BigDecimal.valueOf(2.45), BigDecimal.valueOf(Math.toRadians(90.00))))
				.forEach(input -> this.printResult(input,
						this.calcTurnRadius(input.getWheelBase(), input.getSteeringAngle())));
		;
	}

	private void printResult(Level1Input input, BigDecimal result) {
		logger.info("{} {} -> {}", input.getWheelBase(), input.getSteeringAngle(), result);
	}

	private BigDecimal calcTurnRadius(BigDecimal wheelBase, BigDecimal steeringAngle) {
		return wheelBase.divide(BigDecimalMath.sin(steeringAngle, new MathContext(200, RoundingMode.CEILING)),
				PRECISSION);
	}
}
