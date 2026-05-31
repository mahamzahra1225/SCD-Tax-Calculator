public class TaxLogic {

   
    public double calculateTaxAmount(double grossSalary, boolean isFiler) throws IllegalArgumentException {
     
        if (grossSalary < 0) {
            throw new IllegalArgumentException("Error: Salary cannot be negative!");
        }
        
       
        if (grossSalary <= 50000) {
            return 0.0;
        }
        
      
        double taxRate = isFiler ? 0.05 : 0.10; 
        
        return grossSalary * taxRate;
    }

   
    public double calculateNetSalary(double grossSalary, double taxAmount) {
        return grossSalary - taxAmount;
    }
}