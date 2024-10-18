public class Incendie {
    Case position;
    int vol_pour_eteindre;

    public Incendie(Case position, int vol_pour_eteindre){
        this.position = position;
        this.vol_pour_eteindre = vol_pour_eteindre;
    }

    public Case getPosition() {
        return position;
    }

    public int getVol_pour_eteindre() {
        return vol_pour_eteindre;
    }

    public int setFeuDecrement(int vol){
        return this.vol_pour_eteindre - vol;
    }

}
