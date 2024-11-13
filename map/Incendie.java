package map;
public class Incendie {
    Case position;
    int vol_exctinction;

    public Incendie(Case position, int vol_exctinction){
        this.position = position;
        this.vol_exctinction = vol_exctinction;
    }

    /** methode pour obtenir la position de l'incendie*/
    public Case getPosition() {
        return position;
    }

    /** methode pour obtenir la quantité vol_exctinction*/
    public int getVolExtinction() {
        return this.vol_exctinction;
    }

    /** methode pour deduir la quantité vol de la quantité vol_pour_eteindre
     * @param vol : volume a déduire du vol necessaire pour eteindre le feu*/
    public int setFeuDec(int vol){
        this.vol_exctinction -= vol;
        return this.vol_exctinction;
    }

}
