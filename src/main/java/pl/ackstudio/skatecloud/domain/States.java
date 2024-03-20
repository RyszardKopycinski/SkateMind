package pl.ackstudio.skatecloud.domain;

public enum States {
    DOLNO("dolnośląskie"), KUJAW("kujawsko-pomorskie"), LUBEL("lubelskie"), LUBUS("lubuskie"), LODZK("łódzkie"), MALOP("małopolskie"), MAZOW("mazowieckie"), OPOLS(
            "opolskie"), PODKA("podkarpackie"), PODLA("podlaskie"), POMOR("pomorskie"), SLASK("śląskie"), SWIET("świętokrzyskie"), WARMI("warmińsko-mazurskie"), WIELK(
            "wielkopolskie"), ZACHO("zachodniopomorskie");
    String full;

    private States(String full) {
        this.full = full;
    }
}
