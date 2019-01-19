public class Manager {

    private final String exercise = "exercise";
    private final String hardWork = "hardwork";
    private final String buyingGTX1080 = "gtx1080";
    private final String cheating = "cheating";
    private final String outTillLate = "outtilllate";
    private final String sleep = "sleep";
    private final String coffeeAndWork = "coffeeandwork";

    private State currState;

    public Manager(){
        currState = new Ready();
    }


    /**
     *
     * @param nextState is used to reinitialize the member variable of State.
     */
    public void setCurrState(State nextState) {
        this.currState = nextState;
    }

    /**
     *
     * @param work that will be handle
     */
    public void Do(String work){

        switch (work.toLowerCase()){
            case exercise :
                currState.exercise(this);
                break;
            case hardWork :
                currState.hardWork(this);
                break;
            case buyingGTX1080 :
                currState.gtx1080(this);
                break;
            case cheating :
                currState.cheating(this);
                break;
            case outTillLate :
                currState.outtilllate(this);
                break;
            case sleep :
                currState.sleep(this);
                break;
            case coffeeAndWork :
                currState.coffeeandwork(this);
                break;
            default:
                throw new UnsupportedOperationException();
        }
    }


}
