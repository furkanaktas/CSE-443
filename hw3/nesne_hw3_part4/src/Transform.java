public abstract class Transform {

    public abstract void readFile();
    public abstract void writeFile();
    public abstract void transform();

    /**
     *  transform adımlarını uygular
     */
    public final void doTransform(){
        readFile();
        transform();
        writeFile();
    }
}
