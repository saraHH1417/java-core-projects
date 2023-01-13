package src.Plan;

public class AllRisk extends Risk{

    public String[] allRisksCovered = {
      "Fire",
      "Robbery",
      "Third Party Damage",
      "Vehicle Damage",
      "Driver Damage",
      "Transport",
      "Car Replacement"
    };

    @Override
    public float getPremium() {
        return 0.075f;
    }

    @Override
    public float getCoverage() {
        return 1f;
    }

    @Override
    public float getCeiling() {
        return 10;
    }
}
