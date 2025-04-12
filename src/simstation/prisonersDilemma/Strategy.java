package simstation.prisonersDilemma;

public abstract class Strategy {
    Prisoner prisoner;
    public void setPrisoner(Prisoner p) {
        this.prisoner = p;
    }
    public abstract boolean cooperate();
}
