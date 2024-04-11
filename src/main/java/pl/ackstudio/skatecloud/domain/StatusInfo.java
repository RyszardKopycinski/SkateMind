package pl.ackstudio.skatecloud.domain;

public enum StatusInfo {
    COMPLETED("Completed"), WAITING("Waiting"), IN_PROGRESS("In progress"), CANCELED("Canceled");
    String description;

    private StatusInfo(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
