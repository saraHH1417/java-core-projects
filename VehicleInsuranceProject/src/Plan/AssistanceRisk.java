package src.Plan;

public class AssistanceRisk extends Risk{

    public String[] assistanceRisksCovered = {
        "Fire",
        "Robbery",
        "Transport",
        "Car Replacement"
    };

    @Override
    public float getPremium() {
        return 0.05f;
    }

    @Override
    public float getCoverage() {
        return 0.8f;
    }

    @Override
    public float getCeiling() {
        return 8;
    }
}
