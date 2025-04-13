package prisonersDilemma;

public class Tit4Tat extends Strategy{

    public boolean cooperate() {
        if (this.prisoner.partnerCheated) {
            return false;
        } else {
            return true;
        }
    }
}
