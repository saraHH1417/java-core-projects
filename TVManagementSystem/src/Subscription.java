package src;

public class Subscription {

    private int installFee; // fixed fee: 10$ / TV
    private int nbTV;
    private Subscriber subscriber;
    private SubscriptionCycle cycle;
    private String date;
    private int totalFee;

    public Subscription(int nbTV, Subscriber subscriber, SubscriptionCycle cycle, String date) {
        this.nbTV = nbTV;
        this.subscriber = subscriber;
        this.cycle = cycle;
        this.date = date;

        this.installFee = nbTV * 10;
    }

    public int getNbTV() {
        return nbTV;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public SubscriptionCycle getCycle() {
        return cycle;
    }

    public String getDate() {
        return date;
    }

    public int getTotalFee() {
        totalFee = installFee + 5;
        return totalFee;
    }

    public void setNbTV(int nbTV) {
        this.nbTV = nbTV;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public void setCycle(SubscriptionCycle cycle) {
        this.cycle = cycle;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "installFee=" + installFee +
                ", nbTV=" + nbTV +
                ", subscriber=" + subscriber +
                ", cycle=" + cycle +
                ", today='" + date  +
                ", totalFee=" + totalFee +
                '}';
    }
}
