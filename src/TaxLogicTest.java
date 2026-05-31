import static org.junit.Assert.*;
import org.junit.Test;

public class TaxLogicTest {

	@Test
	public void testFilerTaxCalculation() {
		TaxLogic logic = new TaxLogic();

		double expectedTax = 5000.0;
		double actualTax = logic.calculateTaxAmount(100000, true);
		assertEquals(expectedTax, actualTax, 0.001);
	}

	@Test
	public void testNonFilerTaxCalculation() {
		TaxLogic logic = new TaxLogic();
	
		double expectedTax = 10000.0;
		double actualTax = logic.calculateTaxAmount(100000, false);
		assertEquals(expectedTax, actualTax, 0.001);
	}

	@Test
	public void testNetSalaryCalculation() {
		TaxLogic logic = new TaxLogic();
		
		double expectedNet = 95000.0;
		double actualNet = logic.calculateNetSalary(100000, 5000);
		assertEquals(expectedNet, actualNet, 0.001);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNegativeSalaryException() {
		TaxLogic logic = new TaxLogic();
		
		logic.calculateTaxAmount(-20000, true);
	}
}