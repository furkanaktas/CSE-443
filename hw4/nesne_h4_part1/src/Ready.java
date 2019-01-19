public class Ready implements State {

    /**
     *
     * @param manager  is used for setting the next state
     */
    @Override
    public void exercise(Manager manager) {
        System.out.println("exercise ");
        manager.setCurrState(new Fit());
    }

    /**
     *
     * @param manager  is used for setting the next state
     */
    @Override
    public void hardWork(Manager manager) {
        System.out.println("perseverance & hardwork ");
        manager.setCurrState(new Graduate());
    }

    /**
     *
     * @param manager  is used for setting the next state
     */
    @Override
    public void gtx1080(Manager manager) {
        System.out.println("buying a gtx1080 (or more) ");
        manager.setCurrState(new Unable());
    }

    /**
     *
     * @param manager  is used for setting the next state
     */
    @Override
    public void cheating(Manager manager) {
        System.out.println("cheating ");
        manager.setCurrState(new Unable());
    }

    /**
     *
     * @param manager  is used for setting the next state
     */
    @Override
    public void outtilllate(Manager manager) {
        System.out.println("out till late ");
        manager.setCurrState(new NeedSleep());
    }

    /**
     *
     * @param manager  is used for setting the next state
     */
    @Override
    public void sleep(Manager manager) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param manager  is used for setting the next state
     */
    @Override
    public void coffeeandwork(Manager manager) {
        throw new UnsupportedOperationException();
    }
}
