package src.Plan;

public class DriverRisk extends Risk{

    public String[] getDriverRisksCovered = {"Driver Damage"};

    @Override
    public float getPremium() {
        return 0.05f;
    }

    @Override
    public float getCoverage() {
        return 0.7f;
    }

    @Override
    public float getCeiling() {
        return 6;
    }
}
