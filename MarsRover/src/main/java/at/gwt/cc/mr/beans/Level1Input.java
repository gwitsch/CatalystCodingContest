package at.gwt.cc.mr.beans;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Level1Input {
	private BigDecimal wheelBase;
	private BigDecimal steeringAngle;
}
