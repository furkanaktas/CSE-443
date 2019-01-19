public class NeedSleep implements State {

    /**
     *
     * @param manager  is used for setting the next state
     */
    @Override
    public void exercise(Manager manager) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param manager  is used for setting the next state
     */
    @Override
    public void hardWork(Manager manager) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param manager  is used for setting the next state
     */
    @Override
    public void gtx1080(Manager manager) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param manager  is used for setting the next state
     */
    @Override
    public void cheating(Manager manager) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param manager  is used for setting the next state
     */
    @Override
    public void outtilllate(Manager manager) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param manager  is used for setting the next state
     */
    @Override
    public void sleep(Manager manager) {
        System.out.println("sleep");
        manager.setCurrState(new Ready());
    }

    /**
     *
     * @param manager  is used for setting the next state
     */
    @Override
    public void coffeeandwork(Manager manager) {
        System.out.println("coffee & work ");
        manager.setCurrState(new ChronicIllness());
    }
}
