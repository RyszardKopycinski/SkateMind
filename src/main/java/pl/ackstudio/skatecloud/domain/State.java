package pl.ackstudio.skatecloud.domain;

public enum State {
    DOLNO("dolnośląskie"), KUJAW("kujawsko-pomorskie"), LUBEL("lubelskie"), LUBUS("lubuskie"), LODZK("łódzkie"), MALOP("małopolskie"), MAZOW("mazowieckie"), OPOLS(
            "opolskie"), PODKA("podkarpackie"), PODLA("podlaskie"), POMOR("pomorskie"), SLASK("śląskie"), SWIET("świętokrzyskie"), WARMI("warmińsko-mazurskie"), WIELK(
            "wielkopolskie"), ZACHO("zachodniopomorskie");
    String full;

    private State(String full) {
        this.full = full;
    }

    public String getFull() {
        return full;
    }
}
