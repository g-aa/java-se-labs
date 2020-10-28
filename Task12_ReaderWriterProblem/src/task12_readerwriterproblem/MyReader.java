package task12_readerwriterproblem;

public class MyReader extends MyRW {

    public MyReader(int reader_id, MyDataBase dbLink) {
        super("Reader", reader_id, dbLink, 5000);
    }


    @Override
    public void run() {
        while (true) {
            try {
                long timeOut = getTimeOut();
                Main.myPrinter(this.toString() + " is sleeping (" + timeOut/1000.0 + " c.).");
                Thread.sleep(timeOut);
                Main.myPrinter(this.toString() + " wants to read.");
                m_dbLink.startRead(this.toString());
                m_dbLink.endRead(this.toString());
            }
            catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}